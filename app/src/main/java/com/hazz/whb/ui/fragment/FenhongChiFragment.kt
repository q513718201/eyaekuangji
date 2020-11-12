package com.hazz.whb.ui.fragment

import android.text.TextUtils
import android.view.Gravity
import com.hazz.whb.R
import com.hazz.whb.base.BaseFragment
import com.hazz.whb.events.TimeType
import com.hazz.whb.mvp.contract.LoginContract
import com.hazz.whb.mvp.model.bean.MyAsset
import com.hazz.whb.mvp.model.bean.TimeData
import com.hazz.whb.mvp.presenter.FenhongChiPresenter
import com.hazz.whb.mvp.presenter.ZichanPresenter
import com.hazz.whb.utils.RxBus
import com.hazz.whb.utils.SToast
import com.hazz.whb.view.CheckFragment
import com.hazz.whb.widget.TimeSelectPopWindow
import kotlinx.android.synthetic.main.fragment_fenhongchi.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class FenhongChiFragment : BaseFragment(), LoginContract.FenhongView, LoginContract.ZichanView {
    private var timeData: List<TimeData>? = null
    private var mZichanPresenter: ZichanPresenter = ZichanPresenter(this)
    private var mFenhongChiPresenter: FenhongChiPresenter = FenhongChiPresenter(this)
    private var myAsset: MyAsset? = null
    private var avaiableAmount = "0"
    private lateinit var setting_id: String
    private var mTimeSelectPopWindow: TimeSelectPopWindow? = null
    override fun myAsset(msg: MyAsset) {
        myAsset = msg
        val assets = msg.assets

        for (coin in assets) {
            if (coin.coin == "WHB") {
                avaiableAmount = coin.balance
                tvHaveCoin.text = coin.balance
            }
        }

    }

    override fun commitSuccess(msg: String) {
        SToast.showText(msg)
        lazyLoad()
        var ch:CheckFragment=CheckFragment()
        ch.index=1
        EventBus.getDefault().post(ch)
    }

    override fun timeDataSuccess(msg: List<TimeData>) {
        timeData = msg!!
    }


    override fun getLayoutId(): Int {
        EventBus.getDefault().register(this)
        return R.layout.fragment_fenhongchi
    }

    override fun initView() {
        rl_choose.setOnClickListener {
            showPopWindow()
        }
        tv_all.setOnClickListener {
            et_num.setText(avaiableAmount)
        }
        tv_bt.setOnClickListener {
            var num = et_num.text.toString()
            if (TextUtils.isEmpty(num)) {
                SToast.showText("请输入投入数量")
                return@setOnClickListener
            }
            var touru = num.toDouble()
            if (touru < 100) {
                SToast.showText("最少投资金额为100WHB")
            }

            if (TextUtils.isEmpty(et_pwd.text.toString())) {
                SToast.showText("请输入资金密码")
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(setting_id)) {
                SToast.showText("请选择时长")
                return@setOnClickListener
            }

            mFenhongChiPresenter.addCapital(setting_id, et_num.text.toString(), et_pwd.text.toString())
        }

        RxBus.get().observerOnMain(this, TimeType::class.java) {

        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun refreshHomeEvent(event: TimeType) {
        timeChoice(event)
    }

    private fun timeChoice(it: TimeType?) {
        setting_id = it?.data!!.id
        tvTime.text = it?.data!!.length
        mTimeSelectPopWindow?.dismiss()
    }

    private fun showPopWindow() {
        if (null == mTimeSelectPopWindow) {
            mTimeSelectPopWindow = TimeSelectPopWindow(activity, timeData, rl_choose)
        }
        mTimeSelectPopWindow!!.showAsDropDown(rl_choose, 0, 10, Gravity.CENTER)
    }

    override fun lazyLoad() {
        mZichanPresenter.myAsset()
        mFenhongChiPresenter.timeData()
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

}
