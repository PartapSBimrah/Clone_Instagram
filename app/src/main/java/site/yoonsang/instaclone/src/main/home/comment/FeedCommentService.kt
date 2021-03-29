package site.yoonsang.instaclone.src.main.home.comment

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import site.yoonsang.instaclone.config.ApplicationClass
import site.yoonsang.instaclone.src.main.home.comment.models.CreateCommentResponse
import site.yoonsang.instaclone.src.main.home.comment.models.FeedCommentResponse
import site.yoonsang.instaclone.src.main.home.comment.models.PostCommentCreateRequest
import site.yoonsang.instaclone.src.main.home.feedMore.models.DeleteFeedResponse

class FeedCommentService(val view: FeedCommentFragmentView) {

    fun tryGetFeedComment(userId: Int, feedId: Int) {
        val feedCommentRetrofitInterface = ApplicationClass.sRetrofit.create(FeedCommentRetrofitInterface::class.java)
        feedCommentRetrofitInterface.getFeedComment(userId, feedId).enqueue(object : Callback<FeedCommentResponse> {
            override fun onResponse(
                call: Call<FeedCommentResponse>,
                response: Response<FeedCommentResponse>
            ) {
                view.onGetFeedCommentSuccess(response.body() as FeedCommentResponse)
            }

            override fun onFailure(call: Call<FeedCommentResponse>, t: Throwable) {
                view.onGetFeedCommentFailure(t.message ?: "통신 오류")
            }
        })
    }

    fun tryPostCreateFeedComment(userId: Int, feedId:Int, request: PostCommentCreateRequest) {
        val feedCommentRetrofitInterface = ApplicationClass.sRetrofit.create(FeedCommentRetrofitInterface::class.java)
        feedCommentRetrofitInterface.postFeedCommentCreate(userId, feedId, request).enqueue(object : Callback<CreateCommentResponse> {
            override fun onResponse(
                call: Call<CreateCommentResponse>,
                response: Response<CreateCommentResponse>
            ) {
                view.onPostFeedCommentCreateSuccess(response.body() as CreateCommentResponse)
            }

            override fun onFailure(call: Call<CreateCommentResponse>, t: Throwable) {
                view.onPostFeedCommentCreateFailure(t.message ?: "통신 오류")
            }
        })
    }

    fun tryDeleteComment(userId: Int, commentId: Int) {
        val feedCommentRetrofitInterface = ApplicationClass.sRetrofit.create(FeedCommentRetrofitInterface::class.java)
        feedCommentRetrofitInterface.deleteComment(userId, commentId).enqueue(object : Callback<DeleteFeedResponse> {
            override fun onResponse(
                call: Call<DeleteFeedResponse>,
                response: Response<DeleteFeedResponse>
            ) {
                view.onDeleteCommentSuccess(response.body() as DeleteFeedResponse)
            }

            override fun onFailure(call: Call<DeleteFeedResponse>, t: Throwable) {
                view.onDeleteCommentFailure(t.message ?: "통신 오류")
            }
        })
    }
}