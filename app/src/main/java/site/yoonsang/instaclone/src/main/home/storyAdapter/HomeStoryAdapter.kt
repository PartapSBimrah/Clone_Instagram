package site.yoonsang.instaclone.src.main.home.storyAdapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import site.yoonsang.instaclone.R
import site.yoonsang.instaclone.databinding.ItemHomeStoryBinding
import site.yoonsang.instaclone.src.main.home.storyAdapter.story.StoryActivity

class HomeStoryAdapter(
    val context: Context
): RecyclerView.Adapter<HomeStoryAdapter.ViewHolder>() {

    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    lateinit var binding: ItemHomeStoryBinding

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val profileImage = binding.itemStoryProfileImage
        val username = binding.itemStoryUsername
        var photoUrl: String = ""
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemHomeStoryBinding.inflate(inflater, parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (position % 4) {
            0 -> {
                holder.photoUrl = context.getString(R.string.doolee)
                holder.username.text = "둘리"
                Glide.with(context)
                    .load(holder.photoUrl)
                    .into(holder.profileImage)
            }
            1 -> {
                holder.photoUrl = context.getString(R.string.douner)
                holder.username.text = "도우너"
                Glide.with(context)
                    .load(holder.photoUrl)
                    .into(holder.profileImage)
            }
            2 -> {
                holder.photoUrl = context.getString(R.string.michol)
                holder.username.text = "마이콜"
                Glide.with(context)
                    .load(holder.photoUrl)
                    .into(holder.profileImage)
            }
            else -> {
                holder.photoUrl = context.getString(R.string.gogildong)
                holder.username.text = "고길동"
                Glide.with(context)
                    .load(holder.photoUrl)
                    .into(holder.profileImage)
            }
        }
        holder.itemView.setOnClickListener {
            val intent = Intent(context, StoryActivity::class.java)
            intent.putExtra("username", holder.username.text.toString())
            intent.putExtra("profileImage", holder.photoUrl)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = 4
}