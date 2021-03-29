package site.yoonsang.instaclone.src.main.others.otherfeed

import android.os.Bundle
import android.view.View
import site.yoonsang.instaclone.R
import site.yoonsang.instaclone.config.BaseFragment
import site.yoonsang.instaclone.databinding.FragmentOtherFeedEmptyBinding

class OtherFeedEmptyFragment : BaseFragment<FragmentOtherFeedEmptyBinding>(
    FragmentOtherFeedEmptyBinding::bind,
    R.layout.fragment_other_feed_empty
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}