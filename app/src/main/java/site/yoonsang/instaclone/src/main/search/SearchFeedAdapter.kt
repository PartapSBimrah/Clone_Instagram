package site.yoonsang.instaclone.src.main.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import site.yoonsang.instaclone.R
import site.yoonsang.instaclone.databinding.ItemSearchFeedBinding

class SearchFeedAdapter(
    val context: Context
) : RecyclerView.Adapter<SearchFeedAdapter.ViewHolder>() {

    private val inflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    lateinit var binding: ItemSearchFeedBinding

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val preview = binding.itemSearchPreview
        val photos = binding.itemSearchIfPhotos
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemSearchFeedBinding.inflate(inflater, parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when {
            position % 20 == 2 -> {
                Glide.with(context)
                    .load(R.drawable.cat)
                    .into(holder.preview)
                holder.photos.visibility = View.GONE
            }
            position % 20 == 11 -> {
                Glide.with(context)
                    .load(R.drawable.coding)
                    .into(holder.preview)
                holder.photos.visibility = View.GONE
            }
            else -> {
                Glide.with(context)
                    .load(R.drawable.sample)
                    .into(holder.preview)
            }
        }
    }

    override fun getItemCount(): Int = 60
}