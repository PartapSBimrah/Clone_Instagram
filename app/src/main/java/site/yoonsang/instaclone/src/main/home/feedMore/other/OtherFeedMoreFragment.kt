package site.yoonsang.instaclone.src.main.home.feedMore.other

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import site.yoonsang.instaclone.R
import site.yoonsang.instaclone.databinding.FragmentOtherFeedMoreBinding

class OtherFeedMoreFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentOtherFeedMoreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOtherFeedMoreBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}