package site.yoonsang.instaclone.src.main.shop

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import site.yoonsang.instaclone.databinding.ItemShopMallBinding

class ShopAdapter(
    context: Context
): RecyclerView.Adapter<ShopAdapter.ViewHolder>() {

    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    lateinit var binding: ItemShopMallBinding

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemShopMallBinding.inflate(inflater, parent, false)

//        val lp: GridLayoutManager.LayoutParams = binding.root.layoutParams as GridLayoutManager.LayoutParams
//        lp.width = parent.measuredWidth / 2
//        lp.height = parent.measuredWidth / 2
//        binding.root.layoutParams = lp

        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    }

    override fun getItemCount(): Int = 20
}