package site.yoonsang.instaclone.src.main.home.feedMore

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import site.yoonsang.instaclone.R
import site.yoonsang.instaclone.config.ApplicationClass
import site.yoonsang.instaclone.databinding.FragmentMyFeedMoreBinding
import site.yoonsang.instaclone.src.main.home.feedMore.models.DeleteFeedResponse
import site.yoonsang.instaclone.src.main.home.feedMore.modify.FeedModifyActivity
import site.yoonsang.instaclone.util.LoadingDialog

class MyFeedMoreFragment : BottomSheetDialogFragment(), MyFeedMoreFragmentView {

    private var _binding: FragmentMyFeedMoreBinding? = null
    private val binding get() = _binding!!
    lateinit var listener: DeleteClickedListener
    lateinit var mLoadingDialog: LoadingDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyFeedMoreBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val feedId = arguments?.getInt("feedId")

        binding.myFeedMoreDelete.setOnClickListener {
            showLoadingDialog(context!!)
            MyFeedMoreService(this).tryPatchFeedDelete(
                ApplicationClass.sSharedPreferences.getInt(
                    ApplicationClass.USER_ID,
                    0
                ), feedId!!
            )
        }

        binding.myFeedMoreModify.setOnClickListener {
            val intent = Intent(activity, FeedModifyActivity::class.java)
            intent.putExtra("feedId", arguments?.getInt("feedId"))
            intent.putExtra("username", arguments?.getString("username"))
            intent.putExtra("content", arguments?.getString("content"))
            intent.putExtra("time", arguments?.getString("time"))
            intent.putExtra("image", arguments?.getString("image"))
            startActivity(intent)
        }
    }

    override fun onPatchDeleteFeedSuccess(response: DeleteFeedResponse) {
        dismissLoadingDialog()
        showCustomToast(response.result)
        if (response.isSuccess) {
            listener.onDeleteClicked(true)
            dismiss()
        }
    }

    override fun onPatchDeleteFeedFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast(message)
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun showCustomToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
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

    interface DeleteClickedListener {
        fun onDeleteClicked(content: Boolean)
    }

    fun setOnDeleteClicked(listener: (Boolean) -> Unit) {
        this.listener = object : DeleteClickedListener {
            override fun onDeleteClicked(content: Boolean) {
                listener(content)
            }
        }
    }
}