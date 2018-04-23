package youssef.com.alex_commercial.forthyear;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;

import youssef.com.alex_commercial.R;

/**
 * Created by mohamed on 01/04/2018.
 */

public class Fouryear extends android.support.v4.app.Fragment {


    TabLayout tabLayout;
    ViewPager pager;
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return     inflater.inflate(R.layout.fyear,container,false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        ImageView img=(ImageView)view.findViewById(R.id.sectionview);
        String photo="http://shbabbek.com/upload/20160918194013_107606_SHJ.jpg";
        Glide.with(this)
                .load(photo)
                .into(img);

        progressBar=(ProgressBar)view.findViewById(R.id.waitprogressbar);

        tabLayout=(TabLayout)view.findViewById(R.id.tab);
        pager=(ViewPager)view.findViewById(R.id.pagerv);
        adapter myadapter=new adapter(getChildFragmentManager());

        pager.setAdapter(myadapter);
        tabLayout.setTabsFromPagerAdapter(myadapter);
        tabLayout.setupWithViewPager(pager);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }

    class adapter extends FragmentStatePagerAdapter{


        public adapter(FragmentManager fm){
            super(fm);

            fm.beginTransaction().addToBackStack("Firstyear").commit();
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {

            android.support.v4.app.Fragment fragment;
            if (position==0) {


                return fragment = new termone();
            }
            if  (position==1)
                return fragment=new termseond();

            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            if (position==0)
                return getResources().getString(R.string.term1);
            if (position==1)
                return getResources().getString(R.string.term2);

            return null;

        }

    }
}
