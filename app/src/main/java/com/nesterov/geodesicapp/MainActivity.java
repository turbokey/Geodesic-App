package com.nesterov.geodesicapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnePageFragment.SendMessage {

    public ViewPager vpPager;
    MyPagerAdapter adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vpPager = (ViewPager) findViewById(R.id.pager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        adapterViewPager.addFragment(new OnePageFragment(), getString(R.string.points_A_and_B));
        adapterViewPager.addFragment(new TwoPageFragment(), getString(R.string.point_C));

        SharedPreferences prefs = getSharedPreferences("app_state", Context.MODE_PRIVATE);
        Boolean notAskForPerm = prefs.getBoolean("NotAskForGrantPerm", false);
        if (!notAskForPerm)
            adapterViewPager.addFragment(new ThreePageFragment(), getString(R.string.savings));

        vpPager.setAdapter(adapterViewPager);
        vpPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int i, final float v, final int i2) {
            }

            @Override
            public void onPageSelected(final int i) {
                YourFragmentInterface fragment = (YourFragmentInterface) adapterViewPager.instantiateItem(vpPager, i);
                if (fragment != null) {
                    fragment.fragmentBecameVisible();
                }
            }

            @Override
            public void onPageScrollStateChanged(final int i) {
            }
        });
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(vpPager);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.question:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(this.getString(R.string.instruction));
                builder.setPositiveButton(this.getString(R.string.positive_answer), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // You don't have to do anything here if you just want it dismissed when clicked
                    }
                });

                // Create the AlertDialog object and return it
                builder.create().show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        if (vpPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            vpPager.setCurrentItem(vpPager.getCurrentItem() - 1, true);
        }
    }

    @Override
    public void sendData(String message) {
        String tag = "android:switcher:" + R.id.pager + ":" + 1;
        TwoPageFragment f = (TwoPageFragment) getSupportFragmentManager().findFragmentByTag(tag);
        f.displayReceivedData(message);
    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public MyPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

        public ViewPager getViewPager() {
            if (null == vpPager) {
                vpPager = (ViewPager) findViewById(R.id.pager);
            }
            return vpPager;
        }
    }

