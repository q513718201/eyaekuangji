package com.hazz.whb.ui.adapter


import android.content.Intent
import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hazz.whb.R
import com.hazz.whb.mvp.model.bean.Kuangji
import com.hazz.whb.ui.activity.MingxiRecordActivity
import com.hazz.whb.utils.BigDecimalUtil

class CoinAdapter(layoutResId: Int, data: List<Kuangji.MachineListBean.ListBean>?) : BaseQuickAdapter<Kuangji.MachineListBean.ListBean, BaseViewHolder>(layoutResId, data) {


    lateinit var onConfirm: (View, Int) -> Unit

    override fun convert(helper: BaseViewHolder, item: Kuangji.MachineListBean.ListBean) {

        helper.setText(R.id.tv_name, item.product)
        helper.setText(R.id.tv_day, item.remain)
        if(item.revenue!=null){
            helper.setText(R.id.tv_leiji, BigDecimalUtil.mul(item.revenue,"1",2)+"WHB")
        }

        helper.setText(R.id.tv_touru, BigDecimalUtil.mul(item.price,"1",2)+"USDT")
        if( item.yesterday!=null){
            helper.setText(R.id.tv_yesterday, item.yesterday+"WHB")
        }


        when (item.state) {
            "1" ->{
                helper.setText(R.id.tv_state, "已运行")
                helper.setTextColor(R.id.tv_state, mContext.resources.getColor(R.color.blue))
            }
            "0" -> {
                helper.setText(R.id.tv_state, "已停止")
                helper.setTextColor(R.id.tv_state, mContext.resources.getColor(R.color.redF4))
            }
        }
        helper.getView<TextView>(R.id.tv_mingxi).setOnClickListener {
            mContext.startActivity(Intent(mContext, MingxiRecordActivity::class.java))
        }
    }
}
