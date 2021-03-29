package site.yoonsang.instaclone.src.main.others

import site.yoonsang.instaclone.src.main.others.models.FollowResponse
import site.yoonsang.instaclone.src.main.others.models.OtherProfileResponse

interface OtherProfileView {

    fun onPostFollowSuccess(response: FollowResponse)

    fun onPostFollowFailure(message: String)

    fun onPostUnFollowSuccess(response: FollowResponse)

    fun onPostUnFollowFailure(message: String)

    fun onGetOtherProfileSuccess(response: OtherProfileResponse)

    fun onGetOtherProfileFailure(message: String)
}