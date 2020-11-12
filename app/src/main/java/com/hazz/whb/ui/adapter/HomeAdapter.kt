package com.hazz.whb.ui.adapter


import android.content.Intent
import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hazz.whb.R
import com.hazz.whb.mvp.model.Home
import com.hazz.whb.ui.activity.*
import com.hazz.whb.utils.BigDecimalUtil
import com.hazz.whb.utils.SToast
import com.hazz.whb.widget.TipsDialog

class HomeAdapter(layoutResId: Int, data: List<Home.ProductsBean>?) : BaseQuickAdapter<Home.ProductsBean, BaseViewHolder>(layoutResId, data) {


    lateinit var onConfirm: (View, Int) -> Unit
    private var state=0
    private var isSetpwd="0"
    override fun convert(helper: BaseViewHolder, item: Home.ProductsBean) {

        helper.setText(R.id.tv_name, item.name)
        helper.setText(R.id.tv_time, "挖矿周期:" + item.round + "天")
        helper.setText(R.id.tv_suanli, "日产出:" + BigDecimalUtil.mul(item.rate, "1", 4) + "WHB")
        helper.setText(R.id.tv_amount, BigDecimalUtil.mul(item.price, "1", 4) + "USDT")

        helper.getView<TextView>(R.id.tv_zu).setOnClickListener {
            if(isSetpwd == "0"){
                val tipsDialog = TipsDialog(mContext)
                        .setTitle("提示")
                        .setContent("您未设置交易密码，为了您的账户安全请立即前往设置")
                        .setPwd()
                        .setConfirmListener {
                            mContext.startActivity(Intent(mContext, FindZijinPwdActivity::class.java))
                        }

                tipsDialog.show()
            }else{

                mContext.startActivity(Intent(mContext, ZujieActivity::class.java).putExtra("produce", item))
            }




        }


        if (helper.position == 0) {
            helper.getView<TextView>(R.id.chakan).setOnClickListener {
                mContext.startActivity(Intent(mContext, KuangjiDescActivity::class.java).putExtra("name", item.name))

            }
        } else {
            helper.getView<TextView>(R.id.chakan).setOnClickListener {
                mContext.startActivity(Intent(mContext, KuangjiDesc2Activity::class.java).putExtra("name", item.name))

            }
        }

    }

    fun setState(states: Int, tradePasswordStatus: String) {
        state=states
        isSetpwd=tradePasswordStatus
    }

}
