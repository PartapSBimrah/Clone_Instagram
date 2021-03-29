package site.yoonsang.instaclone.src.main.profile.settings

import android.content.Intent
import android.os.Bundle
import android.view.View
import site.yoonsang.instaclone.R
import site.yoonsang.instaclone.config.ApplicationClass
import site.yoonsang.instaclone.config.BaseFragment
import site.yoonsang.instaclone.databinding.FragmentSettingsBinding
import site.yoonsang.instaclone.src.login.LoginActivity

class SettingsFragment : BaseFragment<FragmentSettingsBinding>(
    FragmentSettingsBinding::bind,
    R.layout.fragment_settings
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.settingsLogout.setOnClickListener {
            ApplicationClass.sSharedPreferences.edit().putString(ApplicationClass.X_ACCESS_TOKEN, null).apply()
            ApplicationClass.sSharedPreferences.edit().putInt(ApplicationClass.USER_ID, 0).apply()
            startActivity(Intent(activity, LoginActivity::class.java))
            activity?.finish()
        }

        binding.settingsAllLogout.setOnClickListener {
            ApplicationClass.sSharedPreferences.edit().putString(ApplicationClass.X_ACCESS_TOKEN, null).apply()
            ApplicationClass.sSharedPreferences.edit().putInt(ApplicationClass.USER_ID, 0).apply()
            startActivity(Intent(activity, LoginActivity::class.java))
            activity?.finish()
        }
    }
}