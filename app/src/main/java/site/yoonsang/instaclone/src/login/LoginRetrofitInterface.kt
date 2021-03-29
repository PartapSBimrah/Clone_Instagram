package site.yoonsang.instaclone.src.login

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import site.yoonsang.instaclone.src.login.models.LoginResponse
import site.yoonsang.instaclone.src.login.models.PostLoginRequest

interface LoginRetrofitInterface {
    @POST("/app/users/login")
    fun postLogin(@Body params: PostLoginRequest): Call<LoginResponse>
}