package site.yoonsang.instaclone.src.main

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import site.yoonsang.instaclone.R
import site.yoonsang.instaclone.config.ApplicationClass
import site.yoonsang.instaclone.config.BaseActivity
import site.yoonsang.instaclone.databinding.ActivityMainBinding
import site.yoonsang.instaclone.databinding.ProfileDrawerHeaderBinding
import site.yoonsang.instaclone.src.main.home.HomeFragment
import site.yoonsang.instaclone.src.main.profile.ProfileFragment
import site.yoonsang.instaclone.src.main.profile.settings.SettingsFragment
import site.yoonsang.instaclone.src.main.reels.ReelsFragment
import site.yoonsang.instaclone.src.main.search.SearchFragment
import site.yoonsang.instaclone.src.main.shop.ShopFragment

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    private val fragmentHome by lazy { HomeFragment() }
    private val fragmentSearch by lazy { SearchFragment() }
    private val fragmentReels by lazy { ReelsFragment() }
    private val fragmentShop by lazy { ShopFragment() }
    private val fragmentProfile by lazy { ProfileFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val headerView = binding.profileDrawerNavigationView.getHeaderView(0)
        val headerBinding = ProfileDrawerHeaderBinding.bind(headerView)

        headerBinding.profileNavSetting.setOnClickListener {
            binding.profileDrawer.closeDrawer(GravityCompat.END)
            changeFragment(SettingsFragment())
        }

        binding.mainBottomNavigation.run {
            setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.main_menu_home -> {
                        changeFragment(fragmentHome)
                    }
                    R.id.main_menu_search -> {
                        changeFragment(fragmentSearch)
                    }
                    R.id.main_menu_reels -> {
                        changeFragment(fragmentReels)
                    }
                    R.id.main_menu_shop -> {
                        changeFragment(fragmentShop)
                    }
                    R.id.main_menu_profile -> {
                        changeFragment(fragmentProfile)
                    }
                }
                true
            }
            selectedItemId = R.id.main_menu_home
        }
    }

    fun addReelsTheme() {
        binding.mainBottomNavigation.setBackgroundResource(R.color.black)
        binding.mainBottomNavigation.itemIconTintList =
            ColorStateList.valueOf(Color.parseColor("#ffffff"))
        window.statusBarColor = Color.BLACK
        window.decorView.systemUiVisibility = 0
    }

    fun deleteReelsTheme() {
        binding.mainBottomNavigation.setBackgroundResource(R.color.white)
        binding.mainBottomNavigation.itemIconTintList =
            ColorStateList.valueOf(Color.parseColor("#000000"))
        window.statusBarColor = Color.WHITE
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

    fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(binding.mainFragmentContainer.id, fragment)
            .commit()
    }

    fun openDrawer() {
        binding.profileDrawer.openDrawer(GravityCompat.END)
    }

    override fun onBackPressed() {
        when {
            binding.profileDrawer.isDrawerOpen(GravityCompat.END) -> {
                binding.profileDrawer.closeDrawer(GravityCompat.END)
            }
            supportFragmentManager.backStackEntryCount > 0 -> {
                supportFragmentManager.popBackStack()
            }
            else -> {
                finish()
            }
        }
    }
}