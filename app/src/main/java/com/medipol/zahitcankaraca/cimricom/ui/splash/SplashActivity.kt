package com.medipol.zahitcankaraca.cimricom.ui.splash

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.medipol.zahitcankaraca.cimricom.utils.AlertDialogUtil
import com.medipol.zahitcankaraca.cimricom.utils.NetworkUtil
import com.medipol.zahitcankaraca.cimricom.R
import com.medipol.zahitcankaraca.cimricom.ui.login.LoginActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        init()
    }

    private fun init() {
        val timer = object: CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
            }

            @RequiresApi(Build.VERSION_CODES.M)
            override fun onFinish() {
                if(NetworkUtil.isOnline(applicationContext)){
                    startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                    finish()
                }
                else{
                    AlertDialogUtil.getAlertDialog(this@SplashActivity, getString(R.string.alert_title), getString(R.string.alert_message), getString(R.string.alert_settings), getString(R.string.alert_close), getString(R.string.splashActivity))
                }
            }
        }
        timer.start()
    }
}