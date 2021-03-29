package site.yoonsang.instaclone.src.main.profile.myfeed.detail.models

import com.google.gson.annotations.SerializedName
import site.yoonsang.instaclone.config.BaseResponse

data class FeedDetailResponse(
    @SerializedName("result") val result: ResultGetFeedDetailRequest
): BaseResponse()