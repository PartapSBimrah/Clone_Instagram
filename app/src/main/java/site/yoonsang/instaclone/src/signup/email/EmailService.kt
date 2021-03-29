package site.yoonsang.instaclone.src.signup.email

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import site.yoonsang.instaclone.config.ApplicationClass
import site.yoonsang.instaclone.src.signup.email.models.EmailSignUpResponse
import site.yoonsang.instaclone.src.signup.email.models.PostEmailSignUp

class EmailService(val view: EmailFragmentView) {

    fun tryPostEmailSignUp(postEmailSignUp: PostEmailSignUp) {
        val emailRetrofitInterface =
            ApplicationClass.sRetrofit.create(EmailRetrofitInterface::class.java)
        emailRetrofitInterface.postEmailSignUp(postEmailSignUp)
            .enqueue(object : Callback<EmailSignUpResponse> {
                override fun onResponse(
                    call: Call<EmailSignUpResponse>,
                    response: Response<EmailSignUpResponse>
                ) {
                    Log.d("checkkk", "${response.code()}")
                    Log.d("checkkk", response.message())
                    Log.d("checkkk", "${response.body()}")
                    if (response.isSuccessful) {
                        view.onPostEmailSignUpSuccess(response.body() as EmailSignUpResponse)
                    }
                }

                override fun onFailure(call: Call<EmailSignUpResponse>, t: Throwable) {
                    view.onPostEmailSignUpFailure(t.message ?: "통신 오류")
                }
            })
    }
}