package com.hazz.whb.mvp.presenter

import android.util.Pair
import com.hazz.whb.mvp.contract.LoginContract
import com.hazz.whb.mvp.model.bean.CurrentInventBean
import com.hazz.whb.net.*
import com.hazz.whb.utils.Utils

class CurrentInventPresenter(view: LoginContract.CurrentInventView) : BasePresenter<LoginContract.CurrentInventView>(view) {

    fun getData() {

        val login = RetrofitManager.service.getCurrentInvent()

        doRequest(login, object : Callback<CurrentInventBean>(view) {
            override fun failed(tBaseResult: BaseResult<CurrentInventBean>): Boolean {

                return false
            }

            override fun success(tBaseResult: BaseResult<CurrentInventBean>) {
                tBaseResult.let { view.setCurrentData(it.data!!) }
            }

        }, true)
    }

    fun redeem(orderId: String, status: String, trade_password: String) {
        val body = RequestUtils.getBody(
                Pair.create<Any, Any>("order_id", orderId),
                Pair.create<Any, Any>("status", status),
                Pair.create<Any, Any>("trade_password", Utils.encryptMD5(trade_password))
        )

        val login = RetrofitManager.service.postRedeem(body)

        doRequest(login, object : Callback<Any>(view) {
            override fun failed(tBaseResult: BaseResult<Any>): Boolean {

                return false
            }

            override fun success(tBaseResult: BaseResult<Any>) {
                view.onResult(tBaseResult.msg)
            }

        }, true)
    }
}