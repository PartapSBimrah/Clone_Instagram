package site.yoonsang.instaclone.src.main.profile.follow.follower

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import site.yoonsang.instaclone.src.main.profile.follow.follower.models.ProfileFollowerResponse

interface ProfileFollowerRetrofitInterface {

    @GET("/app/users/{userId}/followed")
    fun getFollower(
        @Path("userId") userId: Int
    ):Call<ProfileFollowerResponse>
}