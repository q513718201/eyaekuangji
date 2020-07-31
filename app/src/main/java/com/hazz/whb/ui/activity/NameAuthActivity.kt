package com.hazz.whb.ui.activity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.hazz.whb.R
import com.hazz.whb.base.BaseActivity
import com.hazz.whb.mvp.contract.LoginContract
import com.hazz.whb.mvp.model.Image
import com.hazz.whb.mvp.presenter.NameAuthPresenter
import com.hazz.whb.utils.SToast
import com.hazz.whb.utils.ToastUtils
import com.hazz.whb.utils.ToolBarCustom
import com.hazz.whb.widget.ProgressDialog
import com.werb.pickphotoview.PickPhotoView
import com.werb.pickphotoview.util.PickConfig
import kotlinx.android.synthetic.main.mine_activity_nameauth.*
import kotlinx.android.synthetic.main.mine_activity_scan_coin_address.*
import kotlinx.android.synthetic.main.mine_activity_scan_coin_address.mToolbar


class NameAuthActivity : BaseActivity(), LoginContract.AuthView {


    private var countDownTimer: CountDownTimer? = null

    private var mNameAuthPresenter: NameAuthPresenter = NameAuthPresenter(this)
    private val REQUEST_AREACODE_CODE = 10005
    private var iv1: String = ""
    private var iv2: String = ""
    private var iv3: String = ""
    private var areaCode="+86"
    private var currentImage = 1
    private var currentType=0

    override fun layoutId(): Int {

        return R.layout.mine_activity_nameauth
    }

    override fun initData() {
    }

