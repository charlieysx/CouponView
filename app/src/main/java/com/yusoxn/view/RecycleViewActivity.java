package com.yusoxn.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.yusoxn.view.bean.CouponBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class RecycleViewActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private EasyRecyclerView recyclerView;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);

        recyclerView = (EasyRecyclerView) findViewById(R.id.erv_coupon);

        //创建默认的线性LayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //设置分割线
        DividerDecoration itemDecoration = new DividerDecoration(Color.parseColor("#00FFFFFF"), 0, 0, 0);
        itemDecoration.setDrawLastItem(true);
        recyclerView.addItemDecoration(itemDecoration);
        //监听下拉刷新
        recyclerView.setRefreshListener(this);
        //设置下拉刷新控件的颜色,可以有颜色变化,下面的方法传入的参数可以多个的,一个就只显示一种颜色
        recyclerView.setRefreshingColor(ContextCompat.getColor(this, R.color.colorAccent));

        mAdapter = new MyAdapter(this, getBean());
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnRecyclerViewItemSubClickListener(new MyAdapter.OnRecyclerViewItemSubClickListener() {
            @Override
            public void onItemSubClick(int position) {
                Toast.makeText(RecycleViewActivity.this, "clickGive" + position, Toast.LENGTH_SHORT).show();
            }
        });
        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(RecycleViewActivity.this, "clickItem" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRefresh() {
        mAdapter.clear();
        mAdapter.addAll(getBean());
    }

    private List<CouponBean> getBean() {
        List<CouponBean> couponBeens = new ArrayList<>();

        int logo = R.mipmap.icon_logo168;
        String description = "发单可使用";
        String[] moneys = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] startTimes = {"2017.01.02", "2017.01.05", "2017.01.06", "2017.01.08", "2017.03.09", "2017.03.10",
                "2017.03.15", "2017.03.12", "2017.02.25", "2017.02.06"};
        String[] endTimes = {"2017.01.03", "2017.01.08", "2017.01.09", "2017.01.09", "2017.03.15", "2017.03.15",
                "2017.03.20", "2017.03.16", "2017.02.27", "2017.02.17"};
        Random random = new Random();
        int num = random.nextInt(100) % 11;
        for (int i = 0; i < num; ++i) {
            int pos = random.nextInt(10);
            int used = random.nextInt(100);
            couponBeens.add(new CouponBean(logo, description, moneys[pos], startTimes[pos], endTimes[pos], used % 3
                    == 0));
        }
        Collections.sort(couponBeens, new CMP());

        return couponBeens;
    }

    class CMP implements Comparator<CouponBean> {

        @Override
        public int compare(CouponBean o1, CouponBean o2) {

            if(o1.used && !o2.used) {
                return 1;
            }
            if(!o1.used && o2.used) {
                return -1;
            }
            if(o1.money.length() > o2.money.length()) {
                return -1;
            }
            if(o1.money.length() < o2.money.length()){
                return 1;
            }
            return o2.money.compareTo(o1.money);
        }
    }
}
