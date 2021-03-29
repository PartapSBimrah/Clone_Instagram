package site.yoonsang.instaclone.src.main.home.storyAdapter.story

import android.graphics.Color
import android.os.Bundle
import com.bumptech.glide.Glide
import site.yoonsang.instaclone.config.BaseActivity
import site.yoonsang.instaclone.databinding.ActivityStoryBinding

class StoryActivity : BaseActivity<ActivityStoryBinding>(ActivityStoryBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addReelsTheme()

        binding.storyUsername.text = intent.getStringExtra("username")
        Glide.with(this)
            .load(intent.getStringExtra("profileImage"))
            .into(binding.storyProfileImage)

        var time = 0
        binding.storyProgressBar.max = 500

        kotlin.concurrent.timer(period = 10) {
            if (time < 500) time++
            else if (time == 500) {
                finish()
            }
            runOnUiThread {
                binding.storyProgressBar.progress = time
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        deleteReelsTheme()
    }

    private fun addReelsTheme() {
        window.statusBarColor = Color.BLACK
        window.navigationBarColor = Color.BLACK
    }

    private fun deleteReelsTheme() {
        window.statusBarColor = Color.WHITE
        window.navigationBarColor = Color.WHITE
    }
}