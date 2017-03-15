package co.minium.launcher3.mm;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import co.minium.launcher3.R;
import minium.co.core.ui.CoreActivity;

/**
 * Created by tkb on 2017-03-14.
 */

public class MindfulMorningList  extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mm_list, parent, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        ListView listView = (ListView)view.findViewById(R.id.activity_list_view);
        MindfulMorningListAdapter mindfulMorningListAdapter = new MindfulMorningListAdapter(getActivity(),new ActivitiesModel().getActivityModel2());
        listView.setAdapter(mindfulMorningListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String [] title = {"Meditation","Workout","Reading"};
                ((CoreActivity)getActivity()).loadChildFragment(MindfulMorningListDetails_.builder().title(title[i]).build(),R.id.mainView);

            }
        });

        ImageView crossActionBar = (ImageView) view.findViewById(R.id.crossActionBar);
        crossActionBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }
}
