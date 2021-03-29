package site.yoonsang.instaclone.src.main.home.post.feed.photosEdit.photoEdit

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import site.yoonsang.instaclone.config.BaseActivity
import site.yoonsang.instaclone.databinding.ActivityPhotoEditBinding
import site.yoonsang.instaclone.src.main.home.post.feed.photosEdit.photoEdit.filter.PhotoEditFilterFragment
import site.yoonsang.instaclone.src.main.home.post.feed.photosEdit.photoEdit.retouch.PhotoEditRetouchFragment
import site.yoonsang.instaclone.src.main.home.post.feed.photosEdit.upload.UploadActivity

class PhotoEditActivity :
    BaseActivity<ActivityPhotoEditBinding>(ActivityPhotoEditBinding::inflate) {

    private val fragmentPhotoEditFilter by lazy { PhotoEditFilterFragment() }
    private val fragmentPhotoEditRetouch by lazy { PhotoEditRetouchFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val photo = intent.getStringExtra("photo")

        Glide.with(this)
            .load(photo)
            .into(binding.photoEditPreview)

        binding.photoEditBtnNext.setOnClickListener {
            val intent = Intent(this, UploadActivity::class.java)
            intent.putExtra("photo", photo)
            startActivity(intent)
        }

        val bundle = Bundle()
        bundle.putString("photo", photo)
        fragmentPhotoEditFilter.arguments = bundle

        changeFragment(fragmentPhotoEditFilter)

        binding.photoEditTabLayout.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val position = tab!!.position
                var selected: Fragment? = null
                when (position) {
                    0 -> {
                        selected = fragmentPhotoEditFilter
                    }
                    1 -> {
                        selected = fragmentPhotoEditRetouch
                    }
                }
                changeFragment(selected!!)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(binding.photoEditFragmentContainer.id, fragment)
            .commit()
    }
}