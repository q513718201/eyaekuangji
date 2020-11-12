package com.hazz.whb.ui.activity

import android.graphics.Color
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.bigkoo.pickerview.view.TimePickerView
import com.hazz.whb.R
import com.hazz.whb.base.BaseActivity
import com.hazz.whb.mvp.contract.LoginContract
import com.hazz.whb.mvp.model.bean.Kuangji
import com.hazz.whb.mvp.model.bean.Mingxi
import com.hazz.whb.mvp.presenter.KuangjiPresenter
import com.hazz.whb.ui.adapter.MIngxiRecordAdapter
import com.hazz.whb.utils.ToolBarCustom
import com.hazz.whb.widget.RewardItemDeco
import com.scwang.smartrefresh.layout.util.DensityUtil
import kotlinx.android.synthetic.main.invitefriends_record.*
import java.text.SimpleDateFormat
import java.util.*


class MingxiRecordActivity : BaseActivity(), LoginContract.kuangjiView {

    override fun getKuangji(msg: Kuangji) {

    }

    override fun getMingxi(msg: Mingxi) {
        mAdapter!!.setNewData(msg.list)

    }


    override fun layoutId(): Int {
        return R.layout.tibi_record
    }

    override fun initData() {

    }


    private var mAdapter: MIngxiRecordAdapter? = null
    private var year=0
    private var month=0
    private var day=0

    private var mKuangjiPresenter: KuangjiPresenter = KuangjiPresenter(this)
    private var pvTime: TimePickerView? = null
    override fun initView() {
        ToolBarCustom.newBuilder(mToolBar as Toolbar)
                .setLeftIcon(R.mipmap.back_white)
                .setTitle("收益明细")
                .setToolBarBgRescource(R.drawable.bg_hangqing)
                .setTitleColor(resources.getColor(R.color.color_white))
                .setRightOneIcon(R.mipmap.bt_pick_time)
                .setRightOneIconIsShow(true)
                .setOnLeftIconClickListener { view -> finish() }
                .setOnRightIconClickListener {
                    showTime()
                }


        recycle_view.layoutManager = LinearLayoutManager(this)//创建布局管理
        mAdapter = MIngxiRecordAdapter(R.layout.item_mingxi, null)
        recycle_view.adapter = mAdapter
        mAdapter!!.bindToRecyclerView(recycle_view)
        mAdapter!!.setEmptyView(R.layout.fragment_empty)
        val leftRightOffset = DensityUtil.dp2px(10f)

        recycle_view.addItemDecoration(
                RewardItemDeco(
                        0,
                        0,
                        0,
                        leftRightOffset,
                        0
                )
        )

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        year = calendar.get(Calendar.YEAR)
        month = calendar.get(Calendar.MONTH)+1
        day = calendar.get(Calendar.DAY_OF_MONTH)
        var time= "$year-$month-$day"
        mKuangjiPresenter.mingxi(time,time)
    }

    private fun showTime() {
        val startDate = Calendar.getInstance()
        startDate.set(2013, 0, 23)
        val endDate = Calendar.getInstance()
        endDate.set(2029, 11, 28)
        //时间选择器

        pvTime = TimePickerBuilder(this, OnTimeSelectListener { start, end ->

            mKuangjiPresenter.mingxi(start,end)

        }).setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .setSubCalSize(20)//滚轮文字大小
                .setTitleSize(20)//标题文字大小
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setTextColorCenter(Color.parseColor("#0D6E62"))//设置选中项的颜色
                .setTitleColor(Color.BLACK)//标题文字颜色
                .setRangDate(startDate, endDate)//起始终止年月日设定
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .build()
        pvTime!!.setDate(Calendar.getInstance())//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime!!.show()

    }

    private fun getTimes(date: Date): String {//可根据需要自行截取数据显示
        val format = SimpleDateFormat("yyyy-MM-dd")
        return format.format(date)
    }

    override fun start() {


    }


}
