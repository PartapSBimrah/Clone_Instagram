package site.yoonsang.instaclone.src.main.shop

import android.os.Bundle
import android.view.View
import site.yoonsang.instaclone.R
import site.yoonsang.instaclone.config.BaseFragment
import site.yoonsang.instaclone.databinding.FragmentShopBinding

class ShopFragment : BaseFragment<FragmentShopBinding>(FragmentShopBinding::bind, R.layout.fragment_shop) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.shopRecyclerView.adapter = ShopAdapter(context!!)
    }
}