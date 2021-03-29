package site.yoonsang.instaclone.src.main.home.liker

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import site.yoonsang.instaclone.src.main.home.liker.models.FeedLikerResponse

interface FeedLikerRetrofitInterface {

    @GET("/app/users/{userId}/feed/{feedId}/like")
    fun getFeedLiker(
        @Path("userId") userId: Int,
        @Path("feedId") feedId: Int
    ): Call<FeedLikerResponse>
}