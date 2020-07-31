package com.hazz.whb.ui.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.hazz.whb.R
import com.hazz.whb.base.BaseFragment
import com.hazz.whb.mvp.contract.LoginContract
import com.hazz.whb.mvp.model.bean.Kuangji
import com.hazz.whb.mvp.model.bean.Mingxi
import com.hazz.whb.mvp.presenter.KuangjiPresenter
import com.hazz.whb.ui.adapter.CoinAdapter
import com.hazz.whb.utils.BigDecimalUtil
import com.hazz.whb.widget.RewardItemDeco
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.scwang.smartrefresh.layout.util.DensityUtil
import kotlinx.android.synthetic.main.fragment_hangqing.*

class HangqingFragment : BaseFragment(), LoginContract.kuangjiView, OnRefreshListener, OnLoadmoreListener {
    override fun getMingxi(msg: Mingxi) {

    }

    override fun getKuangji(msg: Kuangji) {
        sf_refresh.finishLoadmore()
        sf_refresh.finishRefresh()
        if(msg.total!=null){
            tv_touzi.text=BigDecimalUtil.mul(msg.total,"1",4)
        }
        if(msg.yesterday!=null){
            tv_shouyi.text=BigDecimalUtil.mul(msg.yesterday,"1",4)
        }

        if(page==1){
            list!!.clear()
            list!!.addAll(msg.machine_list.list)
            mAdapter!!.setNewData(list)
        }else{
            list!!.addAll(msg.machine_list.list)
            mAdapter!!.notifyDataSetChanged()
        }
        if(msg.machine_list.list.size<10){
            sf_refresh.finishLoadmoreWithNoMoreData()
        }

    }


    private var mKuangjiPresenter:KuangjiPresenter= KuangjiPresenter(this)
    private var mAdapter: CoinAdapter?=null
    private var list: MutableList<Kuangji.MachineListBean.ListBean>? = mutableListOf()
    private var page=1
    override fun getLayoutId(): Int {
        return R.layout.fragment_hangqing
    }

    override fun initView() {
        recycle_view.layoutManager = LinearLayoutManager(activity)//创建布局管理
        mAdapter = CoinAdapter(R.layout.item_kuangji, null)
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
        sf_refresh.setOnRefreshListener(this)
        sf_refresh.setOnLoadmoreListener(this)

    }

    override fun lazyLoad() {
        mKuangjiPresenter.kuangji(page,10)
    }

    override fun onRefresh(refreshlayout: RefreshLayout?) {
        page=1
        sf_refresh.resetNoMoreData()
        lazyLoad()
    }

    override fun onLoadmore(refreshlayout: RefreshLayout?) {
        page++
        lazyLoad()
    }


}
