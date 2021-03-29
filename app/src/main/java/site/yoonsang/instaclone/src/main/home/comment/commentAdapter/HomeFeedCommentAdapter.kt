package site.yoonsang.instaclone.src.main.home.comment.commentAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import site.yoonsang.instaclone.databinding.ItemCommentBinding
import site.yoonsang.instaclone.src.main.home.comment.HomeFeedCommentFragment
import site.yoonsang.instaclone.src.main.home.comment.models.ResultGetFeedCommentRequest

class HomeFeedCommentAdapter(
    val context: Context,
    private val list: ArrayList<ResultGetFeedCommentRequest>,
    val fragment: HomeFeedCommentFragment
) : RecyclerView.Adapter<HomeFeedCommentAdapter.ViewHolder>() {

    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    lateinit var binding: ItemCommentBinding

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val username = binding.itemCommentUsername
        val content = binding.itemCommentContent
        val time = binding.itemCommentTime
        val reply = binding.itemCommentReply
        val heart = binding.itemCommentHeart
        val likeCount = binding.itemCommentLikeCount
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemCommentBinding.inflate(inflater, parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.username.text = list[position].userName
        holder.content.text = list[position].contents
        holder.time.text = list[position].time
        holder.reply.setOnClickListener {
        }
        holder.heart.setOnClickListener {
        }
        holder.itemView.setOnLongClickListener {
            fragment.deleteComment(list[position].feedCommentId)
            return@setOnLongClickListener true
        }
    }

    override fun getItemCount(): Int = list.size
}