package site.yoonsang.instaclone.src.main.search

import android.os.Bundle
import android.view.View
import com.arasthel.spannedgridlayoutmanager.SpanSize
import com.arasthel.spannedgridlayoutmanager.SpannedGridLayoutManager
import site.yoonsang.instaclone.R
import site.yoonsang.instaclone.config.BaseFragment
import site.yoonsang.instaclone.databinding.FragmentSearchBinding

class SearchFragment :
    BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::bind, R.layout.fragment_search) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spannedGridLayoutManager =
            SpannedGridLayoutManager(SpannedGridLayoutManager.Orientation.VERTICAL, 3)
        spannedGridLayoutManager.spanSizeLookup =
            SpannedGridLayoutManager.SpanSizeLookup { position ->
                when (position % 20) {
                    2 -> {
                        SpanSize(1, 2)
                    }
                    11 -> {
                        SpanSize(2, 2)
                    }
                    else -> {
                        SpanSize(1, 1)
                    }
                }
            }
        binding.searchFeedRecyclerView.setHasFixedSize(true)
        binding.searchFeedRecyclerView.layoutManager = spannedGridLayoutManager
        binding.searchFeedRecyclerView.adapter = SearchFeedAdapter(context!!)
    }
}