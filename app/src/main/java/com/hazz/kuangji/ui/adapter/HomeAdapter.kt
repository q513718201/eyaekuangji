package com.hazz.kuangji.ui.adapter


import android.content.Intent
import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hazz.kuangji.R
import com.hazz.kuangji.mvp.model.Home
import com.hazz.kuangji.ui.activity.KuangjiDesc2Activity
import com.hazz.kuangji.ui.activity.KuangjiDescActivity
import com.hazz.kuangji.ui.activity.ZujieActivity
import com.hazz.kuangji.utils.BigDecimalUtil

class HomeAdapter(layoutResId: Int, data: List<Home.ProductsBean>?) : BaseQuickAdapter<Home.ProductsBean, BaseViewHolder>(layoutResId, data) {


    lateinit var onConfirm: (View, Int) -> Unit

    override fun convert(helper: BaseViewHolder, item: Home.ProductsBean) {

        helper.setText(R.id.tv_name, item.name)
        helper.setText(R.id.tv_time, "合约周期:"+item.round+"天")
        helper.setText(R.id.tv_suanli, "矿机算力:"+item.power)
        helper.setText(R.id.tv_amount, BigDecimalUtil.mul(item.price,"1",4)+"USDT")

        helper.getView<TextView>(R.id.tv_zu).setOnClickListener {
            mContext.startActivity(Intent(mContext, ZujieActivity::class.java).putExtra("produce",item))

        }


        if(helper.position==0){
            helper.getView<TextView>(R.id.chakan).setOnClickListener {
                mContext.startActivity(Intent(mContext, KuangjiDescActivity::class.java).putExtra("name",item.name))

            }
        }else{
            helper.getView<TextView>(R.id.chakan).setOnClickListener {
                mContext.startActivity(Intent(mContext, KuangjiDesc2Activity::class.java).putExtra("name",item.name))

            }
        }

    }

}
