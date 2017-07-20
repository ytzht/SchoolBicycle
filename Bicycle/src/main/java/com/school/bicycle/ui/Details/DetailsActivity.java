package com.school.bicycle.ui.Details;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import com.school.bicycle.R;
import com.school.bicycle.fragment.Consumption_fragment;
import com.school.bicycle.fragment.Share_fragment;
import com.school.bicycle.global.BaseToolBarActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends BaseToolBarActivity {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        setToolbarText("明细");
        initview();
    }

    private void initview() {

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            private String[] mTitles = new String[]{"消费明细", "共享明细"};
            @Override
            public Fragment getItem(int position) {

                if (position == 1) {
                    return new Share_fragment();
                }
                return new Consumption_fragment();
            }

            @Override
            public int getCount() {
                return mTitles.length;
            }


            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles[position];
            }
        });

        tabLayout.setupWithViewPager(viewPager);


    }
}