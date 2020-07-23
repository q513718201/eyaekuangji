package com.hazz.whb.ui.fragment


import com.hazz.whb.R
import com.hazz.whb.base.BaseFragment
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
