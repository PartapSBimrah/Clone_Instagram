package site.yoonsang.instaclone.src.main.home.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class HTags(
    @SerializedName("feedHashTagId") val feedHashTagId: Int,
    @SerializedName("tagName") val tagName: String
): Serializable