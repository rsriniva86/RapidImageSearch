package com.app.rapidimagesearch.ui.imagesearch

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.app.rapidimagesearch.MainActivityDelegate
import com.app.rapidimagesearch.R
import com.app.rapidimagesearch.network.ImageData
import com.app.rapidimagesearch.ui.image.ImageViewModel
import com.app.rapidimagesearch.ui.web.WebPageViewModel
import com.app.rapidimagesearch.utilities.initToolbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_rapid_image_search.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RapidImageSearchFragment : Fragment(), RapidImageSearchAdapter.OnSearchImageItemClickListener {

    private val viewModel: RapidImageSearchViewModel by viewModels()
    private lateinit var mainActivityDelegate: MainActivityDelegate
    private val imageAdapter = RapidImageSearchAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            mainActivityDelegate = context as MainActivityDelegate
        } catch (e: ClassCastException) {
            throw ClassCastException()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_rapid_image_search, container, false)
    }

    @Suppress("DEPRECATION")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initToolbar(toolbar = toolbar, R.string.app_name, false)
        mainActivityDelegate.setupNavDrawer(toolbar)
        mainActivityDelegate.enableNavDrawer(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        imageAdapter.setOnItemClickListener(this)
    }

    override fun onItemClick(imageData: ImageData) {
        imageData.let {
            findNavController().navigate(
                R.id.a_to_b,
                ImageViewModel.createArguments(imageData)
            )
        }
    }

    override fun onUrlClick(imageData: ImageData) {
        imageData.let {
            findNavController().navigate(
                R.id.a_to_c,
                WebPageViewModel.createArguments(imageData)
            )
        }
    }

    private fun setupViews() {
        //rvImages.adapter = imageAdapter
        val loaderStateAdapter = RapidImageLoadStateAdapter {
            imageAdapter.retry()
        }
        rvImages.adapter = imageAdapter.withLoadStateFooter(footer = loaderStateAdapter)
        tvSearch.setOnEditorActionListener { textView, actionId, _ ->

            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    hideKeyboard(textView)
                    getImageList(textView.text.toString())

                    true
                }
                EditorInfo.IME_ACTION_DONE -> {
                    getImageList(textView.text.toString())
                    true
                }
                else -> false
            }
        }

    }

    private fun hideKeyboard(view: View) {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun getImageList(input: String) {

        println("getImageList")
        lifecycleScope.launch {
            viewModel.getData(input)
                .collectLatest { pagingData ->
                    imageAdapter.submitData(pagingData)
                }
        }

        lifecycleScope.launch {
            imageAdapter.loadStateFlow.collectLatest { loadStates ->
                if(loadStates.refresh is LoadState.Loading){
                    progressBar?.visibility = View.VISIBLE
                }else{
                    progressBar?.visibility = View.GONE
                }
            }
        }
    }
}