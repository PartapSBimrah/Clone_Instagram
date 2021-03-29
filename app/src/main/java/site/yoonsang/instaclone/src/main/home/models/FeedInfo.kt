package site.yoonsang.instaclone.src.main.home.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FeedInfo(
    @SerializedName("userId") val userId: Int,
    @SerializedName("userName") val userName: String,
    @SerializedName("name") val name: String,
    @SerializedName("profileImage") val profileImage: String?,
    @SerializedName("feedId") val feedId: Int,
    @SerializedName("content") val content: String,
    @SerializedName("likeNum") val likeNum: Int,
    @SerializedName("mediaNum") val mediaNum: Int,
    @SerializedName("commentNum") val commentNum: Int,
    @SerializedName("time") val time: String,
    @SerializedName("feedCommentId") val feedCommentId: Int?,
    @SerializedName("commentUserProfile") val commentUserProfile: String?,
    @SerializedName("commentUserId") val commentUserId: Int?,
    @SerializedName("commentUserName") val commentUserName: String?,
    @SerializedName("firstComment") val firstComment: String?
): Serializable