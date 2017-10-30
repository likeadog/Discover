package com.zhuang.discover.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.zhuang.discover.R;
import com.zhuang.discover.databinding.ItemDiscoverListBinding;
import com.zhuang.discover.model.Discover;

import java.util.List;

/**
 * Created by zhuang on 2017/10/26.
 */

public class DiscoverListAdapter extends RecyclerView.Adapter<DiscoverListAdapter.DiscoverViewHolder> {

    private List<Discover> mList;
    OnViewClickListener onViewClickListener;

    public DiscoverListAdapter(List<Discover> list) {
        this.mList = list;
    }

    @Override
    public DiscoverViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemDiscoverListBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_discover_list,
                parent,
                false);
        return new DiscoverViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(DiscoverViewHolder holder, int position) {
        final Discover discover = mList.get(position);
        holder.bind(discover);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class DiscoverViewHolder extends RecyclerView.ViewHolder {
        private ItemDiscoverListBinding binding;

        public DiscoverViewHolder(ItemDiscoverListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Discover discover) {
            binding.setDiscover(discover);
           /* binding.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onViewClickListener.delete(position);
                }
            });
            binding.like.setOnLikeClickListener(new LikeView.OnLikeClickListener() {
                @Override
                public void likeClick() {
                    comment.addLikeCount();
                    comment.setIslike(1);
                    notifyDataSetChanged();
                    onViewClickListener.like(comment);
                }
            });*/

            binding.executePendingBindings();
        }
    }

    public void setOnViewClickListener(OnViewClickListener onViewClickListener) {
        this.onViewClickListener = onViewClickListener;
    }

    public interface OnViewClickListener {
        void delete(int position);//删除

        void like(Discover comment);//点赞

        void getUser();//获取用户信息
    }

}
