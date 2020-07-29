package com.hazz.whb.ui.adapter


import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hazz.whb.R
import com.hazz.whb.mvp.model.bean.ChargeRecord

class ChargeRecordAdapter(layoutResId: Int, data: List<ChargeRecord.ListBean>?) : BaseQuickAdapter<ChargeRecord.ListBean, BaseViewHolder>(layoutResId, data) {


    lateinit var onConfirm: (View, Int) -> Unit

    override fun convert(helper: BaseViewHolder, item: ChargeRecord.ListBean) {

        helper.setText(R.id.tv_name, item.coin)
        helper.setText(R.id.tv_time, item.create_at)
        helper.setText(R.id.tv_amount, "+"+item.amount)

//
//        helper.itemView.setOnClickListener {
//
//            mContext.startActivity(Intent(mContext, MsgDescActivity::class.java).putExtra("message", item))
//        }
    }
}
