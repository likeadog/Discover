package com.zhuang.discover.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;

import com.zhuang.discover.BR;
import com.zhuang.discover.model.Discover;
import com.zhuang.discover.myview.ZRecyclerView.OnLoadListener;
import com.zhuang.discover.network.RetrofitHelper;
import com.zhuang.discover.network.ReturnDataList;
import com.zhuang.discover.network.service.DiscoverService;
import com.zhuang.discover.view.adapter.DiscoverListAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhuang on 2017/10/27.
 */

public class FragmentHomeVM extends BaseObservable {

    private List mList;
    private DiscoverListAdapter discoverListAdapter;
    private OnLoadListener onLoadListener;
    private OnRefreshListener onRefreshListener;
    public boolean refreshing = true;//刷新的状态
    private DiscoverService service;

    public FragmentHomeVM() {
        service = RetrofitHelper.createService(DiscoverService.class);
        mList = new ArrayList<Discover>();
        discoverListAdapter = new DiscoverListAdapter(mList);
        onLoadListener = new OnLoadListener() {
            @Override
            public void onLoad() {
                loadData();
            }
        };
        onRefreshListener = new OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        };
    }

    /**
     * 加载更多数据
     */
    public void loadData() {
        Call<ReturnDataList<Discover>> call = service.getDiscoverList();
        call.enqueue(new Callback<ReturnDataList<Discover>>() {
            @Override
            public void onResponse(Call<ReturnDataList<Discover>> call, Response<ReturnDataList<Discover>> response) {
                List<Discover> list = response.body().getData();
                mList.addAll(list);
                discoverListAdapter.notifyDataSetChanged();
                if (refreshing) setRefreshing(false);
            }

            @Override
            public void onFailure(Call<ReturnDataList<Discover>> call, Throwable t) {
                Log.e("zhuang", t.toString());
                if (refreshing) setRefreshing(false);
            }
        });
    }

    /**
     * 刷新数据
     */
    private void refreshData() {
        Call<ReturnDataList<Discover>> call = service.getDiscoverList();
        call.enqueue(new Callback<ReturnDataList<Discover>>() {
            @Override
            public void onResponse(Call<ReturnDataList<Discover>> call, Response<ReturnDataList<Discover>> response) {
                List<Discover> list = response.body().getData();
                mList.clear();
                mList.addAll(list);
                discoverListAdapter.notifyDataSetChanged();
                setRefreshing(false);
            }

            @Override
            public void onFailure(Call<ReturnDataList<Discover>> call, Throwable t) {
                Log.e("zhuang", t.toString());
                setRefreshing(false);
            }
        });
    }

    public DiscoverListAdapter getDiscoverListAdapter() {
        return discoverListAdapter;
    }

    public OnLoadListener getOnLoadListener() {
        return onLoadListener;
    }

    @Bindable
    public boolean getRefreshing() {
        return refreshing;
    }

    public void setRefreshing(boolean refreshing) {
        this.refreshing = refreshing;
        notifyPropertyChanged(BR.refreshing);
    }

    public OnRefreshListener getOnRefreshListener() {
        return onRefreshListener;
    }
}
