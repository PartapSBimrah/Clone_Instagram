package site.yoonsang.instaclone.src.main.profile.follow.following.models

import com.google.gson.annotations.SerializedName
import site.yoonsang.instaclone.config.BaseResponse

data class ProfileFollowingResponse(
    @SerializedName("result") val result: ArrayList<ResultGetMyFollowingRequest>
): BaseResponse()