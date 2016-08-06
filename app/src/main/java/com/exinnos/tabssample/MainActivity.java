package com.exinnos.tabssample;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements UserProfileFragment.OnUserProfileFragmentListener, EditUserProfileFragment.OnEditUserProfileFragmentListener {

    private ViewPager homeViewPager;
    private UserProfileContainerFragment userProfileContainerFragment;
    private TabLayout homeTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeViewPager = (ViewPager) findViewById(R.id.home_view_pager);

        HomeViewPagerAdapter homeViewPagerAdapter = new HomeViewPagerAdapter(getSupportFragmentManager());
        userProfileContainerFragment = new UserProfileContainerFragment();
        homeViewPagerAdapter.addFragment(new UserProfileContainerFragment(), "T1");
        homeViewPagerAdapter.addFragment(new Fragment1(), "T2");
        homeViewPagerAdapter.addFragment(new Fragment2(), "T3");
        homeViewPagerAdapter.addFragment(new Fragment3(), "T4");
        homeViewPager.setAdapter(homeViewPagerAdapter);

        homeTabLayout = (TabLayout) findViewById(R.id.home_tabs);
        homeTabLayout.setupWithViewPager(homeViewPager);


        homeTabLayout.getTabAt(0).select();
    }

    @Override
    public void onUserProfileEditButtonClicked() {
        userProfileContainerFragment.replaceFragment(EditUserProfileFragment.newInstance(), true);
    }

    @Override
    public void onUserProfileSaveButtonClicked() {

    }

    private class HomeViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public HomeViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
    }
}
