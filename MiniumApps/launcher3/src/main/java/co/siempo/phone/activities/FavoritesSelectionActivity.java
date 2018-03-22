package co.siempo.phone.activities;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import co.siempo.phone.R;
import co.siempo.phone.adapters.FavoriteFlaggingAdapter;
import co.siempo.phone.app.CoreApplication;
import co.siempo.phone.event.AppInstalledEvent;
import co.siempo.phone.helper.FirebaseHelper;
import co.siempo.phone.models.AppListInfo;
import co.siempo.phone.service.LoadFavoritePane;
import co.siempo.phone.service.LoadJunkFoodPane;
import co.siempo.phone.utils.PackageUtil;
import co.siempo.phone.utils.PrefSiempo;
import co.siempo.phone.utils.Sorting;
import co.siempo.phone.utils.UIUtils;
import de.greenrobot.event.Subscribe;

public class FavoritesSelectionActivity extends CoreActivity implements AdapterView.OnItemClickListener {

    Set<String> list = new HashSet<>();
    //Junk list removal will be needed here as we need to remove the
    //junk-flagged app from other app list which cn be marked as favorite
    Set<String> junkFoodList = new HashSet<>();
    FavoriteFlaggingAdapter junkfoodFlaggingAdapter;
    int firstPosition;
    List<String> installedPackageList;
    private Toolbar toolbar;
    private ListView listAllApps;
    private PopupMenu popup;
    private boolean isLoadFirstTime = true;
    private List<AppListInfo> favoriteList = new ArrayList<>();
    private List<AppListInfo> unfavoriteList = new ArrayList<>();
    private ArrayList<AppListInfo> bindingList = new ArrayList<>();
    private long startTime = 0;
    private boolean isClickOnView;

    @Subscribe
    public void appInstalledEvent(AppInstalledEvent event) {
        if (event.isAppInstalledSuccessfully()) {
            loadApps();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_selection);
        initView();
        list = PrefSiempo.getInstance(this).read(PrefSiempo.FAVORITE_APPS, new HashSet<String>());
        junkFoodList = PrefSiempo.getInstance(this).read(PrefSiempo.JUNKFOOD_APPS, new HashSet<String>());
        list.removeAll(junkFoodList);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (bindingList != null && bindingList.get(position) != null
                && !bindingList.get(position).packageName.equalsIgnoreCase("")) {
            showPopUp(view, bindingList.get(position).isFlagApp, position);
        }
    }

