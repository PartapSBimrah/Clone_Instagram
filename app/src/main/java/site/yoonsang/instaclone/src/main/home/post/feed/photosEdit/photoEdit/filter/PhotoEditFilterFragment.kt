package site.yoonsang.instaclone.src.main.home.post.feed.photosEdit.photoEdit.filter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import site.yoonsang.instaclone.R
import site.yoonsang.instaclone.config.BaseFragment
import site.yoonsang.instaclone.databinding.FragmentPhotoEditFilterBinding

class PhotoEditFilterFragment : BaseFragment<FragmentPhotoEditFilterBinding>(
    FragmentPhotoEditFilterBinding::bind,
    R.layout.fragment_photo_edit_filter
) {
    var photo: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (arguments != null) {
            photo = arguments?.getString("photo")
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (photo != null) {
            insertImage(photo!!, binding.filterClarendon)
            insertImage(photo!!, binding.filterGingham)
            insertImage(photo!!, binding.filterLark)
            insertImage(photo!!, binding.filterMoon)
            insertImage(photo!!, binding.filterNormal)
            insertImage(photo!!, binding.filterReyes)
        }
    }

    private fun insertImage(str: String, img: ImageView) {
        Glide.with(context!!)
            .load(str)
            .into(img)
    }
}