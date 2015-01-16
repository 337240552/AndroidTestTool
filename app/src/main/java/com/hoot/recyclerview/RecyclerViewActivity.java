package com.hoot.recyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.alarmtest.R;

import java.util.ArrayList;


public class RecyclerViewActivity extends Activity {
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//      layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // 设置布局管理器
        mRecyclerView.setLayoutManager(layoutManager);
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL));

        final ArrayList<String> dataset = genData();
        mRecyclerView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                return false;
            }
        });
        mRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataset.remove(mRecyclerView.getChildPosition(v));
                mRecyclerView.removeViewAt(mRecyclerView.getChildPosition(v));
            }
        });
        RecyclerAdapter mAdapter = new RecyclerAdapter(dataset);
        mRecyclerView.setAdapter(mAdapter);
    }

    private ArrayList<String> genData() {
        ArrayList<String> dataset = new ArrayList<String>();
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                dataset.add("item" + i + "独家记忆 - 陈小春");
            } else {
                dataset.add("item" + i + "x独家记忆 - 陈小春");
            }
        }
        return dataset;
    }
}
