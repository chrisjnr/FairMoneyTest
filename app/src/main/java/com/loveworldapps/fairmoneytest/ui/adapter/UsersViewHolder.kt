package com.loveworldapps.fairmoneytest.ui.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.loveworldapps.fairmoneytest.R
import com.loveworldapps.fairmoneytest.api.models.User
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


/**
 * A simple ViewHolder that can bind a Farmer item. It also accepts null items since the data may
 * not have been fetched before it is bound.
 */
class UsersViewHolder(
        parent: ViewGroup,
        val clickListener: UsersAdapter.onClickListener
) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)) {
    //
    private val nameView = itemView.findViewById<TextView>(R.id.name)
    private val progressBar = itemView.findViewById<ProgressBar>(R.id.progress)
    private val farmerImage = itemView.findViewById<ImageView>(R.id.shop_icon)
    private val usersEmail = itemView.findViewById<TextView>(R.id.usersEmail)

    var user : User? = null

    /**
     * Items might be null if they are not paged in yet. PagedListAdapter will re-bind the
     * ViewHolder when Item is loaded.
     */
    fun bindTo(user : User) {
        this.user = user
        nameView.text = "${user.firstName.toLowerCase().capitalize()} ${user.lastName.toLowerCase().capitalize()}"

        itemView.setOnClickListener {
            clickListener.viewMore(user)
        }
        usersEmail.text = "${user.email}"
        val thumb: String = user.picture
        val placeholder =  if (user.title=="mr") R.drawable.ic_man else R.drawable.ic_man
        try {
            if (!TextUtils.isEmpty(thumb)) Picasso.get().load(thumb)
                    .fit()
                    .centerInside()
                    .placeholder(placeholder)
                    .into(farmerImage, object : Callback {
                        override fun onSuccess() {
                            progressBar.setVisibility(View.GONE)
                        }

                        override fun onError(e: Exception) {
                            progressBar.setVisibility(View.GONE)
                        }
                    }) else {
                progressBar.setVisibility(View.GONE)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}