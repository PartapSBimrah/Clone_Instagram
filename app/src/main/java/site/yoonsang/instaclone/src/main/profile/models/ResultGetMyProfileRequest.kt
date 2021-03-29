package site.yoonsang.instaclone.src.main.profile.models

import com.google.gson.annotations.SerializedName

data class ResultGetMyProfileRequest(
    @SerializedName("user") val user: User,
    @SerializedName("feedCount") val feedCount: Int,
    @SerializedName("followerCount") val followerCount: Int,
    @SerializedName("followingCount") val followingCount: Int,
    @SerializedName("feeds") val feeds: ArrayList<Feed>
)