package site.yoonsang.instaclone.src.main.profile.follow.following

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import site.yoonsang.instaclone.config.ApplicationClass
import site.yoonsang.instaclone.src.main.profile.follow.following.models.ProfileFollowingResponse

class ProfileFollowingService(val view: ProfileFollowingFragmentView) {

    fun tryGetFollowing(userId: Int) {
        val profileFollowingRetrofitInterface = ApplicationClass.sRetrofit.create(ProfileFollowingRetrofitInterface::class.java)
        profileFollowingRetrofitInterface.getFollowing(userId).enqueue(object : Callback<ProfileFollowingResponse> {
            override fun onResponse(
                call: Call<ProfileFollowingResponse>,
                response: Response<ProfileFollowingResponse>
            ) {
                view.onGetFollowingSuccess(response.body() as ProfileFollowingResponse)
            }

            override fun onFailure(call: Call<ProfileFollowingResponse>, t: Throwable) {
                view.onGetFollowingFailure(t.message ?: "통신 오류")
            }
        })
    }
}