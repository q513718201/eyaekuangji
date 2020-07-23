package com.hazz.kuangji.ui.activity

import android.content.ClipData
import android.content.Context
import android.content.Intent
import androidx.appcompat.widget.Toolbar
import com.hazz.kuangji.R
import com.hazz.kuangji.base.BaseActivity
import com.hazz.kuangji.mvp.contract.LoginContract
import com.hazz.kuangji.mvp.model.bean.Charge
import com.hazz.kuangji.mvp.model.bean.ChargeRecord
import com.hazz.kuangji.mvp.model.bean.UserInfo
import com.hazz.kuangji.mvp.presenter.ChargePresenter
import com.hazz.kuangji.utils.QRCodeUtils
import com.hazz.kuangji.utils.SToast
import com.hazz.kuangji.utils.ToolBarCustom
import com.hazz.kuangji.utils.Utils
import kotlinx.android.synthetic.main.charge.*
import kotlinx.android.synthetic.main.charge.mToolBar


class ChargeActivity : BaseActivity(), LoginContract.ChargeView {
    override fun chargeRecord(msg: ChargeRecord) {

    }

    override fun getAddress(msg: Charge) {
        tv_address.text=msg.addr
        val dip2px = Utils.dip2px(this, 180F)

        val createQRCode = QRCodeUtils.createQRCode(msg.addr, dip2px, dip2px, null)
        iv.setImageBitmap(createQRCode)
    }



    private var userInfo: UserInfo? = null
    override fun layoutId(): Int {
        return R.layout.charge
    }

    override fun initData() {
        mChargePresenter.charge("USDT")
    }

    private var mChargePresenter: ChargePresenter = ChargePresenter(this)

    override fun initView() {
        ToolBarCustom.newBuilder(mToolBar as Toolbar)
                .setLeftIcon(R.mipmap.back_white)
                .setTitle(getString(R.string.charge))
                .setTitleColor(resources.getColor(R.color.color_white))
                .setRightText("充值记录")
                .setToolBarBgRescource(R.drawable.bg_hangqing)
                .setRightTextIsShow(true)
                .setOnLeftIconClickListener { view -> finish() }
                .setOnRightClickListener {
                 startActivity(Intent(this,ChargeRecordActivity::class.java))

                }

    }

    override fun start() {
        tv_bt.setOnClickListener {

                val cm = getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
                val clipData = ClipData.newPlainText("invitation_code",tv_address.text.toString())

                cm.primaryClip = clipData

                SToast.showText("已成功复制充值地址")

    }
    }


}
