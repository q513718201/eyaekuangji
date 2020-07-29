package com.hazz.whb.mvp.presenter


import com.hazz.whb.mvp.contract.LoginContract
import com.hazz.whb.mvp.model.bean.Msg
import com.hazz.whb.net.*


class MsgPresenter(view: LoginContract.MsgView) : BasePresenter<LoginContract.MsgView>(view) {


    fun getMsg() {


        val login = RetrofitManager.service.getMsg()

        doRequest(login, object : Callback<List<Msg>>(view) {
            override fun failed(tBaseResult: BaseResult<List<Msg>>): Boolean {

                return false
            }

            override fun success(tBaseResult: BaseResult<List<Msg>>) {
                view.getMsg(tBaseResult.data!!)
            }

        }, false)

    }



}