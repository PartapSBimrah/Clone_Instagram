package site.yoonsang.instaclone.src.main.profile.myfeed

import android.os.Bundle
import android.view.View
import site.yoonsang.instaclone.R
import site.yoonsang.instaclone.config.BaseFragment
import site.yoonsang.instaclone.databinding.FragmentMyFeedEmptyBinding

class MyFeedEmptyFragment : BaseFragment<FragmentMyFeedEmptyBinding>(
    FragmentMyFeedEmptyBinding::bind,
    R.layout.fragment_my_feed_empty
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}