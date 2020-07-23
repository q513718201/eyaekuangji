package com.hazz.whb.mvp.presenter


import com.hazz.whb.mvp.contract.LoginContract
import com.hazz.whb.mvp.model.bean.Node
import com.hazz.whb.mvp.model.bean.Shenfen
import com.hazz.whb.net.*


class NodePresenter(view: LoginContract.NodeView) : BasePresenter<LoginContract.NodeView>(view) {


    fun getNode() {


        val login = RetrofitManager.service.getNode()

        doRequest(login, object : Callback<Node>(view) {
            override fun failed(tBaseResult: BaseResult<Node>): Boolean {

                return false
            }

            override fun success(tBaseResult: BaseResult<Node>) {
                view.getNode(tBaseResult.data!!)
            }

        }, true)

    }



    fun getShenfen() {


        val login = RetrofitManager.service.getShenfen()

        doRequest(login, object : Callback<Shenfen>(view) {
            override fun failed(tBaseResult: BaseResult<Shenfen>): Boolean {

                return false
            }

            override fun success(tBaseResult: BaseResult<Shenfen>) {
                view.getShenfen(tBaseResult.data!!)
            }

        }, true)

    }


}