package com.tatvasoft.test.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.tatvasoft.test.R
import com.tatvasoft.test.databinding.RowUsersBinding
import com.tatvasoft.test.model.UserItem

class UserPagedListAdapter : PagedListAdapter<UserItem, UserPagedListAdapter.ViewHolder>(DIFF_CALLBACK) {

    inner class ViewHolder(val binding: RowUsersBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindView(userEntity: UserItem) {
            binding.tvFirstName.text = userEntity.first_name
            binding.tvLastName.text = userEntity.last_name
            binding.tvEmail.text = userEntity.email

            binding.ivUser.load(userEntity.avatar) {
                placeholder(R.drawable.img_profile)
                error(R.drawable.img_profile)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserItem>() {
            override fun areItemsTheSame(oldItem: UserItem, newItem: UserItem) = oldItem.id == newItem.id


            override fun areContentsTheSame(oldItem: UserItem, newItem: UserItem) = oldItem == newItem

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(RowUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bindView(it) }
    }
}