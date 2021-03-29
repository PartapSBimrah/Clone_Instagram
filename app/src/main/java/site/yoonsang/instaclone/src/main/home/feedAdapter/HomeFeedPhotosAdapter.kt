package site.yoonsang.instaclone.src.main.home.feedAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import site.yoonsang.instaclone.databinding.ItemFeedPhotosBinding
import site.yoonsang.instaclone.src.main.home.models.Medias

class HomeFeedPhotosAdapter(
    val context: Context,
    private val list: ArrayList<Medias>
): RecyclerView.Adapter<HomeFeedPhotosAdapter.ViewHolder>() {
    
    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    lateinit var binding: ItemFeedPhotosBinding

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val image = binding.itemFeedPhotosImage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemFeedPhotosBinding.inflate(inflater, parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context)
            .load(list[position].mediaUrl)
            .into(holder.image)
    }

    override fun getItemCount(): Int = list.size
}