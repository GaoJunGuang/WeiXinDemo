package com.gjg.learn.weixindemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Junguang_Gao on 2016/6/7.
 */
public class TabFragment extends Fragment {
    //默认title
    private String title="defalut";
    //key
    public static final String TITLE="title";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Fragment fragment=new Fragment();
        if(getArguments()!=null){
            title=getArguments().getString(TITLE);
        }
        TextView tv=new TextView(getActivity());
        tv.setText(title);
        tv.setTextSize(30);
        tv.setGravity(Gravity.CENTER);
        return tv;
    }
}
