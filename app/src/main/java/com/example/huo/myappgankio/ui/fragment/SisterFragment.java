package com.example.huo.myappgankio.ui.fragment;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.huo.myappgankio.R;
import com.example.huo.myappgankio.adapter.SisterAdapter;
import com.example.huo.myappgankio.animator.SisterAnimator;
import com.example.huo.myappgankio.base.BaseFragment;
import com.example.huo.myappgankio.bean.SisterBean;
import com.example.huo.myappgankio.ui.activity.MainActivity;
import com.example.huo.myappgankio.ui.activity.SisterActivity;
import com.example.huo.myappgankio.util.BitmpCacheUtil;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link BaseFragment} subclass.
 */
public class SisterFragment extends BaseFragment {

    @BindView(R.id.rv_sister)
    RecyclerView mRvSister;
    private SisterAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData("http://gank.io/api/data/福利/24/1");
    }

    private void getData(String url) {

        StringRequest request = new StringRequest(url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                SisterBean sisterBean = new Gson().fromJson(response, SisterBean.class);
                mAdapter.addData(sisterBean.getResults());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mQueue.add(request);
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
        animator.setStartActivity(new SisterAnimator.StartActivity() {
            @Override
            public void startActivity(int position) {
                Uri uri = Uri.parse(((SisterBean.ResultsBean) mAdapter.getData().get(position))
                        .getUrl());
                ((MainActivity) getActivity()).startActivity(SisterActivity.class, uri);
            }

        });
//        mAdapter.setOnRecyclerViewItemClickListener(getOnRecyclerViewItemClickListener());
        mAdapter.setOnRecyclerViewItemChildClickListener(new BaseQuickAdapter
                .OnRecyclerViewItemChildClickListener() {

            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
//                View viewById = view.findViewById(R.id.v_bg_like);
                mAdapter.notifyItemChanged(i, SisterAdapter.ACTION_LIKE_IMAGE_CLICKED);
                Toast.makeText(getActivity(), "点了吗", Toast.LENGTH_SHORT).show();
            }
        });
        super.onViewCreated(view, savedInstanceState);
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

