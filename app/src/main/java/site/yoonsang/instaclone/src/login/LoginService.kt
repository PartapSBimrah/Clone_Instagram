package site.yoonsang.instaclone.src.login

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import site.yoonsang.instaclone.config.ApplicationClass
import site.yoonsang.instaclone.src.login.models.LoginResponse
import site.yoonsang.instaclone.src.login.models.PostLoginRequest

class LoginService(val view: LoginActivityView) {

    fun tryPostLogin(postLoginRequest: PostLoginRequest) {
        val loginRetrofitInterface =
            ApplicationClass.sRetrofit.create(LoginRetrofitInterface::class.java)
        loginRetrofitInterface.postLogin(postLoginRequest)
            .enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    view.onPostLoginSuccess(response.body() as LoginResponse)
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    view.onPostLoginFailure(t.message ?: "통신 오류")
                }
            })
    }
}