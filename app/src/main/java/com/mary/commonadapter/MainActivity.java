package com.mary.commonadapter;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.mary.commonadapter.fragment.MutliItemTypeFragment;
import com.mary.commonadapter.fragment.NestingTypeFragment;
import com.mary.commonadapter.fragment.SingleItemTypeFragment;

public class MainActivity extends FragmentActivity {

    TabLayout mTabLayout;
    ViewPager mViewPager;

    private final int TAB_COUNT = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabLayout = (TabLayout) findViewById(R.id.id_tablayout);
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                if (i == 1) return new MutliItemTypeFragment();
                else if (i == 0)
                    return new SingleItemTypeFragment();
                else
                    return new NestingTypeFragment();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "Single Item Type";
                    case 1:
                        return "Mutli Item Type";
                    case 2:
                        return "Nesting Item Type";
                }
                return "Helloworld";
            }

            @Override
            public int getCount()
            {
                return TAB_COUNT;
            }
        });


        mTabLayout.setupWithViewPager(mViewPager);
    }
}
