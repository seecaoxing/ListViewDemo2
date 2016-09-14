package com.see.listviewdemo2;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements ReFlashListView.IReflashListener {

    private com.see.listviewdemo2.ReFlashListView listView;

    private List<String> data = new ArrayList<String>();
    private MyAdapter mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        showList(data);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "你点击了第" + position + "个item", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void initView() {
        listView = (com.see.listviewdemo2.ReFlashListView) findViewById(R.id.listview);
        listView.setInterface(this);
        for (int i = 0; i < 20; i++) {
            data.add("第" + i + "个item");
        }
    }


    /**
     * 显示listview
     */
    private void showList(List<String> data) {

        if (mAdapter == null) {
            mAdapter = new MyAdapter(data, getApplicationContext());
            listView.setAdapter(mAdapter);

        } else {

            mAdapter.onDateChange(data);
        }


    }

    @Override
    public void onReflash() {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //获取最新数据
                for (int i = 0; i < 2; i++) {
                    data.add(0, "第" + i + "个刷新数据item");
                }
                //通知界面显示
                showList(data);
                //通知listview刷新数据完毕；
                listView.reflashComplete();
            }
        }, 2000);


    }
}
