package site.yoonsang.instaclone.src.main.home.post.feed.photosEdit

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import site.yoonsang.instaclone.databinding.ItemPhotosEditBinding
import site.yoonsang.instaclone.src.main.home.post.feed.photosEdit.photoEdit.PhotoEditActivity

class PhotosEditAdapter(
    val context: Context,
    val list: ArrayList<String>
): RecyclerView.Adapter<PhotosEditAdapter.ViewHolder>() {

    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    lateinit var binding: ItemPhotosEditBinding

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imageView = binding.itemPhotosEditImageView
        val powder = binding.itemPhotosEditPowder
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemPhotosEditBinding.inflate(inflater, parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context)
            .load(list[position])
            .into(holder.imageView)

        holder.powder.setOnClickListener {
            val intent = Intent(context, PhotoEditActivity::class.java)
            intent.putExtra("photo", list[position])
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = list.size
}