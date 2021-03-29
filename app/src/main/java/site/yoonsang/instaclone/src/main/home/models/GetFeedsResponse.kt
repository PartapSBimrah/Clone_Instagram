package site.yoonsang.instaclone.src.main.home.models

import com.google.gson.annotations.SerializedName
import site.yoonsang.instaclone.config.BaseResponse

data class GetFeedsResponse(
    @SerializedName("result") val result: ArrayList<ResultGetFeedsRequest>
): BaseResponse()