package com.vagrant.usefultest.activity;

import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.vagrant.usefultest.R;
import com.vagrant.usefultest.adapter.DrawerMenuItemAdapter;
import com.vagrant.usefultest.base.BaseActivity;
import com.vagrant.usefultest.fragment.BlankFragment;
import com.vagrant.usefultest.fragment.FirstFragment;
import com.vagrant.usefultest.fragment.ForthFragment;
import com.vagrant.usefultest.fragment.SecondFragment;
import com.vagrant.usefultest.fragment.ThirdFragment;
import com.vagrant.usefultest.view.PagerSlidingTabStrip;

public class MainActivity extends BaseActivity implements
        OnCheckedChangeListener, OnQueryTextListener {

    private FirstFragment firstFragment;
    private SecondFragment secondFragment;
    private ThirdFragment thirdFragment;
    private ForthFragment forthFragment;

    private RadioGroup radioGroup;

    private ViewPager viewPager;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private SystemBarTintManager mTintManager;
    private Toolbar toolbar;
    //侧滑菜单DrawerLayou相关
    private String[] mDrawerMenuItem;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    //Action bar 左边按钮
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    private LinearLayout mDrawerMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//    http://stackoverflow.com/questions/4250149/requestfeature-must-be-called-before-adding-content
//    As said in comments, for both ActionBarSherlock and AppCompat library, it's necessary to call requestFeature() before super.onCreate()
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //侧滑
//        setContentView(R.layout.activity_main_custom_drawer);

        // create our manager instance after the content view is set
        mTintManager = new SystemBarTintManager(this);
        // enable status bar tint
        mTintManager.setStatusBarTintEnabled(true);
        // set a custom tint color for all system bars
        mTintManager.setTintColor(getResources().getColor(R.color.actionbar_background));
        fragmentManager = getSupportFragmentManager();

        // FragmentManager fragmentManager = getSupportFragmentManager();

        // fragmentTransaction = fragmentManager.beginTransaction();
        // fragmentTransaction.add(R.id.content, firstFragment);
        // fragmentTransaction.commit();

        initView();
        initActionBar();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        radioGroup = (RadioGroup) findViewById(R.id.tab_menu);
//        radioGroup.setOnCheckedChangeListener(this);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        MyFragmentPagerAdapter mAdapter = new MyFragmentPagerAdapter(
                getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);

////        initDrawer();
//        FirstFragment firstFragment = new FirstFragment();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.content_frame, firstFragment).commit();
        // Bind the tabs to the ViewPager
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setBackgroundColor(getResources().getColor(R.color.actionbar_background));
        tabs.setViewPager(viewPager);
        tabs.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // 改变底部菜单栏状态
//                RadioButton selectedButton = (RadioButton) radioGroup
//                        .getChildAt(position);
//                selectedButton.setChecked(true);
            }

            // 可通过这个方法实现tab图标的渐变效果
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset,
                        positionOffsetPixels);
            }
        });
    }

    //参照官方的实现
    // 参考： http://developer.android.com/training/implementing-navigation/nav-drawer.html
//    private void initDrawer() {
//        mTitle = mDrawerTitle = getTitle();
//        mDrawerMenuItem = getResources().getStringArray(R.array.drawer_title);
//        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        final MyDrawerListener drawerListener = new MyDrawerListener();
//        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open,R.string.drawer_close) {
//            /** Called when a drawer has settled in a completely open state. */
//            @Override
//            public void onDrawerOpened(View drawerView) {
//                super.onDrawerOpened(drawerView);
//                drawerListener.onDrawerOpened(drawerView);
//                getSupportActionBar().setTitle(mDrawerTitle);
//                invalidateOptionsMenu();// creates call to onPrepareOptionsMenu()
//            }
//            //这里可以做一些优化，参考：https://blog.newrelic.com/2014/07/28/drawerlayout/
//            /** Called when a drawer has settled in a completely closed state. */
//            @Override
//            public void onDrawerClosed(View drawerView) {
//                super.onDrawerClosed(drawerView);
//                drawerListener.onDrawerClosed(drawerView);
//                getSupportActionBar().setTitle(mTitle);
//                invalidateOptionsMenu();  // creates call to onPrepareOptionsMenu()
//            }
//        };
//        // set a custom shadow that overlays the main content when the drawer opens
//        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
//        //设置 ActionBarDrawerToggle 为 监听器，ActionBarDrawerToggle实现了DrawerLayout.DrawerListener接口
//        mDrawerLayout.setDrawerListener(mDrawerToggle);
//
//        mDrawerList = (ListView) findViewById(R.id.left_drawer);
//        mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, mDrawerMenuItem));
//        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
//    }

    // 参考：http://chenqichao.me/2014/12/08/108-Android-Toolbar-DrawerLayout-01/
    //自定义的侧滑菜单的初始化
    private void initDrawer() {
        mTitle = mDrawerTitle = getTitle();
        toolbar = (Toolbar) findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        final MyDrawerListener drawerListener = new MyDrawerListener();
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            /** Called when a drawer has settled in a completely open state. */
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
//                drawerListener.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu();// creates call to onPrepareOptionsMenu()
            }
            //这里可以做一些优化，参考：https://blog.newrelic.com/2014/07/28/drawerlayout/

            /** Called when a drawer has settled in a completely closed state. */
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
//                drawerListener.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu();  // creates call to onPrepareOptionsMenu()
            }
        };
        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        //设置 ActionBarDrawerToggle 为 监听器，ActionBarDrawerToggle实现了DrawerLayout.DrawerListener接口
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerMenu = (LinearLayout) findViewById(R.id.drawer_menu);
        mDrawerList = (ListView) findViewById(R.id.drawer_menu_list);

        mDrawerList.setAdapter(new DrawerMenuItemAdapter(this));
        mDrawerList.setOnItemClickListener(new MyDrawerItemClickListener());
    }

    @SuppressWarnings("deprecation")
    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
