package site.yoonsang.instaclone.src.main.profile.myfeed

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import site.yoonsang.instaclone.databinding.ItemProfileFeedsBinding
import site.yoonsang.instaclone.src.main.MainActivity
import site.yoonsang.instaclone.src.main.profile.models.Feed
import site.yoonsang.instaclone.src.main.profile.myfeed.detail.MyFeedDetailFragment

class MyFeedAdapter(
    val context: Context,
    feeds: ArrayList<Feed>
) : RecyclerView.Adapter<MyFeedAdapter.ViewHolder>() {

    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    lateinit var binding: ItemProfileFeedsBinding
    private val sortFeeds = feeds.reversed()

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val preview = binding.itemProfileFeedsPreview
        val photos = binding.itemProfileFeedsIfPhotos
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemProfileFeedsBinding.inflate(inflater, parent, false)

        val lp: GridLayoutManager.LayoutParams = binding.root.layoutParams as GridLayoutManager.LayoutParams
        lp.width = parent.measuredWidth / 3
        lp.height = parent.measuredWidth / 3
        binding.root.layoutParams = lp

        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context)
            .load(sortFeeds[position].mediaUrl)
            .centerCrop()
            .into(holder.preview)
        if (sortFeeds[position].mediaNum != "multiple") {
            holder.photos.visibility = View.GONE
        }

        holder.itemView.setOnClickListener {
            val myFeedDetailFragment = MyFeedDetailFragment()
            val bundle = Bundle()
            bundle.putInt("feedId", sortFeeds[position].feedId!!)
            myFeedDetailFragment.arguments = bundle
            (context as MainActivity).changeFragment(myFeedDetailFragment)
        }
    }

    override fun getItemCount(): Int = sortFeeds.size
}