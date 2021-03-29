package site.yoonsang.instaclone.src.main.home.post.feed.photosEdit.upload.models

import com.google.gson.annotations.SerializedName

data class PostFeedUploadRequest(
    @SerializedName("content") val content: String,
    @SerializedName("media") val media: ArrayList<Media>,
    @SerializedName("tag") val tag: ArrayList<String>
)