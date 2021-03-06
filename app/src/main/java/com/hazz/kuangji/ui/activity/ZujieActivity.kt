package com.hazz.kuangji.ui.activity

import android.content.Intent
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import androidx.appcompat.widget.Toolbar
import com.hazz.kuangji.R
import com.hazz.kuangji.base.BaseActivity
import com.hazz.kuangji.mvp.contract.LoginContract
import com.hazz.kuangji.mvp.model.Home
import com.hazz.kuangji.mvp.model.bean.MyAsset
import com.hazz.kuangji.mvp.presenter.HomePresenter
import com.hazz.kuangji.mvp.presenter.ZichanPresenter
import com.hazz.kuangji.utils.*
import com.hazz.kuangji.widget.SafeCheckDialog
import kotlinx.android.synthetic.main.charge.mToolBar
import kotlinx.android.synthetic.main.zujie_activity.*


class ZujieActivity : BaseActivity(), LoginContract.HomeView, TextWatcher, LoginContract.ZichanView {

    override fun myAsset(msg: MyAsset) {

        val assets = msg.assets
        if (!assets.isNullOrEmpty()) {
            for (a in assets) {
                if (a.coin == "USDT") {
                    tv_yue.text = "账户余额:" + a.balance
                }
            }
        }
    }

    override fun afterTextChanged(s: Editable?) {
        val mul = BigDecimalUtil.mul(price, rate, 4)
        val div = BigDecimalUtil.div(s.toString(), price, 4)
        val mul1 = BigDecimalUtil.mul(mul, div, 4)
        tv_yuji.text = "预计每日收益" + BigDecimalUtil.mul(mul1, "0.7", 4) + "FIL"
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    override fun getHome(msg: Home) {

    }

    override fun zuyongSucceed(msg: String) {
        SToast.showText(msg)
        finish()

    }


    private var produce: Home.ProductsBean? = null

    private var mHomePresenter: HomePresenter = HomePresenter(this)
    private var rate = "0.1"
    private var coin = "USDT"
    private var id = ""
    private var price = ""
    private var mZichanPresenter: ZichanPresenter = ZichanPresenter(this)
    override fun layoutId(): Int {
        return R.layout.zujie_activity
    }

    override fun initData() {
        mZichanPresenter.myAsset()
    }

    // private var mChargePresenter: ChargePresenter = ChargePresenter(this)

    override fun initView() {
        ToolBarCustom.newBuilder(mToolBar as Toolbar)
                .setLeftIcon(R.mipmap.back_white)
                .setTitle(getString(R.string.zuyong))
                .setTitleColor(resources.getColor(R.color.color_white))
                //  .setRightText("租用规则")
                .setToolBarBgRescource(R.drawable.bg_hangqing)
                // .setRightTextIsShow(true)
                .setOnLeftIconClickListener { view -> finish() }
//                .setOnRightClickListener {
//                    startActivity(Intent(this, RuleActivity::class.java))
//
//                }
        produce = intent.getSerializableExtra("produce") as Home.ProductsBean?
        coin = produce!!.coin
        id = produce!!.id
        price = produce!!.price
        tv_name.text = produce!!.name
        tv_amount.text = BigDecimalUtil.mul(produce!!.price, "1", 4) + "USDT"
        //tv_suanli.text = produce!!.power
        tv_time.text = produce!!.round + "天"
        rate = produce!!.rate


    }

    private var mPasswordDialog: SafeCheckDialog? = null
    override fun start() {
        tv_xieyi.setOnClickListener {

            startActivity(Intent(this, RuleActivity::class.java))
        }

        et_num.addTextChangedListener(this)

        mTvLogin.setOnClickListener {
            if (cb.isChecked) {

                if (TextUtils.isEmpty(et_num.text.toString())) {
                    SToast.showText("请输入数量")
                    return@setOnClickListener
                }
                if (TextUtils.isEmpty(et_name.text.toString())) {
                    SToast.showText("请输入真实姓名")
                    return@setOnClickListener
                }
                if (TextUtils.isEmpty(et_phone.text.toString())) {
                    SToast.showText("请输入手机号码")
                    return@setOnClickListener
                }
                if (TextUtils.isEmpty(et_phone.text.toString())) {
                    SToast.showText("请输入收货地址")
                    return@setOnClickListener
                }

                if (mPasswordDialog == null) {
                    mPasswordDialog = SafeCheckDialog(this)
                            .setCancelListener { }
                            .setForgetListener {
                                startActivity(Intent(this, FindZijinPwdActivity::class.java))

                            }

                            .setConfirmListener { _, password ->

                                mHomePresenter.zuyong(coin, id, password, et_num.text.toString(), et_name.text.toString(), et_phone.text.toString(), et_address.text.toString())

                            }.setCancelListener {

                            }
                }
                mPasswordDialog!!.show()

            } else {
                SToast.showText("请阅读租用服务协议")
            }

        }
    }


}
