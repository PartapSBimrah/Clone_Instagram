package site.yoonsang.instaclone.src.main.home.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Medias(
    @SerializedName("feedMediaId") val feedMediaId: Int,
    @SerializedName("mediaUrl") val mediaUrl: String,
    @SerializedName("mediaType") val mediaType: String
): Serializable