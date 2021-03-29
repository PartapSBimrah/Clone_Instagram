package site.yoonsang.instaclone.src.main.home.post.feed.photosEdit.upload.models

import com.google.gson.annotations.SerializedName

data class Media(
    @SerializedName("mediaUrl") val mediaUrl: String,
    @SerializedName("mediaType") val mediaType: String
)