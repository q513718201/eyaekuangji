package com.hazz.whb.mvp.contract

import com.hazz.whb.mvp.model.DuihuanRecord
import com.hazz.whb.mvp.model.Home
import com.hazz.whb.mvp.model.Image
import com.hazz.whb.mvp.model.Xieyi
import com.hazz.whb.mvp.model.bean.*
import com.hazz.whb.net.BaseView


/**
 * Description:
 * Date：2019/4/9-14:03
 * Author: cwh
 */
interface LoginContract {

    interface LoginView : BaseView {

        fun loginSuccess(msg: UserInfo)
        fun sendSms(msg: String)
        fun registerSucceed(msg: String)
    }

    interface CoinView : BaseView {

        fun getCoin(msg: List<Coin>)
        fun getFriends(msg: Friends)
    }

    interface MsgView : BaseView {

        fun getMsg(msg: List<Msg>)

    }

    interface NodeView : BaseView {

        fun getNode(msg: Node)
        fun getShenfen(msg: Shenfen)
    }

    interface TibiView : BaseView {

        fun tibiSucceed(msg: String)
        fun tibiRecord(msg: TibiRecord)
    }

    interface DuihuanView : BaseView {

        fun duihuanSucceed(msg: String)
        fun duihuanRecord(msg: DuihuanRecord)
    }

    interface ShouyiView : BaseView {

        fun inComing(msg: InComing)
    }

    interface kuangjiView : BaseView {

        fun getKuangji(msg: Kuangji)
        fun getMingxi(msg: Mingxi)
    }

    interface HomeView : BaseView {

        fun getHome(msg: Home)
        fun zuyongSucceed(msg: String)
    }

    interface ChargeView : BaseView {

        fun getAddress(msg: Charge)

        fun chargeRecord(msg: ChargeRecord)
    }

    interface TouziView : BaseView {

        fun touziList(msg: Touzi)
        fun touziConfirm(msg: String)
        fun touziRecord(msg: TouziRecord)
    }

    interface ZichanView : BaseView {

        fun myAsset(msg: MyAsset)
    }

    interface XieyiView : BaseView {

        fun xieyi(msg: Xieyi)
        fun getSignRecord(msg: SignRecord)
    }

    interface MyStateView : BaseView {

        fun myState(msg: MyState)
    }

    interface AuthView : BaseView {

        fun uploadSucceed(msg: Image)
        fun authSucceed(msg: String)
    }

    /**
     * 分红
     */
    interface FenhongView : BaseView {

        fun timeDataSuccess(msg: List<TimeData>)
        fun commitSuccess(msg: String)
    }

    interface CurrentInventView : BaseView {
        fun setCurrentData(data: CurrentInventBean)
        fun onResult(msg: String)
    }
}