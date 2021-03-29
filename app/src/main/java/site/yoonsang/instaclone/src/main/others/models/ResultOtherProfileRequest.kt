package site.yoonsang.instaclone.src.main.others.models

import com.google.gson.annotations.SerializedName
import site.yoonsang.instaclone.src.main.profile.models.Feed
import site.yoonsang.instaclone.src.main.profile.models.User

data class ResultOtherProfileRequest(
    @SerializedName("user") val user: User,
    @SerializedName("feedCount") val feedCount: Int,
    @SerializedName("followerCount") val followerCount: Int,
    @SerializedName("followingCount") val followingCount: Int,
    @SerializedName("feeds") val feeds: ArrayList<Feed>
)