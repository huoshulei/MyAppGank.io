package com.example.huo.myappgankio.ui.activity;



import android.app.FragmentManager;
import android.app.FragmentTransaction;

import com.example.huo.myappgankio.R;
import com.example.huo.myappgankio.base.BaseActivity;
import com.example.huo.myappgankio.ui.fragment.SisterFragment;

public class MainActivity extends BaseActivity {

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        FragmentManager frameLayout=getFragmentManager();
        FragmentTransaction transaction = frameLayout.beginTransaction();
        transaction.replace(R.id.sfl_sister,new SisterFragment());
        transaction.commit();
    }

}
