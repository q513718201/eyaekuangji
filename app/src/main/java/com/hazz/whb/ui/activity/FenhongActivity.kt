package com.hazz.whb.ui.activity

import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.hazz.whb.R
import com.hazz.whb.base.BaseActivity
import com.hazz.whb.mvp.contract.LoginContract
import com.hazz.whb.mvp.model.bean.TimeData
import com.hazz.whb.ui.fragment.CurrentTouziFragment
import com.hazz.whb.ui.fragment.FenhongChiFragment
import com.hazz.whb.utils.ToolBarCustom
import com.hazz.whb.view.CheckFragment
import kotlinx.android.synthetic.main.activity_fenhong.*
import kotlinx.android.synthetic.main.charge.mToolBar
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class FenhongActivity : BaseActivity(), LoginContract.FenhongView{

    override fun timeDataSuccess(msg: List<TimeData>) {
    }

    override fun layoutId(): Int {
        EventBus.getDefault().register(this)
        return R.layout.activity_fenhong
    }

    private lateinit var mFragments: ArrayList<Fragment>
    private var mLastSelect = 0

    override fun initData() {
        initFragment()
        tvFenHongChi.setOnClickListener {
            checkFragment(0)
        }
        tvTouZi.setOnClickListener {
            checkFragment(1)
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun refreshHomeEvent(event: CheckFragment) {
        checkFragment(event.index)
    }

    private fun initFragment() {
        mFragments = ArrayList()
        mFragments.add(FenhongChiFragment())
        mFragments.add(CurrentTouziFragment())
        viewPager.offscreenPageLimit = 2
        viewPager.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return mFragments[position]
            }

            override fun getCount(): Int {
                return mFragments.size
            }

        }
//        supportFragmentManager.beginTransaction()
//                .replace(R.id.mFrame, mFragments[0], mFragments[0]::class.java.simpleName)
//                .commitAllowingStateLoss()

        mLastSelect = 0

    }

    override fun initView() {
        setStatusBarTextColor(false)
        ToolBarCustom.newBuilder(mToolBar as Toolbar)
                .setLeftIcon(R.mipmap.back_white)
                .setTitleColor(resources.getColor(R.color.color_translucent))
                .setToolBarBg(resources.getColor(R.color.color_translucent))
                .setOnLeftIconClickListener { view -> finish() }

    }

    override fun start() {

    }


    override fun commitSuccess(msg: String) {

    }


    private fun checkFragment(index: Int) {
        if (index == mLastSelect) {
            return
        }
        if (index == 0) {
            ivFhBack.visibility = View.VISIBLE
            tvFenHongChi.background = resources.getDrawable(R.drawable.bg_0ab49f_coner4)
            tvTouZi.setBackgroundColor(resources.getColor(R.color.color_translucent))
        } else {
            ivFhBack.visibility = View.VISIBLE
            tvTouZi.background = resources.getDrawable(R.drawable.bg_0ab49f_coner4)
            tvFenHongChi.setBackgroundColor(resources.getColor(R.color.color_translucent))
//            ToolBarCustom.newBuilder(mToolBar as Toolbar).setToolBarBgRescource(R.drawable.bg_hangqing)
//            setStatusBarTextColor(false)
        }
//        ToolBarCustom.newBuilder(mToolBar as Toolbar).setToolBarBg(resources.getColor(R.color.color_translucent))
        viewPager.currentItem = index

        mLastSelect = index

    }

    /**
     * 设置状态栏字体颜色是否为亮色
     */
    private fun setStatusBarTextColor(isLight: Boolean) {
        if (isLight) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or
                    (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        } else {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE or
                    (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

}
