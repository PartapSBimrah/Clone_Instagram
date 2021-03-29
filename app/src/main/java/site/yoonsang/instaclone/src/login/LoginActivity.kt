package site.yoonsang.instaclone.src.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.Log
import com.facebook.*
import com.facebook.login.LoginResult
import org.json.JSONException
import site.yoonsang.instaclone.R
import site.yoonsang.instaclone.config.ApplicationClass
import site.yoonsang.instaclone.config.BaseActivity
import site.yoonsang.instaclone.databinding.ActivityLoginBinding
import site.yoonsang.instaclone.src.login.models.LoginResponse
import site.yoonsang.instaclone.src.login.models.PostLoginRequest
import site.yoonsang.instaclone.src.main.MainActivity
import site.yoonsang.instaclone.src.signup.SignUpActivity

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate),
    LoginActivityView {

    private lateinit var callbackManager: CallbackManager
    var checkEmptyId = true
    var checkEmptyPassword = true
    private var btnActivate = false
    private var eyeOpened = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.loginEtId.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkEmptyId = s?.isNotEmpty() != true
                checkAllInputed()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.loginEtPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkEmptyPassword = s?.isNotEmpty() != true
                checkAllInputed()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.loginPasswordEye.setOnClickListener {
            if (eyeOpened) {
                eyeOpened = false
                binding.loginPasswordEye.setImageResource(R.drawable.ic_eye_closed)
                binding.loginEtPassword.inputType = InputType.TYPE_CLASS_TEXT.or(InputType.TYPE_TEXT_VARIATION_PASSWORD)
                binding.loginEtPassword.setSelection(binding.loginEtPassword.length())
            } else {
                eyeOpened = true
                binding.loginPasswordEye.setImageResource(R.drawable.ic_eye_opened)
                binding.loginEtPassword.inputType = InputType.TYPE_CLASS_TEXT.or(InputType.TYPE_TEXT_VARIATION_NORMAL)
                binding.loginEtPassword.setSelection(binding.loginEtPassword.length())
            }
        }

        binding.loginBtnLogin.setOnClickListener {
            if (btnActivate) {
                val id = binding.loginEtId.text.toString()
                val password = binding.loginEtPassword.text.toString()
                val loginRequest = PostLoginRequest(id, password)
                showLoadingDialog(this)
                LoginService(this).tryPostLogin(loginRequest)
            }
        }

        binding.loginTvSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.loginTvFacebookLogin.setOnClickListener {
            binding.loginFacebookLogin.performClick()
        }

        binding.loginFacebookLogin.setPermissions(listOf("email", "public_profile"))
        callbackManager = CallbackManager.Factory.create()
        binding.loginFacebookLogin.registerCallback(
            callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult?) {
                    if (result?.accessToken != null) {
                        val accessToken = result.accessToken
                        getFacebookInfo(accessToken)
                    } else {
                        Log.d("checkkk", "access token is null")
                    }
                }

                override fun onCancel() {
                }

                override fun onError(error: FacebookException?) {
                }
            })
    }

    override fun onPostLoginSuccess(response: LoginResponse) {
        dismissLoadingDialog()
        if (response.isSuccess) {
            ApplicationClass.sSharedPreferences.edit().putString(ApplicationClass.X_ACCESS_TOKEN, response.result.jwt).apply()
            ApplicationClass.sSharedPreferences.edit().putInt(ApplicationClass.USER_ID, response.result.userId).apply()
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        } else {
            showCustomToast(response.message.toString())
        }
    }

    override fun onPostLoginFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast(message)
    }

    private fun checkAllInputed() {
        btnActivate = if (!checkEmptyId && !checkEmptyPassword) {
            binding.loginBtnLogin.setBackgroundResource(R.color.selected_button)
            true
        } else {
            binding.loginBtnLogin.setBackgroundResource(R.color.unselected_button)
            false
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun getFacebookInfo(accessToken: AccessToken) {
        val graphRequest = GraphRequest.newMeRequest(
            accessToken
        ) { resultObject, _ ->
            try {
                val name = resultObject?.getString("name")
                val email = resultObject?.getString("email")
                val image =
                    resultObject?.getJSONObject("picture")?.getJSONObject("data")?.getString("url")
                Log.d("checkkk", "name $name")
                Log.d("checkkk", "email $email")
                Log.d("checkkk", "image $image")
                Log.d("checkkk", "token ${accessToken.token}")
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }

        val parameters = Bundle()
        parameters.putString("fields", "id,name,email,picture")
        graphRequest.parameters = parameters
        graphRequest.executeAsync()
    }
}