package com.hazz.whb.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hazz.whb.R
import com.hazz.whb.mvp.model.bean.CurrentInventBean

class CurrentFinishInventAdapter(data:List<CurrentInventBean.CompletedOrdersBean>?) : BaseQuickAdapter<CurrentInventBean.CompletedOrdersBean, BaseViewHolder>(R.layout.item_current_finish,data) {
    override fun convert(helper: BaseViewHolder, item: CurrentInventBean.CompletedOrdersBean?) {
        val po: CurrentInventBean.CompletedOrdersBean= data[0]
        item?.let {
            if(po.id!=it.id){
                helper.setVisible(R.id.white_tv,false)
            }
            helper.setText(R.id.tv_no, mContext.getString(R.string.text_order_no, it.number))
            helper.setText(R.id.item1,  String.format("%.4f",it.amount.toDouble()))
            helper.setText(R.id.item2, it.length+"天")
            helper.setText(R.id.item3, String.format("%.2f",it.return_rate.toDouble()*100) + "%")
            helper.setText(R.id.item4, String.format("%.4f",it.revenue.toDouble()))
            helper.setText(R.id.item5, String.format("%.4f",it.amount.toDouble()))
            helper.setText(R.id.tv_time, it.created_at)
            helper.setText(R.id.tv_redeem_time, it.updated_at)
//            helper.setText(R.id.tv_redeem_time, it.created_at)
        }
    }

}