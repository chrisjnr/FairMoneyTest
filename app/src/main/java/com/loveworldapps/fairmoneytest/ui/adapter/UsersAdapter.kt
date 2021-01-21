

package com.loveworldapps.fairmoneytest.ui.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.loveworldapps.fairmoneytest.api.models.User

class UsersAdapter(private val clickListener:  onClickListener) : PagedListAdapter<User, UsersViewHolder>(diffCallback) {
    interface onClickListener{
        fun  viewMore(user: User)
    }


    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bindTo(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder =
            UsersViewHolder(parent,clickListener)

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
                    oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
                    oldItem.equals(newItem)
        }
    }
}
