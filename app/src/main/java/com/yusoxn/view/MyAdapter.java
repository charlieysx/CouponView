package com.yusoxn.view;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * MyOrderAdapter
 * <p>
 * Created by Yusxon on 17/2/2.
 */

public class MyAdapter extends RecyclerArrayAdapter<CouponBean> {

    interface OnRecyclerViewItemSubClickListener {
        /**
         * 点击某一个item中的按钮
         *
         * @param position
         */
        void onItemSubClick(int position);
    }

    private OnRecyclerViewItemSubClickListener mListener;

    public void setOnRecyclerViewItemSubClickListener(OnRecyclerViewItemSubClickListener mListener) {
        this.mListener = mListener;
    }

    public MyAdapter(Context context, List<CouponBean> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coupon,
                parent, false));
    }

    @Override
    public void OnBindViewHolder(BaseViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        CouponBean couponBean = mObjects.get(position);
        viewHolder.couponView
                .setCvLogo(ContextCompat.getDrawable(getContext(), couponBean.logo))
                .setCvDescription(couponBean.description)
                .setCvMoney(couponBean.money)
                .setCvStartTime(couponBean.startTime)
                .setCvEndTime(couponBean.endTime)
                .setCvUsed(couponBean.used)
                .show();
    }

    class ViewHolder extends BaseViewHolder {

        CouponView couponView;

        public ViewHolder(View itemView) {
            super(itemView);

            couponView = (CouponView) itemView.findViewById(R.id.cv_coupon);
            couponView.setOnCVSubClickListener(new CouponView.OnCVSubClickListener() {
                @Override
                public void onSubClick() {
                    if(null != mListener) {
                        mListener.onItemSubClick(getDataPosition());
                    }
                }
            });
        }


    }

}
