package site.yoonsang.instaclone.src.main.profile.myfeed.detail

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import site.yoonsang.instaclone.src.main.home.feedAdapter.models.FeedLikeResponse
import site.yoonsang.instaclone.src.main.profile.myfeed.detail.models.FeedDetailResponse

interface MyFeedDetailRetrofitInterface {

    @GET("/app/users/{userId}/feed/{feedId}")
    fun getFeedDetail(
        @Path("userId") userId: Int,
        @Path("feedId") feedId: Int
    ): Call<FeedDetailResponse>

    @POST("/app/users/{userId}/feed/{feedId}/like")
    fun postFeedLike(
        @Path("userId") userId: Int,
        @Path("feedId") feedId: Int
    ): Call<FeedLikeResponse>
}