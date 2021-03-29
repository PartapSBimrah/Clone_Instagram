package site.yoonsang.instaclone.src.main.profile.mytagged

import android.os.Bundle
import android.view.View
import site.yoonsang.instaclone.R
import site.yoonsang.instaclone.config.BaseFragment
import site.yoonsang.instaclone.databinding.FragmentMyTaggedEmptyBinding

class MyTaggedEmptyFragment : BaseFragment<FragmentMyTaggedEmptyBinding>(
    FragmentMyTaggedEmptyBinding::bind,
    R.layout.fragment_my_tagged_empty
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}