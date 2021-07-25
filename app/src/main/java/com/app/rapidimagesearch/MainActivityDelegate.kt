package com.app.rapidimagesearch

interface MainActivityDelegate {
    fun setupNavDrawer(toolbar: androidx.appcompat.widget.Toolbar)

    fun enableNavDrawer(enable: Boolean)
}