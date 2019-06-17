package co.siempo.phone.main;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import co.siempo.phone.R;
import co.siempo.phone.activities.DashboardActivity;
import co.siempo.phone.app.CoreApplication;
import co.siempo.phone.fragments.PaneFragment;
import co.siempo.phone.fragments.ToolsPaneFragment;
import co.siempo.phone.helper.ActivityHelper;
import co.siempo.phone.helper.FirebaseHelper;
import co.siempo.phone.models.AppMenu;
import co.siempo.phone.models.MainListItem;
import co.siempo.phone.models.MainListItemType;
import co.siempo.phone.utils.Sorting;
import co.siempo.phone.utils.UIUtils;

/**
 * Created by Shahab on 5/4/2017.
 */
public class MainListItemLoader {

    public static final int TOOLS_MAP = 1;
    public static final int TOOLS_TRANSPORT = 2;
    public static final int TOOLS_CALENDAR = 3;
    public static final int TOOLS_WEATHER = 4;
    public static final int TOOLS_NOTES = 5;
    public static final int TOOLS_RECORDER = 6;
    public static final int TOOLS_CAMERA = 7;
    public static final int TOOLS_PHOTOS = 8;
    public static final int TOOLS_PAYMENT = 9;
    public static final int TOOLS_WELLNESS = 10;
    //When enabling new tools interchange the variable of TOOLS_TODO and
    // TOOLS_browser
    public static final int TOOLS_TODO = 12;
    public static final int TOOLS_BROWSER = 11;
    public static final int TOOLS_CALL = 13;
    public static final int TOOLS_CLOCK = 14;
    public static final int TOOLS_MESSAGE = 15;
    public static final int TOOLS_EMAIL = 16;
    public static final int TOOLS_MUSIC = 17;
    public static final int TOOLS_PODCAST = 18;
    public static final int TOOLS_FOOD = 19;
    public static final int TOOLS_FITNESS = 20;

    public static final int TOOLS_CLOUD = 21;
    public static final int TOOLS_BOOKS = 22;
    public static final int TOOLS_AUTHENTICATION = 23;
    public static final int TOOLS_ASSISTANT = 24;
    public static final int TOOLS_ADDITIONAL_MESSAGE = 25;
    public static final int TOOLS_BANKING = 26;
    public static final int TOOLS_COURCE = 27;
    public static final int TOOLS_DOC = 28;
    public static final int TOOLS_FILES = 29;
    public static final int TOOLS_FLASH = 30;
    public static final int TOOLS_HEALTH = 31;
    public static final int TOOLS_JOURNAL = 32;
    public static final int TOOLS_LANGUAGES = 33;
    public static final int TOOLS_LEARNING = 34;
    public static final int TOOLS_MEDITATION = 35;
    public static final int TOOLS_MICROPHONE = 36;
    public static final int TOOLS_NEWS = 37;
    public static final int TOOLS_SEARCH = 38;
    public static final int TOOLS_SETTINGS = 39;
    public static final int TOOLS_VOICE = 40;
    private Context context;

    public MainListItemLoader(Context context) {
        this.context = context;
    }

