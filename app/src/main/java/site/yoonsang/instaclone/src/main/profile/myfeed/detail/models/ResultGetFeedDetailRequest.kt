package site.yoonsang.instaclone.src.main.profile.myfeed.detail.models

import com.google.gson.annotations.SerializedName
import site.yoonsang.instaclone.config.BaseResponse
import site.yoonsang.instaclone.src.main.home.models.FeedInfo
import site.yoonsang.instaclone.src.main.home.models.HTags
import site.yoonsang.instaclone.src.main.home.models.Medias

data class ResultGetFeedDetailRequest(
    @SerializedName("feedInfo") val feedInfo: FeedInfo,
    @SerializedName("medias") val medias: ArrayList<Medias>?,
    @SerializedName("htags") val htags: ArrayList<HTags>?,
    @SerializedName("didLike") val didLike: String
)