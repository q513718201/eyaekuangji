package com.hazz.whb.mvp.presenter


import com.hazz.whb.mvp.contract.LoginContract
import com.hazz.whb.mvp.model.bean.MyAsset
import com.hazz.whb.net.BasePresenter
import com.hazz.whb.net.BaseResult
import com.hazz.whb.net.Callback
import com.hazz.whb.net.RetrofitManager


class ZichanPresenter(view: LoginContract.ZichanView) : BasePresenter<LoginContract.ZichanView>(view) {

    fun myAsset() {



        val login = RetrofitManager.service.myAsset()

        doRequest(login, object : Callback<MyAsset>(view) {
            override fun failed(tBaseResult: BaseResult<MyAsset>): Boolean {

                return false
            }

            override fun success(tBaseResult: BaseResult<MyAsset>) {
                view.myAsset(tBaseResult.data!!)
            }

        }, false)

    }

}