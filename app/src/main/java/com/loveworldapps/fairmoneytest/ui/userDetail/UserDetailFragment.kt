package com.loveworldapps.fairmoneytest.ui.userDetail

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.loveworldapps.fairmoneytest.R
import com.loveworldapps.fairmoneytest.databinding.FragmentUserBinding
import com.loveworldapps.fairmoneytest.databinding.FragmentUserDetailBinding
import com.loveworldapps.fairmoneytest.ui.UserViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import org.koin.android.viewmodel.ext.android.sharedViewModel

/**
 * Created by manuelchris-ogar on 21/01/2021.
 */
class UserDetailFragment  : Fragment(){

    private val _binding  by  lazy { FragmentUserDetailBinding.inflate(layoutInflater) }
    private val binding get() = _binding

    private val viewModel: UserViewModel by sharedViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.slide_right)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    fun setupObservers(){
        viewModel.selectedUser.observe(viewLifecycleOwner, {
            val thumb: String = it.picture
            val placeholder =  if (it.title=="mr") R.drawable.ic_man else R.drawable.ic_man
            try {
                if (!TextUtils.isEmpty(thumb)) Picasso.get().load(thumb)
                        .fit()
                        .centerInside()
                        .placeholder(placeholder)
                        .into(binding.userImage, object : Callback {
                            override fun onSuccess() {
                                binding.progressBar.setVisibility(View.GONE)
                            }

                            override fun onError(e: Exception) {
                                binding.progressBar.setVisibility(View.GONE)
                            }
                        }) else {
                    binding.progressBar.setVisibility(View.GONE)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            binding.title.text = it.title.capitalize()
            binding.firstName.text = it.firstName.capitalize()
            binding.lastName.text = it.lastName.capitalize()
            binding.email.text = it.email

        })
    }
}