package site.yoonsang.instaclone.src.main.profile.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Feed(
    @SerializedName("feedId") val feedId: Int?,
    @SerializedName("firstMediaId") val firstMediaId: Int?,
    @SerializedName("mediaUrl") val mediaUrl: String?,
    @SerializedName("mediaType") val mediaType: String?,
    @SerializedName("mediaNum") val mediaNum: String?
): Serializable