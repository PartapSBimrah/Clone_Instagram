package site.yoonsang.instaclone.src.main.profile.addPost

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import site.yoonsang.instaclone.R
import site.yoonsang.instaclone.databinding.FragmentProfileAddPostBinding
import site.yoonsang.instaclone.src.main.home.post.PostActivity

class ProfileAddPostFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentProfileAddPostBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileAddPostBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.dialogAddPostFeed.setOnClickListener {
            val intent = Intent(activity, PostActivity::class.java)
            startActivity(intent)
        }
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}