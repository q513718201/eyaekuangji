package com.hazz.whb.mvp.presenter


import android.util.Pair
import com.hazz.whb.mvp.contract.LoginContract
import com.hazz.whb.mvp.model.Home
import com.hazz.whb.net.*
import com.hazz.whb.utils.Utils


class HomePresenter(view: LoginContract.HomeView) : BasePresenter<LoginContract.HomeView>(view) {

    fun getHome() {


        val login = RetrofitManager.service.getHome()

        doRequest(login, object : Callback<Home>(view) {
            override fun failed(tBaseResult: BaseResult<Home>): Boolean {

                return false
            }

            override fun success(tBaseResult: BaseResult<Home>) {
                view.getHome(tBaseResult.data!!)
            }

        }, false)

    }

    fun sign() {


        val login = RetrofitManager.service.sign()

        doRequest(login, object : Callback<Any>(view) {
            override fun failed(tBaseResult: BaseResult<Any>): Boolean {

                return false
            }

            override fun success(tBaseResult: BaseResult<Any>) {
                view.zuyongSucceed(tBaseResult.msg)
            }

        }, true)

    }

    fun zuyong(coin: String, product_id: String, trade_password: String


    ) {


        val body = RequestUtils.getBody(

                Pair.create<Any, Any>("coin", coin),
                Pair.create<Any, Any>("product_id", product_id),
                Pair.create<Any, Any>("trade_password", Utils.encryptMD5(trade_password))


        )

        val login = RetrofitManager.service.zuyong(body)

        doRequest(login, object : Callback<Any>(view) {
            override fun failed(tBaseResult: BaseResult<Any>): Boolean {

                return false
            }

            override fun success(tBaseResult: BaseResult<Any>) {
                view.zuyongSucceed(tBaseResult.msg)
            }

        }, true)

    }
}