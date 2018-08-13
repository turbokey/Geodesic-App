package com.nesterov.geodesicapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

public class ThreePageFragment extends Fragment implements YourFragmentInterface {
    ViewGroup rootView2;
    private ListView listView;

    public static ThreePageFragment newInstance(int page, String title) {
        ThreePageFragment fragmentThree = new ThreePageFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentThree.setArguments(args);
        return fragmentThree;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_screen_three, container, false);
        rootView2 = rootView;
        return rootView;
    }
    public void initFileList()
    {
        SharedPreferences prefs = getContext().getSharedPreferences("app_state", Context.MODE_PRIVATE);
        Boolean notAskForPerm = prefs.getBoolean("NotAskForGrantPerm", false);
        if (!notAskForPerm)
        {
            File folder = new File(Environment.getExternalStorageDirectory() + getString(R.string.dir_name));
            if (!folder.exists()) {
                folder.mkdir();
            }
            String path = Environment.getExternalStorageDirectory().toString() + getString(R.string.dir_name);

            File f = new File(path);
            File file[] = f.listFiles();
            ArrayList<String> FilesList = new ArrayList<>();
            if (file != null)
                for (int i = 0; i < file.length; i++)
                    FilesList.add(file[i].getName());

            listView = (ListView) rootView2.findViewById(R.id.listView);
            MyCustomAdapter adapter = new MyCustomAdapter(FilesList, getContext());
            listView.setAdapter(adapter);
        }
    }

    @Override
    public void fragmentBecameVisible() {
        initFileList();
    }


}
