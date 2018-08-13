package com.nesterov.geodesicapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MyCustomAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<String> list = new ArrayList<String>();
    private Context context;
    private ProgressDialog LP;

    public MyCustomAdapter(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
        //just return 0 if your list items do not have an Id variable.
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.file_list_layout, null);
        }

        //Handle TextView and display string from your list
        TextView listItemText = (TextView)view.findViewById(R.id.list_item_string);
        listItemText.setText(list.get(position));

        //Handle buttons and add onClickListeners
        final ImageButton deleteBtn = (ImageButton) view.findViewById(R.id.delete_btn);
        ImageButton addBtn = (ImageButton) view.findViewById(R.id.edit_btn);

        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                String path = Environment.getExternalStorageDirectory().toString()+ context.getString(R.string.dir_name);
                File f = new File(path);
                File file[] = f.listFiles();
                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(context);
                dlgAlert.setTitle(context.getString(R.string.delete_alert_title));
                dlgAlert.setMessage(context.getString(R.string.delete_alert, file[position].getName()));
                dlgAlert.setPositiveButton(context.getString(R.string.positive_answer),
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int which){
                                list.remove(position); //or some other task
                                deleteFile(position);
                                notifyDataSetChanged();
                            }
                        });
                dlgAlert.setNegativeButton(context.getString(R.string.cansel_answer),
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int which){

                            }
                        });
                dlgAlert.setCancelable(false);
                dlgAlert.create().show();
            }
        });
        addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showProgress("Загрузка");
                ArrayList<String[]> list = new ArrayList<String[]>();
                String path = Environment.getExternalStorageDirectory().toString()+ context.getString(R.string.dir_name);
                File f = new File(path);
                File file[] = f.listFiles();

                if(!loadFile(file[position], list))
                {
                    Toast.makeText(context, context.getString(R.string.open_file_error), Toast.LENGTH_LONG).show();
                    hideProgress();
                    return;
                }

                if (list != null) {
                    Intent myIntent = new Intent(context, FileViewActivity.class);
                    myIntent.putExtra("size", list.size());
                    for (int i = 0; i < list.size(); i++)
                        myIntent.putExtra("line "+i,list.get(i));
                    if (getFileType(file[position]) != null)
                        if (getFileType(file[position]).equals(context.getString(R.string.coords)))
                            myIntent.putExtra("Type", "coords");
                        else
                            myIntent.putExtra("Type", "offset");
                    context.startActivity(myIntent);
                }
                else
                    Toast.makeText(context, context.getString(R.string.open_file_error), Toast.LENGTH_LONG).show();

                notifyDataSetChanged();
                hideProgress();
            }
        });

        return view;
    }
    public void deleteFile(int num)
    {
        File folder = new File(Environment.getExternalStorageDirectory() + context.getString(R.string.dir_name));
        if (!folder.exists()) {
            folder.mkdir();
        }
        String path = Environment.getExternalStorageDirectory().toString()+ context.getString(R.string.dir_name);
        File f = new File(path);
        File file[] = f.listFiles();
        file[num].delete();
    }

    public boolean loadFile(File file, ArrayList<String[]> lines)
    {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
            return false;
        File sdPath = Environment.getExternalStorageDirectory();
        sdPath = new File(sdPath.getAbsolutePath() + context.getString(R.string.dir_name));
        File sdFile = file;
        try {
            BufferedReader br = new BufferedReader(new FileReader(sdFile));
            String str = "";
            br.readLine();
            while ((str = br.readLine()) != null) {
                try {
                    String[] separated = str.split(";");
                    lines.add(separated);
                }catch (Exception e)
                {
                    return false;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    private void  showProgress(String text){
        if (LP == null){
            try{
                LP = ProgressDialog.show(context, "", text);
                LP.setCancelable(false);
            } catch (Exception e){

            }
        }
    }
    private void hideProgress(){
        if(LP != null){
            LP.dismiss();
            LP = null;
        }
    }
    public String getFileType(File file)
    {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str = br.readLine();
            if (str != null)
                return str;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}