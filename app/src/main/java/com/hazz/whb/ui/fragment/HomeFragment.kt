package com.hazz.whb.ui.fragment

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.hazz.whb.R
import com.hazz.whb.base.BaseFragment
import com.hazz.whb.mvp.contract.LoginContract
import com.hazz.whb.mvp.model.Home
import com.hazz.whb.mvp.model.bean.Msg
import com.hazz.whb.mvp.model.bean.MyState
import com.hazz.whb.mvp.presenter.HomePresenter
import com.hazz.whb.mvp.presenter.MinePresenter
import com.hazz.whb.mvp.presenter.MsgPresenter
import com.hazz.whb.ui.activity.FenhongActivity
import com.hazz.whb.ui.activity.MsgDescActivity
import com.hazz.whb.ui.activity.SignRecordActivity
import com.hazz.whb.ui.adapter.HomeAdapter
import com.hazz.whb.utils.SToast
import com.hazz.whb.widget.GlideImageLoader
import com.hazz.whb.widget.RewardItemDeco
import com.hazz.whb.widget.TipsDialog
import com.scwang.smartrefresh.layout.util.DensityUtil
import com.tencent.bugly.Bugly.applicationContext
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_new_home.*

class HomeFragment : BaseFragment(), LoginContract.HomeView, LoginContract.MsgView, LoginContract.MyStateView {

    override fun getMsg(rows: List<Msg>) {
        if (!rows.isNullOrEmpty()) {
            viewsList!!.clear()//记得加这句话，不然可能会产生重影现象
            for (i in rows.indices) {
                //设置滚动的单个布局

                val moreView = View.inflate(context, R.layout.item_view_single, null)
                //初始化布局的控件
                val tv1 = moreView.findViewById<View>(R.id.tv_title) as TextView
                /**
                 * 设置监听
                 */
                tv1.setOnClickListener { view ->
                    if (rows != null && !rows.isEmpty()) {
                        val articalBean = rows.get(i)
                        val intent = Intent(activity, MsgDescActivity::class.java)
                        intent.putExtra("message", articalBean)
                        startActivity(intent)
                    }
                }

                //进行对控件赋值
                tv1.setText(rows[i].title)
                //添加到循环滚动数组里面去
                viewsList!!.add(moreView)
            }
            marqueeview?.setViews(viewsList)
            marqueeview?.startFlipping()
        }

    }

    override fun zuyongSucceed(msg: String) {
        tv_qiandao.setBackgroundResource(R.drawable.bg_gray_solid_5dp_coner)
        tv_qiandao.text = "今日已签到"
        tv_qiandao.isClickable = false
        tipsDialog = TipsDialog(activity)
                .setTitle("提示")
                .setContent("签到成功,所有矿机开始运行")
                .sign()
                .setCancleListener { }
                .setConfirmListener {
                    tv_qiandao.setBackgroundResource(R.drawable.bg_gray_solid_5dp_coner)
                    tv_qiandao.text = "今日已签到"
                    tv_qiandao.setTextColor(activity!!.resources.getColor(R.color.hintTextColor))
                    tv_qiandao.isClickable = false
                    tipsDialog!!.dismiss()

                }

        tipsDialog!!.show()
    }


    override fun getHome(msg: Home) {
        val signed = msg.signed
        val carousel = msg.carousel
        if (!carousel.isNullOrEmpty()) {
            adList!!.clear()
            for (a in carousel) {
                adList!!.add(a.url)
            }


        } else {
            adListDefault!!.clear()
            adListDefault!!.add(R.mipmap.br_home01)
            initBannerDefault(adListDefault!!)
        }



        if (signed == 1) {//已签到
            tv_qiandao?.setBackgroundResource(R.drawable.bg_gray_solid_5dp_coner)
            tv_qiandao?.text = "今日已签到"
            tv_qiandao?.setTextColor(activity!!.resources.getColor(R.color.hintTextColor))
            tv_qiandao?.isClickable = false
        }

        mAdapter!!.setNewData(msg.products)

    }


    override fun getLayoutId(): Int {
        return R.layout.fragment_new_home
    }


    private var adList: MutableList<String>? = mutableListOf()
    private var adListDefault: MutableList<Int>? = mutableListOf()
    private var list: MutableList<Home.ProductsBean>? = mutableListOf()
    private var mAdapter: HomeAdapter? = null
    private var mHomePresenter: HomePresenter = HomePresenter(this)
    private var tipsDialog: TipsDialog? = null
    private var viewsList: MutableList<View>? = mutableListOf()
    private var mCoinPresenter: MsgPresenter = MsgPresenter(this)
    private var minePresenter: MinePresenter = MinePresenter(this)
    private var state = 0

    override fun initView() {

        recycle_view.layoutManager = LinearLayoutManager(activity)//创建布局管理
        mAdapter = HomeAdapter(R.layout.item_home, null)
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
        initBanner(arrayListOf(R.mipmap.br_home02))
        ll.setOnClickListener {
            SToast.showText("暂未开放")
        }

        tv_qiandao.setOnClickListener {
            mHomePresenter.sign()

        }

        tv_sign_record.setOnClickListener {
            startActivity(Intent(activity, SignRecordActivity::class.java))
        }

        tv_rule.setOnClickListener {


            tipsDialog = TipsDialog(activity)
                    .setTitle("提示")
                    .setContent("300天周期内，每日签到都可获得当日的收益。如期间出现漏签，收益周期减少且不会发放收益。")

                    .rule()


            tipsDialog!!.show()
        }
    }

    override fun lazyLoad() {
        minePresenter.myAsset()
        mCoinPresenter.getMsg()
        mHomePresenter.getHome()
    }

    override fun reload() {
        super.reload()
        minePresenter.myAsset()
        mCoinPresenter.getMsg()
        mHomePresenter.getHome()
    }

    private fun initBanner(list: List<Int>) {
        banner?.let {
            it.setImageLoader(GlideImageLoader())
            it.setImages(list)

            //设置banner动画效果
            it.setBannerAnimation(Transformer.DepthPage)
            //设置自动轮播，默认为true
            it.isAutoPlay(true)
            //设置轮播时间
            it.setDelayTime(3000)
            it.setOnBannerListener{
                bannerClick(it)
            }
            //设置指示器位置（当banner模式中有指示器时）
            it.setIndicatorGravity(BannerConfig.CENTER)
            //banner设置方法全部调用完毕时最后调用
            it.start()

        }

    }

    /**
     * banner点击事件
     */
    private fun bannerClick(it: Int) {
        if(it == 0){
            startActivity(Intent(activity,FenhongActivity::class.java))
        }
    }

    private fun initBannerDefault(list: List<Int>) {
        banner?.let {
            it.setImageLoader(GlideImageLoader())
            it.setImages(list)

            //设置banner动画效果
            it.setBannerAnimation(Transformer.DepthPage)
            //设置自动轮播，默认为true
            it.isAutoPlay(true)
            //设置轮播时间
            it.setDelayTime(3000)
            //设置指示器位置（当banner模式中有指示器时）
            it.setIndicatorGravity(BannerConfig.CENTER)
            //banner设置方法全部调用完毕时最后调用
            it.start()
        }
    }

    override fun onResume() {
        super.onResume()
        marqueeview.startFlipping()
        Log.d("junjun", "展示")
    }

    override fun onPause() {
        super.onPause()
        marqueeview.stopFlipping()
    }


    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
    }

    override fun myState(msg: MyState) {

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
        mAdapter!!.setState(state, msg.trade_password_status)

        if (msg.is_first == "1") {
            val tipsDialog = TipsDialog(activity).award()
            tipsDialog.show()
        }

    }
}


