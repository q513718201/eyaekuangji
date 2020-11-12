package com.hazz.whb.ui.adapter

import android.content.Intent
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hazz.whb.R
import com.hazz.whb.mvp.model.bean.CurrentInventBean
import com.hazz.whb.ui.activity.FindZijinPwdActivity
import com.hazz.whb.utils.ToastUtils
import com.hazz.whb.widget.SafeCheckDialog

class CurrentInventAdapter(layoutResId: Int, data: List<CurrentInventBean.OrdersBean>?) : BaseQuickAdapter<CurrentInventBean.OrdersBean, BaseViewHolder>(layoutResId, data) {
    private var mPasswordDialog: SafeCheckDialog? = null
    override fun convert(helper: BaseViewHolder, item: CurrentInventBean.OrdersBean?) {
        val po: CurrentInventBean.OrdersBean = data[0]
        item?.let {
            if (po.id != it.id) {
                helper.setVisible(R.id.white_tv, false)
            }
            helper.setText(R.id.tv_no, mContext.getString(R.string.text_order_no, it.number))
                    .addOnClickListener(R.id.bt_transfers)
                    .setText(R.id.tv_days, mContext.getString(R.string.text_cur_days, it.days))
                    .setText(R.id.item1, String.format("%.4f",it.amount.toDouble()))
                    .setText(R.id.item2, it.length+"天")
                    .setText(R.id.item3, String.format("%.2f",it.return_rate.toDouble()*100) + "%")
                    .setText(R.id.item4, String.format("%.4f",it.revenue.toDouble()))
                    .setText(R.id.tv_time, it.created_at)
            var image: View = helper.getView<View>(R.id.bt_transfers)
            image.isEnabled = it.status == 1
            when (it.status) {
                0 -> image.setBackgroundResource(R.drawable.gray_tv)
                1 -> {
                    image.setBackgroundResource(R.drawable.blue_tv)
                }
            }


        }


    }
}
