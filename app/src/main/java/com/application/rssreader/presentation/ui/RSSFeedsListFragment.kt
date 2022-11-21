package com.application.rssreader.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.application.rssreader.core.exception.Failure
import com.application.rssreader.core.extension.invisible
import com.application.rssreader.core.extension.visible
import com.application.rssreader.data.model.RSSFeedModel
import com.application.rssreader.presentation.adapter.RSSFeedsAdapter
import com.application.rssreader.presentation.state.ViewState
import com.application.rssreader.presentation.util.RSSFeedsFailure
import com.application.rssreader.presentation.viewmodel.RSSFeedsViewModel
import com.application.rssreader.presentation.viewmodel.RSSFeedsViewModelFactory
import com.google.android.material.snackbar.Snackbar
import com.application.rssreader.R
import com.application.rssreader.databinding.FragmentRssFeedsListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RSSFeedsListFragment : Fragment() {

    private lateinit var binding: FragmentRssFeedsListBinding
    private lateinit var rssFeedsViewModel: RSSFeedsViewModel

    @Inject
    lateinit var rssFeedsAdapter: RSSFeedsAdapter

    @Inject
    lateinit var factory: RSSFeedsViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_rss_feeds_list, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()

        rssFeedsViewModel =
            ViewModelProvider(requireActivity(), factory).get(RSSFeedsViewModel::class.java)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                rssFeedsViewModel.uiState.collect {
                    onViewStateChange(it)
                }
            }
        }

        rssFeedsAdapter.setOnItemClickListener {
            rssFeedsViewModel.setSelectedListItem(it)
            findNavController().navigate(
                R.id.action_homeFragment_to_RSSStoriesFragment,
            )
        }
        rssFeedsViewModel.getRSSFeeds()
    }


    private fun onViewStateChange(uiState: ViewState<List<RSSFeedModel>>) = when (uiState) {

        is ViewState.Loading -> {
            showProgressBar()
            hideRecyclerView()
            hideNoDataText()
        }
        is ViewState.Error -> {
            hideProgressBar()
            showNoDataText()
            handleFailure(uiState.statusCode, uiState.reason, uiState.errorMessage)
        }
        is ViewState.Success -> uiState.data.let {
            hideProgressBar()
            hideNoDataText()
            showRecyclerView()
            uiState.failure?.let {
                handleFailure(it.statusCode, it.reason, it.errorMessage)

            }
            rssFeedsAdapter.differ.submitList(it)

        }
    }


    private fun initRecyclerView() {
        binding.rvProducts.apply {
            layoutManager = LinearLayoutManager(activity)
            this.adapter = rssFeedsAdapter
        }

    }

    private fun handleFailure(statusCode: Int, failure: Failure, message: String?) {
        when (failure) {
            is Failure.NetworkConnection -> {
                notify(message ?: getString(R.string.failure_network_connection))
            }
            is Failure.ServerError -> {
                notify(message ?: getString(R.string.failure_server_error))
            }
            is Failure.DBError -> {
                notify(message ?: getString(R.string.failure_db_error))
            }
            is RSSFeedsFailure.NoDataAvailable -> {
                notify(message ?: getString(R.string.no_data))
            }
            is Failure.None -> {}
            else -> {
                notify(message ?: getString(R.string.failure_server_error))
            }
        }
    }

    private fun showNoDataText() {
        binding.tvEmpty.visible()
    }


    private fun hideNoDataText() {
        binding.tvEmpty.invisible()
    }

    private fun showProgressBar() {
        binding.progressbar.visible()
    }

    private fun hideProgressBar() {
        binding.progressbar.invisible()
    }

    private fun hideRecyclerView() {
        binding.rvProducts.invisible()
    }

    private fun showRecyclerView() {
        binding.rvProducts.visible()

    }

    private fun notify(message: String) =
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()


}