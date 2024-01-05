package com.kasandi.githubuser.ui.activity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.kasandi.githubuser.R
import com.kasandi.githubuser.data.response.UserDetail
import com.kasandi.githubuser.databinding.ActivityDetailBinding
import com.kasandi.githubuser.ui.adapter.SectionPagerAdapter
import com.kasandi.githubuser.viewmodel.DetailViewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel by viewModels<DetailViewModel>()

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
        const val EXTRA_USERNAME = "username"
        var USERNAME = "default"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionPagerAdapter = SectionPagerAdapter(this)
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = sectionPagerAdapter
        val tabs: TabLayout = binding.tabs
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        supportActionBar?.elevation = 0f

        val username = intent.getStringExtra(EXTRA_USERNAME)
        USERNAME = username.toString()
        detailViewModel.setUserDetail(username.toString())
        detailViewModel.userDetail.observe(this) {
            setUserData(it)
        }

        detailViewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    private fun setUserData(user: UserDetail) {
        binding.tvName.text = user.name
        binding.tvUsername.text = user.login
        binding.tvFollower.text = "Followers : ${user.followers.toString()}"
        binding.tvFollowing.text = "Following : ${user.following.toString()}"
        Glide.with(this)
            .load(user.avatarUrl)
            .transform(CenterCrop(), RoundedCorners(150))
            .into(binding.imgUser)
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.pbDetailUser.visibility = View.VISIBLE
            binding.tvName.visibility = View.INVISIBLE
            binding.tvFollower.visibility = View.INVISIBLE
            binding.tvFollowing.visibility = View.INVISIBLE
            binding.tvUsername.visibility = View.INVISIBLE
        } else {
            binding.pbDetailUser.visibility = View.INVISIBLE
            binding.tvName.visibility = View.VISIBLE
            binding.tvFollower.visibility = View.VISIBLE
            binding.tvFollowing.visibility = View.VISIBLE
            binding.tvUsername.visibility = View.VISIBLE
        }
    }
}