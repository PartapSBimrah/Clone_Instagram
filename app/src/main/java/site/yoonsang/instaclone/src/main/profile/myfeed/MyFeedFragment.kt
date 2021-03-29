package site.yoonsang.instaclone.src.main.profile.myfeed

import android.os.Bundle
import android.util.Log
import android.view.View
import site.yoonsang.instaclone.R
import site.yoonsang.instaclone.config.BaseFragment
import site.yoonsang.instaclone.databinding.FragmentMyFeedBinding
import site.yoonsang.instaclone.src.main.profile.models.Feed

class MyFeedFragment : BaseFragment<FragmentMyFeedBinding>(FragmentMyFeedBinding::bind, R.layout.fragment_my_feed) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val feeds = arguments?.getSerializable("feeds")

        binding.myFeedRecyclerView.adapter = MyFeedAdapter(context!!, (feeds as ArrayList<Feed>))
    }
}