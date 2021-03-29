package site.yoonsang.instaclone.src.main.home.feedAdapter.models

import com.google.gson.annotations.SerializedName

data class ResultFeedLikeRequest(
    @SerializedName("feedLikeRes") val feedLikeRes: FeedLikeRes,
    @SerializedName("totalLikeNum") val totalLikeNum: Int
)