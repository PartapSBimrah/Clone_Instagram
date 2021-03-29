package site.yoonsang.instaclone.src.main.profile.myfeed.detail

import site.yoonsang.instaclone.src.main.home.feedAdapter.models.FeedLikeResponse
import site.yoonsang.instaclone.src.main.profile.myfeed.detail.models.FeedDetailResponse

interface MyFeedDetailFragmentView {

    fun onGetFeedDetailSuccess(response: FeedDetailResponse)

    fun onGetFeedDetailFailure(message: String)

    fun onPostFeedLikeSuccess(response: FeedLikeResponse)

    fun onPostFeedLikeFailure(message: String)
}