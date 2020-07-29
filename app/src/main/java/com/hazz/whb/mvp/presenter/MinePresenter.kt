package com.hazz.whb.mvp.presenter



import com.hazz.whb.mvp.contract.LoginContract
import com.hazz.whb.mvp.model.bean.MyState
import com.hazz.whb.net.BasePresenter
import com.hazz.whb.net.BaseResult
import com.hazz.whb.net.Callback
import com.hazz.whb.net.RetrofitManager


class MinePresenter(view: LoginContract.MyStateView) : BasePresenter<LoginContract.MyStateView>(view) {

    fun myAsset() {



        val login = RetrofitManager.service.myState()

        doRequest(login, object : Callback<MyState>(view) {
            override fun failed(tBaseResult: BaseResult<MyState>): Boolean {

                return false
            }

            override fun success(tBaseResult: BaseResult<MyState>) {
                view.myState(tBaseResult.data!!)
            }

        }, false)

    }

}