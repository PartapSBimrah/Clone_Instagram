package site.yoonsang.instaclone.src.main.home.comment

import site.yoonsang.instaclone.src.main.home.comment.models.CreateCommentResponse
import site.yoonsang.instaclone.src.main.home.comment.models.FeedCommentResponse
import site.yoonsang.instaclone.src.main.home.feedMore.models.DeleteFeedResponse

interface FeedCommentFragmentView {

    fun onGetFeedCommentSuccess(response: FeedCommentResponse)

    fun onGetFeedCommentFailure(message: String)

    fun onPostFeedCommentCreateSuccess(response: CreateCommentResponse)

    fun onPostFeedCommentCreateFailure(message: String)

    fun onDeleteCommentSuccess(response: DeleteFeedResponse)

    fun onDeleteCommentFailure(message: String)
}