//        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);

        Drawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.actionbar_background));
        actionBar.setBackgroundDrawable(colorDrawable);
//        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        // actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        //
        // SpinnerAdapter spinnerAdapter = ArrayAdapter.createFromResource(this,
        // R.array.drop_menu, android.R.layout.simple_spinner_dropdown_item);
        // OnNavigationListener mOnNavigationListener = new
        // OnNavigationListener() {
        // @Override
        // public boolean onNavigationItemSelected(int itemPosition, long
        // itemId) {
        // Toast.makeText(MainActivity.this, R.string.drop_menu_tip,
        // Toast.LENGTH_SHORT).show();
        // return true;
        // }
        // };
        // actionBar.setListNavigationCallbacks(spinnerAdapter,
        // mOnNavigationListener);

        ActionBar.TabListener tabListener = new TabListener() {

            @Override
            public void onTabUnselected(Tab tab,
                                        FragmentTransaction ft) {
            }

            @Override
            public void onTabSelected(Tab tab,
                                      FragmentTransaction ft) {
                viewPager.setCurrentItem(tab.getPosition(), false);
            }

            @Override
            public void onTabReselected(Tab tab,
                                        FragmentTransaction ft) {

            }
        };
////       action bar tab已不再推荐使用
//        参考：
//        https://github.com/astuetz/PagerSlidingTabStrip
//        https://github.com/jpardogo/PagerSlidingTabStrip
//        https://github.com/florent37/MaterialViewPager
//        https://www.google.com/design/spec/components/tabs.html#tabs-specs
//        actionBar.addTab(actionBar.newTab().setText(R.string.actionbar_tab_1)
//                .setTabListener(tabListener));
//        actionBar.addTab(actionBar.newTab().setText(R.string.actionbar_tab_2)
//                .setTabListener(tabListener));
//        actionBar.addTab(actionBar.newTab().setText(R.string.actionbar_tab_3)
//                .setTabListener(tabListener));
//        actionBar.addTab(actionBar.newTab().setText(R.string.actionbar_tab_4)
//                .setTabListener(tabListener));
    }

    // 未关闭应用状况下，通过Home回到主界面，onStop->onRestart->onStart->onResume,再次打开应用，默认显示第一页
    @Override
    protected void onResume() {
        super.onResume();

        // RadioButton firstButton = (RadioButton)findViewById(R.id.rb_first);
        // firstButton.setChecked(true);
        //
        // FragmentManager fragmentManager = getSupportFragmentManager();
        // FragmentTransaction fragmentTransaction =
        // fragmentManager.beginTransaction();
        //
        // hideFragments(fragmentTransaction);
        // fragmentTransaction.show(firstFragment);
        // fragmentTransaction.commit();
//        viewPager.setCurrentItem(0, false);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
//        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
//        mDrawerToggle.onConfigurationChanged(newConfig);
    }

//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        // If the nav drawer is open, hide action items related to the content view
//        boolean isDrawerOpen = mDrawerLayout.isDrawerOpen(mDrawerMenu);
//        //如果侧滑菜单打开则对某些action item执行操作，如隐藏某些action item
//        menu.findItem(R.id.action_search).setVisible(!isDrawerOpen);
//        return super.onPrepareOptionsMenu(menu);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar, menu);

