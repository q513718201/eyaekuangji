package com.hazz.whb.ui.adapter


import android.graphics.Color
import android.view.View
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hazz.whb.R
import com.hazz.whb.mvp.model.DuihuanRecord
import com.hazz.whb.mvp.model.bean.TibiRecord

class DuihuanRecordAdapter(layoutResId: Int, data: List<DuihuanRecord.ListBean>?) : BaseQuickAdapter<DuihuanRecord.ListBean, BaseViewHolder>(layoutResId, data) {


    lateinit var onConfirm: (View, Int) -> Unit

    override fun convert(helper: BaseViewHolder, item: DuihuanRecord.ListBean) {

        helper.setText(R.id.tv_from, item.coin_from_amount+item.coin_from)
        helper.setText(R.id.tv_to, item.coin_to_amount+item.coin_to)
        helper.setText(R.id.tv_time, item.updated_at)

        //helper.setText(R.id.tv_shouxu, item.updated_at)
    }
}
