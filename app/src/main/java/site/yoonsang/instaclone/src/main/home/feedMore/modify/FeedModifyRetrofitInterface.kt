package site.yoonsang.instaclone.src.main.home.feedMore.modify

import retrofit2.Call
import retrofit2.http.PATCH
import retrofit2.http.Path
import site.yoonsang.instaclone.src.main.home.feedMore.modify.models.FeedModifyResponse

interface FeedModifyRetrofitInterface {

    @PATCH("/app/users/{userId}/feed/{feedId}")
    fun patchFeedModify(
        @Path("userId") userId: Int,
        @Path("feedId") feedId: Int
    ):Call<FeedModifyResponse>
}