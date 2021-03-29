package site.yoonsang.instaclone.src.signup.terms

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.AppCompatCheckBox
import site.yoonsang.instaclone.R
import site.yoonsang.instaclone.config.BaseActivity
import site.yoonsang.instaclone.databinding.ActivityTermsBinding
import site.yoonsang.instaclone.src.main.MainActivity
import site.yoonsang.instaclone.src.signup.SignUpActivity
import site.yoonsang.instaclone.src.signup.birthday.BirthdayActivity

class TermsActivity : BaseActivity<ActivityTermsBinding>(ActivityTermsBinding::inflate) {

    var allChecked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.termsCheckBoxAll.setOnClickListener { onCheckChanged(binding.termsCheckBoxAll) }
        binding.termsCheckBoxService.setOnClickListener { onCheckChanged(binding.termsCheckBoxService) }
        binding.termsCheckBoxData.setOnClickListener { onCheckChanged(binding.termsCheckBoxData) }
        binding.termsCheckBoxLocation.setOnClickListener { onCheckChanged(binding.termsCheckBoxLocation) }

        binding.termsBtnNext.setOnClickListener {
            if (allChecked) {
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
    }

    private fun onCheckChanged(checkBox: AppCompatCheckBox) {
        when (checkBox.id) {
            binding.termsCheckBoxAll.id -> {
                if (binding.termsCheckBoxAll.isChecked) {
                    binding.termsCheckBoxService.isChecked = true
                    binding.termsCheckBoxData.isChecked = true
                    binding.termsCheckBoxLocation.isChecked = true
                    binding.termsBtnNext.setBackgroundResource(R.color.selected_button)
                    allChecked = true
                } else {
                    binding.termsCheckBoxService.isChecked = false
                    binding.termsCheckBoxData.isChecked = false
                    binding.termsCheckBoxLocation.isChecked = false
                    binding.termsBtnNext.setBackgroundResource(R.color.unselected_button)
                    allChecked = false
                }
            }
            else -> {
                binding.termsCheckBoxAll.isChecked = (
                        binding.termsCheckBoxService.isChecked
                                && binding.termsCheckBoxData.isChecked
                                && binding.termsCheckBoxLocation.isChecked)
                if (binding.termsCheckBoxAll.isChecked) {
                    allChecked = true
                    binding.termsBtnNext.setBackgroundResource(R.color.selected_button)
                } else {
                    allChecked = false
                    binding.termsBtnNext.setBackgroundResource(R.color.unselected_button)
                }
            }
        }
    }
}