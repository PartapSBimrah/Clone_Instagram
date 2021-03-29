package site.yoonsang.instaclone.src.signup.email

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import site.yoonsang.instaclone.src.signup.email.models.EmailSignUpResponse
import site.yoonsang.instaclone.src.signup.email.models.PostEmailSignUp

interface EmailRetrofitInterface {
    @POST("/app/users/signup-email")
    fun postEmailSignUp(@Body params: PostEmailSignUp): Call<EmailSignUpResponse>
}