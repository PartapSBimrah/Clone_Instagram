package site.yoonsang.instaclone.src.signup.birthday

import android.content.Intent
import android.os.Bundle
import site.yoonsang.instaclone.config.BaseActivity
import site.yoonsang.instaclone.databinding.ActivityBirthdayBinding
import site.yoonsang.instaclone.src.signup.terms.TermsActivity

class BirthdayActivity : BaseActivity<ActivityBirthdayBinding>(ActivityBirthdayBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.birthdayBtnNext.setOnClickListener {
            val intent = Intent(this, TermsActivity::class.java)
            startActivity(intent)
        }

        binding.birthdayDatepicker.maxDate = System.currentTimeMillis()
        binding.birthdayDatepicker.setOnDateChangedListener { view, year, monthOfYear, dayOfMonth ->
            binding.birthdaySelectedDate.text = "${year}년 ${monthOfYear + 1}월 ${dayOfMonth}일"
            binding.birthdayAge.text = "${2021 - year + 1}세"
        }
    }
}