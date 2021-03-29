package site.yoonsang.instaclone.src.main.profile.follow.follower.models

import com.google.gson.annotations.SerializedName
import site.yoonsang.instaclone.config.BaseResponse

data class ProfileFollowerResponse(
    @SerializedName("result") val result: ArrayList<ResultGetMyFollowerRequest>
): BaseResponse()