//        SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
//                .getActionView();
//        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
//        if (mDrawerToggle.onOptionsItemSelected(item)) {
//            return true;
//        }
        switch (item.getItemId()) {
            case android.R.id.home:
                Toast.makeText(this, "you have clicked the logo", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_overflow:
                View overflow = findViewById(R.id.action_overflow);
                showPopup(overflow);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.popmenu_edit:
                        Toast.makeText(MainActivity.this,
                                getString(R.string.popup_tip) + item.getTitle(),
                                Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.popmenu_add:
                        Toast.makeText(MainActivity.this,
                                getString(R.string.popup_tip) + item.getTitle(),
                                Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.popmenu_sort:
                        Toast.makeText(MainActivity.this,
                                getString(R.string.popup_tip) + item.getTitle(),
                                Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        return false;
                }
            }
        });
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.popup, popup.getMenu());
        popup.show();
    }

    @Override
    public boolean onQueryTextChange(String arg0) {
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String arg0) {
        return false;
    }

    private void openHelp() {
        Toast.makeText(this, R.string.help_tip, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        // FragmentTransaction fragmentTransaction = fragmentManager
        // .beginTransaction();
        // hideFragments(fragmentTransaction);
        switch (checkedId) {
            case R.id.rb_first:
                // if (firstFragment == null) {
                // firstFragment = new FirstFragment();
                // fragmentTransaction.add(R.id.content, firstFragment);
                // } else {
                // fragmentTransaction.show(firstFragment);
                // }
                viewPager.setCurrentItem(0, false);
                break;
            case R.id.rb_second:
                // if (secondFragment == null) {
                // secondFragment = new SecondFragment();
                // fragmentTransaction.add(R.id.content, secondFragment);
                // } else {
                // fragmentTransaction.show(secondFragment);
                // }
                viewPager.setCurrentItem(1, false);
                break;
            case R.id.rb_third:
                // if (thirdFragment == null) {
                // thirdFragment = new ThirdFragment();
                // fragmentTransaction.add(R.id.content, thirdFragment);
                // } else {
                // fragmentTransaction.show(thirdFragment);
                // }
                viewPager.setCurrentItem(2, false);
                break;
            case R.id.rb_forth:
                // if (forthFragment == null) {
                // forthFragment = new ForthFragment();
                // fragmentTransaction.add(R.id.content, forthFragment);
                // } else {
                // fragmentTransaction.show(forthFragment);
                // }
                viewPager.setCurrentItem(3, false);
                break;
            default:
                break;
        }
        // fragmentTransaction.commit();
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (firstFragment != null)
            transaction.hide(firstFragment);
        if (secondFragment != null)
            transaction.hide(secondFragment);
        if (thirdFragment != null) {
            transaction.hide(thirdFragment);
        }
        if (forthFragment != null)
            transaction.hide(forthFragment);
    }

    public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        private final String[] TITLES = new String[]{"第一页", "第二页", "第三页", "第四页", "第五页", "第六页", "第七页"};
        //        private int NUM_TABS = 4;
        private final int[] TABS = new int[]{R.layout.pagerslidingstrip_tab1, R.layout.pagerslidingstrip_tab2, R.layout.pagerslidingstrip_tab3};

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    firstFragment = new FirstFragment();
                    return firstFragment;
                case 1:
                    secondFragment = new SecondFragment();
                    return secondFragment;
                case 2:
                    thirdFragment = new ThirdFragment();
                    return thirdFragment;
                case 3:
                    forthFragment = new ForthFragment();
                    return forthFragment;
                case 4:
                    return new ForthFragment();
                case 5:
                    return new ForthFragment();
                case 6:
                    return new ForthFragment();
                default:
                    return null;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }
//
//        @Override
//        public int getPageIconWithTextResId(int position) {
//            return TABS[position];
//        }
    }

    //自定义实现的侧滑菜单子项点击监听器
    private class MyDrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(MainActivity.this, "你点击了选项" + position, Toast.LENGTH_SHORT).show();
            mDrawerList.setItemChecked(position, true);

        }
    }

    //参照官方实现的侧滑菜单子项点击监听器
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        Fragment fragment = BlankFragment.newInstance("你点击了第", String.valueOf(position));
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment).commit();

        mDrawerList.setItemChecked(position, true);
        setTitle(mDrawerMenuItem[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    //自定义的侧滑动作监听类
    private class MyDrawerListener implements DrawerLayout.DrawerListener {

        @Override
        public void onDrawerStateChanged(int newState) {

        }

        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {

        }

        @Override
        public void onDrawerOpened(View drawerView) {
            Toast.makeText(MainActivity.this, "侧滑菜单打开", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onDrawerClosed(View drawerView) {
            Toast.makeText(MainActivity.this, "侧滑菜单关闭", Toast.LENGTH_SHORT).show();
        }
    }
}
