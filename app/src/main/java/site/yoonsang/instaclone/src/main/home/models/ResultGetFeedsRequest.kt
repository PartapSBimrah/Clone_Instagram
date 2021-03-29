package site.yoonsang.instaclone.src.main.home.models

import com.google.gson.annotations.SerializedName
import site.yoonsang.instaclone.config.BaseResponse

data class ResultGetFeedsRequest(
    @SerializedName("feedInfo") val feedInfo: FeedInfo,
    @SerializedName("medias") val medias: ArrayList<Medias>?,
    @SerializedName("htags") val htags: ArrayList<HTags>?,
    @SerializedName("didLike") val didLike: String
): BaseResponse()