package com.hazz.whb.ui.adapter


import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hazz.whb.R
import com.hazz.whb.mvp.model.bean.Friends

class FriendsAdapter(layoutResId: Int, data: List<Friends.InviteUsersBean>?) : BaseQuickAdapter<Friends.InviteUsersBean, BaseViewHolder>(layoutResId, data) {


    lateinit var onConfirm: (View, Int) -> Unit

    override fun convert(helper: BaseViewHolder, item: Friends.InviteUsersBean) {

        helper.setText(R.id.user_name, item.username)
        helper.setText(R.id.invite_time, "于"+item.create_at+"注册")

//
//        helper.itemView.setOnClickListener {
//
//            mContext.startActivity(Intent(mContext, MsgDescActivity::class.java).putExtra("message", item))
//        }
    }
}
