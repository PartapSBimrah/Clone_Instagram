package site.yoonsang.instaclone.src.main.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import site.yoonsang.instaclone.R
import site.yoonsang.instaclone.config.ApplicationClass
import site.yoonsang.instaclone.config.BaseFragment
import site.yoonsang.instaclone.databinding.FragmentProfileBinding
import site.yoonsang.instaclone.src.main.MainActivity
import site.yoonsang.instaclone.src.main.profile.addPost.ProfileAddPostFragment
import site.yoonsang.instaclone.src.main.profile.edit.ProfileEditActivity
import site.yoonsang.instaclone.src.main.profile.follow.ProfileFollowFragment
import site.yoonsang.instaclone.src.main.profile.models.Feed
import site.yoonsang.instaclone.src.main.profile.models.MyProfileResponse
import site.yoonsang.instaclone.src.main.profile.myfeed.MyFeedEmptyFragment
import site.yoonsang.instaclone.src.main.profile.myfeed.MyFeedFragment
import site.yoonsang.instaclone.src.main.profile.mytagged.MyTaggedEmptyFragment

class ProfileFragment :
    BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::bind, R.layout.fragment_profile),
    ProfileFragmentView {

    private val profileFollowFragment by lazy { ProfileFollowFragment() }
    private val myFeedFragment by lazy { MyFeedFragment() }
    private lateinit var bundleFeeds: ArrayList<Feed>
    private var followerCount = 0
    private var followingCount = 0
    private var userName = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showLoadingDialog(context!!)
        ProfileService(this).tryGetMyProfile(
            ApplicationClass.sSharedPreferences.getInt(
                ApplicationClass.USER_ID,
                0
            )
        )

        bundleFeeds = arrayListOf()

        binding.profileFollower.setOnClickListener {
            (activity as MainActivity).changeFragment(profileFollowFragment)
            val bundle = Bundle()
            bundle.putString("userName", userName)
            bundle.putString("follow", "follower")
            bundle.putInt("follower", followerCount)
            bundle.putInt("following", followingCount)
            profileFollowFragment.arguments = bundle
        }

        binding.profileFollowing.setOnClickListener {
            (activity as MainActivity).changeFragment(profileFollowFragment)
            val bundle = Bundle()
            bundle.putString("userName", userName)
            bundle.putString("follow", "following")
            bundle.putInt("follower", followerCount)
            bundle.putInt("following", followingCount)
            profileFollowFragment.arguments = bundle
        }

        binding.profileAddFeed.setOnClickListener {
            val bottomSheet = ProfileAddPostFragment()
            bottomSheet.show(activity!!.supportFragmentManager, bottomSheet.tag)
        }

        binding.profileMenu.setOnClickListener {
            (activity as MainActivity).openDrawer()
        }

        binding.profileTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val position = tab!!.position
                var selected: Fragment? = null
                when (position) {
                    0 -> {
                        selected = if (bundleFeeds.size > 0) {
                            myFeedFragment
                        } else {
                            MyFeedEmptyFragment()
                        }
                    }
                    1 -> {
                        selected = MyTaggedEmptyFragment()
                    }
                }
                changeFragment(selected!!)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        binding.profileBtnEdit.setOnClickListener {
            val intent = Intent(activity, ProfileEditActivity::class.java)
            startActivity(intent)
        }
    }

    private fun changeFragment(fragment: Fragment) {
        childFragmentManager
            .beginTransaction()
            .replace(binding.profileFragmentContainer.id, fragment)
            .commit()
    }

    override fun onGetMyProfileSuccess(response: MyProfileResponse) {
        dismissLoadingDialog()
        if (response.isSuccess) {
            binding.profileUsername.text = response.result.user.userName
            userName = response.result.user.userName

            if (response.result.user.profileImage != null) {
                Glide.with(context!!)
                    .load(response.result.user.profileImage)
                    .into(binding.profileProfileImage)
            }

            if (response.result.user.introduction != null) {
                binding.profileIntroduce.text = response.result.user.introduction
            } else {
                binding.profileIntroduce.visibility = View.GONE
            }

            binding.profilePostNumber.text = response.result.feedCount.toString()
            binding.profileFollowerNumber.text = response.result.followerCount.toString()
            followerCount = response.result.followerCount
            binding.profileFollowingNumber.text = response.result.followingCount.toString()
            followingCount = response.result.followingCount

            if (response.result.feeds.size != 0) {
                for (feed in response.result.feeds) {
                    bundleFeeds.add(feed)
                }
                val bundle = Bundle()
                bundle.putSerializable("feeds", bundleFeeds)
                myFeedFragment.arguments = bundle
            }

            if (bundleFeeds.size > 0) {
                changeFragment(myFeedFragment)
            } else {
                changeFragment(MyFeedEmptyFragment())
            }
        }
    }

    override fun onGetMyProfileFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast(message)
    }
}