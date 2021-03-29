package site.yoonsang.instaclone.src.main.profile.edit

import android.os.Bundle
import com.bumptech.glide.Glide
import site.yoonsang.instaclone.config.ApplicationClass
import site.yoonsang.instaclone.config.BaseActivity
import site.yoonsang.instaclone.databinding.ActivityProfileEditBinding
import site.yoonsang.instaclone.src.main.profile.edit.models.UserInfoResponse

class ProfileEditActivity :
    BaseActivity<ActivityProfileEditBinding>(ActivityProfileEditBinding::inflate), ProfileEditView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showLoadingDialog(this)
        ProfileEditService(this).tryGetUserInfo(ApplicationClass.sSharedPreferences.getInt(ApplicationClass.USER_ID, 0))
    }

    override fun onGetUserInfoSuccess(response: UserInfoResponse) {
        dismissLoadingDialog()
        if (response.isSuccess) {
            binding.profileEditName.text = response.result.name
            binding.profileEditUsername.text = response.result.userName
            if (response.result.website != null) {
                binding.profileEditWebsite.text = response.result.website
            }
            if (response.result.introduce != null) {
                binding.profileEditIntroduce.text = response.result.introduce
            }
            if (response.result.profileImage != null) {
                Glide.with(this)
                    .load(response.result.profileImage)
                    .into(binding.profileEditProfileImage)
            }
        }
    }

    override fun onGetUserInfoFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast(message)
    }
}