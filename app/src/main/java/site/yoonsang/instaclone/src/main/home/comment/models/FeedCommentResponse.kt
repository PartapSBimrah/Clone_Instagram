package site.yoonsang.instaclone.src.main.home.comment.models

import com.google.gson.annotations.SerializedName
import site.yoonsang.instaclone.config.BaseResponse

data class FeedCommentResponse(
    @SerializedName("result") val result: ArrayList<ResultGetFeedCommentRequest>
): BaseResponse()