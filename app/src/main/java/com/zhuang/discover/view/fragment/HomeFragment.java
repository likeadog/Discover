package com.zhuang.discover.view.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhuang.discover.R;
import com.zhuang.discover.databinding.FragmentHomeBinding;
import com.zhuang.discover.model.Discover;
import com.zhuang.discover.myview.ZRecyclerView;
import com.zhuang.discover.network.RetrofitHelper;
import com.zhuang.discover.network.ReturnDataList;
import com.zhuang.discover.network.service.DiscoverService;
import com.zhuang.discover.view.adapter.DiscoverListAdapter;
import com.zhuang.discover.viewmodel.FragmentHomeVM;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 首页
 * create by zhuang 2017.10.26
 */
public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    FragmentHomeVM viewModel;

    public HomeFragment() {
        viewModel = new FragmentHomeVM();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        binding.setViewModel(viewModel);
        viewModel.loadData();
        return binding.getRoot();
    }

}
