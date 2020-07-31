package com.hazz.whb.ui.adapter


import android.content.Intent
import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hazz.whb.R
import com.hazz.whb.mvp.model.Home
import com.hazz.whb.ui.activity.KuangjiDesc2Activity
import com.hazz.whb.ui.activity.KuangjiDescActivity
import com.hazz.whb.ui.activity.NameAuthActivity
import com.hazz.whb.ui.activity.ZujieActivity
import com.hazz.whb.utils.BigDecimalUtil
import com.hazz.whb.utils.SToast
import com.hazz.whb.widget.TipsDialog

class HomeAdapter(layoutResId: Int, data: List<Home.ProductsBean>?) : BaseQuickAdapter<Home.ProductsBean, BaseViewHolder>(layoutResId, data) {


    lateinit var onConfirm: (View, Int) -> Unit
    private var state=0

    override fun convert(helper: BaseViewHolder, item: Home.ProductsBean) {

        helper.setText(R.id.tv_name, item.name)
        helper.setText(R.id.tv_time, "挖矿周期:" + item.round + "天")
        helper.setText(R.id.tv_suanli, "日产出:" + BigDecimalUtil.mul(item.rate, "1", 4) + "WHB")
        helper.setText(R.id.tv_amount, BigDecimalUtil.mul(item.price, "1", 4) + "USDT")

        helper.getView<TextView>(R.id.tv_zu).setOnClickListener {
            when(state){
                1->{
                    mContext.startActivity(Intent(mContext, ZujieActivity::class.java).putExtra("produce", item))
                }
                2->{
                    SToast.showText("实名认证审核中...")
                    return@setOnClickListener
                }
                else->{

                    val tipsDialog = TipsDialog(mContext)
                            .setTitle("提示")
                            .setContent("为了您的帐户安全,请先进行实名认证")
                            .tips()
                            .setConfirmListener {
                                mContext.startActivity(Intent(mContext, NameAuthActivity::class.java))
                            }

                    tipsDialog.show()
                }
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

    fun setState(states: Int) {
        state=states
    }

}
