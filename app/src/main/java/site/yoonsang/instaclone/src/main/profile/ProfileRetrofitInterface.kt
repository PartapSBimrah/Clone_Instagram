package site.yoonsang.instaclone.src.main.profile

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import site.yoonsang.instaclone.src.main.profile.models.MyProfileResponse

interface ProfileRetrofitInterface {

    @GET("/app/users/{userId}/my-page")
    fun getMyProfile(
        @Path("userId") userId: Int
    ): Call<MyProfileResponse>
}