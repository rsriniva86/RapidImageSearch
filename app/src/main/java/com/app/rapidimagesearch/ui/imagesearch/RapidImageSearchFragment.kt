package com.app.rapidimagesearch.ui.imagesearch

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.app.rapidimagesearch.MainActivityDelegate
import com.app.rapidimagesearch.R
import com.app.rapidimagesearch.utilities.initToolbar
import kotlinx.android.synthetic.main.fragment_rapid_image_search.*

class RapidImageSearchFragment : Fragment() {
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
    }


    private fun hideKeyboard(view: View) {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }


}