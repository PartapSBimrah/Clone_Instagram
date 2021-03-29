package site.yoonsang.instaclone.src.main.home.liker.models

import com.google.gson.annotations.SerializedName
import site.yoonsang.instaclone.config.BaseResponse

data class FeedLikerResponse(
    @SerializedName("result") val result: ArrayList<ResultGetFeedLikerRequest>
): BaseResponse()