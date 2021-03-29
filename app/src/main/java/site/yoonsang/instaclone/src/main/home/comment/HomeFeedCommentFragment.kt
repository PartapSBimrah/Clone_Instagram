package site.yoonsang.instaclone.src.main.home.comment

import android.graphics.Typeface
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import site.yoonsang.instaclone.R
import site.yoonsang.instaclone.config.ApplicationClass
import site.yoonsang.instaclone.config.BaseFragment
import site.yoonsang.instaclone.databinding.FragmentHomeFeedCommentBinding
import site.yoonsang.instaclone.src.main.home.comment.commentAdapter.HomeFeedCommentAdapter
import site.yoonsang.instaclone.src.main.home.comment.models.CreateCommentResponse
import site.yoonsang.instaclone.src.main.home.comment.models.FeedCommentResponse
import site.yoonsang.instaclone.src.main.home.comment.models.PostCommentCreateRequest
import site.yoonsang.instaclone.src.main.home.feedMore.models.DeleteFeedResponse
import site.yoonsang.instaclone.src.main.home.models.HTags

class HomeFeedCommentFragment : BaseFragment<FragmentHomeFeedCommentBinding>(
    FragmentHomeFeedCommentBinding::bind,
    R.layout.fragment_home_feed_comment
), FeedCommentFragmentView {

    private var editNotEmpty = false
    private var feedId = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        feedId = arguments?.getInt("feedId")!!

        arguments?.let {
            binding.commentContent.text = "${it.getString("username")} ${it.getString("content")}"
            spanString(
                binding.commentContent,
                it.getString("username")!!,
                it.getStringArrayList("hTags") as ArrayList<HTags>?
            )
            binding.commentUploadDate.text = it.getString("time")
            if (it.getString("profileImage") != null) {
                Glide.with(context!!)
                    .load(it.getString("profileImage"))
                    .into(binding.commentProfileImage)
            }
        }

        showLoadingDialog(context!!)
        FeedCommentService(this).tryGetFeedComment(
            ApplicationClass.sSharedPreferences.getInt(ApplicationClass.USER_ID, 0),
            feedId
        )

        binding.homeFeedCommentEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                editNotEmpty = if (s?.isNotEmpty() == true && s != "") {
                    binding.homeFeedCommentBtnPost.setTextColor(context!!.getColor(R.color.selected_button))
                    true
                } else {
                    binding.homeFeedCommentBtnPost.setTextColor(context!!.getColor(R.color.unselected_button))
                    false
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.homeFeedCommentBtnPost.setOnClickListener {
            if (editNotEmpty) {
                showLoadingDialog(context!!)
                val contents = binding.homeFeedCommentEditText.text.toString()
                val request = PostCommentCreateRequest(
                    ApplicationClass.sSharedPreferences.getInt(ApplicationClass.USER_ID, 0),
                    contents
                )
                FeedCommentService(this).tryPostCreateFeedComment(
                    ApplicationClass.sSharedPreferences.getInt(ApplicationClass.USER_ID, 0),
                    feedId,
                    request
                )
            }
        }
    }

    override fun onGetFeedCommentSuccess(response: FeedCommentResponse) {
        dismissLoadingDialog()
        if (response.isSuccess) {
            binding.commentRecyclerView.adapter =
                HomeFeedCommentAdapter(context!!, response.result, this)
        }
    }

    override fun onGetFeedCommentFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast(message)
    }

    override fun onPostFeedCommentCreateSuccess(response: CreateCommentResponse) {
        dismissLoadingDialog()
        if (response.isSuccess) {
            showLoadingDialog(context!!)
            FeedCommentService(this).tryGetFeedComment(
                ApplicationClass.sSharedPreferences.getInt(ApplicationClass.USER_ID, 0),
                feedId
            )
            binding.homeFeedCommentEditText.setText("")
        } else {
            showCustomToast(response.message ?: "오류")
        }
    }

    override fun onPostFeedCommentCreateFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast(message)
    }

    override fun onDeleteCommentSuccess(response: DeleteFeedResponse) {
        dismissLoadingDialog()
        showCustomToast(response.result)
        if (response.isSuccess) {
            showLoadingDialog(context!!)
            FeedCommentService(this).tryGetFeedComment(
                ApplicationClass.sSharedPreferences.getInt(ApplicationClass.USER_ID, 0),
                feedId
            )
            (context as AppCompatActivity).window.statusBarColor =
                context!!.getColor(R.color.white)
            binding.commentTitleLayout.visibility = View.VISIBLE
            binding.commentDeleteLayout.visibility = View.GONE
        }
    }

    override fun onDeleteCommentFailure(message: String) {
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

    fun deleteComment(commentId: Int) {
        (context as AppCompatActivity).window.statusBarColor =
            context!!.getColor(R.color.deleteStatus)
        binding.commentTitleLayout.visibility = View.GONE
        binding.commentDeleteLayout.visibility = View.VISIBLE
        binding.commentDeleteCancel.setOnClickListener {
            (context as AppCompatActivity).window.statusBarColor =
                context!!.getColor(R.color.white)
            binding.commentTitleLayout.visibility = View.VISIBLE
            binding.commentDeleteLayout.visibility = View.GONE
        }
        binding.commentDelete.setOnClickListener {
            showLoadingDialog(context!!)
            FeedCommentService(this).tryDeleteComment(
                ApplicationClass.sSharedPreferences.getInt(ApplicationClass.USER_ID, 0),
                commentId
            )
        }
    }
}