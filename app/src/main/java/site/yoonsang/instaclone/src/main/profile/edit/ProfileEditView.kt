package site.yoonsang.instaclone.src.main.profile.edit

import site.yoonsang.instaclone.src.main.profile.edit.models.UserInfoResponse

interface ProfileEditView {

    fun onGetUserInfoSuccess(response: UserInfoResponse)

    fun onGetUserInfoFailure(message: String)
}