package co.siempo.phone.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joanzapata.iconify.IconDrawable;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.SystemService;
import org.androidannotations.annotations.ViewById;

import co.siempo.phone.R;
import co.siempo.phone.app.CoreApplication;
import co.siempo.phone.event.HomePressEvent;
import co.siempo.phone.helper.ActivityHelper;
import co.siempo.phone.helper.FirebaseHelper;
import co.siempo.phone.log.Tracer;
import co.siempo.phone.utils.PackageUtil;
import co.siempo.phone.utils.UIUtils;
import de.greenrobot.event.Subscribe;

import static co.siempo.phone.activities.DashboardActivity.IS_FROM_HOME;

/**
 * Created by hardik on 17/8/17.
 */


@EActivity(R.layout.activity_siempo_alpha_settings)
public class AlphaSettingsActivity extends CoreActivity {

    @ViewById
    ImageView icon_UserId;
    @ViewById
    TextView txt_UserId;
    @SystemService
    TelephonyManager telephonyManager;
    private Context context;
    private long startTime = 0;
    private LinearLayout ln_suppressedNotifications;
    private ImageView icon_SuppressedNotifications;
    private LinearLayout ln_permissions;
    private ImageView icon_permissions;
    private Toolbar toolbar;


    @AfterViews
    void afterViews() {
        initView();
        onClickEvents();
    }


    public void initView() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_blue_24dp);
        toolbar.setTitle(R.string.alpha_settings);
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color
                .colorAccent));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        context = AlphaSettingsActivity.this;
        ln_suppressedNotifications = findViewById(R.id.ln_suppressedNotifications);
        ln_permissions = findViewById(R.id.ln_permissions);
        icon_SuppressedNotifications = findViewById(R.id.icon_SuppressedNotifications);
        icon_permissions = findViewById(R.id.icon_permissions);
        icon_permissions.setImageDrawable(new IconDrawable(context, "fa-bell").colorRes(R.color.text_primary).sizeDp(18));
        try {
            icon_SuppressedNotifications.setImageDrawable(new IconDrawable(context, "fa-exclamation").colorRes(R.color.text_primary).sizeDp(18));
        } catch (Exception e) {
            //Todo log exception to fabric
            e.printStackTrace();
//            Crashlytics.logException(e);
        }
        icon_UserId.setImageDrawable(new IconDrawable(context, "fa-user-secret")
                .colorRes(R.color.text_primary)
                .sizeDp(18));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                txt_UserId.setText(String.format("UserId: %s", telephonyManager.getDeviceId()));
            }
        } else {
            txt_UserId.setText(String.format("UserId: %s", telephonyManager.getDeviceId()));
        }

    }

    public void onClickEvents() {

        ln_suppressedNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new ActivityHelper(context).openSiempoSuppressNotificationsSettings();
            }
        });

        ln_permissions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlphaSettingsActivity.this, SiempoPermissionActivity_.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra(IS_FROM_HOME, false);
                startActivity(intent);
            }
        });

    }


    @Override
    protected void onPause() {
        super.onPause();
        FirebaseHelper.getInstance().logScreenUsageTime(AlphaSettingsActivity.this.getClass().getSimpleName(), startTime);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startTime = System.currentTimeMillis();
        PackageUtil.checkPermission(this);
    }

    @Subscribe
    public void homePressEvent(HomePressEvent event) {
        try {
            if (event.isVisible() && UIUtils.isMyLauncherDefault(this)) {
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(startMain);
            }

        } catch (Exception e) {
            CoreApplication.getInstance().logException(e);
            Tracer.e(e, e.getMessage());
        }
    }


}
