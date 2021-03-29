package site.yoonsang.instaclone.src.main.profile.myfeed.detail

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import site.yoonsang.instaclone.R
import site.yoonsang.instaclone.config.ApplicationClass
import site.yoonsang.instaclone.config.BaseFragment
import site.yoonsang.instaclone.databinding.FragmentMyFeedDetailBinding
import site.yoonsang.instaclone.src.main.MainActivity
import site.yoonsang.instaclone.src.main.home.comment.HomeFeedCommentFragment
import site.yoonsang.instaclone.src.main.home.feedAdapter.HomeFeedAdapter
import site.yoonsang.instaclone.src.main.home.feedAdapter.HomeFeedPhotosAdapter
import site.yoonsang.instaclone.src.main.home.feedAdapter.models.FeedLikeResponse
import site.yoonsang.instaclone.src.main.home.feedMore.MyFeedMoreFragment
import site.yoonsang.instaclone.src.main.home.liker.FeedLikerFragment
import site.yoonsang.instaclone.src.main.home.models.HTags
import site.yoonsang.instaclone.src.main.profile.myfeed.detail.models.FeedDetailResponse

class MyFeedDetailFragment : BaseFragment<FragmentMyFeedDetailBinding>(
    FragmentMyFeedDetailBinding::bind,
    R.layout.fragment_my_feed_detail
), MyFeedDetailFragmentView {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showLoadingDialog(context!!)
        val feedId = arguments?.getInt("feedId")

        MyFeedDetailService(this).tryGetFeedDetail(
            ApplicationClass.sSharedPreferences.getInt(
                ApplicationClass.USER_ID,
                0
            ), feedId!!
        )
    }

    override fun onGetFeedDetailSuccess(response: FeedDetailResponse) {
        dismissLoadingDialog()
        if (response.isSuccess) {
            val feed = response.result.feedInfo
            val media = response.result.medias
            val hTags = response.result.htags
            if (feed.profileImage != null) {
                Glide.with(context!!)
                    .load(feed.profileImage)
                    .into(binding.myFeedDetailProfileImage)
            }
            binding.myFeedDetailUsername.text = feed.userName
            binding.myFeedDetailMore.setOnClickListener {
                val myFeedMoreFragment = MyFeedMoreFragment()
                val bundle = Bundle()
                bundle.putInt("feedId", feed.feedId)
                myFeedMoreFragment.arguments = bundle
                myFeedMoreFragment.show(
                    (context as AppCompatActivity).supportFragmentManager,
                    myFeedMoreFragment.tag
                )
            }
            if (media != null) {
                binding.myFeedDetailPhotos.adapter = HomeFeedPhotosAdapter(context!!, media)
                binding.myFeedDetailPhotoIndicator.setViewPager2(binding.myFeedDetailPhotos)
                if (feed.mediaNum == 1) {
                    binding.myFeedDetailPhotoIndicator.visibility = View.GONE
                } else {
                    binding.myFeedDetailPhotoIndicator.visibility = View.VISIBLE
                }
            }

            binding.myFeedDetailHeart.setOnClickListener {
                showLoadingDialog(context!!)
                MyFeedDetailService(this).tryPostFeedLike(
                    ApplicationClass.sSharedPreferences.getInt(ApplicationClass.USER_ID, 0),
                    feed.feedId
                )
            }

            binding.myFeedDetailComment.setOnClickListener {
                val homeFeedCommentFragment = HomeFeedCommentFragment()
                val bundle = Bundle()
                bundle.putString("username", feed.userName)
                if (feed.profileImage != null) {
                    bundle.putString("profileImage", feed.profileImage)
                }
                bundle.putString("content", feed.content)
                bundle.putString("time", feed.time)
                homeFeedCommentFragment.arguments = bundle
                (context as MainActivity).changeFragment(homeFeedCommentFragment)
            }

            binding.myFeedDetailContent.text = "${feed.userName} ${feed.content}"
            spanString(
                binding.myFeedDetailContent,
                feed.userName,
                hTags
            )

            if (feed.likeNum == 0) {
                binding.myFeedDetailLikeCount.visibility = View.GONE
            } else {
                binding.myFeedDetailLikeCount.visibility = View.VISIBLE
                binding.myFeedDetailLikeCount.text = "좋아요 ${feed.likeNum}개"
                binding.myFeedDetailLikeCount.setOnClickListener {
                    val feedLikerFragment = FeedLikerFragment()
                    val bundle = Bundle()
                    bundle.putInt("feedId", feed.feedId)
                    feedLikerFragment.arguments = bundle
                    (context as MainActivity).changeFragment(feedLikerFragment)
                }
            }

            if (feed.commentNum == 0) {
                binding.myFeedDetailCommentCount.visibility = View.GONE
            } else {
                binding.myFeedDetailCommentCount.visibility = View.VISIBLE
                binding.myFeedDetailCommentCount.text = "댓글 ${feed.commentNum}개 모두보기"
                binding.myFeedDetailCommentCount.setOnClickListener {
                    val homeFeedCommentFragment = HomeFeedCommentFragment()
                    val bundle = Bundle()
                    bundle.putInt("feedId", feed.feedId)
                    bundle.putString("username", feed.userName)
                    if (feed.profileImage != null) {
                        bundle.putString("profileImage", feed.profileImage)
                    }
                    bundle.putString("content", feed.content)
                    bundle.putString("time", feed.time)
                    homeFeedCommentFragment.arguments = bundle
                    (context as MainActivity).changeFragment(homeFeedCommentFragment)
                }
            }

            binding.myFeedDetailUploadDate.text = feed.time

            if (feed.firstComment == null) {
                binding.myFeedDetailFirstCommentLayout.visibility = View.GONE
            } else {
                binding.myFeedDetailFirstCommentLayout.visibility = View.VISIBLE
                binding.myFeedDetailFirstCommentUsername.text = feed.commentUserName
                binding.myFeedDetailFirstCommentContent.text = feed.firstComment
            }
        }
    }

    override fun onGetFeedDetailFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast(message)
    }

    override fun onPostFeedLikeSuccess(response: FeedLikeResponse) {
        dismissLoadingDialog()
        if (response.isSuccess) {
            if (response.result.feedLikeRes.status == "Y") {
                binding.myFeedDetailHeart.setImageResource(R.drawable.ic_full_heart)
            } else {
                binding.myFeedDetailHeart.setImageResource(R.drawable.ic_heart)
            }
            if (response.result.totalLikeNum == 0) {
                binding.myFeedDetailLikeCount.visibility = View.GONE
            } else {
                binding.myFeedDetailLikeCount.visibility = View.VISIBLE
                binding.myFeedDetailLikeCount.text = "좋아요 ${response.result.totalLikeNum}개"
            }
        } else {
            showCustomToast(response.message ?: "오류")
        }
    }

    override fun onPostFeedLikeFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast(message)
    }

    private fun spanString(tv: TextView, username: String, hTags: ArrayList<HTags>?) {
        val content = tv.text.toString()
        val spannableString = SpannableString(content)
        val usernameStart = content.indexOf(username)
        val usernameEnd = usernameStart + username.length
        spannableString.setSpan(
            StyleSpan(Typeface.BOLD),
            usernameStart,
            usernameEnd,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        if (hTags != null) {
            for (tag in hTags) {
                val start = content.indexOf(tag.tagName)
                val end = start + tag.tagName.length
                spannableString.setSpan(
                    ForegroundColorSpan(context!!.getColor(R.color.signup_text)),
                    start,
                    end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }
        tv.text = spannableString
    }
}