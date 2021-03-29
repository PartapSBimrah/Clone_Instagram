package site.yoonsang.instaclone.src.main.home.comment

import retrofit2.Call
import retrofit2.http.*
import site.yoonsang.instaclone.src.main.home.comment.models.CreateCommentResponse
import site.yoonsang.instaclone.src.main.home.comment.models.FeedCommentResponse
import site.yoonsang.instaclone.src.main.home.comment.models.PostCommentCreateRequest
import site.yoonsang.instaclone.src.main.home.feedMore.models.DeleteFeedResponse

interface FeedCommentRetrofitInterface {

    @GET("/app/users/{userId}/feed/{feedId}/comment")
    fun getFeedComment(
        @Path("userId") userId: Int,
        @Path("feedId") feedId: Int
    ): Call<FeedCommentResponse>

    @POST("/app/users/{userId}/feed/{feedId}/comment")
    fun postFeedCommentCreate(
        @Path("userId") userId: Int,
        @Path("feedId") feedId: Int,
        @Body params: PostCommentCreateRequest
    ):Call<CreateCommentResponse>

    @DELETE("/app/users/{userId}/feed-comment/{commentId}")
    fun deleteComment(
        @Path("userId") userId: Int,
        @Path("commentId") commentId: Int
    ):Call<DeleteFeedResponse>
}