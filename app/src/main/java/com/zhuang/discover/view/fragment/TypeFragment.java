package com.zhuang.discover.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhuang.discover.R;

/**
 * 首页
 * create by zhuang 2017.10.26
 */
public class TypeFragment extends Fragment {

    public TypeFragment() {
    }

    public static TypeFragment newInstance() {
        TypeFragment fragment = new TypeFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_type, container, false);
    }

}
