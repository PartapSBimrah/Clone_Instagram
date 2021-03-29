package site.yoonsang.instaclone.src.main.home.post.feed

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import site.yoonsang.instaclone.R
import site.yoonsang.instaclone.databinding.ItemPostFeedBinding

class PostFeedAdapter(
    val context: Context,
    private val uriArr: ArrayList<String>,
    private val preview: ImageView,
) : RecyclerView.Adapter<PostFeedAdapter.ViewHolder>() {

    private val inflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    lateinit var binding: ItemPostFeedBinding
    var multiple = false
    val multipleArray = arrayListOf<String>()
    var defaultUrl = uriArr[0]

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView = binding.itemFeedImageView
        val multipleOrderLayout = binding.itemFeedMultipleOrder
        val orderText = binding.itemFeedOrderText
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemPostFeedBinding.inflate(inflater, parent, false)

        val lp: GridLayoutManager.LayoutParams =
            binding.root.layoutParams as GridLayoutManager.LayoutParams
        lp.width = parent.measuredWidth / 4
        lp.height = parent.measuredWidth / 4
        binding.root.layoutParams = lp

        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context)
            .load(uriArr[position])
            .into(holder.imageView)

        if (multiple) {
            multipleArray.clear()
            holder.multipleOrderLayout.visibility = View.VISIBLE
            holder.imageView.setOnClickListener {
                Glide.with(context)
                    .load(uriArr[position])
                    .into(preview)
                if (!multipleArray.contains(uriArr[position])) {
                    holder.multipleOrderLayout.setBackgroundResource(R.drawable.feed_photo_order_selected_background)
                    multipleArray.add(uriArr[position])
                    holder.orderText.text = (multipleArray.indexOf(uriArr[position]) + 1).toString()
                    defaultUrl = uriArr[position]
                } else {
                    holder.multipleOrderLayout.setBackgroundResource(R.drawable.feed_photo_order_unselected_background)
                    multipleArray.remove(uriArr[position])
                    holder.orderText.text = ""
                }
            }
        } else {
            multipleArray.clear()
            holder.multipleOrderLayout.visibility = View.GONE
            holder.imageView.setOnClickListener {
                if (multipleArray.size != 0) {
                    multipleArray.clear()
                }
                multipleArray.add(uriArr[position])
                Glide.with(context)
                    .load(uriArr[position])
                    .into(preview)
                defaultUrl = uriArr[position]
            }
        }
    }

    override fun getItemCount(): Int = uriArr.size

}