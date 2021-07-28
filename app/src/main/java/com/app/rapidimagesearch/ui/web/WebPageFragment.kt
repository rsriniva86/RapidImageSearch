package com.app.rapidimagesearch.ui.web

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.app.rapidimagesearch.R
import com.app.rapidimagesearch.utilities.initToolbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_web.*

@AndroidEntryPoint
class WebPageFragment : Fragment() {
    private val viewModel: WebPageViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_web, container, false)
        viewModel.loadArguments(arguments)
        return view
    }

    @Suppress("DEPRECATION")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initToolbar(toolbarWeb, R.string.web_page, true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        img_webview.webViewClient = WebViewClient()
        viewModel.getImageDetailData().observe(viewLifecycleOwner, { imageData ->
            val url = imageData?.webPageUrl
            url?.let {
                img_webview.loadUrl(url)
            }
        })
    }


}