    override fun initView() {

        ToolBarCustom.newBuilder(mToolbar as Toolbar)
                .setTitle("实名认证")
                .setLeftIcon(R.mipmap.back_white)
                .setToolBarBgRescource(R.drawable.bg_hangqing)
                .setTitleColor(resources.getColor(R.color.color_white))
                .setOnLeftIconClickListener { finish() }

    }
    private fun initPop() {
        val contentview = View.inflate(this, R.layout.mine_popwnd_card_select, null)
        val tv_china = contentview.findViewById<TextView>(R.id.tv_china)
        val tv_jingwai = contentview.findViewById<TextView>(R.id.tv_jingwai)
        contentview.isFocusableInTouchMode = true
        var popupWindow = PopupWindow(contentview, rl_type.measuredWidth, LinearLayout.LayoutParams.WRAP_CONTENT)
        popupWindow.isOutsideTouchable = true
        popupWindow.isFocusable = true
        popupWindow.showAsDropDown(rl_type)
        tv_china.setOnClickListener {
            currentType=0
            mTvCardType.text="身份证"
            popupWindow.dismiss()
        }
        tv_jingwai.setOnClickListener {
            currentType=1
            mTvCardType.text="护照"
            popupWindow.dismiss()
        }
    }
    override fun start() {
        rl_type.setOnClickListener {
            initPop()
        }

        iv_upload_id1.setOnClickListener {
            currentImage = 1
            permissionsnew!!.request(
                    Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE
                    , Manifest.permission.WRITE_EXTERNAL_STORAGE
            ).subscribe { permission ->
                if (permission!!) {
                    PickPhotoView.Builder(this)
                            .setPickPhotoSize(1)
                            .setClickSelectable(true)
                            .setShowCamera(true)
                            .setSpanCount(3)
                            .setLightStatusBar(true)
                            .setStatusBarColor(R.color.color_white)
                            .setToolbarColor(R.color.color_white)
                            .setToolbarTextColor(R.color.color_black)
                            .setShowGif(false)
                            .start()
                } else {
                    showMissingPermissionDialog()
                }
            }

        }

        iv_upload_id2.setOnClickListener {
            currentImage = 2
            permissionsnew!!.request(
                    Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE
                    , Manifest.permission.WRITE_EXTERNAL_STORAGE
            ).subscribe { permission ->
                if (permission!!) {
                    PickPhotoView.Builder(this)
                            .setPickPhotoSize(1)
                            .setClickSelectable(true)
                            .setShowCamera(true)
                            .setSpanCount(3)
                            .setLightStatusBar(true)
                            .setStatusBarColor(R.color.color_white)
                            .setToolbarColor(R.color.color_white)
                            .setToolbarTextColor(R.color.color_black)
                            .setShowGif(false)
                            .start()
                } else {
                    showMissingPermissionDialog()
                }
            }

        }

        iv_upload_id3.setOnClickListener {
            currentImage = 3
            permissionsnew!!.request(
                    Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE
                    , Manifest.permission.WRITE_EXTERNAL_STORAGE
            ).subscribe { permission ->
                if (permission!!) {
                    PickPhotoView.Builder(this)
                            .setPickPhotoSize(1)
                            .setClickSelectable(true)
                            .setShowCamera(true)
                            .setSpanCount(3)
                            .setLightStatusBar(true)
                            .setStatusBarColor(R.color.color_white)
                            .setToolbarColor(R.color.color_white)
                            .setToolbarTextColor(R.color.color_black)
                            .setShowGif(false)
                            .start()
                } else {
                    showMissingPermissionDialog()
                }
            }

        }

        rl_area.setOnClickListener {
            startActivityForResult(Intent(this, CountryActivity::class.java), REQUEST_AREACODE_CODE)
        }
        mTvConfirm.setOnClickListener {
            if (TextUtils.isEmpty(mEtName.text)) {
                ToastUtils.showToast(this, "请输入姓名")
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(mEtIdCard.text)) {
                ToastUtils.showToast(this, "请输入证件号码")
                return@setOnClickListener
            }
//            if (currentType == 0) {
//
//                val idCardValidate = IdCardCheckUtil.IDCardValidate(mEtIdCard.text.toString())
//                if (!TextUtils.isEmpty(idCardValidate)) {
//                    ToastUtils.showToast(this, idCardValidate)
//                    return@setOnClickListener
//                }
//
//
//            }

            if (TextUtils.isEmpty(iv1)) {
                ToastUtils.showToast(this, "请选择证件照证正面")
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(iv2)) {
                ToastUtils.showToast(this, "请选择证件照反面")
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(iv3)) {
                ToastUtils.showToast(this, "请选择手持证件照")
                return@setOnClickListener
            }
            mNameAuthPresenter.auth(areaCode,mEtName.text.toString(),currentType,mEtIdCard.text.toString(),iv1,iv2,iv3)


        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_AREACODE_CODE) {
            val areaName = data?.getStringExtra("countryName") ?: "中国"
            val countryNumber = data?.getStringExtra("countryNumber") ?: "+86"
            mTvAreaCode.text=areaName
            areaCode=countryNumber
        }

        if (requestCode == PickConfig.PICK_PHOTO_DATA) {
            if(data!=null){
                when (currentImage) {
                    1 -> {
                        val paths = data.getSerializableExtra(PickConfig.INTENT_IMG_LIST_SELECT) as ArrayList<String>
                        Glide.with(this)
                                .load(paths[0])
                                .thumbnail(0.1f)
                                .into(iv_upload_id1)
                        Log.d("junjun",paths[0])
                        iv_upload_id1_default.visibility= View.GONE
                        mNameAuthPresenter.upload(paths[0])

                    }
                    2 -> {
                        val paths = data.getSerializableExtra(PickConfig.INTENT_IMG_LIST_SELECT) as ArrayList<String>
                        Glide.with(this)
                                .load(paths[0])
                                .thumbnail(0.1f)
                                .into(iv_upload_id2)

                        iv_upload_id2_default.visibility= View.GONE
                        mNameAuthPresenter.upload(paths[0])

                    }
                    3-> {
                        val paths = data.getSerializableExtra(PickConfig.INTENT_IMG_LIST_SELECT) as ArrayList<String>
                        Glide.with(this)
                                .load(paths[0])
                                .thumbnail(0.1f)
                                .into(iv_upload_id3)

                        iv_upload_id3_default.visibility= View.GONE
                        mNameAuthPresenter.upload(paths[0])

                    }
                }
            }

        }


    }

    override fun uploadSucceed(msg: Image) {
        when (currentImage) {
            1->iv1=msg.url
            2->iv2=msg.url
            3->iv3=msg.url
        }
    }

    override fun authSucceed(msg: String) {
        SToast.showText(msg)
        finish()
    }
}