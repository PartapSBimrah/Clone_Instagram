package site.yoonsang.instaclone.src.main.home.feedMore

import retrofit2.Call
import retrofit2.http.PATCH
import retrofit2.http.Path
import site.yoonsang.instaclone.src.main.home.feedMore.models.DeleteFeedResponse

interface MyFeedMoreRetrofitInterface {

    @PATCH("/app/users/{userId}/feed/{feedId}/status")
    fun deleteFeed(
        @Path("userId") userId: Int,
        @Path("feedId") feedId: Int
    ):Call<DeleteFeedResponse>
}