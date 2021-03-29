package site.yoonsang.instaclone.src.main.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import site.yoonsang.instaclone.R
import site.yoonsang.instaclone.config.ApplicationClass
import site.yoonsang.instaclone.config.BaseFragment
import site.yoonsang.instaclone.databinding.FragmentHomeBinding
import site.yoonsang.instaclone.src.main.home.feedAdapter.HomeFeedAdapter
import site.yoonsang.instaclone.src.main.home.models.GetFeedsResponse
import site.yoonsang.instaclone.src.main.home.post.PostActivity
import site.yoonsang.instaclone.src.main.home.storyAdapter.HomeStoryAdapter

class HomeFragment :
    BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home),
    HomeFeedView {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeAddPost.setOnClickListener {
            startActivity(Intent(activity, PostActivity::class.java))
        }

        binding.homeStoryRecyclerView.adapter = HomeStoryAdapter(context!!)
    }

    override fun onResume() {
        super.onResume()
        getFeeds()
    }

    override fun onGetFeedsSuccess(response: GetFeedsResponse) {
        dismissLoadingDialog()
        if (response.isSuccess) {
            binding.homeFeedRecyclerView.adapter = HomeFeedAdapter(context!!, response.result)
        }
    }

    override fun onGetFeedsFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast(message)
    }

    fun getFeeds() {
        showLoadingDialog(context!!)
        HomeFeedService(this).tryGetFeeds(
            ApplicationClass.sSharedPreferences.getInt(
                ApplicationClass.USER_ID,
                0
            )
        )
    }
}