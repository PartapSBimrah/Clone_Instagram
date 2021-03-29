package site.yoonsang.instaclone.src.main.reels

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import site.yoonsang.instaclone.R
import site.yoonsang.instaclone.databinding.ItemReelsBinding

class ReelsAdapter(
    val context: Context
): RecyclerView.Adapter<ReelsAdapter.ViewHolder>() {

    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    lateinit var binding: ItemReelsBinding

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imageView = binding.itemReelsImageView
        val textMarquee = binding.itemReelsTextMarquee
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemReelsBinding.inflate(inflater, parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context)
            .load(R.drawable.cat)
            .into(holder.imageView)
        holder.textMarquee.isSelected = true
    }

    override fun getItemCount(): Int = Int.MAX_VALUE
}