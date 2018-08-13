package com.nesterov.geodesicapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class FileViewActivity extends AppCompatActivity {
    ArrayList<String[]> line = new ArrayList<>();
    String mode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.table_layout);
        Intent myIntent = getIntent();
        mode = myIntent.getStringExtra("Type");
        int size = myIntent.getIntExtra("size",0);
        for (int i = 0; i < size; i++)
            line.add(myIntent.getStringArrayExtra("line " + i));

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.app_name));
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
    }

    public void init() {
        TableLayout stk = (TableLayout) findViewById(R.id.maintable);
        stk.removeAllViews();
        TableRow tbrow0 = new TableRow(this);
        for (int i = 0; i < line.size(); i++) {
            TableRow tbrow = new TableRow(this);
            TextView t1v = new TextView(this);
            t1v.setText("X: "+line.get(i)[0] + getString(R.string.meter) + "\n" + "Y: "+line.get(i)[1] + getString(R.string.meter) + "\n" + "H: "+line.get(i)[2] + getString(R.string.meter));
            t1v.setTextColor(Color.BLACK);
            t1v.setBackgroundColor(Color.WHITE);
            TableRow.LayoutParams tb = new TableRow.LayoutParams((int)getResources().getDimension(R.dimen.table_width), TableRow.LayoutParams.WRAP_CONTENT, 1f);
            tb.setMargins((int)getResources().getDimension(R.dimen.table_margin),(int)getResources().getDimension(R.dimen.table_margin),
                    (int)getResources().getDimension(R.dimen.table_margin),(int)getResources().getDimension(R.dimen.table_margin));
            t1v.setLayoutParams(tb);
            tbrow.addView(t1v);
            TextView t2v = new TextView(this);
            t2v.setText("X: "+line.get(i)[3] + getString(R.string.meter) + "\n" + "Y: "+line.get(i)[4] + getString(R.string.meter) + "\n" + "H: "+line.get(i)[5] + getString(R.string.meter));
            t2v.setTextColor(Color.BLACK);
            t2v.setBackgroundColor(Color.WHITE);
            t2v.setLayoutParams(tb);
            tbrow.addView(t2v);
            TextView t3v = new TextView(this);
            if (mode.equals("coords"))
                t3v.setText("a: " + line.get(i)[6] + getString(R.string.meter) + "\n" + "b: " + line.get(i)[7] + getString(R.string.meter) + "\n" + "h: " + line.get(i)[8] + getString(R.string.meter));
            else
                t3v.setText("X: " + line.get(i)[6] + getString(R.string.meter) + "\n" + "Y: " + line.get(i)[7] + getString(R.string.meter) + "\n" + "H: " + line.get(i)[8] + getString(R.string.meter));
            t3v.setTextColor(Color.BLACK);
            t3v.setBackgroundColor(Color.WHITE);
            t3v.setLayoutParams(tb);
            tbrow.addView(t3v);
            TextView t4v = new TextView(this);
            if (mode.equals("coords"))
                t4v.setText("X: " + line.get(i)[9] + getString(R.string.meter) + "\n" + "Y: " + line.get(i)[10] + getString(R.string.meter) + "\n" + "H: " + line.get(i)[11] + getString(R.string.meter));
            else
                t4v.setText("a: " + line.get(i)[9] + getString(R.string.meter) + "\n" + "b: " + line.get(i)[10] + getString(R.string.meter) + "\n" + line.get(i)[11] + getString(R.string.meter));
            t4v.setTextColor(Color.BLACK);
            t4v.setBackgroundColor(Color.WHITE);
            TableRow.LayoutParams tb4 = new TableRow.LayoutParams((int)getResources().getDimension(R.dimen.table_width), TableRow.LayoutParams.WRAP_CONTENT, 1.3f);
            tb4.setMargins((int)getResources().getDimension(R.dimen.table_margin),(int)getResources().getDimension(R.dimen.table_margin),
                    (int)getResources().getDimension(R.dimen.table_margin),(int)getResources().getDimension(R.dimen.table_margin));
            t4v.setLayoutParams(tb4);
            tbrow.addView(t4v);
            stk.addView(tbrow);
        }
    }
}