    public void loadItemsDefaultApp(List<MainListItem> items) {
        if (context != null) {
            Log.d("hardikkamothi","load default items..");
            items.add(new MainListItem(TOOLS_MAP, context.getResources()
                    .getString(R.string.title_map), R.drawable.ic_vector_map));
            items.add(new MainListItem(TOOLS_TRANSPORT, context.getResources
                    ().getString(R.string.title_transport), R.drawable
                    .ic_vector_transport));
            items.add(new MainListItem(TOOLS_CALENDAR, context.getResources()
                    .getString(R.string.title_calendar), R.drawable.ic_vector_calendar));
            items.add(new MainListItem(TOOLS_WEATHER, context.getResources()
                    .getString(R.string.title_weather), R.drawable.ic_vector_cloud));
            items.add(new MainListItem(TOOLS_NOTES, context.getResources()
                    .getString(R.string.title_note), R.drawable.ic_vector_note,
                    MainListItemType.ACTION));
            items.add(new MainListItem(TOOLS_RECORDER, context.getResources()
                    .getString(R.string.title_recorder), R.drawable
                    .ic_vector_recorder));
            items.add(new MainListItem(TOOLS_CAMERA, context.getResources()
                    .getString(R.string.title_camera), R.drawable.ic_vector_camera));
            items.add(new MainListItem(TOOLS_PHOTOS, context.getResources()
                    .getString(R.string.title_photos), R.drawable.ic_vector_photo));
            items.add(new MainListItem(TOOLS_PAYMENT, context.getResources()
                    .getString(R.string.title_payment), R.drawable
                    .ic_vector_payment));
            items.add(new MainListItem(TOOLS_WELLNESS, context.getResources()
                    .getString(R.string.title_wellness), R.drawable
                    .ic_vector_wellness));

            items.add(new MainListItem(TOOLS_TODO, context.getResources()
                    .getString(R.string.title_todo), R.drawable
                    .ic_vector_todo));
            items.add(new MainListItem(TOOLS_BROWSER, context.getResources()
                    .getString(R.string.title_browser), R.drawable
                    .ic_vector_browser));
            items.add(new MainListItem(TOOLS_MUSIC, context.getResources()
                    .getString(R.string.title_music), R.drawable
                    .ic_vector_music));
            items.add(new MainListItem(TOOLS_PODCAST, context.getResources()
                    .getString(R.string.title_podcast), R.drawable
                    .ic_vector_podcast));
            items.add(new MainListItem(TOOLS_FOOD, context.getResources()
                    .getString(R.string.title_food), R.drawable
                    .ic_vector_food));
            items.add(new MainListItem(TOOLS_FITNESS, context.getResources()
                    .getString(R.string.title_fitness), R.drawable
                    .ic_vector_fitness));
            items.add(new MainListItem(TOOLS_CLOUD, context.getResources()
                    .getString(R.string.title_cloud), R.drawable.ic_vector_cloud));
            items.add(new MainListItem(TOOLS_BOOKS, context.getResources()
                    .getString(R.string.title_books), R.drawable.ic_vector_books));
            items.add(new MainListItem(TOOLS_AUTHENTICATION, context.getResources()
                    .getString(R.string.title_authentication), R.drawable.ic_vector_authenticator));
            items.add(new MainListItem(TOOLS_ASSISTANT, context.getResources()
                    .getString(R.string.title_assistant), R.drawable.ic_vector_assistant));
            items.add(new MainListItem(TOOLS_ADDITIONAL_MESSAGE, context.getResources()
                    .getString(R.string.title_additional_message), R.drawable.ic_vector_additionalmessage));
            items.add(new MainListItem(TOOLS_BANKING, context.getResources()
                    .getString(R.string.title_banking), R.drawable.ic_vector_banking));
            items.add(new MainListItem(TOOLS_COURCE, context.getResources()
                    .getString(R.string.title_course), R.drawable.ic_vector_cource));
            items.add(new MainListItem(TOOLS_DOC, context.getResources()
                    .getString(R.string.title_doc), R.drawable.ic_vector_doc));
            items.add(new MainListItem(TOOLS_FILES, context.getResources()
                    .getString(R.string.title_files), R.drawable.ic_vector_files));
            items.add(new MainListItem(TOOLS_FLASH, context.getResources()
                    .getString(R.string.title_flash), R.drawable.ic_vector_flash));
            items.add(new MainListItem(TOOLS_HEALTH, context.getResources()
                    .getString(R.string.title_health), R.drawable.ic_vector_health));
            items.add(new MainListItem(TOOLS_JOURNAL, context.getResources()
                    .getString(R.string.title_journal), R.drawable.ic_vector_journal));
            items.add(new MainListItem(TOOLS_LANGUAGES, context.getResources()
                    .getString(R.string.title_languages), R.drawable.ic_vector_language));
            items.add(new MainListItem(TOOLS_LEARNING, context.getResources()
                    .getString(R.string.title_learning), R.drawable.ic_vector_learning));
            items.add(new MainListItem(TOOLS_MEDITATION, context.getResources()
                    .getString(R.string.title_meditation), R.drawable.ic_vector_meditation));
            items.add(new MainListItem(TOOLS_MICROPHONE, context.getResources()
                    .getString(R.string.title_microphone), R.drawable.ic_vector_microphone));
            items.add(new MainListItem(TOOLS_NEWS, context.getResources()
                    .getString(R.string.title_news), R.drawable.ic_vector_news));
            items.add(new MainListItem(TOOLS_SEARCH, context.getResources()
                    .getString(R.string.title_search), R.drawable.ic_vector_search));
            items.add(new MainListItem(TOOLS_SETTINGS, context.getResources()
                    .getString(R.string.title_settings), R.drawable.ic_vector_settings));
            items.add(new MainListItem(TOOLS_VOICE, context.getResources()
                    .getString(R.string.title_voice), R.drawable.ic_vector_voice));
            items.add(new MainListItem(TOOLS_CALL, context.getResources()
                    .getString(R.string.title_call), R.drawable.ic_vector_call,
                    MainListItemType.ACTION));
            items.add(new MainListItem(TOOLS_CLOCK, context.getResources()
                    .getString(R.string.title_clock), R.drawable
                    .ic_vector_clock));
            items.add(new MainListItem(TOOLS_MESSAGE, context.getResources()
                    .getString(R.string.title_messages), R.drawable
                    .ic_vector_messages, MainListItemType.ACTION));
            items.add(new MainListItem(TOOLS_EMAIL, context.getResources()
                    .getString(R.string.title_email), R.drawable.ic_vector_email));


        }
    }


