# CouponView
`CouponView`是一个自定义的代金券的view，可以设置背景颜色，logo，钱数，有效时间，描述，是否使用

## 效果图
![view](/screenshot/screen.png)

## 提供的方法

        CouponView setCvLogo(Drawable cvLogo); // 设置logo
        CouponView setCvDescription(String cvDescription); // 设置代金券的描述信息
        CouponView setCvMoney(String cvMoney); // 设置钱数
        CouponView setCvBgColor(int cvBgColor); // 设置背景颜色
        CouponView setCvStartTime(String cvStartTime); // 设置起始时间
        CouponView setCvEndTime(String cvEndTime); // 设置结束时间
        CouponView setCvUsed(boolean cvUsed); // 设置是否可以过期
        void show(); // 使前面的设置生效
        void setOnCVSubClickListener(OnCVSubClickListener onCVSubClickListener); // 设置赠送按钮的监听事件

## 在xml中配置信息

        <com.yusoxn.view.widget.CouponView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_margin="10dp"
            app:cv_bgColor="@color/cv_bgColor_n"
            app:cv_description="发单可使用"
            app:cv_endTime="2017.02.02"
            app:cv_logo="@mipmap/icon_logo168"
            app:cv_money="100"
            app:cv_startTime="2017.01.02"
            app:cv_used="true">

        </com.yusoxn.view.widget.CouponView>


## 在java代码中配置信息

        couponView.setOnCVSubClickListener(new CouponView.OnCVSubClickListener() {
            @Override
            public void onSubClick() {

            }
        });
        couponView.setCvLogo(ContextCompat.getDrawable(this, R.mipmap.icon_logo168))
              .setCvMoney("10")
              .setCvDescription("发单可使用")
              .setCvStartTime("2017.03.12")
              .setCvEndTime("2017.03.25")
              .setCvUsed(false)
              .setCvBgColor(Color.GREEN)
              .show();
