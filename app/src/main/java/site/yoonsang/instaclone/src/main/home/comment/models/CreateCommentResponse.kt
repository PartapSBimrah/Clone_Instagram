package site.yoonsang.instaclone.src.main.home.comment.models

import com.google.gson.annotations.SerializedName
import site.yoonsang.instaclone.config.BaseResponse

data class CreateCommentResponse(
    @SerializedName("result") val result: ResultCreateCommentRequest
): BaseResponse()