    public void loadItems(List<MainListItem> items, Fragment fragment) {
        if (context != null) {
            List<MainListItem> allAppsData = new ArrayList<>();
            ArrayList<MainListItem> toolsItems = new ArrayList<>();
            HashMap<Integer, AppMenu> toolsSettings = CoreApplication.getInstance().getToolsSettings
                    ();
            if (!TextUtils.isEmpty(toolsSettings.get(TOOLS_MAP)
                    .getApplicationName()) && toolsSettings.get(TOOLS_MAP)
                    .getApplicationName().contains(".")) {
                toolsItems.add(new MainListItem(TOOLS_MAP, context.getResources().getString(R.string.title_map), R.drawable.ic_menu_map));
            }

            if (!TextUtils.isEmpty(toolsSettings.get(TOOLS_TRANSPORT)
                    .getApplicationName()) && toolsSettings.get(TOOLS_TRANSPORT)
                    .getApplicationName().contains(".")) {
                toolsItems.add(new MainListItem(TOOLS_TRANSPORT, context
                        .getResources().getString(R.string.title_transport),
                        R.drawable.ic_vector_transport));
            }

            if (!TextUtils.isEmpty(toolsSettings.get(TOOLS_CALENDAR)
                    .getApplicationName()) && toolsSettings.get(TOOLS_CALENDAR)
                    .getApplicationName().contains(".")) {
                toolsItems.add(new MainListItem(TOOLS_CALENDAR, context
                        .getResources().getString(R.string.title_calendar), R
                        .drawable.ic_vector_calendar));

            }
            if (!TextUtils.isEmpty(toolsSettings.get(TOOLS_WEATHER)
                    .getApplicationName()) && toolsSettings.get(TOOLS_WEATHER)
                    .getApplicationName().contains(".")) {
                toolsItems.add(new MainListItem(TOOLS_WEATHER, context
                        .getResources().getString(R.string.title_weather), R
                        .drawable.ic_vector_cloud));
            }

            if (!TextUtils.isEmpty(toolsSettings.get(TOOLS_NOTES)
                    .getApplicationName())) {
                toolsItems.add(new MainListItem(TOOLS_NOTES, context.getResources().getString(R.string.title_note), R.drawable.ic_vector_note, MainListItemType.ACTION));
            }

            if (!TextUtils.isEmpty(toolsSettings.get(TOOLS_RECORDER)
                    .getApplicationName()) && toolsSettings.get(TOOLS_RECORDER)
                    .getApplicationName().contains(".")) {
                toolsItems.add(new MainListItem(TOOLS_RECORDER, context.getResources().getString(R.string.title_recorder), R.drawable.ic_vector_recorder));
            }

            if (!TextUtils.isEmpty(toolsSettings.get(TOOLS_TODO)
                    .getApplicationName()) && toolsSettings.get(TOOLS_TODO)
                    .getApplicationName().contains(".")) {
                toolsItems.add(new MainListItem(TOOLS_TODO, context
                        .getResources().getString(R.string.title_todo), R
                        .drawable.ic_vector_todo));
            }

            if (!TextUtils.isEmpty(toolsSettings.get(TOOLS_BROWSER)
                    .getApplicationName()) && toolsSettings.get(TOOLS_BROWSER)
                    .getApplicationName().contains(".")) {
                toolsItems.add(new MainListItem(TOOLS_BROWSER, context.getResources().getString(R.string.title_browser), R.drawable.ic_vector_browser));
            }
            if (!TextUtils.isEmpty(toolsSettings.get(TOOLS_PODCAST)
                    .getApplicationName()) && toolsSettings.get(TOOLS_PODCAST)
                    .getApplicationName().contains(".")) {
                toolsItems.add(new MainListItem(TOOLS_PODCAST, context
                        .getResources().getString(R.string.title_podcast), R
                        .drawable.ic_vector_podcast));
            }
            if (!TextUtils.isEmpty(toolsSettings.get(TOOLS_FOOD)
                    .getApplicationName()) && toolsSettings.get(TOOLS_FOOD)
                    .getApplicationName().contains(".")) {
                toolsItems.add(new MainListItem(TOOLS_FOOD, context
                        .getResources().getString(R.string.title_food), R
                        .drawable.ic_vector_food));
            }
            if (!TextUtils.isEmpty(toolsSettings.get(TOOLS_FITNESS)
                    .getApplicationName()) && toolsSettings.get(TOOLS_FITNESS)
                    .getApplicationName().contains(".")) {
                toolsItems.add(new MainListItem(TOOLS_FITNESS, context
                        .getResources().getString(R.string.title_fitness), R
                        .drawable.ic_vector_fitness));
            }

            if (!TextUtils.isEmpty(toolsSettings.get(TOOLS_MUSIC)
                    .getApplicationName()) && toolsSettings.get(TOOLS_MUSIC)
                    .getApplicationName().contains(".")) {
                toolsItems.add(new MainListItem(TOOLS_MUSIC, context
                        .getResources().getString(R.string.title_music), R
                        .drawable.ic_vector_music));
            }

            if (!TextUtils.isEmpty(toolsSettings.get(TOOLS_CAMERA)
                    .getApplicationName()) && toolsSettings.get(TOOLS_CAMERA)
                    .getApplicationName().contains(".")) {
                toolsItems.add(new MainListItem(TOOLS_CAMERA, context.getResources().getString(R.string.title_camera), R.drawable.ic_vector_camera));
            }

            if (!TextUtils.isEmpty(toolsSettings.get(TOOLS_PHOTOS)
                    .getApplicationName()) && toolsSettings.get(TOOLS_PHOTOS)
                    .getApplicationName().contains(".")) {
                toolsItems.add(new MainListItem(TOOLS_PHOTOS, context.getResources().getString(R.string.title_photos), R.drawable.ic_vector_photo));
            }


            if (!TextUtils.isEmpty(toolsSettings.get(TOOLS_PAYMENT)
                    .getApplicationName()) && toolsSettings.get(TOOLS_PAYMENT)
                    .getApplicationName().contains(".")) {
                toolsItems.add(new MainListItem(TOOLS_PAYMENT, context.getResources().getString(R.string.title_payment), R.drawable.ic_vector_payment));
            }


            if (!TextUtils.isEmpty(toolsSettings.get(TOOLS_WELLNESS)
                    .getApplicationName()) && toolsSettings.get(TOOLS_WELLNESS)
                    .getApplicationName().contains(".")) {
                toolsItems.add(new MainListItem(TOOLS_WELLNESS, context.getResources().getString(R.string.title_wellness), R.drawable.ic_vector_wellness));
            }

            if (!TextUtils.isEmpty(toolsSettings.get(TOOLS_CALL)
                    .getApplicationName()) && toolsSettings.get(TOOLS_CALL)
                    .getApplicationName().contains(".")) {
                toolsItems.add(new MainListItem(TOOLS_CALL, context.getResources().getString(R.string.title_call), R.drawable.ic_vector_call, MainListItemType.ACTION));
            }

            if (!TextUtils.isEmpty(toolsSettings.get(TOOLS_CLOCK)
                    .getApplicationName()) && toolsSettings.get(TOOLS_CLOCK)
                    .getApplicationName().contains(".")) {
                toolsItems.add(new MainListItem(TOOLS_CLOCK, context.getResources().getString(R.string.title_clock), R.drawable.ic_vector_clock));
            }

            if (!TextUtils.isEmpty(toolsSettings.get(TOOLS_MESSAGE)
                    .getApplicationName()) && toolsSettings.get(TOOLS_MESSAGE)
                    .getApplicationName().contains(".")) {
                toolsItems.add(new MainListItem(TOOLS_MESSAGE, context
                        .getResources().getString(R.string.title_messages), R
                        .drawable.ic_vector_messages, MainListItemType.ACTION));
            }

            if (!TextUtils.isEmpty(toolsSettings.get(TOOLS_EMAIL)
                    .getApplicationName()) && toolsSettings.get(TOOLS_EMAIL)
                    .getApplicationName().contains(".")) {
                toolsItems.add(new MainListItem(TOOLS_EMAIL, context
                        .getResources().getString(R.string.title_email), R.drawable.ic_vector_email));
            }

            if (!TextUtils.isEmpty(toolsSettings.get(TOOLS_CLOUD)
                    .getApplicationName()) && toolsSettings.get(TOOLS_CLOUD)
                    .getApplicationName().contains(".")) {
                toolsItems.add(new MainListItem(TOOLS_CLOUD, context
                        .getResources().getString(R.string.title_cloud), R.drawable.ic_vector_cloud));
            }


            if (!TextUtils.isEmpty(toolsSettings.get(TOOLS_BOOKS)
                    .getApplicationName()) && toolsSettings.get(TOOLS_BOOKS)
                    .getApplicationName().contains(".")) {
                toolsItems.add(new MainListItem(TOOLS_BOOKS, context
                        .getResources().getString(R.string.title_books), R.drawable.ic_vector_books));
            }


            if (!TextUtils.isEmpty(toolsSettings.get(TOOLS_AUTHENTICATION)
                    .getApplicationName()) && toolsSettings.get(TOOLS_AUTHENTICATION)
                    .getApplicationName().contains(".")) {
                toolsItems.add(new MainListItem(TOOLS_AUTHENTICATION, context
                        .getResources().getString(R.string.title_authentication), R.drawable.ic_vector_authenticator));
            }



            if (!TextUtils.isEmpty(toolsSettings.get(TOOLS_ASSISTANT)
                    .getApplicationName()) && toolsSettings.get(TOOLS_ASSISTANT)
                    .getApplicationName().contains(".")) {
                toolsItems.add(new MainListItem(TOOLS_ASSISTANT, context
                        .getResources().getString(R.string.title_assistant), R.drawable.ic_vector_assistant));
            }



            if (!TextUtils.isEmpty(toolsSettings.get(TOOLS_ADDITIONAL_MESSAGE)
                    .getApplicationName()) && toolsSettings.get(TOOLS_ADDITIONAL_MESSAGE)
                    .getApplicationName().contains(".")) {
                toolsItems.add(new MainListItem(TOOLS_ADDITIONAL_MESSAGE, context
                        .getResources().getString(R.string.title_additional_message), R.drawable.ic_vector_additionalmessage));
            }



            if (!TextUtils.isEmpty(toolsSettings.get(TOOLS_BANKING)
                    .getApplicationName()) && toolsSettings.get(TOOLS_BANKING)
                    .getApplicationName().contains(".")) {
                toolsItems.add(new MainListItem(TOOLS_BANKING, context
                        .getResources().getString(R.string.title_banking), R.drawable.ic_vector_banking));
            }




            if (!TextUtils.isEmpty(toolsSettings.get(TOOLS_COURCE)
                    .getApplicationName()) && toolsSettings.get(TOOLS_COURCE)
                    .getApplicationName().contains(".")) {
                toolsItems.add(new MainListItem(TOOLS_COURCE, context
                        .getResources().getString(R.string.title_course), R.drawable.ic_vector_cource));
            }




            if (!TextUtils.isEmpty(toolsSettings.get(TOOLS_DOC)
                    .getApplicationName()) && toolsSettings.get(TOOLS_DOC)
                    .getApplicationName().contains(".")) {
                toolsItems.add(new MainListItem(TOOLS_DOC, context
                        .getResources().getString(R.string.title_doc), R.drawable.ic_vector_doc));
            }



            if (!TextUtils.isEmpty(toolsSettings.get(TOOLS_FILES)
                    .getApplicationName()) && toolsSettings.get(TOOLS_FILES)
                    .getApplicationName().contains(".")) {
                toolsItems.add(new MainListItem(TOOLS_FILES, context
                        .getResources().getString(R.string.title_files), R.drawable.ic_vector_files));
            }



            if (!TextUtils.isEmpty(toolsSettings.get(TOOLS_FLASH)
                    .getApplicationName()) && toolsSettings.get(TOOLS_FLASH)
                    .getApplicationName().contains(".")) {
                toolsItems.add(new MainListItem(TOOLS_FLASH, context
                        .getResources().getString(R.string.title_flash), R.drawable.ic_vector_flash));
            }



            if (!TextUtils.isEmpty(toolsSettings.get(TOOLS_HEALTH)
                    .getApplicationName()) && toolsSettings.get(TOOLS_HEALTH)
                    .getApplicationName().contains(".")) {
                toolsItems.add(new MainListItem(TOOLS_HEALTH, context
                        .getResources().getString(R.string.title_health), R.drawable.ic_vector_health));
            }




            if (!TextUtils.isEmpty(toolsSettings.get(TOOLS_JOURNAL)
                    .getApplicationName()) && toolsSettings.get(TOOLS_JOURNAL)
                    .getApplicationName().contains(".")) {
                toolsItems.add(new MainListItem(TOOLS_JOURNAL, context
                        .getResources().getString(R.string.title_journal), R.drawable.ic_vector_journal));
            }



            if (!TextUtils.isEmpty(toolsSettings.get(TOOLS_LANGUAGES)
                    .getApplicationName()) && toolsSettings.get(TOOLS_LANGUAGES)
                    .getApplicationName().contains(".")) {
                toolsItems.add(new MainListItem(TOOLS_LANGUAGES, context
                        .getResources().getString(R.string.title_languages), R.drawable.ic_vector_language));
            }




            if (!TextUtils.isEmpty(toolsSettings.get(TOOLS_LEARNING)
                    .getApplicationName()) && toolsSettings.get(TOOLS_LEARNING)
                    .getApplicationName().contains(".")) {
                toolsItems.add(new MainListItem(TOOLS_LEARNING, context
                        .getResources().getString(R.string.title_learning), R.drawable.ic_vector_learning));
            }


            if (!TextUtils.isEmpty(toolsSettings.get(TOOLS_MEDITATION)
                    .getApplicationName()) && toolsSettings.get(TOOLS_MEDITATION)
                    .getApplicationName().contains(".")) {
                toolsItems.add(new MainListItem(TOOLS_MEDITATION, context
                        .getResources().getString(R.string.title_meditation), R.drawable.ic_vector_meditation));
            }




            if (!TextUtils.isEmpty(toolsSettings.get(TOOLS_MICROPHONE)
                    .getApplicationName()) && toolsSettings.get(TOOLS_MICROPHONE)
                    .getApplicationName().contains(".")) {
                toolsItems.add(new MainListItem(TOOLS_MICROPHONE, context
                        .getResources().getString(R.string.title_microphone), R.drawable.ic_vector_microphone));
            }


            if (!TextUtils.isEmpty(toolsSettings.get(TOOLS_NEWS)
                    .getApplicationName()) && toolsSettings.get(TOOLS_NEWS)
                    .getApplicationName().contains(".")) {
                toolsItems.add(new MainListItem(TOOLS_NEWS, context
                        .getResources().getString(R.string.title_news), R.drawable.ic_vector_news));
            }





            if (!TextUtils.isEmpty(toolsSettings.get(TOOLS_SEARCH)
                    .getApplicationName()) && toolsSettings.get(TOOLS_SEARCH)
                    .getApplicationName().contains(".")) {
                toolsItems.add(new MainListItem(TOOLS_SEARCH, context
                        .getResources().getString(R.string.title_search), R.drawable.ic_vector_search));
            }




            if (!TextUtils.isEmpty(toolsSettings.get(TOOLS_SETTINGS)
                    .getApplicationName()) && toolsSettings.get(TOOLS_SETTINGS)
                    .getApplicationName().contains(".")) {
                toolsItems.add(new MainListItem(TOOLS_SETTINGS, context
                        .getResources().getString(R.string.title_settings), R.drawable.ic_vector_settings));
            }




            if (!TextUtils.isEmpty(toolsSettings.get(TOOLS_VOICE)
                    .getApplicationName()) && toolsSettings.get(TOOLS_VOICE)
                    .getApplicationName().contains(".")) {
                toolsItems.add(new MainListItem(TOOLS_VOICE, context
                        .getResources().getString(R.string.title_voice), R.drawable.ic_vector_voice));
            }























            toolsItems = Sorting.sortToolAppAssignment(context, toolsItems);


            ArrayList<MainListItem> appItems = new ArrayList<>();

            if (fragment instanceof PaneFragment || fragment instanceof ToolsPaneFragment) {
                try {
                    Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
                    mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                    List<ResolveInfo> installedPackageList = context.getPackageManager().queryIntentActivities(mainIntent, 0);
                    for (ResolveInfo resolveInfo : installedPackageList) {
                        if (!TextUtils.isEmpty(resolveInfo.activityInfo.packageName) && !TextUtils.isEmpty(resolveInfo.loadLabel(context.getPackageManager()))) {
                            String packageName = resolveInfo.activityInfo.packageName;
                            boolean isEnable = UIUtils.isAppInstalledAndEnabled(context, packageName);
                            if (isEnable && !packageName.equalsIgnoreCase(context.getPackageName())) {
                                appItems.add(new MainListItem(-1, "" + resolveInfo.loadLabel(context.getPackageManager()), resolveInfo.activityInfo.packageName));
                            }
                        }
                    }

                } catch (Exception e) {
                    CoreApplication.getInstance().logException(e);
                    e.printStackTrace();
                }
            }


            appItems = Sorting.SortApplications(appItems);

            try {
                items.addAll(toolsItems);
                items.addAll(appItems);
            } catch (Exception ae) {
                ae.printStackTrace();
            }
        }
    }