    /**
     * Initialize the view.
     */
    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_flagging_screen);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color
                .colorAccent));
        listAllApps = findViewById(R.id.lst_OtherApps);
    }


    /**
     * load system apps and filter the application for junkfood and normal.
     */
    private void loadApps() {
        List<String> installedPackageListLocal = CoreApplication.getInstance().getPackagesList();
        List<String> appList = new ArrayList<>();

        for (int i = 0; i < installedPackageListLocal.size(); i++) {
            appList.add(installedPackageListLocal.get(i));
        }
        installedPackageList = appList;
        bindData(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_junkfood_flagging, menu);
        MenuItem menuItem = menu.findItem(R.id.item_save);
        setTextColorForMenuItem(menuItem, R.color.colorAccent);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                junkFoodList.removeAll(list);
                PrefSiempo.getInstance(FavoritesSelectionActivity.this).write(PrefSiempo.FAVORITE_APPS, list);
                PrefSiempo.getInstance(FavoritesSelectionActivity.this).write(PrefSiempo.JUNKFOOD_APPS, junkFoodList);
                new LoadJunkFoodPane(FavoritesSelectionActivity.this).execute();
                new LoadFavoritePane(FavoritesSelectionActivity.this).execute();
                finish();
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


    /**
     * change text color of menuitem
     *
     * @param menuItem
     * @param color
     */
    private void setTextColorForMenuItem(MenuItem menuItem, @ColorRes int color) {
        SpannableString spanString = new SpannableString(menuItem.getTitle().toString());
        spanString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, color)), 0, spanString.length(), 0);
        menuItem.setTitle(spanString);
    }

    /**
     * bind the list view of flag app and all apps.
     */
    private void bindData(boolean isNotify) {
        try {
            favoriteList = new ArrayList<>();
            unfavoriteList = new ArrayList<>();
            bindingList = new ArrayList<>();

            for (String resolveInfo : installedPackageList) {
                if (!resolveInfo.equalsIgnoreCase(getPackageName())) {
                    boolean isEnable = UIUtils.isAppInstalledAndEnabled(this, resolveInfo);
                    if (isEnable) {
                        if (list.contains(resolveInfo)) {
                            favoriteList.add(new AppListInfo(resolveInfo, false, false, true));
                        } else {
                            if (null != junkFoodList && !junkFoodList
                                    .contains(resolveInfo)) {
                                unfavoriteList.add(new AppListInfo(resolveInfo, false, false, false));
                            }
                        }
                    }
                }
            }
            setToolBarText(favoriteList.size());
            if (favoriteList.size() == 0) {
                favoriteList.add(new AppListInfo("", true, true, true));
            } else {
                favoriteList.add(0, new AppListInfo("", true, false, true));
            }
            favoriteList = Sorting.sortApplication(favoriteList);
            bindingList.addAll(favoriteList);

            if (unfavoriteList.size() == 0) {
                unfavoriteList.add(new AppListInfo("", true, true, false));
            } else {
                unfavoriteList.add(0, new AppListInfo("", true, false, false));
            }
            unfavoriteList = Sorting.sortApplication(unfavoriteList);
            bindingList.addAll(unfavoriteList);
            junkfoodFlaggingAdapter = new FavoriteFlaggingAdapter(this, bindingList, list);
            listAllApps.setAdapter(junkfoodFlaggingAdapter);
            listAllApps.setOnItemClickListener(this);
            if (isNotify) {
                junkfoodFlaggingAdapter.notifyDataSetChanged();
                listAllApps.setSelection(firstPosition);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * show pop dialog on List item click for flag/un-flag and application information.
     *
     * @param view
     * @param position
     */
    private void showPopUp(View view, final boolean isFlagApp, final int position) {

        if (popup != null) {
            popup.dismiss();
        }
        popup = new PopupMenu(FavoritesSelectionActivity.this, view, Gravity.END);
        popup.getMenuInflater().inflate(R.menu.junkfood_popup, popup.getMenu());
        MenuItem menuItem = popup.getMenu().findItem(R.id.item_Unflag);
        if (isFlagApp) {
            if (favoriteList
                    .size() == 2) {
                menuItem.setVisible(false);
            } else {
                menuItem.setVisible(true);
            }
        } else {
            if (favoriteList != null && (favoriteList.size() < 13)) {
                menuItem.setVisible(true);
            } else {
                menuItem.setVisible(false);
            }
        }
        menuItem.setTitle(isFlagApp ? getString(R.string.favorite_menu_unselect) : getString(R.string.favorite_menu_select));
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.item_Unflag) {
                    try {
                        popup.dismiss();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (list.contains(bindingList.get(position).packageName)) {
                                    list.remove(bindingList.get(position).packageName);
                                    //get the JSON array of the ordered of sorted customers
                                    String jsonListOfSortedFavorites = PrefSiempo.getInstance(FavoritesSelectionActivity.this).read(PrefSiempo.FAVORITE_SORTED_MENU, "");
                                    //convert onNoteListChangedJSON array into a List<Long>
                                    Gson gson1 = new Gson();
                                    List<String> listOfSortFavoritesApps = gson1.fromJson(jsonListOfSortedFavorites, new TypeToken<List<String>>() {
                                    }.getType());

                                    for (ListIterator<String> it =
                                         listOfSortFavoritesApps.listIterator(); it.hasNext
                                            (); ) {
                                        String packageName = it.next();
                                        if (bindingList.get(position).packageName.equalsIgnoreCase(packageName)) {
                                            //Used List Iterator to set empty
                                            // value for package name retaining
                                            // the positions of elements
                                            it.set("");
                                        }
                                    }

                                    Gson gson2 = new Gson();
                                    String jsonListOfFavoriteApps = gson2.toJson(listOfSortFavoritesApps);
                                    PrefSiempo.getInstance(FavoritesSelectionActivity.this).write(PrefSiempo.FAVORITE_SORTED_MENU, jsonListOfFavoriteApps);
                                    isLoadFirstTime = false;
                                    //setToolBarText(favoriteList.size());
                                } else {

                                    if (favoriteList != null && favoriteList.size() < 13) {
                                        list.add(bindingList.get(position).packageName);
                                        isLoadFirstTime = false;
                                    }
                                    // setToolBarText(favoriteList.size());
                                }
                                firstPosition = listAllApps.getFirstVisiblePosition();
//                                bindData(true);
                                new FilterApps(true).execute();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else if (item.getItemId() == R.id.item_Info) {
                    try {
                        PackageUtil.appSettings(FavoritesSelectionActivity.this, bindingList.get(position).packageName);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });

        popup.show();
        popup.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
                popup = null;
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        startTime = System.currentTimeMillis();
        loadApps();
    }

    @Override
    protected void onPause() {
        super.onPause();
        FirebaseHelper.getInstance().logScreenUsageTime(this.getClass().getSimpleName(), startTime);
    }

    public void setToolBarText(int count) {
        int remainapps = 12 - count;
        toolbar.setTitle("Select up to " + remainapps + " more apps");
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            if (popup != null) {
                popup.dismiss();
            }
        }
    }

    class FilterApps extends AsyncTask<String, String, ArrayList<AppListInfo>> {
        boolean isNotify;
        int size;

        FilterApps(boolean isNotify) {
            this.isNotify = isNotify;
            listAllApps.setOnItemClickListener(null);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            isClickOnView = false;
            favoriteList = new ArrayList<>();
            unfavoriteList = new ArrayList<>();
            bindingList = new ArrayList<>();
        }

        @Override
        protected ArrayList<AppListInfo> doInBackground(String... strings) {
            for (String resolveInfo : installedPackageList) {
                if (!resolveInfo.equalsIgnoreCase(getPackageName())) {
                    boolean isEnable = UIUtils.isAppInstalledAndEnabled(FavoritesSelectionActivity.this, resolveInfo);
                    if (isEnable) {
                        if (list.contains(resolveInfo)) {
                            favoriteList.add(new AppListInfo(resolveInfo, false, false, true));
                        } else {
                            if (null != junkFoodList && !junkFoodList
                                    .contains(resolveInfo)) {
                                unfavoriteList.add(new AppListInfo(resolveInfo, false, false, false));
                            }
                        }
                    }
                }
            }
            size = favoriteList.size();

            if (favoriteList.size() == 0) {
                favoriteList.add(new AppListInfo("", true, true, true));
            } else {
                favoriteList.add(0, new AppListInfo("", true, false, true));
            }
            favoriteList = Sorting.sortApplication(favoriteList);
            bindingList.addAll(favoriteList);

            if (unfavoriteList.size() == 0) {
                unfavoriteList.add(new AppListInfo("", true, true, false));
            } else {
                unfavoriteList.add(0, new AppListInfo("", true, false, false));
            }
            unfavoriteList = Sorting.sortApplication(unfavoriteList);
            bindingList.addAll(unfavoriteList);
            return bindingList;
        }

        @Override
        protected void onPostExecute(ArrayList<AppListInfo> s) {
            super.onPostExecute(s);
            try {
                if (toolbar != null) {
                    setToolBarText(size);
                    junkfoodFlaggingAdapter = new FavoriteFlaggingAdapter(FavoritesSelectionActivity.this, bindingList, list);
                    listAllApps.setAdapter(junkfoodFlaggingAdapter);
                    listAllApps.setOnItemClickListener(FavoritesSelectionActivity.this);
                    if (isNotify) {
                        junkfoodFlaggingAdapter.notifyDataSetChanged();
                        listAllApps.setSelection(firstPosition);
                    }
                }
                isClickOnView = true;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}

