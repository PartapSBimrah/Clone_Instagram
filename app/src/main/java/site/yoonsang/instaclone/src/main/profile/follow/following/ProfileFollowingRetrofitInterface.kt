package site.yoonsang.instaclone.src.main.profile.follow.following

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import site.yoonsang.instaclone.src.main.profile.follow.following.models.ProfileFollowingResponse

interface ProfileFollowingRetrofitInterface {

    @GET("/app/users/{userId}/following")
    fun getFollowing(
        @Path("userId") userId: Int
    ):Call<ProfileFollowingResponse>
}