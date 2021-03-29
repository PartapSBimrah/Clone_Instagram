package site.yoonsang.instaclone.src.signup.email

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import site.yoonsang.instaclone.R
import site.yoonsang.instaclone.config.ApplicationClass
import site.yoonsang.instaclone.config.BaseFragment
import site.yoonsang.instaclone.databinding.FragmentEmailBinding
import site.yoonsang.instaclone.src.main.MainActivity
import site.yoonsang.instaclone.src.signup.birthday.BirthdayActivity
import site.yoonsang.instaclone.src.signup.email.models.EmailSignUpResponse
import site.yoonsang.instaclone.src.signup.email.models.PostEmailSignUp
import site.yoonsang.instaclone.src.signup.terms.TermsActivity

class EmailFragment :
    BaseFragment<FragmentEmailBinding>(FragmentEmailBinding::bind, R.layout.fragment_email),
    EmailFragmentView {

    var checkEmptyName = true
    var checkEmptyUserName = true
    var checkEmptyPassword = true
    var checkEmptyEmail = true
    private var btnActivate = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.emailEtName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkEmptyName = s?.isNotEmpty() != true
                checkAllInputed()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.emailEtUsername.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkEmptyUserName = s?.isNotEmpty() != true
                checkAllInputed()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.emailEtPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkEmptyPassword = s?.isNotEmpty() != true
                checkAllInputed()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.emailEtEmailAddress.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkEmptyEmail = s?.isNotEmpty() != true
                checkAllInputed()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.emailBtnNext.setOnClickListener {
            if (btnActivate) {
                val name = binding.emailEtName.text.toString()
                val userName = binding.emailEtUsername.text.toString()
                val password = binding.emailEtPassword.text.toString()
                val email = binding.emailEtEmailAddress.text.toString()
                val postEmailRequest = PostEmailSignUp(name, userName, password, email)
                showLoadingDialog(context!!)
                EmailService(this).tryPostEmailSignUp(postEmailRequest)
            }
        }
    }

    override fun onPostEmailSignUpSuccess(response: EmailSignUpResponse) {
        dismissLoadingDialog()
        if (response.isSuccess) {
            ApplicationClass.sSharedPreferences.edit()
                .putString(ApplicationClass.X_ACCESS_TOKEN, response.result.jwt).apply()
            ApplicationClass.sSharedPreferences.edit()
                .putInt(ApplicationClass.USER_ID, response.result.userId).apply()
            val intent = Intent(activity, BirthdayActivity::class.java)
            startActivity(intent)
        } else {
            showCustomToast(response.message.toString())
        }
    }

    override fun onPostEmailSignUpFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast(message)
    }

    private fun checkAllInputed() {
        btnActivate =
            if (!checkEmptyName && !checkEmptyUserName && !checkEmptyPassword && !checkEmptyEmail) {
                binding.emailBtnNext.setBackgroundResource(R.color.selected_button)
                true
            } else {
                binding.emailBtnNext.setBackgroundResource(R.color.unselected_button)
                false
            }
    }
}