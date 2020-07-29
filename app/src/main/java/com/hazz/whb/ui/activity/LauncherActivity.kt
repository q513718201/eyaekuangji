package com.hazz.whb.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.hazz.whb.R
import com.hazz.whb.utils.SPUtil

import io.reactivex.Observable
import kotlinx.android.synthetic.main.launcher.*
import pl.droidsonroids.gif.GifDrawable

import java.util.concurrent.TimeUnit


class LauncherActivity : AppCompatActivity() {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!isTaskRoot) {
            var intent = intent
            var intentAction = intent.action
            if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && intentAction != null && intentAction.equals(Intent
                            .ACTION_MAIN)) {
                finish()
                return
            }
        }
        setContentView(R.layout.launcher)
        val gifDrawable = GifDrawable(resources, R.drawable.whb)
        gifDrawable.loopCount=1
        gif.setImageDrawable(gifDrawable)

        Observable.timer(5, TimeUnit.SECONDS)
                .subscribe {
                    if (!TextUtils.isEmpty(SPUtil.getString("token"))) {
                        startActivity(Intent(this, MainActivity::class.java))
                    } else {
                        //IFragmentActivity.startSelf(this,LoginFragment())
                        startActivity(Intent(this, LoginActivity::class.java))

                    }
                    finish()

                }
    }


}
