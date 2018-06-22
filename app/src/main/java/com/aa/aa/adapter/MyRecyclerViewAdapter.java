package com.aa.aa.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.aa.aa.R;

import java.util.HashSet;
import java.util.List;

/**
 * Created by flisar on 03.03.2017.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private List<Integer> mDatas;
    private ItemClickListener mClickListener;
    private boolean editable = false;

    private HashSet<Integer> mSelected;


    public void setmDatas(List<Integer> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    public MyRecyclerViewAdapter(Context context, List<Integer> mDatas) {
        mContext = context;
        this.mDatas = mDatas;
        mSelected = new HashSet<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_item_test, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvText.setText(String.valueOf(mDatas.get(position)));
        if (editable) {
            holder.checkBox.setVisibility(View.VISIBLE);
            if (mSelected.contains(position)) {
                holder.checkBox.setChecked(true);
            } else {
                holder.checkBox.setChecked(false);
            }
        } else {
            holder.checkBox.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    // ----------------------
    // Editable
    // ----------------------

    public void setEdit(boolean editable) {
        this.editable = editable;
        notifyDataSetChanged();
    }

    // ----------------------
    // Selection
    // ----------------------

    public void toggleSelection(int pos) {
        if (editable) {
            if (mSelected.contains(pos)) {
                mSelected.remove(pos);
            } else {
                mSelected.add(pos);
            }
            notifyItemChanged(pos);
        }
    }

    public void select(int pos, boolean selected) {
        if (selected)
            mSelected.add(pos);
        else
            mSelected.remove(pos);
        notifyItemChanged(pos);
    }

    public void selectRange(int start, int end, boolean selected) {
        for (int i = start; i <= end; i++) {
            if (selected)
                mSelected.add(i);
            else
                mSelected.remove(i);
        }
        notifyItemRangeChanged(start, end - start + 1);
    }

    public void deselectAll() {
        // this is not beautiful...
        mSelected.clear();
        notifyDataSetChanged();
    }

    public void selectAll() {
        for (int i = 0; i < mDatas.size(); i++)
            mSelected.add(i);
        notifyDataSetChanged();
    }

    public int getCountSelected() {
        return mSelected.size();
    }

    public HashSet<Integer> getSelection() {
        return mSelected;
    }

    // ----------------------
    // Click Listener
    // ----------------------

    public void setClickListener(ItemClickListener itemClickListener) {
        mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);

        boolean onItemLongClick(View view, int position);
    }

    // ----------------------
    // ViewHolder
    // ----------------------

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView tvText;
        CheckBox checkBox;

        ViewHolder(View itemView) {
            super(itemView);
            tvText = itemView.findViewById(R.id.tvText);
            checkBox = itemView.findViewById(R.id.checkbox);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null)
                mClickListener.onItemClick(view, getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view) {
            return mClickListener != null && mClickListener.onItemLongClick(view, getAdapterPosition());
        }
    }
}
