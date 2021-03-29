package site.yoonsang.instaclone.src.main.home.feedAdapter.models

import com.google.gson.annotations.SerializedName
import site.yoonsang.instaclone.config.BaseResponse

data class FeedLikeResponse(
    @SerializedName("result") val result: ResultFeedLikeRequest
): BaseResponse()