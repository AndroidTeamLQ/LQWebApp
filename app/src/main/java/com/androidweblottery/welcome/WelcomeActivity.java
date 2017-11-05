package com.androidweblottery.welcome;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;


import com.androidweblottery.HomeActivity;
import com.androidweblottery.R;

import java.util.ArrayList;



/**
 * 引导页
 * author :  xt
 * date :  2016/4/20
 */
public class WelcomeActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TextView btn;
    private int[] zyIcons = new int[]{R.mipmap.fun_1, R.mipmap.fun_2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //当系统版本为4.4或者4.4以上时可以使用沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.activity_welcome);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        btn = (TextView) findViewById(R.id.btn);

//        requestLocalPermission();

        initFragment();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
        viewPager.setAdapter(new WelcomeAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == fragments.size() - 1) {
                    btn.setVisibility(View.GONE);
                } else {
                    btn.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private ArrayList<Fragment> fragments = new ArrayList<Fragment>();

    private void initFragment() {
        int[] icons = zyIcons;
//        if (BuildConfig.product.equals("04")) {
//            icons = a8Icons;
//        } else if (BuildConfig.product.equals("17")) {
//            icons = dplIcons;
//            btn.setText("");
//            btn.setBackgroundResource(R.drawable.dpl_skip_btn);
//        } else {
//            icons = zyIcons;
//        }
        for (int i = 0; i < icons.length; i++) {
            WelcomeFragment fragment = new WelcomeFragment();
            Bundle bunble = new Bundle();
            if (i == icons.length - 1) {
                bunble.putBoolean("isLast", true);
            } else {
                bunble.putBoolean("isLast", false);
            }
            bunble.putInt("icon", icons[i]);
            fragment.setArguments(bunble);
            fragments.add(fragment);
        }

        if (fragments.size() <= 1) {
            btn.setVisibility(View.GONE);
        }
    }

    private class WelcomeAdapter extends FragmentPagerAdapter {

        public WelcomeAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
