package com.tatvasoft.test.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.tatvasoft.test.R
import com.tatvasoft.test.databinding.RowUsersBinding
import com.tatvasoft.test.model.UserEntity

class UserPagedListAdapter : PagedListAdapter<UserEntity, UserPagedListAdapter.ViewHolder>(DIFF_CALLBACK) {

    inner class ViewHolder(val binding: RowUsersBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindView(userEntity: UserEntity) {
            binding.tvFirstName.text = userEntity.first_name
            binding.tvLastName.text = userEntity.last_name
            binding.tvEmail.text = userEntity.email

            binding.ivUser.load(userEntity.avatar) {
                placeholder(R.drawable.ic_user_placeholder)
                error(R.drawable.ic_broken_image)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserEntity>() {
            override fun areItemsTheSame(oldItem: UserEntity, newItem: UserEntity) = oldItem.id == newItem.id


            override fun areContentsTheSame(oldItem: UserEntity, newItem: UserEntity) = oldItem == newItem

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(RowUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bindView(it) }
    }
}