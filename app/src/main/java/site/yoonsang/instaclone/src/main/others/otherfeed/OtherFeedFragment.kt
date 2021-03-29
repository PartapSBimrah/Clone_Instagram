package site.yoonsang.instaclone.src.main.others.otherfeed

import android.os.Bundle
import android.view.View
import site.yoonsang.instaclone.R
import site.yoonsang.instaclone.config.BaseFragment
import site.yoonsang.instaclone.databinding.FragmentOtherFeedBinding
import site.yoonsang.instaclone.src.main.profile.models.Feed

class OtherFeedFragment : BaseFragment<FragmentOtherFeedBinding>(
    FragmentOtherFeedBinding::bind,
    R.layout.fragment_other_feed
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val feeds = arguments?.getSerializable("feeds") as ArrayList<Feed>
        binding.otherFeedRecyclerView.adapter = OtherFeedAdapter(context!!, feeds)
    }
}