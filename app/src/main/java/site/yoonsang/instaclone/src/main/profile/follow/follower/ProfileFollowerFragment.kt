package site.yoonsang.instaclone.src.main.profile.follow.follower

import android.os.Bundle
import android.view.View
import site.yoonsang.instaclone.R
import site.yoonsang.instaclone.config.ApplicationClass
import site.yoonsang.instaclone.config.BaseFragment
import site.yoonsang.instaclone.databinding.FragmentProfileFollowerBinding
import site.yoonsang.instaclone.src.main.profile.follow.follower.models.ProfileFollowerResponse
import site.yoonsang.instaclone.src.main.profile.follow.follower.models.ResultGetMyFollowerRequest

class ProfileFollowerFragment : BaseFragment<FragmentProfileFollowerBinding>(
    FragmentProfileFollowerBinding::bind,
    R.layout.fragment_profile_follower
), ProfileFollowerFragmentView {

    private lateinit var followerList: ArrayList<ResultGetMyFollowerRequest>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showLoadingDialog(context!!)
        ProfileFollowerService(this).tryGetFollower(
            ApplicationClass.sSharedPreferences.getInt(
                ApplicationClass.USER_ID,
                0
            )
        )
    }

    override fun onGetFollowerSuccess(response: ProfileFollowerResponse) {
        dismissLoadingDialog()
        if (response.isSuccess) {
            followerList = arrayListOf()
            if (response.result.size > 0) {
                for (i in response.result) {
                    followerList.add(i)
                }
            }
            binding.myFollowerRecyclerView.adapter = MyFollowerAdapter(context!!, followerList)
        }
    }

    override fun onGetFollowerFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast(message)
    }
}