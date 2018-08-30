package view;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.example.smile.nytimes.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        setupViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    private void setupTabIcons() {
        TextView tabEmailed = (TextView) LayoutInflater.from(this).inflate(R.layout.news_tab,null);
        tabEmailed.setText("Emailed");
        tabEmailed.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_email,0,0);
        tabLayout.getTabAt(0).setCustomView(tabEmailed);

        TextView tabShared = (TextView) LayoutInflater.from(this).inflate(R.layout.news_tab,null);
        tabShared.setText("Shared");
        tabShared.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_share,0,0);
        tabLayout.getTabAt(1).setCustomView(tabShared);

        TextView tabViewed = (TextView) LayoutInflater.from(this).inflate(R.layout.news_tab,null);
        tabViewed.setText("Viewed");
        tabViewed.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_visibility,0,0);
        tabLayout.getTabAt(2).setCustomView(tabViewed);

        TextView tabFavorite = (TextView) LayoutInflater.from(this).inflate(R.layout.news_tab,null);
        tabFavorite.setText("Favorite");
        tabFavorite.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_favorite,0,0);
        tabLayout.getTabAt(3).setCustomView(tabFavorite);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new EmailedFragment(),"News 1");
        viewPagerAdapter.addFragment(new SharedFragment(),"News 2");
        viewPagerAdapter.addFragment(new ViewedFragment(),"News 3");
        viewPagerAdapter.addFragment(new FavoriteFragment(),"News 4");
        viewPager.setAdapter(viewPagerAdapter);
    }
}

class ViewPagerAdapter extends FragmentPagerAdapter{
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
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

    public void addFragment(Fragment fragment, String title){
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}
