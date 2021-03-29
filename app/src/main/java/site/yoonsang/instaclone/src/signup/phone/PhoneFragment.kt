package site.yoonsang.instaclone.src.signup.phone

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import site.yoonsang.instaclone.R
import site.yoonsang.instaclone.config.ApplicationClass
import site.yoonsang.instaclone.config.BaseFragment
import site.yoonsang.instaclone.databinding.FragmentPhoneBinding
import site.yoonsang.instaclone.src.main.MainActivity
import site.yoonsang.instaclone.src.signup.birthday.BirthdayActivity
import site.yoonsang.instaclone.src.signup.phone.models.PhoneSignUpResponse
import site.yoonsang.instaclone.src.signup.phone.models.PostPhoneSignUp
import site.yoonsang.instaclone.src.signup.terms.TermsActivity

class PhoneFragment :
    BaseFragment<FragmentPhoneBinding>(FragmentPhoneBinding::bind, R.layout.fragment_phone),
    PhoneFragmentView {

    var checkEmptyName = true
    var checkEmptyUserName = true
    var checkEmptyPassword = true
    var checkEmptyPhone = true
    private var btnActivate = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.phoneEtName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkEmptyName = s?.isNotEmpty() != true
                checkAllInputed()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.phoneEtUsername.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkEmptyUserName = s?.isNotEmpty() != true
                checkAllInputed()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.phoneEtPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkEmptyPassword = s?.isNotEmpty() != true
                checkAllInputed()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.phoneEtPhoneNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkEmptyPhone = s?.isNotEmpty() != true
                checkAllInputed()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.phoneBtnNext.setOnClickListener {
            if (btnActivate) {
                val name = binding.phoneEtName.text.toString()
                val userName = binding.phoneEtUsername.text.toString()
                val password = binding.phoneEtPassword.text.toString()
                val phone = binding.phoneEtPhoneNumber.text.toString()
                val postPhoneRequest = PostPhoneSignUp(name, userName, password, phone)
                showLoadingDialog(context!!)
                PhoneService(this).tryPostPhoneSignUp(postPhoneRequest)
            }
        }
    }

    override fun onPostPhoneSignUpSuccess(response: PhoneSignUpResponse) {
        dismissLoadingDialog()
        if (response.isSuccess) {
            ApplicationClass.sSharedPreferences.edit().putString(ApplicationClass.X_ACCESS_TOKEN, response.result.jwt).apply()
            ApplicationClass.sSharedPreferences.edit().putInt(ApplicationClass.USER_ID, response.result.userId).apply()
            val intent = Intent(activity, BirthdayActivity::class.java)
            startActivity(intent)
        } else {
            showCustomToast(response.message.toString())
        }
    }

    override fun onPostPhoneSignUpFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast(message)
    }

    private fun checkAllInputed() {
        btnActivate = if (!checkEmptyName && !checkEmptyUserName && !checkEmptyPassword && !checkEmptyPhone) {
            binding.phoneBtnNext.setBackgroundResource(R.color.selected_button)
            true
        } else {
            binding.phoneBtnNext.setBackgroundResource(R.color.unselected_button)
            false
        }
    }
}