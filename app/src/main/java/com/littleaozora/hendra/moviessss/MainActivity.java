package com.littleaozora.hendra.moviessss;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Testing GitLab
        //Testing GitLab2

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setLogo(R.mipmap.ic_launcher);
        //toolbar.setTitle("");
        setSupportActionBar(toolbar);


        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        //PagerAdapter pagerAdapter = PagerAdapter(getSupportFragmentManager(), MainActivity.this);
        //PagerAdapter pagerAdapter = PagerAdapter(getSupportFragmentManager(), MainActivity.this);

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), MainActivity.this);

        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i<tabLayout.getTabCount(); i++){
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(pagerAdapter.getTabView(i));

        }

    }

    @Override
    public void onResume() {super.onResume();}


    class PagerAdapter extends FragmentPagerAdapter{
        String tabTitles[] = new String[] {"Now Playing", "Popular", "Upcoming"};

        Context context;

        public PagerAdapter(FragmentManager fm, Context context){
            super(fm);
            this.context = context;
        }


        //Override
        public int getCount(){return  tabTitles.length; }

        @Override
        public Fragment getItem(int position){
            switch (position){
                case 0:
                    return new NowPlayingFragment();
                case 1:
                    return new PopularFragment();
                case 2:
                    return new UpcomingFragment();
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position){
            return tabTitles[position];
        }

        public View getTabView(int position){
            View tab = LayoutInflater.from(MainActivity.this).inflate(R.layout.custom_tab, null);
            TextView tv = (TextView) tab.findViewById(R.id.custom_text);
            tv.setText(tabTitles[position]);
            return  tab;
        }

    }


}
