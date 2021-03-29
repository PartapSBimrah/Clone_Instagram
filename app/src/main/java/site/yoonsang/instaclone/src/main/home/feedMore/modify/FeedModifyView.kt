package site.yoonsang.instaclone.src.main.home.feedMore.modify

import site.yoonsang.instaclone.src.main.home.feedMore.modify.models.FeedModifyResponse

interface FeedModifyView {

    fun onPatchFeedModifySuccess(response: FeedModifyResponse)

    fun onPatchFeedModifyFailure(message: String)
}