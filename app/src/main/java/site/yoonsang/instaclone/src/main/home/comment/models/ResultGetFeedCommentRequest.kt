package site.yoonsang.instaclone.src.main.home.comment.models

import com.google.gson.annotations.SerializedName

data class ResultGetFeedCommentRequest(
    @SerializedName("profilImage") val profileImage: String?,
    @SerializedName("userId") val userId: Int,
    @SerializedName("userName") val userName: String,
    @SerializedName("feedCommentId") val feedCommentId: Int,
    @SerializedName("contents") val contents: String,
    @SerializedName("commentType") val commentType: String,
    @SerializedName("replyingTold") val replyingTold: Int,
    @SerializedName("time") val time: String,
    @SerializedName("likeNum") val likeNum: Int
)