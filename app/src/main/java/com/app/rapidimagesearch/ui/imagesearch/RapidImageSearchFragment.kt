package com.app.rapidimagesearch.ui.imagesearch

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.rapidimagesearch.MainActivityDelegate
import com.app.rapidimagesearch.R
import com.app.rapidimagesearch.network.ImageData
import com.app.rapidimagesearch.ui.components.OnLoadMoreListener
import com.app.rapidimagesearch.ui.components.RecyclerViewLoadMoreScroll
import com.app.rapidimagesearch.utilities.initToolbar
import kotlinx.android.synthetic.main.fragment_rapid_image_search.*

class RapidImageSearchFragment : Fragment() {
    private lateinit var adapter: RapidImageSearchAdapter
    private lateinit var scrollListener: RecyclerViewLoadMoreScroll
    private lateinit var mLayoutManager: LinearLayoutManager
    private val viewModel: RapidImageSearchViewModel by viewModels()
    private lateinit var mainActivityDelegate: MainActivityDelegate

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
        adapter= RapidImageSearchAdapter(mutableListOf())
        adapter.notifyDataSetChanged()
        rvImages.adapter=adapter
        setRVLayoutManager()
        setRVScrollListener()
    }


    private fun hideKeyboard(view: View) {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }



    private fun setRVLayoutManager() {
        mLayoutManager = LinearLayoutManager(requireContext())
        rvImages.layoutManager = mLayoutManager
        rvImages.setHasFixedSize(true)
    }

    private  fun setRVScrollListener() {
        mLayoutManager = LinearLayoutManager(requireContext())
        scrollListener = RecyclerViewLoadMoreScroll(mLayoutManager as LinearLayoutManager)
        scrollListener.setOnLoadMoreListener(object : OnLoadMoreListener {
            override fun onLoadMore() {
                loadMoreData()
            }
        })
        rvImages.addOnScrollListener(scrollListener)
    }
    private fun loadMoreData() {
        //Add the Loading View
        adapter.addLoadingView()

//        Handler().postDelayed({
//            for (i in start..end) {
//                //Get data and add them to loadMoreItemsCells ArrayList
//                loadMoreItemsCells.add("Item $i")
//            }
//            //Remove the Loading View
//            adapter.removeLoadingView()
//            //We adding the data to our main ArrayList
//            adapter.addData(loadMoreItemsCells)
//            //Change the boolean isLoading to false
//            scrollListener.setLoaded()
//            //Update the recyclerView in the main thread
//            items_linear_rv.post {
//                adapterLinear.notifyDataSetChanged()
//            }
//        }, 3000)

    }

}