package site.yoonsang.instaclone.src.main.home.post.feed.photosEdit

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import site.yoonsang.instaclone.R
import site.yoonsang.instaclone.config.BaseActivity
import site.yoonsang.instaclone.databinding.ActivityPhotosEditBinding
import site.yoonsang.instaclone.src.main.home.post.feed.photosEdit.upload.UploadActivity

class PhotosEditActivity :
    BaseActivity<ActivityPhotosEditBinding>(ActivityPhotosEditBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val photos = arrayListOf<String>()
        for (photo in intent.getSerializableExtra("photos") as ArrayList<String>) {
            photos.add(photo)
        }

        insertImage(photos[0], binding.filterClarendon)
        insertImage(photos[0], binding.filterGingham)
        insertImage(photos[0], binding.filterLark)
        insertImage(photos[0], binding.filterMoon)
        insertImage(photos[0], binding.filterNormal)
        insertImage(photos[0], binding.filterReyes)

        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin)
        val pagerWidth = resources.getDimensionPixelOffset(R.dimen.pagerWidth)
        val screenWidth = resources.displayMetrics.widthPixels
        val offsetPx = screenWidth - pageMarginPx - pagerWidth

        binding.phtosEditViewPager.setPageTransformer { page, position ->
            page.translationX = position * -offsetPx
        }

        binding.phtosEditViewPager.offscreenPageLimit = 3
        binding.phtosEditViewPager.adapter = PhotosEditAdapter(this, photos)

        binding.photosEditBtnNext.setOnClickListener {
            val intent = Intent(this, UploadActivity::class.java)
            intent.putExtra("photos", photos)
            startActivity(intent)
        }
    }

    private fun insertImage(str: String, img: ImageView) {
        Glide.with(this)
            .load(str)
            .into(img)
    }
}