    public void listItemClicked(int id) {
        String packageName, applicationName;
        if (context != null) {
            switch (id) {
                case TOOLS_MAP://Map
                    packageName = CoreApplication.getInstance().getToolsSettings().get
                            (TOOLS_MAP).getApplicationName().trim();
                    applicationName = CoreApplication.getInstance().getApplicationNameFromPackageName(packageName);
                    FirebaseHelper.getInstance().logSiempoMenuUsage(3, context.getResources().getString(R.string.title_map), applicationName);
                    new ActivityHelper(context).openAppWithPackageName
                            (packageName);
                    break;
                case TOOLS_TRANSPORT://Transport
                    packageName = CoreApplication.getInstance().getToolsSettings().get
                            (TOOLS_TRANSPORT).getApplicationName().trim();
                    applicationName = CoreApplication.getInstance().getApplicationNameFromPackageName(packageName);
                    FirebaseHelper.getInstance().logSiempoMenuUsage(3, context.getResources().getString(R.string.title_transport), applicationName);
                    new ActivityHelper(context).openAppWithPackageName
                            (packageName);
                    break;
                case TOOLS_CALENDAR://Calender
                    packageName = CoreApplication.getInstance().getToolsSettings().get
                            (TOOLS_CALENDAR).getApplicationName().trim();
                    applicationName = CoreApplication.getInstance().getApplicationNameFromPackageName(packageName);
                    FirebaseHelper.getInstance().logSiempoMenuUsage(3, context.getResources().getString(R.string.title_calendar), applicationName);
                    new ActivityHelper(context).openAppWithPackageName
                            (packageName);
                    break;
                case TOOLS_WEATHER://Weather
                    packageName = CoreApplication.getInstance().getToolsSettings().get
                            (TOOLS_WEATHER).getApplicationName().trim();
                    applicationName = CoreApplication.getInstance().getApplicationNameFromPackageName(packageName);
                    FirebaseHelper.getInstance().logSiempoMenuUsage(3, context.getResources().getString(R.string.title_weather), applicationName);
                    new ActivityHelper(context).openAppWithPackageName
                            (packageName);
                    break;
                case TOOLS_NOTES:// Notes
                    packageName = CoreApplication.getInstance().getToolsSettings().get
                            (TOOLS_NOTES).getApplicationName().trim();
                    if (packageName.equalsIgnoreCase("Notes")) {
                        FirebaseHelper.getInstance().logSiempoMenuUsage(3, context.getResources().getString(R.string.title_note), context.getResources().getString(R.string.title_note));
                        new ActivityHelper(context).openNotesApp(false);
                    } else {
                        applicationName = CoreApplication.getInstance().getApplicationNameFromPackageName(packageName);
                        FirebaseHelper.getInstance().logSiempoMenuUsage(3, context.getResources().getString(R.string.title_note), applicationName);
                        new ActivityHelper(context).openAppWithPackageName
                                (packageName);
                    }
                    break;
                case TOOLS_RECORDER://Recorder
                    packageName = CoreApplication.getInstance().getToolsSettings().get
                            (TOOLS_RECORDER).getApplicationName().trim();
                    applicationName = CoreApplication.getInstance().getApplicationNameFromPackageName(packageName);
                    FirebaseHelper.getInstance().logSiempoMenuUsage(3, context.getResources().getString(R.string.title_recorder), applicationName);
                    new ActivityHelper(context).openAppWithPackageName
                            (packageName);
                    break;

                case TOOLS_TODO://TODO
                    packageName = CoreApplication.getInstance().getToolsSettings().get
                            (TOOLS_TODO).getApplicationName().trim();
                    applicationName = CoreApplication.getInstance().getApplicationNameFromPackageName(packageName);
                    FirebaseHelper.getInstance().logSiempoMenuUsage(3,
                            context.getResources().getString(R.string
                                    .title_todo), applicationName);
                    new ActivityHelper(context).openAppWithPackageName
                            (packageName);
                    break;


                case TOOLS_PODCAST://Podcast
                    packageName = CoreApplication.getInstance().getToolsSettings().get
                            (TOOLS_PODCAST).getApplicationName().trim();
                    applicationName = CoreApplication.getInstance().getApplicationNameFromPackageName(packageName);
                    FirebaseHelper.getInstance().logSiempoMenuUsage(3,
                            context.getResources().getString(R.string
                                    .title_podcast), applicationName);
                    new ActivityHelper(context).openAppWithPackageName
                            (packageName);
                    break;

                case TOOLS_FOOD://Food
                    packageName = CoreApplication.getInstance().getToolsSettings().get
                            (TOOLS_FOOD).getApplicationName().trim();
                    applicationName = CoreApplication.getInstance().getApplicationNameFromPackageName(packageName);
                    FirebaseHelper.getInstance().logSiempoMenuUsage(3,
                            context.getResources().getString(R.string
                                    .title_food), applicationName);
                    new ActivityHelper(context).openAppWithPackageName
                            (packageName);
                    break;


                case TOOLS_FITNESS://Fitness
                    packageName = CoreApplication.getInstance().getToolsSettings().get
                            (TOOLS_FITNESS).getApplicationName().trim();
                    applicationName = CoreApplication.getInstance().getApplicationNameFromPackageName(packageName);
                    FirebaseHelper.getInstance().logSiempoMenuUsage(3,
                            context.getResources().getString(R.string
                                    .title_fitness), applicationName);
                    new ActivityHelper(context).openAppWithPackageName
                            (packageName);
                    break;

                case TOOLS_MUSIC://Music
                    packageName = CoreApplication.getInstance().getToolsSettings().get
                            (TOOLS_MUSIC).getApplicationName().trim();
                    applicationName = CoreApplication.getInstance().getApplicationNameFromPackageName(packageName);
                    FirebaseHelper.getInstance().logSiempoMenuUsage(3,
                            context.getResources().getString(R.string
                                    .title_music), applicationName);
                    new ActivityHelper(context).openAppWithPackageName
                            (packageName);
                    break;
                case TOOLS_CAMERA:// Camera
                    packageName = CoreApplication.getInstance().getToolsSettings().get
                            (TOOLS_CAMERA).getApplicationName().trim();
                    applicationName = CoreApplication.getInstance().getApplicationNameFromPackageName(packageName);
                    FirebaseHelper.getInstance().logSiempoMenuUsage(3, context.getResources().getString(R.string.title_camera), applicationName);
                    new ActivityHelper(context).openAppWithPackageName
                            (packageName);
                    break;
                case TOOLS_PHOTOS://Photos
                    packageName = CoreApplication.getInstance().getToolsSettings().get
                            (TOOLS_PHOTOS).getApplicationName().trim();
                    applicationName = CoreApplication.getInstance().getApplicationNameFromPackageName(packageName);
                    FirebaseHelper.getInstance().logSiempoMenuUsage(3, context.getResources().getString(R.string.title_photos), applicationName);
                    new ActivityHelper(context).openAppWithPackageName
                            (packageName);

                    break;
                case TOOLS_PAYMENT://Payment
                    packageName = CoreApplication.getInstance().getToolsSettings().get
                            (TOOLS_PAYMENT).getApplicationName().trim();
                    applicationName = CoreApplication.getInstance().getApplicationNameFromPackageName(packageName);
                    FirebaseHelper.getInstance().logSiempoMenuUsage(3, context.getResources().getString(R.string.title_payment), applicationName);
                    new ActivityHelper(context).openAppWithPackageName
                            (packageName);
                    break;
                case TOOLS_WELLNESS://Wellness
                    packageName = CoreApplication.getInstance().getToolsSettings().get
                            (TOOLS_WELLNESS).getApplicationName().trim();
                    applicationName = CoreApplication.getInstance().getApplicationNameFromPackageName(packageName);
                    FirebaseHelper.getInstance().logSiempoMenuUsage(3, context.getResources().getString(R.string.title_wellness), applicationName);
                    new ActivityHelper(context).openAppWithPackageName
                            (packageName);
                    break;
                case TOOLS_BROWSER:// Browser
                    packageName = CoreApplication.getInstance().getToolsSettings().get
                            (TOOLS_BROWSER).getApplicationName().trim();
                    applicationName = CoreApplication.getInstance().getApplicationNameFromPackageName(packageName);
                    FirebaseHelper.getInstance().logSiempoMenuUsage(3, context.getResources().getString(R.string.title_browser), applicationName);
                    new ActivityHelper(context).openAppWithPackageName
                            (packageName);
                    break;
                case TOOLS_CALL:// Call
                    packageName = CoreApplication.getInstance().getToolsSettings().get
                            (TOOLS_CALL).getApplicationName().trim();
                    applicationName = CoreApplication.getInstance().getApplicationNameFromPackageName(packageName);
                    FirebaseHelper.getInstance().logSiempoMenuUsage(3, context.getResources().getString(R.string.title_call), applicationName);
                    try {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setPackage(packageName);
                        context.startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                        new ActivityHelper(context).openAppWithPackageName
                                (packageName);
                    }
                    break;
                case TOOLS_CLOCK://Clock
                    packageName = CoreApplication.getInstance().getToolsSettings().get
                            (TOOLS_CLOCK).getApplicationName().trim();
                    applicationName = CoreApplication.getInstance().getApplicationNameFromPackageName(packageName);
                    FirebaseHelper.getInstance().logSiempoMenuUsage(3, context.getResources().getString(R.string.title_clock), applicationName);
                    new ActivityHelper(context).openAppWithPackageName
                            (packageName);
                    break;
                case TOOLS_MESSAGE:// Message
                    packageName = CoreApplication.getInstance().getToolsSettings().get
                            (TOOLS_MESSAGE).getApplicationName().trim();
                    applicationName = CoreApplication.getInstance().getApplicationNameFromPackageName(packageName);
                    FirebaseHelper.getInstance().logSiempoMenuUsage(3, context.getResources().getString(R.string.title_messages), applicationName);
                    new ActivityHelper(context).openAppWithPackageName
                            (packageName);

                    break;
                case TOOLS_EMAIL:// Email

                    packageName = CoreApplication.getInstance().getToolsSettings().get
                            (TOOLS_EMAIL).getApplicationName().trim();
                    applicationName = CoreApplication.getInstance().getApplicationNameFromPackageName(packageName);
                    FirebaseHelper.getInstance().logSiempoMenuUsage(3, context.getResources().getString(R.string.title_email), applicationName);
                    new ActivityHelper(context).openAppWithPackageName
                            (packageName);

                    break;

                case TOOLS_CLOUD:// Cloud

                    packageName = CoreApplication.getInstance().getToolsSettings().get
                            (TOOLS_CLOUD).getApplicationName().trim();
                    applicationName = CoreApplication.getInstance().getApplicationNameFromPackageName(packageName);
                    FirebaseHelper.getInstance().logSiempoMenuUsage(3, context.getResources().getString(R.string.title_cloud), applicationName);
                    new ActivityHelper(context).openAppWithPackageName
                            (packageName);

                    break;

                case TOOLS_BOOKS:// Books

                    packageName = CoreApplication.getInstance().getToolsSettings().get
                            (TOOLS_BOOKS).getApplicationName().trim();
                    applicationName = CoreApplication.getInstance().getApplicationNameFromPackageName(packageName);
                    FirebaseHelper.getInstance().logSiempoMenuUsage(3, context.getResources().getString(R.string.title_books), applicationName);
                    new ActivityHelper(context).openAppWithPackageName
                            (packageName);
                    break;


                case TOOLS_AUTHENTICATION:// Authentication

                    packageName = CoreApplication.getInstance().getToolsSettings().get
                            (TOOLS_AUTHENTICATION).getApplicationName().trim();
                    applicationName = CoreApplication.getInstance().getApplicationNameFromPackageName(packageName);
                    FirebaseHelper.getInstance().logSiempoMenuUsage(3, context.getResources().getString(R.string.title_authentication), applicationName);
                    new ActivityHelper(context).openAppWithPackageName
                            (packageName);
                    break;


                case TOOLS_ASSISTANT:// Assistant

                    packageName = CoreApplication.getInstance().getToolsSettings().get
                            (TOOLS_ASSISTANT).getApplicationName().trim();
                    applicationName = CoreApplication.getInstance().getApplicationNameFromPackageName(packageName);
                    FirebaseHelper.getInstance().logSiempoMenuUsage(3, context.getResources().getString(R.string.title_assistant), applicationName);
                    new ActivityHelper(context).openAppWithPackageName
                            (packageName);
                    break;



                case TOOLS_ADDITIONAL_MESSAGE:// Assistant

                    packageName = CoreApplication.getInstance().getToolsSettings().get
                            (TOOLS_ADDITIONAL_MESSAGE).getApplicationName().trim();
                    applicationName = CoreApplication.getInstance().getApplicationNameFromPackageName(packageName);
                    FirebaseHelper.getInstance().logSiempoMenuUsage(3, context.getResources().getString(R.string.title_additional_message), applicationName);
                    new ActivityHelper(context).openAppWithPackageName
                            (packageName);
                    break;


                case TOOLS_BANKING:

                    packageName = CoreApplication.getInstance().getToolsSettings().get
                            (TOOLS_BANKING).getApplicationName().trim();
                    applicationName = CoreApplication.getInstance().getApplicationNameFromPackageName(packageName);
                    FirebaseHelper.getInstance().logSiempoMenuUsage(3, context.getResources().getString(R.string.title_banking), applicationName);
                    new ActivityHelper(context).openAppWithPackageName
                            (packageName);
                    break;

                case TOOLS_COURCE:

                    packageName = CoreApplication.getInstance().getToolsSettings().get
                            (TOOLS_COURCE).getApplicationName().trim();
                    applicationName = CoreApplication.getInstance().getApplicationNameFromPackageName(packageName);
                    FirebaseHelper.getInstance().logSiempoMenuUsage(3, context.getResources().getString(R.string.title_course), applicationName);
                    new ActivityHelper(context).openAppWithPackageName
                            (packageName);
                    break;


                case TOOLS_DOC:

                    packageName = CoreApplication.getInstance().getToolsSettings().get
                            (TOOLS_DOC).getApplicationName().trim();
                    applicationName = CoreApplication.getInstance().getApplicationNameFromPackageName(packageName);
                    FirebaseHelper.getInstance().logSiempoMenuUsage(3, context.getResources().getString(R.string.title_doc), applicationName);
                    new ActivityHelper(context).openAppWithPackageName
                            (packageName);
                    break;



                case TOOLS_FILES:

                    packageName = CoreApplication.getInstance().getToolsSettings().get
                            (TOOLS_FILES).getApplicationName().trim();
                    applicationName = CoreApplication.getInstance().getApplicationNameFromPackageName(packageName);
                    FirebaseHelper.getInstance().logSiempoMenuUsage(3, context.getResources().getString(R.string.title_files), applicationName);
                    new ActivityHelper(context).openAppWithPackageName
                            (packageName);
                    break;

                case TOOLS_FLASH:

                    packageName = CoreApplication.getInstance().getToolsSettings().get
                            (TOOLS_FLASH).getApplicationName().trim();
                    applicationName = CoreApplication.getInstance().getApplicationNameFromPackageName(packageName);
                    FirebaseHelper.getInstance().logSiempoMenuUsage(3, context.getResources().getString(R.string.title_flash), applicationName);
                    new ActivityHelper(context).openAppWithPackageName
                            (packageName);
                    break;


                case TOOLS_HEALTH:

                    packageName = CoreApplication.getInstance().getToolsSettings().get
                            (TOOLS_HEALTH).getApplicationName().trim();
                    applicationName = CoreApplication.getInstance().getApplicationNameFromPackageName(packageName);
                    FirebaseHelper.getInstance().logSiempoMenuUsage(3, context.getResources().getString(R.string.title_health), applicationName);
                    new ActivityHelper(context).openAppWithPackageName
                            (packageName);
                    break;


                case TOOLS_JOURNAL:

                    packageName = CoreApplication.getInstance().getToolsSettings().get
                            (TOOLS_JOURNAL).getApplicationName().trim();
                    applicationName = CoreApplication.getInstance().getApplicationNameFromPackageName(packageName);
                    FirebaseHelper.getInstance().logSiempoMenuUsage(3, context.getResources().getString(R.string.title_journal), applicationName);
                    new ActivityHelper(context).openAppWithPackageName
                            (packageName);
                    break;

                case TOOLS_LANGUAGES:

                    packageName = CoreApplication.getInstance().getToolsSettings().get
                            (TOOLS_LANGUAGES).getApplicationName().trim();
                    applicationName = CoreApplication.getInstance().getApplicationNameFromPackageName(packageName);
                    FirebaseHelper.getInstance().logSiempoMenuUsage(3, context.getResources().getString(R.string.title_languages), applicationName);
                    new ActivityHelper(context).openAppWithPackageName
                            (packageName);
                    break;


                case TOOLS_LEARNING:

                    packageName = CoreApplication.getInstance().getToolsSettings().get
                            (TOOLS_LEARNING).getApplicationName().trim();
                    applicationName = CoreApplication.getInstance().getApplicationNameFromPackageName(packageName);
                    FirebaseHelper.getInstance().logSiempoMenuUsage(3, context.getResources().getString(R.string.title_learning), applicationName);
                    new ActivityHelper(context).openAppWithPackageName
                            (packageName);
                    break;




                case TOOLS_MEDITATION:

                    packageName = CoreApplication.getInstance().getToolsSettings().get
                            (TOOLS_MEDITATION).getApplicationName().trim();
                    applicationName = CoreApplication.getInstance().getApplicationNameFromPackageName(packageName);
                    FirebaseHelper.getInstance().logSiempoMenuUsage(3, context.getResources().getString(R.string.title_meditation), applicationName);
                    new ActivityHelper(context).openAppWithPackageName
                            (packageName);
                    break;



                case TOOLS_MICROPHONE:

                    packageName = CoreApplication.getInstance().getToolsSettings().get
                            (TOOLS_MICROPHONE).getApplicationName().trim();
                    applicationName = CoreApplication.getInstance().getApplicationNameFromPackageName(packageName);
                    FirebaseHelper.getInstance().logSiempoMenuUsage(3, context.getResources().getString(R.string.title_microphone), applicationName);
                    new ActivityHelper(context).openAppWithPackageName
                            (packageName);
                    break;



                case TOOLS_NEWS:

                    packageName = CoreApplication.getInstance().getToolsSettings().get
                            (TOOLS_NEWS).getApplicationName().trim();
                    applicationName = CoreApplication.getInstance().getApplicationNameFromPackageName(packageName);
                    FirebaseHelper.getInstance().logSiempoMenuUsage(3, context.getResources().getString(R.string.title_news), applicationName);
                    new ActivityHelper(context).openAppWithPackageName
                            (packageName);
                    break;




                case TOOLS_SEARCH:

                    packageName = CoreApplication.getInstance().getToolsSettings().get
                            (TOOLS_SEARCH).getApplicationName().trim();
                    applicationName = CoreApplication.getInstance().getApplicationNameFromPackageName(packageName);
                    FirebaseHelper.getInstance().logSiempoMenuUsage(3, context.getResources().getString(R.string.title_search), applicationName);
                    new ActivityHelper(context).openAppWithPackageName
                            (packageName);
                    break;




                case TOOLS_SETTINGS:

                    packageName = CoreApplication.getInstance().getToolsSettings().get
                            (TOOLS_SETTINGS).getApplicationName().trim();
                    applicationName = CoreApplication.getInstance().getApplicationNameFromPackageName(packageName);
                    FirebaseHelper.getInstance().logSiempoMenuUsage(3, context.getResources().getString(R.string.title_settings), applicationName);
                    new ActivityHelper(context).openAppWithPackageName
                            (packageName);
                    break;



                case TOOLS_VOICE:

                    packageName = CoreApplication.getInstance().getToolsSettings().get
                            (TOOLS_VOICE).getApplicationName().trim();
                    applicationName = CoreApplication.getInstance().getApplicationNameFromPackageName(packageName);
                    FirebaseHelper.getInstance().logSiempoMenuUsage(3, context.getResources().getString(R.string.title_voice), applicationName);
                    new ActivityHelper(context).openAppWithPackageName
                            (packageName);
                    break;

                default:
                    UIUtils.alert(context, context.getResources().getString(R.string.msg_not_yet_implemented));
                    break;
            }
            DashboardActivity.isTextLenghGreater = "";

        }
    }

}
