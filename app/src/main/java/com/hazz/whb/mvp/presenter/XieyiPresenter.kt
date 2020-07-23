package com.hazz.whb.mvp.presenter


import com.hazz.whb.mvp.contract.LoginContract
import com.hazz.whb.mvp.model.Xieyi
import com.hazz.whb.mvp.model.bean.SignRecord
import com.hazz.whb.net.BasePresenter
import com.hazz.whb.net.BaseResult
import com.hazz.whb.net.Callback
import com.hazz.whb.net.RetrofitManager


class XieyiPresenter(view: LoginContract.XieyiView) : BasePresenter<LoginContract.XieyiView>(view) {

    fun xieyi(target:String) {



        val login = RetrofitManager.service.xieyi(target)

        doRequest(login, object : Callback<Xieyi>(view) {
            override fun failed(tBaseResult: BaseResult<Xieyi>): Boolean {

                return false
            }

            override fun success(tBaseResult: BaseResult<Xieyi>) {
                view.xieyi(tBaseResult.data!!)
            }

        }, true)

    }

    fun signRecord() {



        val login = RetrofitManager.service.signRecord()

        doRequest(login, object : Callback<SignRecord>(view) {
            override fun failed(tBaseResult: BaseResult<SignRecord>): Boolean {

                return false
            }

            override fun success(tBaseResult: BaseResult<SignRecord>) {
                view.getSignRecord(tBaseResult.data!!)
            }

        }, true)

    }

}