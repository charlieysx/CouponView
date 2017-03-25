package com.yusoxn.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.yusoxn.view.R;

/**
 * 代金券的view
 * <p>
 * Created by Yusxon on 17/3/24.
 */

public class CouponView extends FrameLayout {

    public interface OnCVSubClickListener {
        /**
         * view中的某个子view的点击事件
         * (这里只有一个赠送按钮的点击事件)
         */
        void onSubClick();
    }

    private static final int DEFAULTCVLOGO = R.mipmap.ic_launcher;
    private static final String DEFAULTCVMONEY = "";
    private static final int DEFAULTTIME = R.string.cv_defalut_time;
    private static final boolean DEFAULTUSED = false;
    private static final int DEFAULTDESCRIPTION = R.string.cv_coupon;
    private static final int DEFAULTBGCOLORY = R.color.cv_bgColor_y;
    private static final int DEFAULTBGCOLORN = R.color.cv_bgColor_n;

    /**
     * logo
     */
    private Drawable cvLogo;
    /**
     * 代金券优惠钱数
     */
    private String cvMoney;
    /**
     * 背景颜色
     */
    private int cvBgColor;
    /**
     * 代金券使用期限的开始时间
     */
    private String cvStartTime;
    /**
     * 代金券使用期限的结束时间
     */
    private String cvEndTime;
    /**
     * 代金券使用描述
     */
    private String cvDescription;
    /**
     * 是否使用过
     */
    private boolean cvUsed;

    private ImageView ivLogo;
    private TextView tvDescription;
    private TextView tvMoney;
    private TextView tvStartTime;
    private TextView tvEndTime;
    private TextView tvUsed;
    private TextView tvGive;
    private ImageView ivExpired;
    private CardView cavBg;
    private TextView tvCoupon;

    private OnCVSubClickListener onCVSubClickListener;

    public CouponView(Context context) {
        this(context, null);
    }

