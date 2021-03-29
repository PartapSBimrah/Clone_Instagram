package site.yoonsang.instaclone.src.signup

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import site.yoonsang.instaclone.config.BaseActivity
import site.yoonsang.instaclone.databinding.ActivitySignUpBinding
import site.yoonsang.instaclone.src.login.LoginActivity
import site.yoonsang.instaclone.src.signup.email.EmailFragment
import site.yoonsang.instaclone.src.signup.phone.PhoneFragment

class SignUpActivity : BaseActivity<ActivitySignUpBinding>(ActivitySignUpBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction()
            .replace(binding.signupFragmentContainer.id, PhoneFragment()).commit()

        binding.signupTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val position = tab!!.position
                var selected: Fragment? = null
                when (position) {
                    0 -> {
                        binding.signupTvTitle.text = "전화번호로 회원가입"
                        selected = PhoneFragment()
                    }
                    1 -> {
                        binding.signupTvTitle.text = "이메일로 회원가입"
                        selected = EmailFragment()
                    }
                }
                supportFragmentManager.beginTransaction()
                    .replace(binding.signupFragmentContainer.id, selected!!).commit()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        binding.signupGotoLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}