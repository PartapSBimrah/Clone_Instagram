package site.yoonsang.instaclone.src.main.profile

import site.yoonsang.instaclone.src.main.profile.models.MyProfileResponse

interface ProfileFragmentView {

    fun onGetMyProfileSuccess(response: MyProfileResponse)

    fun onGetMyProfileFailure(message: String)
}