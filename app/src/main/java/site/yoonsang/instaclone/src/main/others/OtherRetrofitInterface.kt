package site.yoonsang.instaclone.src.main.others

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import site.yoonsang.instaclone.src.main.others.models.FollowResponse
import site.yoonsang.instaclone.src.main.others.models.OtherProfileResponse

interface OtherRetrofitInterface {

    @POST("/app/follow/{from}/{to}")
    fun postFollow(
        @Path("from") from: Int,
        @Path("to") to: Int
    ): Call<FollowResponse>

    @POST("/app/unfollow/{from}/{to}")
    fun postUnFollow(
        @Path("from") from: Int,
        @Path("to") to: Int
    ): Call<FollowResponse>

    @GET("/app/users/{userId}/profile/{otherId}")
    fun getOtherProfile(
        @Path("userId") userId: Int,
        @Path("otherId") otherId: Int
    ): Call<OtherProfileResponse>
}