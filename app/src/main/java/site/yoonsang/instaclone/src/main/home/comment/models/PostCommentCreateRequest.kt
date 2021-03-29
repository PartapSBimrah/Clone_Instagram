package site.yoonsang.instaclone.src.main.home.comment.models

import com.google.gson.annotations.SerializedName

data class PostCommentCreateRequest(
    @SerializedName("userId") val userId: Int,
    @SerializedName("contents") val contents: String
)