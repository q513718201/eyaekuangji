package com.hazz.whb.ui.fragment

import android.content.Intent
import com.hazz.whb.R
import com.hazz.whb.base.BaseFragment
import com.hazz.whb.mvp.contract.LoginContract
import com.hazz.whb.mvp.model.bean.MyState
import com.hazz.whb.mvp.model.bean.Node
import com.hazz.whb.mvp.model.bean.Shenfen
import com.hazz.whb.mvp.model.bean.UserInfo
import com.hazz.whb.mvp.presenter.MinePresenter
import com.hazz.whb.mvp.presenter.NodePresenter
import com.hazz.whb.ui.activity.*
import com.hazz.whb.utils.SPUtil
import com.hazz.whb.utils.SToast
import kotlinx.android.synthetic.main.fragment_mine.*

class MineFragment : BaseFragment(), LoginContract.MyStateView {


    override fun getLayoutId(): Int {
        return R.layout.fragment_mine
    }

    private var minePresenter: MinePresenter = MinePresenter(this)
    private var userInfo: UserInfo? = null
    private var state = 0
    override fun initView() {


        userInfo = SPUtil.getObj("user", UserInfo::class.java)

        if (userInfo != null) {

            mTvUserName.text = userInfo!!.username
            mTvMobile.text = userInfo!!.mobile
        } else {
            val userName = SPUtil.getString("username")
            val mobile = SPUtil.getString("mobile")
            mTvMobile.text = mobile
            mTvUserName.text = userName
        }



        iv_msg.setOnClickListener {
            startActivity(Intent(activity, MsgListActivity::class.java))
        }
        layout_invite.setOnClickListener {
            startActivity(Intent(activity, InviteActivity::class.java))
        }
        layout_setting.setOnClickListener {
            startActivity(Intent(activity, SettingActivity::class.java))
        }

        layout_customer.setOnClickListener {
            startActivity(Intent(activity, CustomerActivity::class.java))
        }

        layout_jiedian.setOnClickListener {
            startActivity(Intent(activity, NodeActivity::class.java))
        }
        layout_incoming.setOnClickListener {
            startActivity(Intent(activity, IncomingActivity::class.java))
        }
        layout_auth.setOnClickListener {
            when (state) {
                1 -> {
                    SToast.showText("已实名")
                    return@setOnClickListener
                }
                2 -> {
                    SToast.showText("审核中")
                    return@setOnClickListener
                }
            }
            startActivity(Intent(activity, NameAuthActivity::class.java))

        }
        layout_about.setOnClickListener {
            startActivity(Intent(activity, RegistRuleActivity::class.java).putExtra("type", 1))
        }

    }

    override fun lazyLoad() {
        minePresenter.myAsset()
    }

    override fun reload() {
        minePresenter.myAsset()
    }

    override fun myState(msg: MyState) {

        iv_type?.setImageResource(when (msg.level) {
            "v1" -> R.mipmap.v1
            "v2" -> R.mipmap.v2
            "v3" -> R.mipmap.v3
            "v4" -> R.mipmap.v4
            "v5" -> R.mipmap.v5
            "v6" -> R.mipmap.v6
            "v7" -> R.mipmap.v7
            else -> R.mipmap.v0
        }
        )
        when (msg.real_name) {
            "0" -> {
                //未实名
                state = 0
                tv_state.text = "未认证"
            }
            "1" -> {
                //已实名
                state = 1
                tv_state.text = "已实名"
            }
            "2" -> {
                //审核zhong
                state = 2
                tv_state.text = "审核中"
            }
            "3" -> {
                state = 3
                tv_state.text = "审核失败"
            }
        }
    }
}
