package com.hazz.whb.mvp.presenter


import android.util.Pair
import com.hazz.whb.mvp.contract.LoginContract
import com.hazz.whb.mvp.model.DuihuanRecord
import com.hazz.whb.mvp.model.bean.TibiRecord
import com.hazz.whb.mvp.model.bean.TimeData
import com.hazz.whb.net.*
import com.hazz.whb.utils.Utils


class FenhongChiPresenter(view: LoginContract.FenhongView) : BasePresenter<LoginContract.FenhongView>(view) {

    fun addCapital(settingId:String,amount: String, trade_password: String
    ) {


        val body = RequestUtils.getBody(
                Pair.create<Any, Any>("coin", "WHB"),
                Pair.create<Any, Any>("setting_id", settingId),
                Pair.create<Any, Any>("amount", amount),
                Pair.create<Any, Any>("trade_password", Utils.encryptMD5(trade_password))


        )

        val login = RetrofitManager.service.addCapital(body)

        doRequest(login, object : Callback<Any>(view) {
            override fun failed(tBaseResult: BaseResult<Any>): Boolean {

                return false
            }

            override fun success(tBaseResult: BaseResult<Any>) {
                view.commitSuccess(tBaseResult.msg)
            }

        }, true)

    }

    fun timeData(){

        val time = RetrofitManager.service.timeData()

        doRequest(time, object : Callback<List<TimeData>>(view) {
            override fun failed(tBaseResult: BaseResult<List<TimeData>>): Boolean {

                return false
            }

            override fun success(tBaseResult: BaseResult<List<TimeData>>) {
                view.timeDataSuccess(tBaseResult.data!!)
            }

        }, true)

    }

}