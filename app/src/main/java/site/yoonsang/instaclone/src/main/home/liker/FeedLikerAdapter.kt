package site.yoonsang.instaclone.src.main.home.liker

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import site.yoonsang.instaclone.config.ApplicationClass
import site.yoonsang.instaclone.databinding.ItemFeedLikerBinding
import site.yoonsang.instaclone.src.main.home.liker.models.ResultGetFeedLikerRequest

class FeedLikerAdapter(
    val context: Context,
    private val list: ArrayList<ResultGetFeedLikerRequest>
):RecyclerView.Adapter<FeedLikerAdapter.ViewHolder>() {

    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    lateinit var binding: ItemFeedLikerBinding

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val username = binding.itemLikerUsername
        val name = binding.itemLikerName
        val profileImage = binding.itemLikerProfileImage
        val followBtn = binding.itemLikerFollowBtn
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemFeedLikerBinding.inflate(inflater, parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.username.text = list[position].userName
        holder.name.text = list[position].name
        if (list[position].profileImage != null) {
            Glide.with(context)
                .load(list[position].profileImage)
                .into(holder.profileImage)
        }
        if (list[position].userId == ApplicationClass.sSharedPreferences.getInt(ApplicationClass.USER_ID, 0)) {
            holder.followBtn.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int = list.size
}