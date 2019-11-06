package com.octavianionel.openxchangetest.views.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.os.SystemClock
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.octavianionel.openxchangetest.R
import java.lang.ref.WeakReference

/**
 * Created by Reply on 05/11/2019.
 */
class NoLeakSplashScreenActivity: AppCompatActivity() {
    private var activity: Activity? = null

    private var mStartTime: Long = 0

    private var mIsDone: Boolean = false

    private var context: Context? = null


    private var mHandler: UiHandler? = null

    private class UiHandler(srcActivity: NoLeakSplashScreenActivity) : Handler() {
        private val mActivityWeakReference: WeakReference<NoLeakSplashScreenActivity>

        init {
            this.mActivityWeakReference = WeakReference(srcActivity)
        }


        override fun handleMessage(msg: Message) {
            val srcActivity = this.mActivityWeakReference.get()
            if (srcActivity == null) {
                Log.d(TAG_LOG, "Reference to NoLeakSplashScreenActivity lost!")
                return
            }
            when (msg.what) {
                GO_AHEAD_WAIT -> {
                    val elapsedTime = SystemClock.uptimeMillis() - srcActivity.mStartTime
                    if (elapsedTime >= MIN_WAIT_INTERVAL && !srcActivity.mIsDone) {
                        srcActivity.mIsDone = true
                        srcActivity.goAhead()
                    }
                }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        context = this
        activity = this
        //View decorView = getWindow().getDecorView();
        // int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        // decorView.setSystemUiVisibility(uiOptions);
        mHandler = UiHandler(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mHandler?.removeCallbacksAndMessages(null)
    }

    override fun onStart() {
        super.onStart()
        mStartTime = SystemClock.uptimeMillis()
        val goAheadMessage = mHandler?.obtainMessage(GO_AHEAD_WAIT)
        mHandler?.sendMessageAtTime(goAheadMessage, mStartTime + MAX_WAIT_INTERVAL)
        Log.d(TAG_LOG, "Handler message sent")
    }

    private fun goAhead() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    companion object {

        private val TAG_LOG = NoLeakSplashScreenActivity::class.java.name

        private val MIN_WAIT_INTERVAL = 1500L
        private val MAX_WAIT_INTERVAL = 2500L
        private val GO_AHEAD_WAIT = 1
    }
}