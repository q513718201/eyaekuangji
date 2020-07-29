package com.hazz.whb.mvp.presenter


import android.util.Pair
import com.hazz.whb.mvp.contract.LoginContract
import com.hazz.whb.mvp.model.Sms
import com.hazz.whb.mvp.model.bean.UserInfo
import com.hazz.whb.net.*
import com.hazz.whb.utils.Utils
import io.reactivex.Observer
import io.reactivex.disposables.Disposable


class LoginPresenter(view: LoginContract.LoginView) : BasePresenter<LoginContract.LoginView>(view) {

    fun login(credential: String, password: String
    ) {


        val body = RequestUtils.getBody(

                Pair.create<Any, Any>("username", credential),
                Pair.create<Any, Any>("password", Utils.encryptMD5(password))

        )

        val login = RetrofitManager.service.login(body)

        doRequest(login, object : Callback<UserInfo>(view) {
            override fun failed(tBaseResult: BaseResult<UserInfo>): Boolean {

                return false
            }

            override fun success(tBaseResult: BaseResult<UserInfo>) {
                view.loginSuccess(tBaseResult.data!!)
            }

        }, true)

    }

    fun resetPwd(old_password: String, password: String
    ) {


        val body = RequestUtils.getBody(

                Pair.create<Any, Any>("old_password", Utils.encryptMD5(old_password)),
                Pair.create<Any, Any>("password", Utils.encryptMD5(password))

        )

        val login = RetrofitManager.service.resetPwd(body)

        doRequest(login, object : Callback<Any>(view) {
            override fun failed(tBaseResult: BaseResult<Any>): Boolean {

                return false
            }

            override fun success(tBaseResult: BaseResult<Any>) {
                view.registerSucceed(tBaseResult.msg)
            }

        }, true)

    }

    fun sendSMs(mobile: String) {

        val login = RetrofitManager.service.sendCode(mobile,"+86")


        doRequest1(login, object : Observer<Sms> {
            override fun onComplete() {
                view.sendSms("发送成功")
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: Sms) {
            }

            override fun onError(e: Throwable) {
                view.sendSms("发送失败")
            }

        }, true)
//        doRequest(login, object : Callback<Any>(view) {
//            override fun failed(tBaseResult: BaseResult<Any>): Boolean {
//
//                return false
//            }
//
//            override fun success(tBaseResult: BaseResult<Any>) {
//                view.sendSms(tBaseResult.msg)
//            }
//
//        }, true)


    }

    fun regist(username: String, invitation_code: String, mobile: String,
               captcha: String, password: String, trade_password: String) {


        val body = RequestUtils.getBody(
                Pair.create<Any, Any>("username", username),
                Pair.create<Any, Any>("invitation_code", invitation_code),
                Pair.create<Any, Any>("mobile", mobile),
                Pair.create<Any, Any>("captcha", captcha),
                Pair.create<Any, Any>("password", Utils.encryptMD5(password)),
                Pair.create<Any, Any>("trade_password", Utils.encryptMD5(trade_password)),
                Pair.create<Any, Any>("countryCode", "86")

        )

        val login = RetrofitManager.service.regist(body)

        doRequest(login, object : Callback<Any>(view) {
            override fun failed(tBaseResult: BaseResult<Any>): Boolean {

                return false
            }

            override fun success(tBaseResult: BaseResult<Any>) {
                view.registerSucceed(tBaseResult.msg)
            }

        }, true)

    }


    fun forgetPwd(username: String, mobile: String, captcha: String, password: String, confirm_password: String, password_type: String) {


        val body = RequestUtils.getBody(
                Pair.create<Any, Any>("username", username),
                Pair.create<Any, Any>("mobile", mobile),
                Pair.create<Any, Any>("captcha", captcha),
                Pair.create<Any, Any>("password", Utils.encryptMD5(password)),
                Pair.create<Any, Any>("confirm_password", Utils.encryptMD5(confirm_password)),
                Pair.create<Any, Any>("password_type", password_type)
        )

        val login = RetrofitManager.service.forgetPwd(body)

        doRequest(login, object : Callback<Any>(view) {
            override fun failed(tBaseResult: BaseResult<Any>): Boolean {

                return false
            }

            override fun success(tBaseResult: BaseResult<Any>) {
                view.registerSucceed(tBaseResult.msg)
            }

        }, true)

    }


}