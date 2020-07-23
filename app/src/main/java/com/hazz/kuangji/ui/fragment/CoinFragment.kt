package com.hazz.kuangji.ui.fragment


import com.hazz.kuangji.R
import com.hazz.kuangji.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_coin.*

class CoinFragment : BaseFragment() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_coin
    }

    override fun initView() {
        web_view.loadUrl("https://m.huobi.me/zh-cn/market/")

    }

    override fun lazyLoad() {

    }



}
