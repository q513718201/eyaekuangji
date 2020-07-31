package com.hazz.whb.mvp.presenter


import com.hazz.whb.mvp.contract.LoginContract
import com.hazz.whb.mvp.model.bean.InComing
import com.hazz.whb.net.BasePresenter
import com.hazz.whb.net.BaseResult
import com.hazz.whb.net.Callback
import com.hazz.whb.net.RetrofitManager


class ShouyiPresenter(view: LoginContract.ShouyiView) : BasePresenter<LoginContract.ShouyiView>(view) {

    fun shouyi(pageNum:Int,pageSize:Int) {



        val login = RetrofitManager.service.inComing(pageNum,pageSize)

        doRequest(login, object : Callback<InComing>(view) {
            override fun failed(tBaseResult: BaseResult<InComing>): Boolean {

                return false
            }

            override fun success(tBaseResult: BaseResult<InComing>) {
                view.inComing(tBaseResult.data!!)
            }

        }, true)

    }

}