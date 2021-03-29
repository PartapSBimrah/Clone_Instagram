package site.yoonsang.instaclone.src.main.profile.follow.follower

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import site.yoonsang.instaclone.R
import site.yoonsang.instaclone.databinding.ItemMyFollowerBinding
import site.yoonsang.instaclone.src.main.MainActivity
import site.yoonsang.instaclone.src.main.others.OtherProfileFragment
import site.yoonsang.instaclone.src.main.profile.follow.follower.models.ResultGetMyFollowerRequest

class MyFollowerAdapter(
    val context: Context,
    private val followerList: ArrayList<ResultGetMyFollowerRequest>
): RecyclerView.Adapter<MyFollowerAdapter.ViewHolder>() {

    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    lateinit var binding: ItemMyFollowerBinding

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val profileImage = binding.itemMyFollowerProfileImage
        val userName = binding.itemMyFollowerUsername
        val name = binding.itemMyFollowerName
        val delete = binding.itemMyFollowerDelete
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemMyFollowerBinding.inflate(inflater, parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context)
            .load(followerList[position].profileImage)
            .placeholder(R.drawable.user)
            .into(holder.profileImage)
        holder.userName.text = followerList[position].userName
        holder.name.text = followerList[position].name
        holder.userName.setOnClickListener {
            val otherProfileFragment = OtherProfileFragment()
            val bundle = Bundle()
            bundle.putInt("otherId", followerList[position].userId)
            bundle.putString("follow", "follower")
            otherProfileFragment.arguments = bundle
            (context as MainActivity).changeFragment(otherProfileFragment)
        }
        holder.delete.setOnClickListener {

        }
    }

    override fun getItemCount(): Int = followerList.size
}