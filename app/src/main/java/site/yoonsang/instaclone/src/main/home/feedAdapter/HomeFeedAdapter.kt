package site.yoonsang.instaclone.src.main.home.feedAdapter

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import site.yoonsang.instaclone.R
import site.yoonsang.instaclone.config.ApplicationClass
import site.yoonsang.instaclone.databinding.ItemHomeFeedBinding
import site.yoonsang.instaclone.src.main.MainActivity
import site.yoonsang.instaclone.src.main.home.HomeFeedService
import site.yoonsang.instaclone.src.main.home.HomeFragment
import site.yoonsang.instaclone.src.main.home.comment.HomeFeedCommentFragment
import site.yoonsang.instaclone.src.main.home.feedMore.MyFeedMoreFragment
import site.yoonsang.instaclone.src.main.home.feedAdapter.models.FeedLikeResponse
import site.yoonsang.instaclone.src.main.home.feedMore.other.OtherFeedMoreFragment
import site.yoonsang.instaclone.src.main.home.liker.FeedLikerFragment
import site.yoonsang.instaclone.src.main.home.models.HTags
import site.yoonsang.instaclone.src.main.home.models.ResultGetFeedsRequest
import site.yoonsang.instaclone.util.LoadingDialog

class HomeFeedAdapter(
    val context: Context,
    private val list: ArrayList<ResultGetFeedsRequest>
) : RecyclerView.Adapter<HomeFeedAdapter.ViewHolder>(), FeedAdapterView {

    private val inflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private val sortedList = list.reversed()
    lateinit var binding: ItemHomeFeedBinding
    private lateinit var mLoadingDialog: LoadingDialog

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profileImage = binding.itemFeedProfileImage
        val userName = binding.itemFeedUsername
        val more = binding.itemFeedMore
        val photos = binding.itemFeedPhotos
        val indicator = binding.itemFeedPhotoIndicator
        val heart = binding.itemFeedHeart
        val comment = binding.itemFeedComment
        val content = binding.itemFeedContent
        val likeCount = binding.itemFeedLikeCount
        val commentCount = binding.itemFeedCommentCount
        val uploadDate = binding.itemFeedUploadDate
        val firstCommentLayout = binding.itemFeedFirstCommentLayout
        val firstCommentUsername = binding.itemFeedFirstCommentUsername
        val firstCommentContent = binding.itemFeedFirstCommentContent
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemHomeFeedBinding.inflate(inflater, parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.uploadDate.text = sortedList[position].feedInfo.time
        holder.userName.text = sortedList[position].feedInfo.userName
        if (sortedList[position].medias != null) {
            holder.photos.adapter = HomeFeedPhotosAdapter(context, sortedList[position].medias!!)
            holder.indicator.setViewPager2(holder.photos)
            if (sortedList[position].feedInfo.mediaNum == 1) {
                holder.indicator.visibility = View.GONE
            } else {
                holder.indicator.visibility = View.VISIBLE
            }
        }

        if (sortedList[position].feedInfo.profileImage != null) {
            Glide.with(context)
                .load(sortedList[position].feedInfo.profileImage)
                .into(holder.profileImage)
        }

        if (sortedList[position].feedInfo.likeNum == 0) {
            holder.likeCount.visibility = View.GONE
        } else {
            holder.likeCount.visibility = View.VISIBLE
            holder.likeCount.text = "좋아요 ${sortedList[position].feedInfo.likeNum}개"
            holder.likeCount.setOnClickListener {
                val feedLikerFragment = FeedLikerFragment()
                val bundle = Bundle()
                bundle.putInt("feedId", sortedList[position].feedInfo.feedId)
                feedLikerFragment.arguments = bundle
                (context as MainActivity).changeFragment(feedLikerFragment)
            }
        }

        if (sortedList[position].feedInfo.commentNum == 0) {
            holder.commentCount.visibility = View.GONE
        } else {
            holder.commentCount.visibility = View.VISIBLE
            holder.commentCount.text = "댓글 ${sortedList[position].feedInfo.commentNum}개 모두보기"
            holder.commentCount.setOnClickListener {
                val homeFeedCommentFragment = HomeFeedCommentFragment()
                val bundle = Bundle()
                bundle.putInt("feedId", sortedList[position].feedInfo.feedId)
                bundle.putString("username", sortedList[position].feedInfo.userName)
                if (sortedList[position].feedInfo.profileImage != null) {
                    bundle.putString("profileImage", sortedList[position].feedInfo.profileImage)
                }
                bundle.putString("content", sortedList[position].feedInfo.content)
                bundle.putString("time", sortedList[position].feedInfo.time)
                if (sortedList[position].htags != null) {
                    bundle.putStringArrayList(
                        "hTags",
                        sortedList[position].htags as java.util.ArrayList<String>?
                    )
                }
                homeFeedCommentFragment.arguments = bundle
                (context as MainActivity).changeFragment(homeFeedCommentFragment)
            }
        }

        holder.content.text =
            "${sortedList[position].feedInfo.userName} ${sortedList[position].feedInfo.content}"
        spanString(
            holder.content,
            sortedList[position].feedInfo.userName,
            sortedList[position].htags
        )

        holder.comment.setOnClickListener {
            val homeFeedCommentFragment = HomeFeedCommentFragment()
            val bundle = Bundle()
            bundle.putInt("feedId", sortedList[position].feedInfo.feedId)
            bundle.putString("username", sortedList[position].feedInfo.userName)
            if (sortedList[position].feedInfo.profileImage != null) {
                bundle.putString("profileImage", sortedList[position].feedInfo.profileImage)
            }
            bundle.putString("content", sortedList[position].feedInfo.content)
            bundle.putString("time", sortedList[position].feedInfo.time)
            if (sortedList[position].htags != null) {
                bundle.putStringArrayList(
                    "hTags",
                    sortedList[position].htags as java.util.ArrayList<String>?
                )
            }
            homeFeedCommentFragment.arguments = bundle
            (context as MainActivity).changeFragment(homeFeedCommentFragment)
        }

        holder.more.setOnClickListener {
            if (sortedList[position].feedInfo.userId == ApplicationClass.sSharedPreferences.getInt(
                    ApplicationClass.USER_ID,
                    0
                )
            ) {
                val myFeedMoreFragment = MyFeedMoreFragment()
                val bundle = Bundle()
                bundle.putInt("feedId", sortedList[position].feedInfo.feedId)
                bundle.putString("username", sortedList[position].feedInfo.userName)
                if (sortedList[position].feedInfo.profileImage != null) {
                    bundle.putString("profileImage", sortedList[position].feedInfo.profileImage)
                }
                bundle.putString("content", sortedList[position].feedInfo.content)
                bundle.putString("time", sortedList[position].feedInfo.time)
                if (sortedList[position].medias != null) {
                    bundle.putString("image", sortedList[position].medias!![0].mediaUrl)
                }
                myFeedMoreFragment.arguments = bundle
                myFeedMoreFragment.show(
                    (context as AppCompatActivity).supportFragmentManager,
                    myFeedMoreFragment.tag
                )
                myFeedMoreFragment.setOnDeleteClicked {
                }
            } else {
                val bottomSheet = OtherFeedMoreFragment()
                bottomSheet.show(
                    (context as AppCompatActivity).supportFragmentManager,
                    bottomSheet.tag
                )
            }
        }

        if (sortedList[position].didLike == "Y") {
            Glide.with(context)
                .load(R.drawable.ic_full_heart)
                .into(holder.heart)
        } else {
            Glide.with(context)
                .load(R.drawable.ic_heart)
                .into(holder.heart)
        }

        holder.heart.setOnClickListener {
            showLoadingDialog(context)
            FeedAdapterService(this).tryPostFeedLike(
                ApplicationClass.sSharedPreferences.getInt(ApplicationClass.USER_ID, 0),
                sortedList[position].feedInfo.feedId,
                holder
            )
        }

        if (sortedList[position].feedInfo.firstComment != null) {
            holder.firstCommentLayout.visibility = View.VISIBLE
            holder.firstCommentUsername.text = sortedList[position].feedInfo.commentUserName
            holder.firstCommentContent.text = sortedList[position].feedInfo.firstComment
        } else {
            holder.firstCommentLayout.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int = sortedList.size

    override fun onPostFeedLikeSuccess(response: FeedLikeResponse, holder: ViewHolder) {
        dismissLoadingDialog()
        if (response.isSuccess) {
            if (response.result.feedLikeRes.status == "Y") {
                holder.heart.setImageResource(R.drawable.ic_full_heart)
            } else {
                holder.heart.setImageResource(R.drawable.ic_heart)
            }
            if (response.result.totalLikeNum == 0) {
                holder.likeCount.visibility = View.GONE
            } else {
                holder.likeCount.visibility = View.VISIBLE
                holder.likeCount.text = "좋아요 ${response.result.totalLikeNum}개"
                holder.likeCount.setOnClickListener {
                    val feedLikerFragment = FeedLikerFragment()
                    val bundle = Bundle()
                    bundle.putInt("feedId", response.result.feedLikeRes.feedId)
                    feedLikerFragment.arguments = bundle
                    (context as MainActivity).changeFragment(feedLikerFragment)
                }
            }
        } else {
            showCustomToast(response.message ?: "오류")
        }
    }

    override fun onPostFeedLikeFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast(message)
    }

    fun showCustomToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun showLoadingDialog(context: Context) {
        mLoadingDialog = LoadingDialog(context)
        mLoadingDialog.show()
    }

    fun dismissLoadingDialog() {
        if (mLoadingDialog.isShowing) {
            mLoadingDialog.dismiss()
        }
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
                    ForegroundColorSpan(context.getColor(R.color.signup_text)),
                    start,
                    end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }
        tv.text = spannableString
    }
}