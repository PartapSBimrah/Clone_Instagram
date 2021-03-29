package site.yoonsang.instaclone.src.main.home.post.feed.photosEdit.upload.models

import com.google.gson.annotations.SerializedName

data class Tag(
    @SerializedName("feedHashTagId") val feedHashTagId: Int,
    @SerializedName("tagName") val tagName: String
)