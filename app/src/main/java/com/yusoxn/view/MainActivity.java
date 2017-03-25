package com.yusoxn.view;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private CouponView couponView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        couponView = (CouponView) findViewById(R.id.cv_coupon);
        couponView.setOnCVSubClickListener(new CouponView.OnCVSubClickListener() {
            @Override
            public void onSubClick() {
                couponView.setCvUsed(true).show();
            }
        });
        couponView.setCvLogo(ContextCompat.getDrawable(this, R.mipmap.icon_logo168))
                .setCvMoney("10")
                .setCvDescription("发单可使用")
                .setCvStartTime("2017.03.12")
                .setCvEndTime("2017.03.25")
                .show();
    }
}
