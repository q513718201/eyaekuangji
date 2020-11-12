package com.hazz.whb.ui.adapter


import android.view.View
import android.widget.RelativeLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hazz.whb.R
import com.hazz.whb.events.Index
import com.hazz.whb.events.TimeType
import com.hazz.whb.mvp.model.bean.TimeData
import com.hazz.whb.utils.BigDecimalUtil
import com.hazz.whb.utils.RxBus
import org.greenrobot.eventbus.EventBus

class TimeAdapter(layoutResId: Int, data: List<TimeData>?) : BaseQuickAdapter<TimeData, BaseViewHolder>(layoutResId, data) {


    lateinit var onConfirm: (View, Int) -> Unit

    override fun convert(helper: BaseViewHolder, item: TimeData) {

        helper.setText(R.id.tvDay, item.length)
        if (item.return_rate != null) {
            helper.setText(R.id.tvShouyi, BigDecimalUtil.mul(item.return_rate, "100", 2) + "%")
        }

        helper.getView<RelativeLayout>(R.id.rlView).setOnClickListener {
//            RxBus.get().send(TimeType(item))
            EventBus.getDefault().post(TimeType(item))
        }

    }
}