    public CouponView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CouponView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setWillNotDraw(false);
        this.setClickable(true);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CouponView, defStyleAttr, 0);

        cvLogo = a.getDrawable(R.styleable.CouponView_cv_logo);
        cvMoney = a.getString(R.styleable.CouponView_cv_money);
        cvBgColor = a.getColor(R.styleable.CouponView_cv_bgColor, ContextCompat.getColor(getContext(),
                DEFAULTBGCOLORY));
        cvStartTime = a.getString(R.styleable.CouponView_cv_startTime);
        cvEndTime = a.getString(R.styleable.CouponView_cv_endTime);
        cvDescription = a.getString(R.styleable.CouponView_cv_description);
        cvUsed = a.getBoolean(R.styleable.CouponView_cv_used, DEFAULTUSED);

        a.recycle();

        if (null == cvLogo) {
            cvLogo = ContextCompat.getDrawable(getContext(), DEFAULTCVLOGO);
        }
        if (null == cvMoney) {
            cvMoney = DEFAULTCVMONEY;
        }
        if (null == cvStartTime) {
            cvStartTime = getResources().getString(DEFAULTTIME);
        }
        if (null == cvEndTime) {
            cvEndTime = getResources().getString(DEFAULTTIME);
        }
        if (null == cvDescription) {
            cvDescription = getResources().getString(DEFAULTDESCRIPTION);
        }

        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_coupon, this, true);

        cavBg = (CardView) findViewById(R.id.cav_bg);
        ivLogo = (ImageView) findViewById(R.id.iv_logo);
        tvDescription = (TextView) findViewById(R.id.tv_description);
        tvMoney = (TextView) findViewById(R.id.tv_money);
        tvStartTime = (TextView) findViewById(R.id.tv_startTime);
        tvEndTime = (TextView) findViewById(R.id.tv_endTime);
        tvGive = (TextView) findViewById(R.id.tv_give);
        tvUsed = (TextView) findViewById(R.id.tv_used);
        ivExpired = (ImageView) findViewById(R.id.iv_expired);
        tvCoupon = (TextView) findViewById(R.id.tv_coupon);

        tvGive.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != onCVSubClickListener) {
                    onCVSubClickListener.onSubClick();
                }
            }
        });

        show();
    }

    /**
     * 显示数据
     */
    public void show() {
        showBgColor();
        showLogo();
        showCoupon();
        showDescription();
        showMoney();
        showStartTime();
        showEndTime();
        showUsed();
        showExpired();
        showGive();
    }

    /**
     * 显示logo
     */
    private void showLogo() {
        if (null != ivLogo && null != cvLogo) {
            ivLogo.setImageDrawable(cvLogo);
            if(cvUsed){
                float[] hsv = new float[3];
                Color.colorToHSV(ContextCompat.getColor(getContext(), DEFAULTBGCOLORN), hsv);
                hsv[1] -= 0.6;
                hsv[2] -= 0.3;
                ivLogo.setColorFilter(Color.HSVToColor(hsv), PorterDuff.Mode.MULTIPLY);
            } else {
                ivLogo.clearColorFilter();
            }
        }
    }

    /**
     * 获取比背景色深点的颜色
     *
     * @return
     */
    private int getDarkColor() {
        float[] hsv = new float[3];
        if (cvUsed) {
            Color.colorToHSV(ContextCompat.getColor(getContext(), DEFAULTBGCOLORN), hsv);
        } else {
            Color.colorToHSV(cvBgColor, hsv);
        }
        hsv[2] -= 0.2;
        return Color.HSVToColor(hsv);
    }

    /**
     * 显示代金券大字
     */
    private void showCoupon() {
        if (null != tvCoupon) {
            tvCoupon.setTextColor(getDarkColor());
        }
    }

    /**
     * 显示代金券使用描述
     */
    private void showDescription() {
        if (null != tvDescription && null != cvDescription) {
            tvDescription.setText(cvDescription);
            tvDescription.setTextColor(getDarkColor());
        }
    }

    /**
     * 显示代金券优惠钱数
     */
    private void showMoney() {
        if (null != tvMoney && null != cvMoney) {
            tvMoney.setText(cvMoney);
        }
    }

    /**
     * 显示代金券使用期限的开始时间
     */
    private void showStartTime() {
        if (null != tvStartTime && null != cvStartTime) {
            tvStartTime.setText(cvStartTime);
        }
    }

    /**
     * 显示代金券使用期限的开始时间
     */
    private void showEndTime() {
        if (null != tvEndTime && null != cvEndTime) {
            tvEndTime.setText(cvEndTime);
        }
    }

    /**
     * 显示是否使用过
     */
    private void showUsed() {
        if (null != tvUsed) {
            tvUsed.setText(cvUsed ? "已过期" : "未使用");
        }
    }

    /**
     * 显示背景颜色
     */
    private void showBgColor() {
        if (null != cavBg) {
            if (cvUsed) {
                cavBg.setCardBackgroundColor(ContextCompat.getColor(getContext(), DEFAULTBGCOLORN));
            } else {
                cavBg.setCardBackgroundColor(cvBgColor);
            }
        }
    }

    /**
     * 显示已过期图标
     */
    private void showExpired() {
        if (null != ivExpired) {
            if (cvUsed) {
                ivExpired.setVisibility(VISIBLE);
            } else {
                ivExpired.setVisibility(GONE);
            }
        }
    }

    /**
     * 显示赠送按钮
     */
    private void showGive() {
        if (null != tvGive) {
            if (cvUsed) {
                tvGive.setVisibility(GONE);
            } else {
                tvGive.setVisibility(VISIBLE);
            }
        }
    }

    /**
     * 设置监听事件
     *
     * @param onCVSubClickListener
     */
    public void setOnCVSubClickListener(OnCVSubClickListener onCVSubClickListener) {
        this.onCVSubClickListener = onCVSubClickListener;
    }

    public Drawable getCvLogo() {
        return cvLogo;
    }

    public CouponView setCvLogo(Drawable cvLogo) {
        this.cvLogo = cvLogo;
        return this;
    }

    public String getCvMoney() {
        return cvMoney;
    }

    public CouponView setCvMoney(String cvMoney) {
        this.cvMoney = cvMoney;
        return this;
    }

    public int getCvBgColor() {
        return cvBgColor;
    }

    public CouponView setCvBgColor(int cvBgColor) {
        this.cvBgColor = cvBgColor;
        return this;
    }

    public String getCvStartTime() {
        return cvStartTime;
    }

    public CouponView setCvStartTime(String cvStartTime) {
        this.cvStartTime = cvStartTime;
        return this;
    }

    public String getCvEndTime() {
        return cvEndTime;
    }

    public CouponView setCvEndTime(String cvEndTime) {
        this.cvEndTime = cvEndTime;
        return this;
    }

    public String getCvDescription() {
        return cvDescription;
    }

    public CouponView setCvDescription(String cvDescription) {
        this.cvDescription = cvDescription;
        return this;
    }

    public boolean isCvUsed() {
        return cvUsed;
    }

    public CouponView setCvUsed(boolean cvUsed) {
        this.cvUsed = cvUsed;
        return this;
    }
}
