package com.hazz.kuangji.api

import com.hazz.kuangji.mvp.model.Home
import com.hazz.kuangji.mvp.model.Sms
import com.hazz.kuangji.mvp.model.Xieyi
import com.hazz.kuangji.mvp.model.bean.*
import com.hazz.kuangji.net.BaseResult
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.*



interface AiPickService{

    /**
     * 登陆
     */
    @POST("login")
    fun login(@Body request: RequestBody): Observable<BaseResult<UserInfo>>

    /**
     * 获取验证码
     */
    @GET("{mobile}")
    fun sendCode(@Path("mobile" )mobile:String): Observable<Sms>

    /**
     *设置资金密码
     */
    @PUT("find/password")
    fun forgetPwd(@Body request: RequestBody): Observable<BaseResult<Any>>

    /**
     * 修改登陆密码
     */
    @PUT(" reset/password")
    fun resetPwd(@Body request: RequestBody): Observable<BaseResult<Any>>
    /**
     * 注册
     */
    @POST("user/register")
    fun regist(@Body request: RequestBody): Observable<BaseResult<Any>>

    /**
     * 获取行情
     */
    @GET("coins")
    fun getCoin(): Observable<BaseResult<List<Coin>>>

    /**
     * 获取g公告
     */
    @GET("notices")
    fun getMsg(): Observable<BaseResult<List<Msg>>>
    /**
     * 邀请好友
     */
    @GET("sharelog")
    fun getFriends(): Observable<BaseResult<Friends>>

    /**
     * 节点
     */
    @GET("team")
    fun getNode(): Observable<BaseResult<Node>>


    /**
     * 提币
     */
    @POST("trade/withdrawal")
    fun tibi(@Body request: RequestBody): Observable<BaseResult<Any>>


    /**
     * 提币记录
     */
    @GET("trade/withdrawal")
    fun tibiRecord(): Observable<BaseResult<TibiRecord>>
    /**
     * 充值
     */

    /**
     * 充值哦
     */
    @GET("trade/recharge/address")
    fun charge(@Query("coin" )coin:String): Observable<BaseResult<Charge>>

    /**
     * 充值记录
     */
    @GET("trade/recharge")
    fun chargeRecord(): Observable<BaseResult<ChargeRecord>>
    /**
     * 投资列表
     */
    @GET("trade/products")
    fun touxiList(): Observable<BaseResult<Touzi>>
    /**
     * 投资jilu
     */
    @GET("trade/investment")
    fun touziRecord(): Observable<BaseResult<TouziRecord>>
    /**
     * queren投资
     */
    @POST("trade/investment")
    fun touzi(@Body request: RequestBody): Observable<BaseResult<Any>>
    /**
     * 我的收益
     */
    @GET("revenue")
    fun inComing(): Observable<BaseResult<InComing>>

    /**
     * 我的资产
     */
    @GET("trade/my_asset")
    fun myAsset(): Observable<BaseResult<MyAsset>>

    /**
     * 首页
     */
    @GET("trade/products")
    fun getHome(): Observable<BaseResult<Home>>

    /**
     * 签到
     */
    @POST("trade/sign")
    fun sign(): Observable<BaseResult<Any>>

    /**
     * 立即租用
     */
    @POST("trade/investment")
    fun zuyong(@Body request: RequestBody): Observable<BaseResult<Any>>

    /**
     * 矿机
     */
    @GET("trade/investment")
    fun kuangji(): Observable<BaseResult<Kuangji>>

    /**
     * 矿机明细
     */
    @GET("trade/statement")
    fun mingxi(@Query("start" )start:String,@Query("end" )end:String): Observable<BaseResult<Mingxi>>

    /**
     * 矿机
     */
    @GET("profile")
    fun getShenfen(): Observable<BaseResult<Shenfen>>


    /**
     * 用户协议
     */
    @GET("about")
    fun xieyi(@Query("target")target:String): Observable<BaseResult<Xieyi>>


    /**
     * 签到记录
     */
    @GET("trade/sign_records")
    fun signRecord(): Observable<BaseResult<SignRecord>>
}