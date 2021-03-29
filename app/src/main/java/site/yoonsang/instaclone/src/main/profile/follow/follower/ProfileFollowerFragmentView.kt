package site.yoonsang.instaclone.src.main.profile.follow.follower

import site.yoonsang.instaclone.src.main.profile.follow.follower.models.ProfileFollowerResponse

interface ProfileFollowerFragmentView {

    fun onGetFollowerSuccess(response: ProfileFollowerResponse)

    fun onGetFollowerFailure(message: String)
}