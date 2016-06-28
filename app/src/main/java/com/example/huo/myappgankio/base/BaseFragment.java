package com.example.huo.myappgankio.base;


import android.app.Fragment;
import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.example.huo.myappgankio.ui.activity.MainActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {
    public RequestQueue mQueue;
    public MainActivity mMainActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainActivity = (MainActivity) getActivity();
        mQueue = mMainActivity.getQueue();
    }

    public BaseFragment() {
        // Required empty public constructor
    }


}
