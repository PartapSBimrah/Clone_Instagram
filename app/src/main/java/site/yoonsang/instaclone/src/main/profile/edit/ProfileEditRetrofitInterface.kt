package site.yoonsang.instaclone.src.main.profile.edit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import site.yoonsang.instaclone.src.main.profile.edit.models.UserInfoResponse

interface ProfileEditRetrofitInterface {

    @GET("/app/users/{userId}/profile")
    fun getUserInfo(
        @Path("userId") userId: Int
    ):Call<UserInfoResponse>
}