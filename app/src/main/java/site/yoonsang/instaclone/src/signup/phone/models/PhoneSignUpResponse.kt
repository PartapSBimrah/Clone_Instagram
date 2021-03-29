package site.yoonsang.instaclone.src.signup.phone.models

import com.google.gson.annotations.SerializedName
import site.yoonsang.instaclone.config.BaseResponse

data class PhoneSignUpResponse(
    @SerializedName("result") val result: ResultPhoneSignUp
): BaseResponse()