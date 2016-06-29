package com.example.huo.myappgankio.ui.activity;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huo.myappgankio.R;
import com.example.huo.myappgankio.animator.MyAnimation;
import com.example.huo.myappgankio.base.BaseActivity;
import com.example.huo.myappgankio.ui.fragment.AndroidFragment;
import com.example.huo.myappgankio.adapter.DrawerAdapter;
import com.example.huo.myappgankio.ui.fragment.SisterFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.sfl_sister)
    FrameLayout mSflSister;
    @BindView(R.id.ll_main)
    LinearLayout mLlMain;
    @BindView(R.id.activity_main)
    DrawerLayout mActivityMain;
    DrawerAdapter mAdapter;
    String title;
//    ActionBarDrawerToggle mDrawerToggle;
    @BindView(R.id.left_drawer)
    ListView mLeftDrawer;

    //    MyAnimation mAnimation;
    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        FragmentManager frameLayout = getFragmentManager();
        FragmentTransaction transaction = frameLayout.beginTransaction();
        transaction.replace(R.id.sfl_sister, new SisterFragment());
        transaction.commit();
        title = "福利";
        mTvTitle.setText(title);
        mTvTitle.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.fuli, 0, 0, 0);
        mTvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivityMain.openDrawer(mLeftDrawer);
//                mLlMain.setAnimation(mAnimation);
            }
        });
        mAdapter = new DrawerAdapter(getApplicationContext());
        mLeftDrawer.setAdapter(mAdapter);
        mLeftDrawer.setOnItemClickListener(new DrawerItemClickListener());
//        mDrawerToggle = new DrawerMenuToggle(this, mActivityMain, 0, R.string.drawer_open, R
//                .string.drawer_close);
//        mActivityMain.addDrawerListener(mDrawerToggle);
//        mAnimation=new MyAnimation(300,getWidth(),getHeight()/2);
    }

//    /**
//     * 侧滑菜单状态监听器（开、关），通过继承ActionBarDrawerToggle实现
//     */
//    private class DrawerMenuToggle extends ActionBarDrawerToggle {
//
//        /**
//         * @param drawerLayout             ：就是加载的DrawerLayout容器组件
//         * @param drawerImageRes           ： 要使用的ActionBar左上角的指示图标
//         * @param openDrawerContentDescRes 、closeDrawerContentDescRes：开启和关闭的两个描述字段，没有太大的用处
//         */
//        public DrawerMenuToggle(Activity activity, DrawerLayout drawerLayout,
//                                int drawerImageRes, int openDrawerContentDescRes,
//                                int closeDrawerContentDescRes) {
//
//            super(activity, drawerLayout, openDrawerContentDescRes, closeDrawerContentDescRes);
//
//        }
//
////        /**
////         * 当侧滑菜单达到完全关闭的状态时，回调这个方法
////         */
////        public void onDrawerClosed(View view) {
////            super.onDrawerClosed(view);
////            //当侧滑菜单关闭后，显示ListView选中项的标题，如果并没有点击ListView中的任何项，那么显示原来的标题
//////            invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
////        }
////
////        /**
////         * 当侧滑菜单完全打开时，这个方法被回调
////         */
////        public void onDrawerOpened(View drawerView) {
////            super.onDrawerOpened(drawerView);
//////            invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
////        }
//    }


//    /**
//     * 为了能够让ActionBarDrawerToggle监听器
//     * 能够在Activity的整个生命周期中都能够以正确的逻辑工作
//     * 需要添加下面两个方法
//     */
//    @Override
//    protected void onPostCreate(Bundle savedInstanceState) {
//        super.onPostCreate(savedInstanceState);
//        // Sync the toggle state after onRestoreInstanceState has occurred.
//        mDrawerToggle.syncState();
//    }
//
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        mDrawerToggle.onConfigurationChanged(newConfig);
//    }
//
//    /**
//     * 最后做一些菜单上处理
//     */
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        //第一个if 要加上，为的是让ActionBarDrawerToggle以正常的逻辑工作
//        if (mDrawerToggle.onOptionsItemSelected(item)) {
//            return true;
//        }
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        if (id == R.id.action_websearch) {
//            Toast.makeText(this, "webSearch 菜单项被单击", Toast.LENGTH_SHORT).show();
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    /**
//     * 每次调用 invalidateOptionsMenu() ，下面的这个方法就会被回调
//     */
////    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//
//        // 如果侧滑菜单的状态监听器在侧滑菜单打开和关闭时都调用了invalidateOptionsMenu()方法，
//        //当侧滑菜单打开时将ActionBar上的某些菜单图标隐藏起来，使得这时仅显示“推酷”这个全局标题
//        //本应用中是将ActiongBar上的action菜单项隐藏起来
//
//        boolean drawerOpen = mActivityMain.isDrawerOpen(mLeftDrawer);//判定当前侧滑菜单的状态
////        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
//        return super.onPrepareOptionsMenu(menu);
//    }

    /**
     * 《当用户按下了"手机上的返回功能按键"的时候会回调这个方法》
     */
    @Override
    public void onBackPressed() {
        boolean drawerState = mActivityMain.isDrawerOpen(mLeftDrawer);
        if (drawerState) {
            mActivityMain.closeDrawers();
            return;
        }
        //也就是说，当按下返回功能键的时候，不是直接对Activity进行弹栈，而是先将菜单视图关闭
        super.onBackPressed();
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        private Fragment mContentFragment;

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {

            selectItem(position);

        }

        public void selectItem(int position) {

            //为内容视图加载新的Fragment
//            Bundle bd = new Bundle();
//            bd.putString(AndroidFragment.SELECTED_ITEM, mAdapter.getItem(position)
//                    .getMenuTitle());
            if (position == 0 || mAdapter.getItem(position).getMenuTitle().equals("福利")) {
                mContentFragment = new SisterFragment();
            } else {
                mContentFragment = AndroidFragment.newInstance("data", mAdapter.getItem
                        (position).getMenuTitle());
                Log.d(TAG, "selectItem: " + mAdapter.getItem(position).getMenuTitle());
            }
//            contentFragment.setArguments(bd);

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.sfl_sister, mContentFragment).commit();

            //将选中的菜单项置为高亮
            mLeftDrawer.setItemChecked(position, true);
            //将ActionBar中标题更改为选中的标题项
            mTvTitle.setText(mAdapter.getItem(position).getMenuTitle());
            mTvTitle.setCompoundDrawablesWithIntrinsicBounds(mAdapter.getItem(position)
                    .getMenuIcon(), 0, 0, 0);
//            setTitle(mAdapter.getItem(position).getMenuTitle());
            //将当前的侧滑菜单关闭，调用DrawerLayout的closeDrawer（）方法即可
            mActivityMain.closeDrawer(mLeftDrawer);
        }

//        public void setTitle(String title1) {
//            title = title1; // 更改当前的CurrentContentTitle标题内容
////            getActionBar().setTitle(title);
//
//        }
    }

}
