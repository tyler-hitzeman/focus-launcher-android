package co.siempo.phone.pause;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.jesusm.holocircleseekbar.lib.HoloCircleSeekBar;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import co.siempo.phone.R;
import co.siempo.phone.app.Launcher3Prefs_;
import co.siempo.phone.util.AudioUtils;
import co.siempo.phone.util.VibrationUtils_;
import minium.co.core.log.Tracer;
import minium.co.core.ui.CoreActivity;
import minium.co.core.ui.CoreFragment;

import static co.siempo.phone.app.Launcher3App.DND_START_STOP_ACTION;

/**
 * Created by Shahab on 3/16/2017.
 */

@EFragment(R.layout.fragment_pause_activated)
public class PauseActivatedFragment extends CoreFragment {

    @ViewById
    Toolbar toolbar;

    @ViewById
    HoloCircleSeekBar seekbar;

    @ViewById
    ImageView imgBackground;

    @ViewById
    TextView txtRemainingTime;

    @ViewById
    TextView txtEndingTime;

    @ViewById
    TextView titleActionBar;

    @ViewById
    ViewGroup endingLayout;

    @ViewById
    ImageView imgRight;

    @Pref
    Launcher3Prefs_ launcherPrefs;

    Handler handler;

    private int atMillis = 0;

    @FragmentArg
    int maxMillis = 0;

    public PauseActivatedFragment() {
        // Required empty public constructor
    }

    @AfterViews
    void afterViews() {
        ((CoreActivity)getActivity()).setSupportActionBar(toolbar);
        handler = new Handler();
        titleActionBar.setText(R.string.title_pause);
        imgRight.setVisibility(View.INVISIBLE);
        if (maxMillis == -1) startPauseInfinite();
        else startPause();
    }

    private void startPauseInfinite() {
        seekbar.setVisibility(View.INVISIBLE);
        endingLayout.setVisibility(View.INVISIBLE);
        imgBackground.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in));
        launcherPrefs.isPauseActive().put(true);
        getActivity().sendBroadcast(new Intent().setAction(DND_START_STOP_ACTION));
    }

    private void startPause() {
        if (maxMillis < 1) return;
        seekbar.setMax(maxMillis / (60 * 1000));
        seekbar.setValue(0);
        imgBackground.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in));
        launcherPrefs.isPauseActive().put(true);
        handler.postDelayed(pauseActiveRunnable, 1000);
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.add(Calendar.MILLISECOND, maxMillis);
        txtEndingTime.setText(new SimpleDateFormat("hh:mm a", Locale.US).format(cal.getTime()));
        getActivity().sendBroadcast(new Intent().setAction(DND_START_STOP_ACTION));
    }

    @Click
    void imgLeft() {
        getActivity().onBackPressed();
    }

    @Click
    void imgRight() {
        ((CoreActivity)getActivity()).loadChildFragment(PausePreferenceFragment_.builder().build(),R.id.mainView);
    }

    private Runnable pauseActiveRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                atMillis += 1000;

                if (atMillis >= maxMillis) {
                    stopPause(false);
                } else {
                    Tracer.v("Now : " + atMillis + " seekbar value: " + atMillis / (1000 * 60.0f));
                    seekbar.setValue(atMillis / (1000 * 60.0f));
                    txtRemainingTime.setText(String.format(Locale.US, "%d minute", TimeUnit.MILLISECONDS.toMinutes(maxMillis - atMillis)));
                }

                handler.postDelayed(this, 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    public void stopPause(boolean isStopByUser) {
        seekbar.setValue(0);
        seekbar.setShowTitle(false);
        handler.removeCallbacks(pauseActiveRunnable);
        launcherPrefs.isPauseActive().put(false);
        getActivity().sendBroadcast(new Intent().setAction(DND_START_STOP_ACTION));
        if (!isStopByUser) {
//            VibrationUtils_.getInstance_(getActivity()).vibrate();
//            AudioUtils.playnotification(getActivity());
        }
        getActivity().finish();
    }
}