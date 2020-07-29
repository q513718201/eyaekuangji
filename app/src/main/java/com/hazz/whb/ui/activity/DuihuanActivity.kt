package com.hazz.whb.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.PopupWindow
import androidx.appcompat.widget.Toolbar
import com.hazz.whb.R
import com.hazz.whb.base.BaseActivity
import com.hazz.whb.mvp.contract.LoginContract
import com.hazz.whb.mvp.model.DuihuanRecord
import com.hazz.whb.mvp.model.bean.MyAsset
import com.hazz.whb.mvp.presenter.DuihuanPresenter
import com.hazz.whb.utils.BigDecimalUtil
import com.hazz.whb.utils.SToast
import com.hazz.whb.utils.ToolBarCustom
import kotlinx.android.synthetic.main.duihuan.*


class DuihuanActivity : BaseActivity(), TextWatcher, LoginContract.DuihuanView {
    override fun afterTextChanged(s: Editable?) {

    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        val div = BigDecimalUtil.div(rate, "100", 4)
        et_usdt.setText(BigDecimalUtil.mul(s.toString(),currentRate,2))

        tv_need.text = BigDecimalUtil.mul(s.toString(), shouxu, 2)
        tv_shiji.text = BigDecimalUtil.sub(s.toString(), tv_need.text.toString(), 2)
    }



    override fun layoutId(): Int {
        return R.layout.duihuan
    }

    override fun initData() {

    }

    private var mTiBiPresenter: DuihuanPresenter = DuihuanPresenter(this)
    private var myAsset: MyAsset? = null
    private var popWnd: PopupWindow? = null
    private var currentName = "WHB"
    private var rate = "0.00"
    private var currentRate="0.8"
    private var avaiableAmount = "0"
    private var shouxu = "0"
    private var assets: List<MyAsset.AssetsBean>? = null

    @SuppressLint("SetTextI18n")
    override fun initView() {
        ToolBarCustom.newBuilder(mToolBar as Toolbar)
                .setLeftIcon(R.mipmap.back_white)
                .setTitle("兑换")
                .setTitleColor(resources.getColor(R.color.color_white))
                .setRightText("兑换记录")
                .setToolBarBgRescource(R.drawable.bg_hangqing)
                .setRightTextIsShow(true)
                .setOnLeftIconClickListener { view -> finish() }
                .setOnRightClickListener {
                    startActivity(Intent(this, DuihuanRecordActivity::class.java))

                }
        myAsset = intent.getSerializableExtra("amount") as MyAsset?
        assets = myAsset!!.assets

        for (a in myAsset!!.config) {

            if(a.id=="7"){
                currentRate = a.value
                tv_rate.text="1WHB="+a.value+"USDT"
            }

            if(a.id=="11"){
                shouxu = a.value
            }
        }
        //
        for (a in myAsset!!.assets) {

            if(a.coin==currentName){
               tv_amount.text=a.balance
            }
        }

        et_whb.addTextChangedListener(this)
    }

    override fun start() {


        tv_bt.setOnClickListener {
//            if (TextUtils.isEmpty(et_num.text.toString())) {
//                SToast.showText("请输入提币数量")
//                return@setOnClickListener
//            }
            if (TextUtils.isEmpty(et_pwd.text.toString())) {
                SToast.showText("请输入资金密码")
                return@setOnClickListener
            }
//            if (TextUtils.isEmpty(et_address.text.toString())) {
//                SToast.showText("请输入提币地址")
//                return@setOnClickListener
//            }

            mTiBiPresenter.duanhuan(et_whb.text.toString(), et_pwd.text.toString())
        }


    }

    override fun duihuanSucceed(msg: String) {
        SToast.showText(msg)
        finish()
    }

    override fun duihuanRecord(msg: DuihuanRecord) {
    }


}
