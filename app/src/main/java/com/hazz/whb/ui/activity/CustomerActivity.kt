package com.hazz.whb.ui.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.widget.Toolbar
import com.hazz.whb.R
import com.hazz.whb.base.BaseActivity
import com.hazz.whb.mvp.contract.LoginContract
import com.hazz.whb.mvp.model.bean.Coin
import com.hazz.whb.mvp.model.bean.Friends
import com.hazz.whb.mvp.model.bean.UserInfo
import com.hazz.whb.mvp.presenter.CoinPresenter
import com.hazz.whb.net.UrlPaths.URL_INVITE
import com.hazz.whb.utils.*
import kotlinx.android.synthetic.main.invitefriends.*


class CustomerActivity : BaseActivity(){

    override fun layoutId(): Int {
        return R.layout.activity_custom
    }

    override fun initData() {

    }

    @SuppressLint("SetTextI18n")
    override fun initView() {
        ToolBarCustom.newBuilder(mToolBar as Toolbar)
                .setLeftIcon(R.mipmap.back_white)
                .setTitle("联系客服")
                .setTitleColor(resources.getColor(R.color.color_white))
                .setOnLeftIconClickListener { view -> finish() }



    }

    override fun start() {

    }


}
