package com.example.huo.myappgankio.ui.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.huo.myappgankio.R;
import com.example.huo.myappgankio.adapter.ListAdapter;
import com.example.huo.myappgankio.base.BaseFragment;
import com.example.huo.myappgankio.bean.ResultBean;
import com.example.huo.myappgankio.http.HttpRetrofitDemo;
import com.example.huo.myappgankio.rxjava.HttpRxJava;
import com.example.huo.myappgankio.ui.activity.MainActivity;
import com.example.huo.myappgankio.ui.activity.SisterActivity;
import com.example.huo.myappgankio.ui.activity.WebActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AndroidFragment extends BaseFragment {
//    public static final String SELECTED_ITEM = "gank.io";
    private static final String TAG = "AndroidFragment";
    String data = "data";
    String type = "Android";
    int page = 1;
    HttpRetrofitDemo mRetrofitDemo;
    @BindView(R.id.rv_show)
    RecyclerView mRvShow;
    ListAdapter mAdapter;
    private boolean isLoadNext = true;

    public AndroidFragment() {
        // Required empty public constructor
    }

    public static AndroidFragment newInstance(String data, String type) {
        Bundle args = new Bundle();
        args.putString("TYPE", type);
        args.putString("DATA", data);
        AndroidFragment fragment = new AndroidFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getArguments().getString("TYPE");
        data = getArguments().getString("DATA");
        getData(page + "");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_android, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvShow.setLayoutManager(layoutManager);
        mAdapter = new ListAdapter(getActivity(), R.layout.layout_item);
        mAdapter.openLoadAnimation();
        mRvShow.setAdapter(mAdapter);
        mAdapter.openLoadMore(true);
        mAdapter.setOnRecyclerViewItemClickListener(getOnRecyclerViewItemClickListener());
        mAdapter.setOnLoadMoreListener(getRequestLoadMoreListener());
    }

    @NonNull
    private BaseQuickAdapter.RequestLoadMoreListener getRequestLoadMoreListener() {
        return new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (isLoadNext) {
                    isLoadNext = false;
                    page++;
                    getData("" + page);
                }
            }
        };
    }

    @NonNull
    private BaseQuickAdapter.OnRecyclerViewItemClickListener getOnRecyclerViewItemClickListener() {
        return new BaseQuickAdapter.OnRecyclerViewItemClickListener() {


            @Override
            public void onItemClick(View view, int i) {
                Uri uri = Uri.parse(((ResultBean) mAdapter.getData().get(i)).getUrl());
                if (type.equals("休息视频")) {
                    mMainActivity.startActivity(new Intent(Intent.ACTION_VIEW, uri));
                } else {
                    mMainActivity.startActivity(WebActivity.class, uri);
                }
            }
        };
    }

    private void getData(String page) {
        if (mRetrofitDemo == null)
            mRetrofitDemo = new HttpRetrofitDemo();
        mRetrofitDemo.getData(data, type, "20", page);
        mRetrofitDemo.setRxjavaListener(new HttpRxJava.RxjavaListener() {
            @Override
            public void onNext(List t) {
                if (t.size() <= 0) {
                    Toast.makeText(getActivity(), "已无更多可以加载", Toast.LENGTH_SHORT).show();
                    mAdapter.openLoadMore(false);
                    return;
                }
                Log.d(TAG, "onNext:>>>>>>>>>>>> " + ((ResultBean) t.get(2)).getDesc());
                mAdapter.addData(t);
                mAdapter.setPageSize(mAdapter.getPageSize() + t.size());
                mAdapter.notifyDataChangedAfterLoadMore(true);
                isLoadNext = true;
            }

            @Override
            public void onError() {
                mMainActivity.showToast("请检查网路！");
            }
        });
    }

}
