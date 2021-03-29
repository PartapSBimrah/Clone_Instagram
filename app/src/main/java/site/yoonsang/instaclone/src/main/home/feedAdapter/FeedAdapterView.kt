package site.yoonsang.instaclone.src.main.home.feedAdapter

import site.yoonsang.instaclone.src.main.home.feedAdapter.models.FeedLikeResponse

interface FeedAdapterView {

    fun onPostFeedLikeSuccess(response: FeedLikeResponse, holder: HomeFeedAdapter.ViewHolder)

    fun onPostFeedLikeFailure(message: String)
}