package com.something.subfirstjetpack.ui.home

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.something.subfirstjetpack.R
import com.something.subfirstjetpack.ui.movie.MovieFragment
import com.something.subfirstjetpack.ui.tvshow.TvShowFragment

class SectionsPagerAdapter(private val ctx: Context, fm: FragmentManager) : FragmentPagerAdapter(fm , BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    companion object{
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.movie, R.string.tshow)
    }

    override fun getCount(): Int = TAB_TITLES.size

    override fun getItem(position: Int): Fragment =
            when(position){
                0 -> MovieFragment()
                1 -> TvShowFragment()
                else -> Fragment()
            }

    override fun getPageTitle(position: Int): CharSequence  = ctx.resources.getString(TAB_TITLES[position])
}