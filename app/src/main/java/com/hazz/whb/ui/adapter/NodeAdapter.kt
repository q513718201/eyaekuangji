package com.hazz.whb.ui.adapter


import android.content.Intent
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hazz.whb.R
import com.hazz.whb.mvp.model.bean.Node
import com.hazz.whb.ui.activity.NodeSecondActivity
import com.hazz.whb.utils.BigDecimalUtil

class NodeAdapter(layoutResId: Int, data: List<Node.InviteUsersBean>?) : BaseQuickAdapter<Node.InviteUsersBean, BaseViewHolder>(layoutResId, data) {


    lateinit var onConfirm: (View, Int) -> Unit

    override fun convert(helper: BaseViewHolder, item: Node.InviteUsersBean) {

        if(4+helper.position<10){
            helper.setText(R.id.tv_id, "0"+(4+helper.position).toString())
        }else{
            helper.setText(R.id.tv_id, (4+helper.position).toString())
        }

        helper.setText(R.id.tv_name, item.username)
        helper.setText(R.id.tv_amount, BigDecimalUtil.mul(item.self_purchase,"1",4)+"USDT")


        helper.itemView.setOnClickListener {

            mContext.startActivity(Intent(mContext, NodeSecondActivity::class.java).putExtra("type", item))
        }
    }
}
