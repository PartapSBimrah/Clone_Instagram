package site.yoonsang.instaclone.src.main.home.liker

import site.yoonsang.instaclone.src.main.home.liker.models.FeedLikerResponse

interface FeedLikerFragmentView {

    fun onGetFeedLikerSuccess(response: FeedLikerResponse)

    fun onGetFeedLikerFailure(message: String)
}