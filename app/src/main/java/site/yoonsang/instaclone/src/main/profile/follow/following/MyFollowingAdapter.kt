package site.yoonsang.instaclone.src.main.profile.follow.following

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import site.yoonsang.instaclone.R
import site.yoonsang.instaclone.databinding.ItemMyFollowingBinding
import site.yoonsang.instaclone.src.main.MainActivity
import site.yoonsang.instaclone.src.main.others.OtherProfileFragment
import site.yoonsang.instaclone.src.main.profile.follow.following.models.ResultGetMyFollowingRequest

class MyFollowingAdapter(
    val context: Context,
    private val followingList: ArrayList<ResultGetMyFollowingRequest>
): RecyclerView.Adapter<MyFollowingAdapter.ViewHolder>() {

    private val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    lateinit var binding: ItemMyFollowingBinding

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val profileImage = binding.itemMyFollowingProfileImage
        val userName = binding.itemMyFollowingUsername
        val name = binding.itemMyFollowingName
        val status = binding.itemMyFollowingStatus
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemMyFollowingBinding.inflate(inflater, parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context)
            .load(followingList[position].profileImage)
            .placeholder(R.drawable.user)
            .into(holder.profileImage)
        holder.userName.text = followingList[position].userName
        holder.name.text = followingList[position].name
        holder.userName.setOnClickListener {
            val otherProfileFragment = OtherProfileFragment()
            val bundle = Bundle()
            bundle.putInt("otherId", followingList[position].userId)
            bundle.putString("follow", "following")
            otherProfileFragment.arguments = bundle
            (context as MainActivity).changeFragment(otherProfileFragment)
        }
        holder.status.setOnClickListener {

        }
    }

    override fun getItemCount(): Int = followingList.size
}