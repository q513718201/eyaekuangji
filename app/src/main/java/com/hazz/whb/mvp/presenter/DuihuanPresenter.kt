package com.hazz.whb.mvp.presenter


import android.util.Pair
import com.hazz.whb.mvp.contract.LoginContract
import com.hazz.whb.mvp.model.DuihuanRecord
import com.hazz.whb.mvp.model.bean.TibiRecord
import com.hazz.whb.net.*
import com.hazz.whb.utils.Utils


class DuihuanPresenter(view: LoginContract.DuihuanView) : BasePresenter<LoginContract.DuihuanView>(view) {

    fun duanhuan(amount: String, trade_password: String
    ) {


        val body = RequestUtils.getBody(
                Pair.create<Any, Any>("coin", "WHB"),
                Pair.create<Any, Any>("amount", amount),
                Pair.create<Any, Any>("trade_password", Utils.encryptMD5(trade_password))


        )

        val login = RetrofitManager.service.duihuan(body)

        doRequest(login, object : Callback<Any>(view) {
            override fun failed(tBaseResult: BaseResult<Any>): Boolean {

                return false
            }

            override fun success(tBaseResult: BaseResult<Any>) {
                view.duihuanSucceed(tBaseResult.msg
                )
            }

        }, true)

    }

    fun duihuanRecord(){


        val login = RetrofitManager.service.duihuanRecord()

        doRequest(login, object : Callback<DuihuanRecord>(view) {
            override fun failed(tBaseResult: BaseResult<DuihuanRecord>): Boolean {

                return false
            }

            override fun success(tBaseResult: BaseResult<DuihuanRecord>) {
                view.duihuanRecord(tBaseResult.data!!)
            }

        }, true)

    }

}