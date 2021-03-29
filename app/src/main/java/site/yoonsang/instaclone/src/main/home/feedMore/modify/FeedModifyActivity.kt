package site.yoonsang.instaclone.src.main.home.feedMore.modify

import android.os.Bundle
import com.bumptech.glide.Glide
import site.yoonsang.instaclone.config.ApplicationClass
import site.yoonsang.instaclone.config.BaseActivity
import site.yoonsang.instaclone.databinding.ActivityFeedModifyBinding
import site.yoonsang.instaclone.src.main.home.feedMore.modify.models.FeedModifyResponse

class FeedModifyActivity :
    BaseActivity<ActivityFeedModifyBinding>(ActivityFeedModifyBinding::inflate), FeedModifyView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.feedModifyUsername.text = intent.getStringExtra("username")
        binding.feedModifyTime.text = intent.getStringExtra("time")
        Glide.with(this)
            .load(intent.getStringExtra("image"))
            .into(binding.feedModifyImage)
        binding.feedModifyContent.setText(intent.getStringExtra("content"))

        binding.feedModifyCheck.setOnClickListener {
            showLoadingDialog(this)
            FeedModifyService(this).tryPatchFeedModify(
                ApplicationClass.sSharedPreferences.getInt(ApplicationClass.USER_ID, 0),
                intent.getIntExtra("feedId", 0)
            )
        }
    }

    override fun onPatchFeedModifySuccess(response: FeedModifyResponse) {
        dismissLoadingDialog()
        showCustomToast(response.result)
        if (response.isSuccess) {
            finish()
        }
    }

    override fun onPatchFeedModifyFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast(message)
    }
}