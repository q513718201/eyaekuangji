package com.hazz.whb.mvp.presenter


import android.util.Pair
import com.hazz.whb.mvp.contract.LoginContract
import com.hazz.whb.mvp.model.Image
import com.hazz.whb.net.*
import com.hazz.whb.utils.PhotoUtils
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File


class NameAuthPresenter(view: LoginContract.AuthView) : BasePresenter<LoginContract.AuthView>(view) {

    fun auth(country_code: String, name: String, category: Int, file_number: String,
             img_front: String,
             img_back: String,
             img_handle: String
    ) {


        val body = RequestUtils.getBody(

                Pair.create<Any, Any>("country_code", country_code),
                Pair.create<Any, Any>("name", name),
                Pair.create<Any, Any>("category", category),
                Pair.create<Any, Any>("file_number", file_number),
                Pair.create<Any, Any>("img_front", img_front),
                Pair.create<Any, Any>("img_back", img_back),
                Pair.create<Any, Any>("img_handle", img_handle)
        )

        val login = RetrofitManager.service.auth(body)

        doRequest(login, object : Callback<Any>(view) {
            override fun failed(tBaseResult: BaseResult<Any>): Boolean {

                return false
            }

            override fun success(tBaseResult: BaseResult<Any>) {
                view.authSucceed(tBaseResult.msg)
            }

        }, true)

    }

    fun upload(path: String) {

        val file = File(PhotoUtils.compressImage(path))
        val requestFile: RequestBody = RequestBody.create(MediaType.parse("image/jpg"), file)
        val body = MultipartBody.Part.createFormData("file", file.name, requestFile)
        val login = RetrofitManager.service.upload(body)

        doRequest(login, object : Callback<Image>(view) {
            override fun failed(tBaseResult: BaseResult<Image>): Boolean {

                return false
            }

            override fun success(tBaseResult: BaseResult<Image>) {
                view.uploadSucceed(tBaseResult.data!!)
            }

        }, true)

    }

}