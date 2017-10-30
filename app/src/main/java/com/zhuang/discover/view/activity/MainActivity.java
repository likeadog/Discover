package com.zhuang.discover.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zhuang.discover.R;
import com.zhuang.discover.databinding.ActivityMainBinding;
import com.zhuang.discover.view.fragment.HomeFragment;
import com.zhuang.discover.view.fragment.MeFragment;
import com.zhuang.discover.view.fragment.MerchantFragment;
import com.zhuang.discover.view.fragment.TypeFragment;

/**
 * 系统入口
 */
public class MainActivity extends AppCompatActivity {

    private Fragment[] fragments = new Fragment[4];
    private int currentIndex = 0;//当前显示的fragment的索引位置
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initHomeFragment();
    }

    /**
     * 初始化主页
     */
    public void initHomeFragment() {
        if (fragments[0] == null) {
            fragments[0] = new HomeFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.content, fragments[0], "home").commit();
        } else {
            getSupportFragmentManager().beginTransaction().show(fragments[0]).commit();
        }
    }

    public void showHome(View view) {
        int showIndex = 0;
        if (currentIndex == showIndex) return;//如果已经是当前的fragment，不用切换
        FragmentTransaction transition = getSupportFragmentManager().beginTransaction();
        if (fragments[showIndex] == null) {
            fragments[showIndex] = new HomeFragment();
            transition.add(R.id.content, fragments[showIndex], "home");
        }
        hideAndShow(showIndex, transition);
    }

    public void showType(View view) {
        int showIndex = 1;
        if (currentIndex == showIndex) return;//如果已经是当前的fragment，不用切换
        FragmentTransaction transition = getSupportFragmentManager().beginTransaction();
        if (fragments[showIndex] == null) {
            fragments[showIndex] = new TypeFragment();
            transition.add(R.id.content, fragments[showIndex], "merchant");
        }
        hideAndShow(showIndex, transition);
    }

    public void showMerchant(View view) {
        int showIndex = 2;
        if (currentIndex == showIndex) return;//如果已经是当前的fragment，不用切换
        FragmentTransaction transition = getSupportFragmentManager().beginTransaction();
        if (fragments[showIndex] == null) {
            fragments[showIndex] = new MerchantFragment();
            transition.add(R.id.content, fragments[showIndex], "merchant");
        }
        hideAndShow(2, transition);
    }

    public void showMe(View view) {
        int showIndex = 3;
        if (currentIndex == showIndex) return;//如果已经是当前的fragment，不用切换
        FragmentTransaction transition = getSupportFragmentManager().beginTransaction();
        if (fragments[showIndex] == null) {
            fragments[showIndex] = new MeFragment();
            transition.add(R.id.content, fragments[showIndex], "me");
        }
        hideAndShow(showIndex, transition);
    }

    /**
     * 除了指定的fragment不hide，其他fragment全hide
     *
     * @param expectIndex 指定的fragment在fragments中的位置
     */
    private void hideAndShow(int expectIndex, FragmentTransaction transition) {
        for (int i = 0; i < fragments.length; i++) {
            if (i != expectIndex && fragments[i] != null) {
                transition.hide(fragments[i]);
            }
        }
        transition.show(fragments[expectIndex]);
        transition.commit();
        currentIndex = expectIndex;
    }
}
