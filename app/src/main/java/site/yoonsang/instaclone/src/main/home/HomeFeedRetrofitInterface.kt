package site.yoonsang.instaclone.src.main.home

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import site.yoonsang.instaclone.src.main.home.feedAdapter.models.FeedLikeResponse
import site.yoonsang.instaclone.src.main.home.models.GetFeedsResponse

interface HomeFeedRetrofitInterface {

    @GET("/app/users/{userId}/home-page")
    fun getFeeds(
        @Path("userId") userId: Int
    ): Call<GetFeedsResponse>
}