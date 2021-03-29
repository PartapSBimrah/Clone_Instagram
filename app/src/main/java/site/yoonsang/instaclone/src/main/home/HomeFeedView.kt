package site.yoonsang.instaclone.src.main.home

import site.yoonsang.instaclone.src.main.home.models.GetFeedsResponse

interface HomeFeedView {

    fun onGetFeedsSuccess(response: GetFeedsResponse)

    fun onGetFeedsFailure(message: String)
}