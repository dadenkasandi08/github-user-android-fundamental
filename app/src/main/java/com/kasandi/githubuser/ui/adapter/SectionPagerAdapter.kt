package com.kasandi.githubuser.ui.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kasandi.githubuser.ui.activity.DetailActivity
import com.kasandi.githubuser.ui.fragment.FollowListFragment

class SectionPagerAdapter(actvity: AppCompatActivity) : FragmentStateAdapter(actvity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = FollowListFragment()
        fragment.arguments = Bundle().apply {
            putInt(FollowListFragment.ARG_SECTION_NUMBER, position + 1)
            putString(FollowListFragment.EXTRA_USERNAME, DetailActivity.USERNAME)
        }
        return fragment
    }
}