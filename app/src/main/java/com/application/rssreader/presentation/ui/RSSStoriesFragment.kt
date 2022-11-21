package com.application.rssreader.presentation.ui

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.application.rssreader.R
import com.application.rssreader.core.extension.invisible
import com.application.rssreader.core.extension.visible
import com.application.rssreader.databinding.FragmentRssStoriesBinding
import com.application.rssreader.presentation.viewmodel.RSSFeedsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RSSStoriesFragment : Fragment() {

    private lateinit var binding: FragmentRssStoriesBinding

    private val viewModel: RSSFeedsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rss_stories, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch(Dispatchers.Main) {
            binding.wvStories.apply {
                webViewClient = object : WebViewClient() {

                    override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                        super.onPageStarted(view, url, favicon)
                        view.invisible()
                        binding.progressBar.visible()
                    }

                    override fun onPageFinished(view: WebView, url: String) {
                        super.onPageFinished(view, url)
                        view.visible()
                        binding.progressBar.invisible()
                    }

                    override fun onReceivedError(
                        view: WebView?, request: WebResourceRequest?, error: WebResourceError?,
                    ) {
                        super.onReceivedError(view, request, error)
                        view?.visible()
                        binding.progressBar.invisible()

                    }
                }
                settings.javaScriptEnabled = true
                loadUrl(viewModel.getItem.value?.link ?: "")
            }
        }


    }
}