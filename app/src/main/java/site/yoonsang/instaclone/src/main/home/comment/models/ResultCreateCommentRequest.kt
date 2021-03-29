package site.yoonsang.instaclone.src.main.home.comment.models

import com.google.gson.annotations.SerializedName

data class ResultCreateCommentRequest(
    @SerializedName("userId") val userId: Int,
    @SerializedName("feedCommentId") val feedCommentId: Int
)