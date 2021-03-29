package site.yoonsang.instaclone.src.main.others

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import site.yoonsang.instaclone.R
import site.yoonsang.instaclone.config.ApplicationClass
import site.yoonsang.instaclone.config.BaseFragment
import site.yoonsang.instaclone.databinding.FragmentOtherProfileBinding
import site.yoonsang.instaclone.src.main.others.models.FollowResponse
import site.yoonsang.instaclone.src.main.others.models.OtherProfileResponse
import site.yoonsang.instaclone.src.main.others.otherfeed.OtherFeedEmptyFragment
import site.yoonsang.instaclone.src.main.others.otherfeed.OtherFeedFragment
import site.yoonsang.instaclone.src.main.others.othertagged.OtherTaggedEmptyFragment

class OtherProfileFragment : BaseFragment<FragmentOtherProfileBinding>(
    FragmentOtherProfileBinding::bind,
    R.layout.fragment_other_profile
), OtherProfileView {

    private val otherFeedFragment by lazy { OtherFeedFragment() }
    private val otherFeedEmptyFragment by lazy { OtherFeedEmptyFragment() }
    private val otherTaggedEmptyFragment by lazy { OtherTaggedEmptyFragment() }
    private var isEmpty = true
    private var isFollowed = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val otherId = arguments?.getInt("otherId")

        isFollowed = arguments?.getString("follow") == "following"

        if (isFollowed) {
            binding.otherProfileBtnFollow.text = "팔로잉"
            binding.otherProfileBtnFollow.setTextColor(Color.BLACK)
            binding.otherProfileBtnFollow.setBackgroundResource(R.drawable.login_edittext_background)
        } else {
            binding.otherProfileBtnFollow.text = "팔로우"
            binding.otherProfileBtnFollow.setTextColor(Color.WHITE)
            binding.otherProfileBtnFollow.setBackgroundResource(R.drawable.login_button_selected)
        }

        showLoadingDialog(context!!)
        OtherService(this).tryGetOtherProfile(
            ApplicationClass.sSharedPreferences.getInt(ApplicationClass.USER_ID, 0),
            otherId!!
        )

        binding.otherProfileBtnFollow.setOnClickListener {
            showLoadingDialog(context!!)
            if (!isFollowed) {
                OtherService(this).tryPostFollow(
                    ApplicationClass.sSharedPreferences.getInt(ApplicationClass.USER_ID, 0),
                    otherId
                )
            } else {
                OtherService(this).tryPostUnFollow(
                    ApplicationClass.sSharedPreferences.getInt(ApplicationClass.USER_ID, 0),
                    otherId
                )
            }
        }

        binding.otherProfileTabLayout.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val position = tab!!.position
                var selected: Fragment? = null
                when (position) {
                    0 -> {
                        if (isEmpty) {
                            selected = otherFeedEmptyFragment
                        } else {
                            selected = otherFeedFragment
                        }
                    }
                    1 -> {
                        selected = otherTaggedEmptyFragment
                    }
                }
                changeFragment(selected!!)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    override fun onPostUnFollowSuccess(response: FollowResponse) {
        dismissLoadingDialog()
        if (response.isSuccess) {
            if (response.result.status == "N") {
                binding.otherProfileBtnFollow.text = "팔로우"
                binding.otherProfileBtnFollow.setTextColor(Color.WHITE)
                binding.otherProfileBtnFollow.setBackgroundResource(R.drawable.login_button_selected)
                isFollowed = false
            } else {
                binding.otherProfileBtnFollow.text = "팔로잉"
                binding.otherProfileBtnFollow.setTextColor(Color.BLACK)
                binding.otherProfileBtnFollow.setBackgroundResource(R.drawable.login_edittext_background)
            }
            showCustomToast(response.message!!)
        } else {
            showCustomToast(response.message ?: "오류")
        }
    }

    override fun onPostUnFollowFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast(message)
    }

    override fun onPostFollowSuccess(response: FollowResponse) {
        dismissLoadingDialog()
        if (response.isSuccess) {
            if (response.result.status == "Y") {
                binding.otherProfileBtnFollow.text = "팔로잉"
                binding.otherProfileBtnFollow.setTextColor(Color.BLACK)
                binding.otherProfileBtnFollow.setBackgroundResource(R.drawable.login_edittext_background)
                isFollowed = true
            } else {
                binding.otherProfileBtnFollow.text = "팔로우"
                binding.otherProfileBtnFollow.setTextColor(Color.WHITE)
                binding.otherProfileBtnFollow.setBackgroundResource(R.drawable.login_button_selected)
            }
            showCustomToast(response.message!!)
        } else {
            showCustomToast(response.message ?: "오류")
        }
    }

    override fun onPostFollowFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast(message)
    }

    override fun onGetOtherProfileSuccess(response: OtherProfileResponse) {
        dismissLoadingDialog()
        if (response.isSuccess) {
            val user = response.result.user
            val feeds = response.result.feeds
            binding.otherProfileUsername.text = user.userName
            binding.otherProfilePostCount.text = feeds.size.toString()
            binding.otherProfileFollowerCount.text = response.result.followerCount.toString()
            binding.otherProfileFollowingCount.text = response.result.followingCount.toString()
            if (feeds.size > 0) {
                val bundle = Bundle()
                bundle.putSerializable("feeds", feeds)
                otherFeedFragment.arguments = bundle
                changeFragment(otherFeedFragment)
                isEmpty = false
            } else {
                changeFragment(otherFeedEmptyFragment)
                isEmpty = true
            }
        }
    }

    override fun onGetOtherProfileFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast(message)
    }

    fun changeFragment(fragment: Fragment) {
        childFragmentManager
            .beginTransaction()
            .replace(binding.otherProfileFragmentContainer.id, fragment)
            .commit()
    }
}