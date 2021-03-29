package site.yoonsang.instaclone.src.main.others.othertagged

import android.os.Bundle
import android.view.View
import site.yoonsang.instaclone.R
import site.yoonsang.instaclone.config.BaseFragment
import site.yoonsang.instaclone.databinding.FragmentOtherTaggedEmptyBinding

class OtherTaggedEmptyFragment : BaseFragment<FragmentOtherTaggedEmptyBinding>(
    FragmentOtherTaggedEmptyBinding::bind,
    R.layout.fragment_other_tagged_empty
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}