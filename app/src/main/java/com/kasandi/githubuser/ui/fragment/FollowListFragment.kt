package com.kasandi.githubuser.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.kasandi.githubuser.data.response.UserItem
import com.kasandi.githubuser.databinding.FragmentFollowListBinding
import com.kasandi.githubuser.ui.adapter.ListUserAdapter
import com.kasandi.githubuser.viewmodel.FollowListViewModel

class FollowListFragment : Fragment() {

    companion object {
        const val EXTRA_USERNAME = "username"
        const val ARG_SECTION_NUMBER = "section_number"
    }

    private lateinit var viewModel: FollowListViewModel
    private lateinit var binding: FragmentFollowListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            FragmentFollowListBinding.inflate(inflater, container, false) // Initialize the binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(FollowListViewModel::class.java)

        val layoutManager = LinearLayoutManager(context)
        binding.rvListFollow.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(context, layoutManager.orientation)
        binding.rvListFollow.addItemDecoration(itemDecoration)

        val index = arguments?.getInt(ARG_SECTION_NUMBER, 0)
        val username = arguments?.getString(EXTRA_USERNAME).toString()

        if (index == 1) {
            viewModel.setUserFollowers(username)
        } else if (index == 2) {
            viewModel.setUserFollowing(username)
        }

        viewModel.userFollowers.observe(viewLifecycleOwner) {
            setListUser(it)
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.pbFollow.visibility = if (isLoading) View.VISIBLE else View.INVISIBLE
    }

    private fun setListUser(listUser: List<UserItem>) {
        val adapter = ListUserAdapter()
        adapter.submitList(listUser)
        binding.rvListFollow.adapter = adapter
    }
}