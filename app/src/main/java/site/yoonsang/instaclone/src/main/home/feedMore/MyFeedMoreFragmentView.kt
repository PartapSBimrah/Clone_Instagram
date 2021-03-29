package site.yoonsang.instaclone.src.main.home.feedMore

import site.yoonsang.instaclone.src.main.home.feedMore.models.DeleteFeedResponse

interface MyFeedMoreFragmentView {

    fun onPatchDeleteFeedSuccess(response: DeleteFeedResponse)

    fun onPatchDeleteFeedFailure(message: String)
}