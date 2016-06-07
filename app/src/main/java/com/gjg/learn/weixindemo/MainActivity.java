package com.gjg.learn.weixindemo;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener , ViewPager.OnPageChangeListener{
    private ViewPager mViewPager;
    private String[] titles=new String[]{"first page","second page","third page","fourth page"};
    private List<Fragment> lists=new ArrayList<Fragment>();
    private FragmentPagerAdapter fragmentPagerAdapter;
    private List<CustomView> mTabIndicators = new ArrayList<CustomView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if((Build.VERSION.SDK_INT >= 10) & (Build.VERSION.SDK_INT < 21)) {
            setOverflowButtonAlways();
        }
        //不显示actionbar上的图标
        getActionBar().setDisplayShowHomeEnabled(false);
        initView();

        mViewPager= (ViewPager) this.findViewById(R.id.viewpager);
        for(String str : titles){
            TabFragment tabFragment=new TabFragment();
            Bundle bundle=new Bundle();
            bundle.putCharSequence(TabFragment.TITLE,str);
            tabFragment.setArguments(bundle);
            lists.add(tabFragment);

        }
        fragmentPagerAdapter=new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return lists.get(position);
            }

            @Override
            public int getCount() {
                return lists.size();
            }
        };
        mViewPager.setAdapter(fragmentPagerAdapter);
        mViewPager.setOnPageChangeListener(this);

    }

    private void initView()
    {


        CustomView one = (CustomView) findViewById(R.id.id_one);
        mTabIndicators.add(one);
        CustomView two = (CustomView) findViewById(R.id.id_two);
        mTabIndicators.add(two);
        CustomView three = (CustomView) findViewById(R.id.id_three);
        mTabIndicators.add(three);
        CustomView four = (CustomView) findViewById(R.id.id_four);
        mTabIndicators.add(four);

        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);

        one.setIconAlpha(1.0f);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tab_menu,menu);
        return true;
    }

    //设置overflow按钮总是显示在actionbar
    private void setOverflowButtonAlways()
    {
        try
        {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKey = ViewConfiguration.class
                    .getDeclaredField("sHasPermanentMenuKey");
            menuKey.setAccessible(true);
            menuKey.setBoolean(config, false);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        clickTab(v);

    }

    /**
     * 点击Tab按钮
     *
     * @param v
     */
    private void clickTab(View v)
    {
        resetOtherTabs();

        switch (v.getId())
        {
            case R.id.id_one:
                mTabIndicators.get(0).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(0, false);
                break;
            case R.id.id_two:
                mTabIndicators.get(1).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(1, false);
                break;
            case R.id.id_three:
                mTabIndicators.get(2).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(2, false);
                break;
            case R.id.id_four:
                mTabIndicators.get(3).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(3, false);
                break;
        }
    }

    /**
     * 设置menu显示icon
     */
    @Override
    public boolean onMenuOpened(int featureId, Menu menu)
    {

        if (featureId == Window.FEATURE_ACTION_BAR && menu != null)
        {
            if (menu.getClass().getSimpleName().equals("MenuBuilder"))
            {
                try
                {
                    Method m = menu.getClass().getDeclaredMethod(
                            "setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }

        return super.onMenuOpened(featureId, menu);
    }

    /**
     * 重置其他的TabIndicator的颜色
     */
    private void resetOtherTabs()
    {
        for (int i = 0; i < mTabIndicators.size(); i++)
        {
            mTabIndicators.get(i).setIconAlpha(0);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset,
                               int positionOffsetPixels)
    {
        // Log.e("TAG", "position = " + position + " ,positionOffset =  "
        // + positionOffset);
        if (positionOffset > 0)
        {
            CustomView left = mTabIndicators.get(position);
            CustomView right = mTabIndicators.get(position + 1);
            left.setIconAlpha(1 - positionOffset);
            right.setIconAlpha(positionOffset);
        }

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
