package site.yoonsang.instaclone.src.main.reels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import site.yoonsang.instaclone.R
import site.yoonsang.instaclone.config.BaseFragment
import site.yoonsang.instaclone.databinding.FragmentReelsBinding
import site.yoonsang.instaclone.src.main.MainActivity

class ReelsFragment : BaseFragment<FragmentReelsBinding>(FragmentReelsBinding::bind, R.layout.fragment_reels) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).addReelsTheme()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.reelsViewPager.adapter = ReelsAdapter(context!!)
        binding.reelsViewPager.orientation = ViewPager2.ORIENTATION_VERTICAL
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as MainActivity).deleteReelsTheme()
    }
}