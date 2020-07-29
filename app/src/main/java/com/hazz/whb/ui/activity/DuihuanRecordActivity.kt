package com.hazz.whb.ui.activity

import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.hazz.whb.R
import com.hazz.whb.base.BaseActivity
import com.hazz.whb.mvp.contract.LoginContract
import com.hazz.whb.mvp.model.DuihuanRecord
import com.hazz.whb.mvp.model.bean.TibiRecord
import com.hazz.whb.mvp.presenter.DuihuanPresenter
import com.hazz.whb.mvp.presenter.TiBiPresenter
import com.hazz.whb.ui.adapter.DuihuanRecordAdapter
import com.hazz.whb.ui.adapter.TibiRecordAdapter
import com.hazz.whb.utils.*
import com.hazz.whb.widget.RewardItemDeco
import com.scwang.smartrefresh.layout.util.DensityUtil
import kotlinx.android.synthetic.main.invitefriends_record.*


class DuihuanRecordActivity : BaseActivity(), LoginContract.DuihuanView {


    override fun layoutId(): Int {
        return R.layout.duihuan_record
    }

    override fun initData() {
        mTiBiPresenter.duihuanRecord()
    }


    private var mAdapter: DuihuanRecordAdapter?=null

    private var mTiBiPresenter: DuihuanPresenter = DuihuanPresenter(this)

    override fun initView() {
        ToolBarCustom.newBuilder(mToolBar as Toolbar)
                .setLeftIcon(R.mipmap.back_white)
                .setTitle("兑换记录")
                .setToolBarBgRescource(R.drawable.bg_hangqing)
                .setTitleColor(resources.getColor(R.color.color_white))
                .setOnLeftIconClickListener { view -> finish() }


        recycle_view.layoutManager = LinearLayoutManager(this)//创建布局管理
        mAdapter = DuihuanRecordAdapter(R.layout.item_duihuan, null)
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
    override fun start() {


    }

    override fun duihuanSucceed(msg: String) {

    }

    override fun duihuanRecord(msg: DuihuanRecord) {
        mAdapter?.setNewData(msg.list)
    }


}
