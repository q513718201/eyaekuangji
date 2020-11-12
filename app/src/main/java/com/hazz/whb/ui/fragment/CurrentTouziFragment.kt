package com.hazz.whb.ui.fragment


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.text.InputType
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.hazz.whb.R
import com.hazz.whb.base.BaseFragment
import com.hazz.whb.mvp.contract.LoginContract
import com.hazz.whb.mvp.model.bean.CurrentInventBean
import com.hazz.whb.mvp.presenter.CurrentInventPresenter
import com.hazz.whb.ui.adapter.CurrentFinishInventAdapter
import com.hazz.whb.ui.adapter.CurrentInventAdapter
import com.hazz.whb.utils.ToastUtils
import com.hazz.whb.widget.RewardItemDeco
import com.scwang.smartrefresh.layout.util.DensityUtil
import kotlinx.android.synthetic.main.fragment_dangqiantouzi.*
import java.util.*


/**
 * 当前投资
 */
class CurrentTouziFragment : BaseFragment(), LoginContract.CurrentInventView, TabLayout.OnTabSelectedListener {
    var coin = "WHB"
    override fun onTabReselected(p0: TabLayout.Tab?) {

    }

    override fun onTabUnselected(p0: TabLayout.Tab?) {

    }

    override fun onTabSelected(p0: TabLayout.Tab?) {
        p0?.let {
            if (p0.position == 0) {
                recyclerView.adapter = adapter
            } else {
                recyclerView.adapter = finishAdapter
            }
        }

    }

    @SuppressLint("SetTextI18n")
    override fun setCurrentData(data: CurrentInventBean) {
        refreshLayout?.finishRefresh()
        adapter?.setNewData(data.orders)
        finishAdapter?.setNewData(data.completed_orders)
//        finishAdapter?.setNewData(completed_orders)
        if (data.revenue == null) {
            tv_shouyi?.text = "0" + coin
        } else {
            tv_shouyi?.text = String.format("%.4f", data.revenue.toDouble()) + coin
        }
        if (data.invest == null) {
            tv_touzi?.text = "0" + coin
        } else {
            tv_touzi?.text = String.format("%.4f", data.invest.toDouble()) + coin
        }
    }

    override fun onResult(msg: String) {
        adapter?.remove(mPoition)
        ToastUtils.showToast(activity, msg)
    }

    private var mPoition: Int = 0
    private var mPresenter = CurrentInventPresenter(this)
    private val list = ArrayList<String>()
    private val orders = ArrayList<CurrentInventBean.OrdersBean>()
    private val completed_orders = ArrayList<CurrentInventBean.CompletedOrdersBean>()

    var adapter: CurrentInventAdapter? = null
    var finishAdapter: CurrentFinishInventAdapter? = null

    //    private var mPasswordDialog: SafeCheckDialog? = null
    private var mPopWindow: PopupWindow? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_dangqiantouzi
    }

    override fun initView() {
        list.add("分红中")
        list.add("已赎回")
        for (title in list) {
            tabLayout.addTab(tabLayout.newTab().setText(title))
        }
        tabLayout.addOnTabSelectedListener(this)
        recyclerView.layoutManager = LinearLayoutManager(activity)//创建布局管理
        val leftRightOffset = DensityUtil.dp2px(10f)

        recyclerView.addItemDecoration(
                RewardItemDeco(
                        0,
                        0,
                        0,
                        leftRightOffset,
                        0
                )
        )
        adapter = CurrentInventAdapter(R.layout.item_current_order, orders)
        adapter?.setOnItemChildClickListener { _, view, position ->
            if (view.id == R.id.bt_transfers) {
                var data = adapter?.getItem(position)
                var shuhui:CurrentInventBean.CompletedOrdersBean=CurrentInventBean.CompletedOrdersBean()
                shuhui.days=data?.days
                shuhui.number=data?.number
                shuhui.amount=data?.amount
                shuhui.length=data?.length
                shuhui.return_rate=data?.return_rate
                shuhui.created_at=data?.created_at
                shuhui.revenue=data?.revenue
                completed_orders.add(shuhui)
                val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                var linearLayout: LinearLayout = LinearLayout(context)
                val popupView = inflater.inflate(R.layout.redemption_pop, linearLayout)
                var isshow: Boolean = true
                popupView?.let {
                    var number = it.findViewById<TextView>(R.id.number_re)//订单编号
                    var re_x = it.findViewById<ImageView>(R.id.re_x)
                    var re_sum = it.findViewById<EditText>(R.id.re_sum)//转出数量
                    var re_psw = it.findViewById<EditText>(R.id.re_psw)//资金密码
                    var show_psw_re = it.findViewById<ImageView>(R.id.show_psw_re)//显示密码
                    var confirm = it.findViewById<TextView>(R.id.re_confirm)//确定
                    number.text = "订单编号" + data?.number
                    re_sum.setText(String.format("%.4f",data?.amount?.toDouble()))
                    re_sum.isFocusable=false
                    re_x.setOnClickListener {
                        mPopWindow?.dismiss()
                    }

                    show_psw_re.setOnClickListener {
                        if (isshow) {
                            re_psw.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                            show_psw_re.setBackgroundResource(R.mipmap.login_bt_hide)
                            isshow = false
                        } else {
                            re_psw.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                            isshow = true
                            show_psw_re.setBackgroundResource(R.mipmap.yinca)
                        }
                    }

                    confirm.setOnClickListener {
                        var ss: String = re_psw.text.toString()
                        if (re_psw.text.toString() == "") {
                            Toast.makeText(context, "资金密码为空", Toast.LENGTH_SHORT).show()
                        } else {
                            mPresenter.redeem(data?.number!!, data?.status.toString(),re_psw.text.toString() )
                            mPopWindow?.dismiss()
                        }
                    }

                }

                if (mPopWindow == null) {
                    mPopWindow = PopupWindow(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                    mPopWindow!!.setBackgroundDrawable(ColorDrawable(0x00000000))
                    mPopWindow!!.isClippingEnabled = true
                    mPopWindow!!.isFocusable = true
                    mPopWindow!!.setOnDismissListener {
                        darkenBackground(1f)
                    }
                }
                mPopWindow!!.contentView = popupView
                mPopWindow!!.showAtLocation(popupView, Gravity.CENTER, 0, 0)
                darkenBackground(0.5f)
            }
        }
        finishAdapter = CurrentFinishInventAdapter(completed_orders)
        recyclerView.adapter = adapter
        refreshLayout.setOnRefreshListener { mPresenter.getData() }
        refreshLayout.isEnableLoadmore = false
    }


    override fun lazyLoad() {
        mPresenter.getData()
    }

    private fun darkenBackground(bgcolor: Float?) {
        val lp = activity?.window!!.attributes
        lp.alpha = bgcolor!!

        activity?.window!!.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        activity?.window!!.attributes = lp

    }
}


