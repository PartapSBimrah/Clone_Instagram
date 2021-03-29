package site.yoonsang.instaclone.src.signup.phone

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import site.yoonsang.instaclone.src.signup.phone.models.PhoneSignUpResponse
import site.yoonsang.instaclone.src.signup.phone.models.PostPhoneSignUp

interface PhoneRetrofitInterface {
    @POST("/app/users/signup-phone")
    fun postPhoneSignUp(@Body params: PostPhoneSignUp): Call<PhoneSignUpResponse>
}