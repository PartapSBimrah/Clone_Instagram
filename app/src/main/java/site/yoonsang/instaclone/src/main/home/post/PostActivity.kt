package site.yoonsang.instaclone.src.main.home.post

import android.Manifest
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import site.yoonsang.instaclone.config.BaseActivity
import site.yoonsang.instaclone.databinding.ActivityPostBinding
import site.yoonsang.instaclone.src.main.home.post.feed.PostFeedFragment
import site.yoonsang.instaclone.src.main.home.post.live.PostLiveFragment
import site.yoonsang.instaclone.src.main.home.post.reels.PostReelsFragment
import site.yoonsang.instaclone.src.main.home.post.story.PostStoryFragment

class PostActivity : BaseActivity<ActivityPostBinding>(ActivityPostBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val permissionListener = object : PermissionListener {
            override fun onPermissionGranted() {
                changeFragment(PostFeedFragment())
            }

            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                showCustomToast("해당 기능을 이용하기 위해선 접근 권한이 필요합니다.")
                finish()
            }
        }

        TedPermission.with(this)
            .setPermissionListener(permissionListener)
            .setDeniedMessage("[설정] -> [권한]에서 다시 권한을 허용할 수 있습니다.")
            .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
            .check()

        binding.postMenuFeed.setOnClickListener {
            changeFragment(PostFeedFragment())
        }
        binding.postMenuStory.setOnClickListener {
            changeFragment(PostStoryFragment())
        }
        binding.postMenuReels.setOnClickListener {
            changeFragment(PostReelsFragment())
        }
        binding.postMenuLive.setOnClickListener {
            changeFragment(PostLiveFragment())
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(binding.postFragmentContainer.id, fragment)
            .commit()
    }
}