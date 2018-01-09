package com.weshape3d.mvpdemo;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class SearchViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);
        //设置最大宽
    }
    android.support.v7.widget.SearchView mSearchView = null;
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {

        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.menu_search);
        //通过MenuItem得到SearchView
        mSearchView = ( android.support.v7.widget.SearchView) MenuItemCompat.getActionView(searchItem);
        mSearchView.setMaxWidth(500);
        //设置是否显示搜索框展开时的提交按钮
        mSearchView.setSubmitButtonEnabled(true);
        //设置输入框提示语
        mSearchView.setQueryHint("hint");
        //设置searchView的字体的大小
        mSearchView.setScrollBarSize(16*3);
        return super.onCreateOptionsMenu(menu);
    }
}
