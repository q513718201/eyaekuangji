package com.hazz.whb.ui.fragment

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.hazz.whb.BuildConfig
import com.hazz.whb.R
import com.hazz.whb.base.BaseFragment
import com.hazz.whb.events.Index
import com.hazz.whb.mvp.contract.LoginContract
import com.hazz.whb.mvp.model.bean.MyAsset
import com.hazz.whb.mvp.model.bean.MyState
import com.hazz.whb.mvp.presenter.MinePresenter
import com.hazz.whb.mvp.presenter.ZichanPresenter
import com.hazz.whb.ui.activity.*
import com.hazz.whb.ui.adapter.ZichanAdapter
import com.hazz.whb.utils.*
import com.hazz.whb.widget.RewardItemDeco
import com.hazz.whb.widget.TipsDialog
import com.scwang.smartrefresh.layout.util.DensityUtil
import kotlinx.android.synthetic.main.fragment_zichan.*
import kotlinx.android.synthetic.main.fragment_zichan.recycle_view


class ZichanFragment : BaseFragment(), LoginContract.ZichanView, LoginContract.MyStateView {


    @SuppressLint("SetTextI18n")
    override fun myAsset(msg: MyAsset) {
        myAsset = msg
        tv_copy?.text = msg.wallet_address
        if (msg.investment != null) {
            tv_touzi?.text = BigDecimalUtil.mul(msg.investment.toString(), "1", 5)
        }

        if (msg.fcoin_revenue != null) {
            tv_shouyi?.text = BigDecimalUtil.mul(msg.fcoin_revenue, "1", 2)
        }


        val assets = msg.assets
        list!!.clear()
        for (coin in assets) {
            if (coin.coin != "BTC") {
                list!!.add(coin)
            }
        }

        mAdapter!!.setNewData(list)
        minePresenter.myAsset()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_zichan
    }


    private var mZichanPresenter: ZichanPresenter = ZichanPresenter(this)
    private var myAsset: MyAsset? = null
    private var mAdapter: ZichanAdapter? = null
    private var list: MutableList<MyAsset.AssetsBean>? = mutableListOf()
    private var minePresenter: MinePresenter = MinePresenter(this)
    private var state = 0
    private var isSetpwd = if (BuildConfig.DEBUG) "1" else "0"
    @SuppressLint("SetTextI18n")
    override fun initView() {


        tv_tibi.setOnClickListener {
            if (myAsset != null) {

                if (isSetpwd == "0") {
                    val tipsDialog = TipsDialog(activity)
                            .setTitle("提示")
                            .setContent("您未设置交易密码，为了您的账户安全请立即前往设置")
                            .setPwd()
                            .setConfirmListener {
                                startActivity(Intent(activity, FindZijinPwdActivity::class.java))
                            }

                    tipsDialog.show()
                } else {
                    startActivity(Intent(activity, TibiActivity::class.java).putExtra("amount", myAsset))

                }


            } else {
                SToast.showText("正在获取资产信息...")
            }


        }

        ll_duihuan.setOnClickListener {
            if (myAsset != null) {
                if (isSetpwd == "0") {
                    val tipsDialog = TipsDialog(activity)
                            .setTitle("提示")
                            .setContent("您未设置交易密码，为了您的账户安全请立即前往设置")
                            .setPwd()
                            .setConfirmListener {
                                startActivity(Intent(activity, FindZijinPwdActivity::class.java))
                            }

                    tipsDialog.show()
                } else {

                    when (state) {
                        1 -> {
                            startActivity(Intent(activity, DuihuanActivity::class.java).putExtra("amount", myAsset))

                        }
                        2 -> {
                            SToast.showText("实名认证审核中...")
                            return@setOnClickListener
                        }
                        else -> {

                            val tipsDialog = TipsDialog(activity)
                                    .setTitle("提示")
                                    .setContent("为了您的帐户安全,请先进行实名认证")
                                    .tips()
                                    .setConfirmListener {
                                        startActivity(Intent(activity, NameAuthActivity::class.java))
                                    }

                            tipsDialog.show()
                        }
                    }

                }


            } else {
                SToast.showText("正在获取资产信息...")
            }
        }

        rl_charge.setOnClickListener {
            startActivity(Intent(activity, ChargeActivity::class.java))

        }
        rl_touzi.setOnClickListener {
            RxBus.get().send(Index())
        }
        rl_shouyi.setOnClickListener {
            startActivity(Intent(activity, IncomingActivity::class.java))
        }
        tv_copy.setOnClickListener {
            val cm = activity!!.getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
            val clipData = ClipData.newPlainText("invitation_code", tv_copy.text)

            cm.primaryClip = clipData

            SToast.showText("已成功复制钱包地址")
        }

        recycle_view.layoutManager = LinearLayoutManager(activity)//创建布局管理
        mAdapter = ZichanAdapter(R.layout.item_zichan, null)
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


    }

    override fun lazyLoad() {
        mZichanPresenter.myAsset()
    }

    override fun reload() {
        super.reload()
        mZichanPresenter.myAsset()
    }

    override fun myState(msg: MyState) {
        isSetpwd = msg.trade_password_status
        when (msg.real_name) {
            "0" -> {
                //未实名
                state = 0

            }
            "1" -> {
                //已实名
                state = 1

            }
            "2" -> {
                //审核zhong
                state = 2

            }
            "3" -> {
                state = 3

            }
        }
    }


}
