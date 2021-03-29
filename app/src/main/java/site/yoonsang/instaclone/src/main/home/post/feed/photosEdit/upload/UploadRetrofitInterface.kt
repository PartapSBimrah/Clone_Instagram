package site.yoonsang.instaclone.src.main.home.post.feed.photosEdit.upload

import retrofit2.Call
import retrofit2.http.*
import site.yoonsang.instaclone.src.main.home.post.feed.photosEdit.upload.models.PostFeedUploadRequest
import site.yoonsang.instaclone.src.main.home.post.feed.photosEdit.upload.models.PostFeedUploadResponse

interface UploadRetrofitInterface {

    @POST("/app/users/{userId}/feed")
    fun createFeed(
        @Path("userId") userId: Int,
        @Body params: PostFeedUploadRequest,
    ): Call<PostFeedUploadResponse>
}