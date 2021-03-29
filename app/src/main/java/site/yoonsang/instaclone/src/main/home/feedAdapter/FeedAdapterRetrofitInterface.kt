package site.yoonsang.instaclone.src.main.home.feedAdapter

import retrofit2.Call
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import site.yoonsang.instaclone.src.main.home.feedAdapter.models.FeedLikeResponse

interface FeedAdapterRetrofitInterface {

    @POST("/app/users/{userId}/feed/{feedId}/like")
    fun postFeedLike(
        @Path("userId") userId: Int,
        @Path("feedId") feedId: Int
    ): Call<FeedLikeResponse>
}