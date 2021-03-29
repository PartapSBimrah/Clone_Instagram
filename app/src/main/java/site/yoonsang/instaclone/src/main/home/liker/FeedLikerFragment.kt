package site.yoonsang.instaclone.src.main.home.liker

import android.os.Bundle
import android.util.Log
import android.view.View
import site.yoonsang.instaclone.R
import site.yoonsang.instaclone.config.ApplicationClass
import site.yoonsang.instaclone.config.BaseFragment
import site.yoonsang.instaclone.databinding.FragmentFeedLikerBinding
import site.yoonsang.instaclone.src.main.home.liker.models.FeedLikerResponse

class FeedLikerFragment : BaseFragment<FragmentFeedLikerBinding>(
    FragmentFeedLikerBinding::bind,
    R.layout.fragment_feed_liker
), FeedLikerFragmentView {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val feedId = arguments?.getInt("feedId")

        showLoadingDialog(context!!)
        FeedLikerService(this).tryGetFeedLiker(
            ApplicationClass.sSharedPreferences.getInt(ApplicationClass.USER_ID, 0),
            feedId!!
        )
    }

    override fun onGetFeedLikerSuccess(response: FeedLikerResponse) {
        dismissLoadingDialog()
        if (response.isSuccess) {
            binding.feedLikerRecyclerView.adapter = FeedLikerAdapter(context!!, response.result)
        } else {
            showCustomToast(response.message ?: "오류")
        }
    }

    override fun onGetFeedLikerFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast(message)
    }
}
