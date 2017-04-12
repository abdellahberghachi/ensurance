package com.hao.e_nsurance.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toolbar;

import com.hao.e_nsurance.fragments.AutresInfosFragment;
import com.hao.e_nsurance.fragments.CirconstancesFragment;
import com.hao.e_nsurance.fragments.CroquisFragment;
import com.hao.e_nsurance.fragments.DegatsApparentsFragment;
import com.hao.e_nsurance.fragments.ObservationsFragment;
import com.hao.e_nsurance.fragments.PointChocFragment;
import com.hao.e_nsurance.R;

/**
 * Created by user on 11/03/2017.
 */

public class ConstatActivity extends FragmentActivity {

    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 6;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

    ProgressBar progressBar;
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.constat_viewpager);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        mPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });


        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setActionBar(myToolbar);
        /*getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowHomeEnabled(true);*/

        //mPager.setPageTransformer(true, new ZoomOutPageTransformer());

        progressBar = (ProgressBar) findViewById(R.id.progress);

        mPager.setCurrentItem(0);
    }

    @Override
    public void onBackPressed() {

        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            //super.onBackPressed();
            new AlertDialog.Builder(this)
                    .setTitle(R.string.annuler_constat)
                    .setMessage(R.string.etes_vous_sur_annuler_constat)
                    .setPositiveButton(R.string.oui, new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }

                    })
                    .setNegativeButton(R.string.reprendre, null)
                    .show();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
            percentage(mPager.getCurrentItem());
        }
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            Fragment f = null;
            switch (position) {
                case 0:
                    f = new AutresInfosFragment();
                    break;
                case 1:
                    f = new PointChocFragment();
                    break;
                case 2:
                    f = new DegatsApparentsFragment();
                    break;
                case 3:
                    f = new CirconstancesFragment();
                    break;
                case 4:
                    f = new ObservationsFragment();
                    break;
                case 5:
                    f = new CroquisFragment();
                    break;
            }
            return f;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.constat_menu, menu);
        this.menu = menu;
        showingButtons();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.precedent) {
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
            percentage(mPager.getCurrentItem());
            return true;
        }
        if (id == R.id.suivant) {
            mPager.setCurrentItem(mPager.getCurrentItem() + 1);
            percentage(mPager.getCurrentItem());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void percentage(int currentPage){
        switch (currentPage){
            case 0:
                progressBar.setProgress(16);
                break;
            case 1:
                progressBar.setProgress(32);
                break;
            case 2:
                progressBar.setProgress(48);
                break;
            case 3:
                progressBar.setProgress(68);
                break;
            case 4:
                progressBar.setProgress(84);
                break;
            case 5:
                progressBar.setProgress(100);
                break;
        }
        showingButtons();
    }

    public void showingButtons() {
        if(mPager.getCurrentItem() == 0)
            menu.getItem(0).setVisible(false);
        else
            menu.getItem(0).setVisible(true);

        if(mPager.getCurrentItem() == 5)
            menu.getItem(1).setTitle(R.string.terminer);
        else
            menu.getItem(1).setTitle(R.string.suivant);
    }
}
