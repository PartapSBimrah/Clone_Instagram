package site.yoonsang.instaclone.src.main.profile.follow.following

import site.yoonsang.instaclone.src.main.profile.follow.following.models.ProfileFollowingResponse

interface ProfileFollowingFragmentView {

    fun onGetFollowingSuccess(response: ProfileFollowingResponse)

    fun onGetFollowingFailure(message: String)
}