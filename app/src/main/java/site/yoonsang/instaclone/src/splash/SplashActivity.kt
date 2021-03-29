package site.yoonsang.instaclone.src.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import site.yoonsang.instaclone.config.ApplicationClass
import site.yoonsang.instaclone.config.BaseActivity
import site.yoonsang.instaclone.databinding.ActivitySplashBinding
import site.yoonsang.instaclone.src.login.LoginActivity
import site.yoonsang.instaclone.src.main.MainActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            if (ApplicationClass.sSharedPreferences.getString(
                    ApplicationClass.X_ACCESS_TOKEN,
                    null
                ) != null && ApplicationClass.sSharedPreferences.getInt(
                    ApplicationClass.USER_ID,
                    0
                ) != 0
            ) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }, 1500)
    }
}