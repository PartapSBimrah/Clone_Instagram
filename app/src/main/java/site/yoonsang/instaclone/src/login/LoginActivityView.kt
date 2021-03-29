package site.yoonsang.instaclone.src.login

import site.yoonsang.instaclone.src.login.models.LoginResponse

interface LoginActivityView {

    fun onPostLoginSuccess(response: LoginResponse)

    fun onPostLoginFailure(message: String)
}