package com.gjg.learn.weixindemo;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.ViewConfiguration;

import java.lang.reflect.Field;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if((Build.VERSION.SDK_INT >= 10) & (Build.VERSION.SDK_INT < 21)) {
            setOverflowButtonAlways();
        }
        //不显示actionbar上的图标
        getActionBar().setDisplayShowHomeEnabled(false);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tab_menu,menu);
        return true;
    }

    //设置overflow按钮总是显示在actionbar
    private void setOverflowButtonAlways()
    {
        try
        {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKey = ViewConfiguration.class
                    .getDeclaredField("sHasPermanentMenuKey");
            menuKey.setAccessible(true);
            menuKey.setBoolean(config, false);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
