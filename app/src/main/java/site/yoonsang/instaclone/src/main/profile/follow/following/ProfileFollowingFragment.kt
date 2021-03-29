package site.yoonsang.instaclone.src.main.profile.follow.following

import android.os.Bundle
import android.view.View
import site.yoonsang.instaclone.R
import site.yoonsang.instaclone.config.ApplicationClass
import site.yoonsang.instaclone.config.BaseFragment
import site.yoonsang.instaclone.databinding.FragmentProfileFollowingBinding
import site.yoonsang.instaclone.src.main.profile.follow.following.models.ProfileFollowingResponse
import site.yoonsang.instaclone.src.main.profile.follow.following.models.ResultGetMyFollowingRequest

class ProfileFollowingFragment : BaseFragment<FragmentProfileFollowingBinding>(
    FragmentProfileFollowingBinding::bind,
    R.layout.fragment_profile_following
), ProfileFollowingFragmentView {

    private lateinit var followingList: ArrayList<ResultGetMyFollowingRequest>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showLoadingDialog(context!!)
        ProfileFollowingService(this).tryGetFollowing(
            ApplicationClass.sSharedPreferences.getInt(
                ApplicationClass.USER_ID,
                0
            )
        )
    }

    override fun onGetFollowingSuccess(response: ProfileFollowingResponse) {
        dismissLoadingDialog()
        if (response.isSuccess) {
            followingList = arrayListOf()
            if (response.result.size > 0) {
                for (i in response.result) {
                    followingList.add(i)
                }
            }
            binding.myFollowingRecyclerView.adapter = MyFollowingAdapter(context!!, followingList)
        }
    }

    override fun onGetFollowingFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast(message)
    }
}