package com.hazz.whb.ui.adapter


import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.google.gson.Gson
import com.hazz.whb.R
import com.hazz.whb.mvp.model.bean.RemarkBean
import com.hazz.whb.mvp.model.bean.TibiRecord
import com.hazz.whb.utils.BigDecimalUtil

class TibiRecordAdapter(layoutResId: Int, data: List<TibiRecord.ListBean>?) : BaseQuickAdapter<TibiRecord.ListBean, BaseViewHolder>(layoutResId, data) {


    lateinit var onConfirm: (View, Int) -> Unit

    override fun convert(helper: BaseViewHolder, item: TibiRecord.ListBean) {

        helper.setText(R.id.tv_name, item.coin)
        helper.setText(R.id.tv_time, item.create_at)
        when (item.status) {
            "pass" -> {
                helper.setTextColor(R.id.tv_status, Color.parseColor("#4CDB93"))
                helper.setText(R.id.tv_status, "已完成")
            }
            "wait" -> {
                helper.setTextColor(R.id.tv_status, Color.parseColor("#FF973E"))
                helper.setText(R.id.tv_status, "审核中")
            }
            "fail" -> {
                helper.setTextColor(R.id.tv_status, Color.parseColor("#F86442"))
                helper.setText(R.id.tv_status, "提币失败")
            }
        }
        if (item.coin == "USDT") {
            helper.getView<ImageView>(R.id.iv).setImageResource(R.mipmap.usdt)
        } else {
            helper.getView<ImageView>(R.id.iv).setImageResource(R.mipmap.ic_whb_20)
        }
        Log.e("Remark", item.remark)
        var remark = Gson().fromJson<RemarkBean>(item.remark.replace("/", ""), RemarkBean::class.java)
        helper.setText(R.id.tv_price, "${BigDecimalUtil.formatString(remark.rate, 2)} CNY")
        helper.setText(R.id.tv_real_money, "${BigDecimalUtil.formatString(remark.get_amount, 2)} ${item.coin}")
        helper.setText(R.id.tv_amount, "${BigDecimalUtil.formatString(item.amount, 2)} ${item.coin}")


    }
}
