package com.hazz.whb.mvp.presenter


import com.hazz.whb.mvp.contract.LoginContract
import com.hazz.whb.mvp.model.bean.Kuangji
import com.hazz.whb.mvp.model.bean.Mingxi
import com.hazz.whb.net.BasePresenter
import com.hazz.whb.net.BaseResult
import com.hazz.whb.net.Callback
import com.hazz.whb.net.RetrofitManager


class KuangjiPresenter(view: LoginContract.kuangjiView) : BasePresenter<LoginContract.kuangjiView>(view) {

    fun kuangji(start:Int,end:Int) {



        val login = RetrofitManager.service.kuangji(start,end)

        doRequest(login, object : Callback<Kuangji>(view) {
            override fun failed(tBaseResult: BaseResult<Kuangji>): Boolean {

                return false
            }

            override fun success(tBaseResult: BaseResult<Kuangji>) {
                view.getKuangji(tBaseResult.data!!)
            }

        }, false)

    }


    fun mingxi(start:String,end:String) {



        val login = RetrofitManager.service.mingxi(start,end)

        doRequest(login, object : Callback<Mingxi>(view) {
            override fun failed(tBaseResult: BaseResult<Mingxi>): Boolean {

                return false
            }

            override fun success(tBaseResult: BaseResult<Mingxi>) {
                view.getMingxi(tBaseResult.data!!)
            }

        }, true)

    }

}