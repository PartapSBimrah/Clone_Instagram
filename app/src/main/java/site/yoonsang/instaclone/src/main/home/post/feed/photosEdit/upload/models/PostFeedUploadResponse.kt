package site.yoonsang.instaclone.src.main.home.post.feed.photosEdit.upload.models

import com.google.gson.annotations.SerializedName
import site.yoonsang.instaclone.config.BaseResponse

data class PostFeedUploadResponse(
    @SerializedName("result") val result: ResultFeedUploadRequest
): BaseResponse()