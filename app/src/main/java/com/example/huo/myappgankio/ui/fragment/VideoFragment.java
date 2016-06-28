package com.example.huo.myappgankio.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.huo.myappgankio.R;
import com.example.huo.myappgankio.adapter.VideoAdapter;
import com.example.huo.myappgankio.base.BaseFragment;
import com.example.huo.myappgankio.http.HttpRetrofitDemo;
import com.example.huo.myappgankio.rxjava.HttpRxJava;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends BaseFragment {


    @BindView(R.id.rv_show)
    RecyclerView mRvShow;
    VideoAdapter mAdapter;
    int page = 1;
    HttpRetrofitDemo mRetrofit;
    private boolean isLoadNext = true;

    public VideoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData(page + "");
    }

    private void getData(String page) {
        if (mRetrofit == null)
            mRetrofit = new HttpRetrofitDemo();
        mRetrofit.getData("data", "休息视频", "5", page);
        mRetrofit.setRxjavaListener(new HttpRxJava.RxjavaListener() {

            @Override
            public void onNext(List t) {
                if (t.size() <= 0) {
                    Toast.makeText(getActivity(), "已无更多可以加载", Toast.LENGTH_SHORT).show();
                    mAdapter.openLoadMore(false);
                    return;
                }
                mAdapter.addData(t);
                mAdapter.setPageSize(mAdapter.getPageSize() + t.size());
                mAdapter.notifyDataChangedAfterLoadMore(true);
                isLoadNext = true;
            }

            @Override
            public void onError() {
                mMainActivity.showToast("请检查网络！");
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvShow.setLayoutManager(layoutManager);
        mAdapter = new VideoAdapter(getActivity(), R.layout.layout_video);
        mRvShow.setAdapter(mAdapter);
        mAdapter.openLoadMore(true);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (isLoadNext) {
                    isLoadNext = false;
                    page++;
                    getData("" + page );
                }
            }
        });
    }
}
