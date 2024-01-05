package com.kasandi.githubuser.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.kasandi.githubuser.data.response.UserItem
import com.kasandi.githubuser.databinding.UserItemBinding
import com.kasandi.githubuser.ui.activity.DetailActivity

class ListUserAdapter : ListAdapter<UserItem, ListUserAdapter.MyViewHolder>(DIFF_CALLBACK) {
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserItem>() {
            override fun areItemsTheSame(oldItem: UserItem, newItem: UserItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: UserItem, newItem: UserItem): Boolean {
                return oldItem == newItem
            }
        }
    }

    class MyViewHolder(val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserItem) {
            binding.tvListUsername.text = user.login

            Glide.with(binding.imgUserAvatar.context)
                .load(user.avatarUrl)
                .transform(CenterCrop(), RoundedCorners(100))
                .into(binding.imgUserAvatar)

            binding.cvUser.setOnClickListener {
                val intentDetail = Intent(binding.cvUser.context, DetailActivity::class.java)
                intentDetail.putExtra(DetailActivity.EXTRA_USERNAME, user.login)
                binding.cvUser.context.startActivity(intentDetail)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListUserAdapter.MyViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListUserAdapter.MyViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }
}