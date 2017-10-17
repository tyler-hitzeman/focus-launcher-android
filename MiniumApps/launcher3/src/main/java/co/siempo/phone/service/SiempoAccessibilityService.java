package co.siempo.phone.service;

import android.accessibilityservice.AccessibilityService;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import org.androidannotations.annotations.SystemService;

import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityEvent;

import co.siempo.phone.SiempoNotificationBar.ViewService_;
import co.siempo.phone.util.PackageUtil;

import static android.view.accessibility.AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED;


public class SiempoAccessibilityService extends AccessibilityService {

    public static String packageName = "";
    public static String activityName = "";
    private final String TAG = "Accessibility";

    AudioManager audioManager;

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        if (event.getEventType() == TYPE_WINDOW_STATE_CHANGED) {
            if (event != null && event.getPackageName() != null && event.getClass() != null) {
                ComponentName componentName = new ComponentName(event.getPackageName().toString(), event.getClassName().toString());
                ActivityInfo activityInfo = getActivityInfo(componentName);
                boolean isActivity = activityInfo != null;
                if (isActivity) {
                    packageName = activityInfo.packageName;
                    activityName = componentName.flattenToShortString();
                }
                Log.d(TAG, "Packag eName::" + packageName);
                Log.d(TAG, "Activity name::" + activityName);
                if (!PackageUtil.isSiempoLauncher(this) && !packageName.equalsIgnoreCase(getPackageName())) {
                    audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (Settings.canDrawOverlays(this)) {

                        siempoNotificationBarStatus();
                    }
                } else {
                    siempoNotificationBarStatus();
                }
            }

        }
    }

    @Override
    public void onInterrupt() {

    }


    private ActivityInfo getActivityInfo(ComponentName componentName) {
        try {
            return getPackageManager().getActivityInfo(componentName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    public synchronized void siempoNotificationBarStatus() {
        if ((PackageUtil.isSiempoLauncher(this) || packageName.equalsIgnoreCase(getPackageName()))) {
            ViewService_.intent(getApplication()).showMask().start();
        } else {
            ViewService_.intent(getApplication()).hideMask().start();
        }
    }
}