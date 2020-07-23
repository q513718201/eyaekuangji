package com.hazz.whb.ui.activity

import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.hazz.whb.R
import com.hazz.whb.base.BaseActivity
import com.hazz.whb.mvp.contract.LoginContract
import com.hazz.whb.mvp.model.Xieyi
import com.hazz.whb.mvp.model.bean.SignRecord
import com.hazz.whb.mvp.presenter.XieyiPresenter
import com.hazz.whb.ui.adapter.SignRecordAdapter
import com.hazz.whb.utils.*
import com.hazz.whb.widget.RewardItemDeco
import com.scwang.smartrefresh.layout.util.DensityUtil
import kotlinx.android.synthetic.main.invitefriends_record.*


class SignRecordActivity : BaseActivity(), LoginContract.XieyiView {
    override fun xieyi(msg: Xieyi) {

    }

    override fun getSignRecord(msg: SignRecord) {
        val list = msg.list
        mAdapter!!.setNewData(list)
    }


    override fun layoutId(): Int {
        return R.layout.tibi_record
    }

    override fun initData() {
        mXieyiPresenter.signRecord()
    }


    private var mAdapter: SignRecordAdapter?=null

    private var mXieyiPresenter: XieyiPresenter = XieyiPresenter(this)

    override fun initView() {
        ToolBarCustom.newBuilder(mToolBar as Toolbar)
                .setLeftIcon(R.mipmap.back_white)
                .setTitle("签到记录")
                .setToolBarBgRescource(R.drawable.bg_hangqing)
                .setTitleColor(resources.getColor(R.color.color_white))
                .setOnLeftIconClickListener { view -> finish() }


        recycle_view.layoutManager = LinearLayoutManager(this)//创建布局管理
        mAdapter = SignRecordAdapter(R.layout.item_sign, null)
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


}
