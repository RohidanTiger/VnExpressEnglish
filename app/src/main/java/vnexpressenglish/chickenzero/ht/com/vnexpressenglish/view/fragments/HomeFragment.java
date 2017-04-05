package vnexpressenglish.chickenzero.ht.com.vnexpressenglish.view.fragments;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import vnexpressenglish.chickenzero.ht.com.vnexpressenglish.BaseFragment;
import vnexpressenglish.chickenzero.ht.com.vnexpressenglish.R;

/**
 * Created by QuyDV on 4/5/17.
 */

public class HomeFragment extends BaseFragment {

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    private ViewPagerAdapter adapter;

    @Override
    protected int getViewContent() {
        return R.layout.home_fragment;
    }

    @Override
    protected void initUI() {
        adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new NewsFragment(), "New Movie");
        adapter.addFragment(new PhotosFragment(),"New Feeds");
        adapter.addFragment(new PhotosFragment(), "New Songs");
        adapter.addFragment(new IeltsFragment(), "New Haha");

        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

        viewPager.setOffscreenPageLimit(3);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            if(position==0){
                return new NewsFragment();
            }
            if(position==1){
                return new PhotosFragment();
            }
            if(position==2){
                return new PhotosFragment();
            }
            if(position==3){
                return new IeltsFragment();
            }
            return null;
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
}
