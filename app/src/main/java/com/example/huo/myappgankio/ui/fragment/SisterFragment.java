package com.example.huo.myappgankio.ui.fragment;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.huo.myappgankio.R;
import com.example.huo.myappgankio.adapter.SisterAdapter;
import com.example.huo.myappgankio.animator.SisterAnimator;
import com.example.huo.myappgankio.base.BaseFragment;
import com.example.huo.myappgankio.bean.ResultBean;
import com.example.huo.myappgankio.http.HttpRetrofitDemo;
import com.example.huo.myappgankio.rxjava.HttpRxJava.RxjavaListener;
import com.example.huo.myappgankio.ui.activity.MainActivity;
import com.example.huo.myappgankio.ui.activity.SisterActivity;
import com.example.huo.myappgankio.util.BitmpCacheUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link BaseFragment} subclass.
 */
public class SisterFragment extends BaseFragment {
    private static final String TAG = "SisterFragment";
    @BindView(R.id.rv_sister)
    RecyclerView mRvSister;
    private SisterAdapter mAdapter;
    String URL = "http://gank.io/api/data/福利/20/";
    int i = 1;
    private boolean isLoadNext = false;
    private HttpRetrofitDemo mRetrofit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData(i + "");
//        getData(URL + "1");
    }

//    private void getData(String url) {
//        Log.d(TAG, "getData: 这是什么鬼" + url);
//        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
//
//            @Override
//            public void onResponse(String response) {
//                SisterBean sisterBean = new Gson().fromJson(response, SisterBean.class);
//                if (sisterBean.getResults().size() <= 0) {
//                    Toast.makeText(getActivity(), "已无更多可以加载", Toast.LENGTH_SHORT).show();
//                    mAdapter.openLoadMore(false);
//                    return;
//                }
//                mAdapter.addData(sisterBean.getResults());
//                mAdapter.setPageSize(mAdapter.getPageSize() + sisterBean.getResults().size());
//                mAdapter.notifyDataChangedAfterLoadMore(true);
//                isLoadNext = true;
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getActivity(), "小伙子 你确定有网络！", Toast.LENGTH_SHORT).show();
//            }
//        });
//        mQueue.add(request);
//    }

    private void getData(String page) {
        if (mRetrofit == null)
            mRetrofit = new HttpRetrofitDemo();
        mRetrofit.getData("福利", "20", page);
        mRetrofit.setRxjavaListener(new RxjavaListener<ResultBean>() {

            @Override
            public void onNext(List<ResultBean> t) {
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

            }
        });
    }

    public SisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sister, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mRvSister.setHasFixedSize(true);
        mRvSister.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager
                .VERTICAL));
        mAdapter = new SisterAdapter(getActivity(), R.layout.layout_photo_item, new
                ImageLoader(mQueue, new BitmpCacheUtil()));
        mAdapter.openLoadAnimation();
        mRvSister.setAdapter(mAdapter);
        SisterAnimator animator = new SisterAnimator();
        mRvSister.setItemAnimator(animator);
        animator.setStartActivity(getStartActivity());
//        mAdapter.setOnRecyclerViewItemClickListener(getOnRecyclerViewItemClickListener());
        mAdapter.setOnRecyclerViewItemChildClickListener(getRecyclerViewItem());
//        mAdapter.addFooterView(getView());
        mAdapter.openLoadMore(true);
//        mAdapter.setLoadingView();
        mAdapter.setOnLoadMoreListener(getRequestLoadMoreListener());
        super.onViewCreated(view, savedInstanceState);
    }

    @NonNull
    private BaseQuickAdapter.RequestLoadMoreListener getRequestLoadMoreListener() {
        return new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (isLoadNext) {
                    isLoadNext = false;
                    i++;
                    getData("" + i);
                }
            }
        };
    }

    @NonNull
    private BaseQuickAdapter.OnRecyclerViewItemChildClickListener getRecyclerViewItem() {
        return new BaseQuickAdapter.OnRecyclerViewItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                mAdapter.notifyItemChanged(i, SisterAdapter.ACTION_LIKE_IMAGE_CLICKED);
//                Toast.makeText(getActivity(), "点了吗" + i, Toast.LENGTH_SHORT).show();
            }
        };
    }

    @NonNull
    private SisterAnimator.StartActivity getStartActivity() {
        return new SisterAnimator.StartActivity() {
            @Override
            public void startActivity(int position) {
                Uri uri = Uri.parse(((ResultBean) mAdapter.getData().get(position)).getUrl());
                ((MainActivity) getActivity()).startActivity(SisterActivity.class, uri);
            }

        };
    }

//    @NonNull
//    private BaseQuickAdapter.OnRecyclerViewItemClickListener getOnRecyclerViewItemClickListener
// () {
//        return new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
//            @Override
//            public void onItemClick(View view, int i) {
//
////                view.findViewById(R.id.v_bg_like).setVisibility(View.VISIBLE);
////                view.findViewById(R.id.iv_like).setVisibility(View.VISIBLE);
//                Toast.makeText(getActivity(), "点了吗", Toast.LENGTH_SHORT).show();
//            }
//        };
//    }
}

