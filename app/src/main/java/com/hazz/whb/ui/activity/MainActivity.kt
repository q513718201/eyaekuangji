package com.hazz.whb.ui.activity

import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.hazz.whb.R
import com.hazz.whb.events.Index
import com.hazz.whb.ui.fragment.*
import com.hazz.whb.utils.ActivityManager
import com.hazz.whb.utils.RxBus
import com.tencent.bugly.Bugly
import kotlinx.android.synthetic.main.activity_main_ruoyu_new.*


class MainActivity : AppCompatActivity(), RadioGroup.OnCheckedChangeListener {


    private lateinit var mFragments: ArrayList<Fragment>
    private var mLastSelect = 0
    /**
     * 想要去到的页面，由于需要登录，无法直接显示
     */
    private var mWantSelectIndex = -1
    private var mTags: String? = null
    private var mLastClickTime: Long = 0

    private var builder: AlertDialog.Builder? = null
    private var dialog: AlertDialog? = null
    private val mHits = LongArray(2)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_ruoyu_new)
        // mPresenter= AppNewVersionPresenter(this)
        initFragment()
        mRG.setOnCheckedChangeListener(this)
        Bugly.init(this, "b2e92dff41", false)
        RxBus.get().observerOnMain(this,Index::class.java) {
            checkFragment(1)
            mRbMining.isChecked = true
        }

    }

    override fun onResume() {
        super.onResume()
        // mPresenter.getAppVersion(SPUtils.getLanguage(this),"android")
    }


    private fun initFragment() {
        mFragments = ArrayList()
        mFragments.add(HomeFragment())
        mFragments.add(HangqingFragment())
        mFragments.add(ZichanFragment())
        mFragments.add(CoinFragment())
        mFragments.add(MineFragment())
        supportFragmentManager.beginTransaction()
            .replace(R.id.mFrame, mFragments[0], mFragments[0]::class.java.simpleName)
            .commitAllowingStateLoss()

        mRbMall.isChecked = true
        mLastSelect = 0

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

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        when (checkedId) {

            R.id.mRbMall -> {
                checkFragment(0)
            }
            R.id.mRbMining -> {
                checkFragment(1)
            }
            R.id.mRbHangqing -> {
                checkFragment(3)

            }
            R.id.mRbShopCar -> {
                checkFragment(2)

            }
            R.id.mRbOtc -> {
                checkFragment(4)

            }


        }
    }

    private fun checkFragment(index: Int) {
        if (index == mLastSelect) {
            return
        }
        val transaction = supportFragmentManager.beginTransaction()
        transaction.hide(mFragments[mLastSelect])
        val fragment = mFragments[index]
        if (!fragment.isAdded) {
            transaction.add(R.id.mFrame, fragment, fragment::class.java.simpleName).show(fragment)
        } else {
            transaction.show(fragment)
        }
        transaction.commitAllowingStateLoss()
        mLastSelect = index

    }

    fun setSelectRb(index: Int) {
        when (index) {

            0 -> mRbMall.isChecked = true
            1 -> mRbMining.isChecked = true
            2 -> mRbHangqing.isChecked = true
            3 -> mRbShopCar.isChecked = true
            4 -> mRbOtc.isChecked = true

        }
    }

    override fun onBackPressed() {
        System.arraycopy(mHits, 1, mHits, 0, mHits.size - 1)
        mHits[mHits.size - 1] = SystemClock.uptimeMillis()
        if (mHits[0] >= SystemClock.uptimeMillis() - 500) {
            super.onBackPressed()
            ActivityManager.getInstance().finishAll()
        } else {
            Toast.makeText(applicationContext, "轻按两次退出", Toast.LENGTH_SHORT).show()
        }
    }


}
