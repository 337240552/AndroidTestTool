package com.hoot.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.alarmtest.R;

import java.util.ArrayList;

/**
 * Created by mingchunhu on 2015/1/13.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements View.OnClickListener{
    private static final int TYPE_SMALL = 1;
    private static final int TYPE_NORMAL = 0;

    private ArrayList<String> mDataset;
    /**
     * @Description: TODO
     */
    public RecyclerAdapter(ArrayList<String> dataset) {
        mDataset = dataset;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.delete) {
            Integer i = (Integer) v.getTag();
            String s = mDataset.remove((int)i);
            Log.d("A", "i:" + i + ", " + s + "," + mDataset.size());

            notifyItemRemoved(i);
            notifyItemRangeChanged(i, mDataset.size());
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;
        public Button mDeleteBtn;

        /**
         * @Description: TODO
         * @param itemView
         */
        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.text);
            mDeleteBtn = (Button) itemView.findViewById(R.id.delete);
        }
    }

    public static class SmallerViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public SmallerViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
        }

    }
    /**
     * @Method: getItemCount
     * @Description: TODO
     * @return
     * @see android.support.v7.widget.RecyclerView.Adapter#getItemCount()
     */
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    /**
     * @Method: onBindViewHolder
     * @Description: TODO
     * @param holder
     * @param position
     * @see android.support.v7.widget.RecyclerView.Adapter#onBindViewHolder(android.support.v7.widget.RecyclerView.ViewHolder,
     *      int)
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mDataset.get(position));
        holder.mDeleteBtn.setOnClickListener(this);
        holder.mDeleteBtn.setTag(position);
    }

    /**
     * @Method: onCreateViewHolder
     * @Description: TODO
     * @param parent
     * @param viewType
     * @return
     * @see android.support.v7.widget.RecyclerView.Adapter#onCreateViewHolder(android.view.ViewGroup,
     *      int)
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        if (viewType == TYPE_NORMAL) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleitemview, parent, false);
            ViewHolder holder = new ViewHolder(view);
            return holder;
//        } else if (viewType == TYPE_SMALL) {
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.small_recycleitemview, parent, false);
//            SmallerViewHolder holder = new SmallerViewHolder(view);
//            return holder;
//        }
    }
}
