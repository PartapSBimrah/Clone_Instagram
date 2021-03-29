package site.yoonsang.instaclone.src.main.profile.follow

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import site.yoonsang.instaclone.R
import site.yoonsang.instaclone.config.BaseFragment
import site.yoonsang.instaclone.databinding.FragmentProfileFollowBinding
import site.yoonsang.instaclone.src.main.profile.follow.follower.ProfileFollowerFragment
import site.yoonsang.instaclone.src.main.profile.follow.following.ProfileFollowingFragment

class ProfileFollowFragment : BaseFragment<FragmentProfileFollowBinding>(
    FragmentProfileFollowBinding::bind,
    R.layout.fragment_profile_follow
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            when (it.getString("follow")) {
                "follower" -> {
                    changerFragment(ProfileFollowerFragment())
                    binding.profileFollowTabLayout.getTabAt(0)?.select()
                }
                "following" -> {
                    changerFragment(ProfileFollowingFragment())
                    binding.profileFollowTabLayout.getTabAt(1)?.select()
                }
                else -> {}
            }
            binding.profileFollowUsername.text = it.getString("userName")
            binding.profileFollowTabLayout.getTabAt(0)!!.text = "팔로워 ${it.getInt("follower")}명"
            binding.profileFollowTabLayout.getTabAt(1)!!.text = "팔로잉 ${it.getInt("following")}명"
        }

        binding.profileFollowTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val position = tab!!.position
                var selected: Fragment? = null
                when (position) {
                    0 -> {
                        selected = ProfileFollowerFragment()
                    }
                    1 -> {
                        selected = ProfileFollowingFragment()
                    }
                }
                changerFragment(selected!!)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun changerFragment(fragment: Fragment) {
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(binding.profileFollowFragmentContainer.id, fragment)
            ?.commit()
    }
}