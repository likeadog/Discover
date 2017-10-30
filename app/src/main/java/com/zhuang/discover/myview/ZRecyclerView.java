package com.zhuang.discover.myview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhuang.discover.R;


/**
 * Created by zhuang on 2016/9/14.
 */

public class ZRecyclerView extends RecyclerView {

    private static final int TYPE_LOAD_MORE = 1000;
    private static final int TYPE_EMPTY = 1002;
    private int oldPosition;//加载完一次数据之后，最后一条数据的位置
    private View footView;
    private View emptyView;
    private WrapAdapter mWrapAdapter;
    private DataObserver mDataObserver;

    private OnItemClickListener onItemClickListener;
    private OnLongItemClickListener onLongItemClickListener;
    private OnLoadListener onLoadListener;

    public ZRecyclerView(Context context) {
        super(context);
    }

    public ZRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ZRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setEmptyView(View view) {
        emptyView = view;
    }

    public void setAdapter(Adapter adapter) {
        mWrapAdapter = new WrapAdapter(adapter);
        mDataObserver = new DataObserver();
        super.setAdapter(mWrapAdapter);
        adapter.registerAdapterDataObserver(mDataObserver);
    }

    private class WrapAdapter extends Adapter<ViewHolder> {

        private Adapter adapter;

        public WrapAdapter(Adapter adapter) {
            this.adapter = adapter;
        }

        private class EmptyViewHolder extends ViewHolder {
            public EmptyViewHolder(View itemView) {
                super(itemView);
            }
        }

        private class FootViewHolder extends ViewHolder {
            public FootViewHolder(View itemView) {
                super(itemView);
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == TYPE_LOAD_MORE) {
                return new FootViewHolder(footView);
            } else if (viewType == TYPE_EMPTY) {
                return new EmptyViewHolder(emptyView);
            }
            return adapter.onCreateViewHolder(parent, viewType);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if (getItemViewType(position) == TYPE_EMPTY || getItemViewType(position) == TYPE_LOAD_MORE) {
                return;
            }

            adapter.onBindViewHolder(holder, position);

            //item点击事件处理
            final int fPosition = position;
            if (onItemClickListener != null) {
                holder.itemView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onItemClick(fPosition);
                    }
                });
            }
            if (onLongItemClickListener != null) {
                holder.itemView.setOnLongClickListener(new OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        onLongItemClickListener.OnLongItemClick(fPosition);
                        return true;
                    }
                });
            }

            //加载更多
            if (position + 1 == adapter.getItemCount() && onLoadListener != null) {
                if (oldPosition != position) {
                    oldPosition = position;
                    onLoadListener.onLoad();
                } else {
                    footView.findViewById(R.id.progressBar).setVisibility(View.GONE);
                    ((TextView) footView.findViewById(R.id.textView)).setText("没有更多数据");
                }
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (isFooter(position)) {
                return TYPE_LOAD_MORE;
            }
            if (isEmptyView()) {
                return TYPE_EMPTY;
            }
            return adapter.getItemViewType(position);
        }

        @Override
        public int getItemCount() {
            int count = adapter.getItemCount();
            if (emptyView != null && count == 0) return ++count;
            if (footView != null && count != 0) return ++count;
            return count;
        }

        private boolean isFooter(int position) {
            if (footView == null) return false;
            return position == getItemCount() - 1;
        }

        private boolean isEmptyView() {
            if (emptyView != null && adapter.getItemCount() == 0) {
                return true;
            }
            return false;
        }
    }

    private class DataObserver extends AdapterDataObserver {
        @Override
        public void onChanged() {
            if (mWrapAdapter != null) {
                mWrapAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            mWrapAdapter.notifyItemRangeInserted(positionStart, itemCount);
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            mWrapAdapter.notifyItemRangeChanged(positionStart, itemCount);
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            mWrapAdapter.notifyItemRangeChanged(positionStart, itemCount, payload);
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            mWrapAdapter.notifyItemRangeRemoved(positionStart, itemCount);
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            mWrapAdapter.notifyItemMoved(fromPosition, toPosition);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public interface OnLoadListener {
        void onLoad();
    }

    public interface OnLongItemClickListener {
        void OnLongItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnLoadListener(OnLoadListener onLoadListener) {
        this.onLoadListener = onLoadListener;
        footView = LayoutInflater.from(getContext()).inflate(R.layout.zrecycleview_loadmore, null);
    }

    public void setOnLongItemClickListener(OnLongItemClickListener onLongItemClickListener) {
        this.onLongItemClickListener = onLongItemClickListener;
    }

}
