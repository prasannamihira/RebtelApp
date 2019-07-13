package com.rebtel.country.base

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.l4digital.fastscroll.FastScrollRecyclerView
import com.rebtel.country.R
import com.rebtel.country.data.model.ListRow
import com.rebtel.country.ui.country.detail.CountryDetailActivity
import com.rebtel.country.ui.country.list.CountryListActivity
import com.rebtel.country.ui.custom.RecyclerSectionItemDecoration
import io.reactivex.disposables.CompositeDisposable
import java.net.ConnectException

open class BaseActivity : AppCompatActivity() {

    val subscription = CompositeDisposable()

    /**
     * progress bar
     * @param view:ProgressBar
     * @param show:Boolean
     */
    fun showProgress(view: ProgressBar, show: Boolean) {

        if (show)
            view.visibility = View.VISIBLE
        else
            view.visibility = View.INVISIBLE
    }

    /**
     * handle network error with toast message
     * @param e // exception throwable
     */

    fun handleNetworkError(e: Throwable) {
        e.printStackTrace()
        if (e is ConnectException) {
            showToast(resources.getString(R.string.error_connect_internet))
        } else {
            showToast(resources.getString(R.string.error_try_again))
        }
    }

    /**
     * call bak of section of list row
     * @param country
     */
    fun getSectionCallback(country: List<ListRow>): RecyclerSectionItemDecoration.SectionCallback {
        return object : RecyclerSectionItemDecoration.SectionCallback {
            override fun isSection(position: Int): Boolean {
                return country[position].isHeader
            }

            override fun getSectionHeader(position: Int): CharSequence {
                return country[position].char.toString()
            }
        }
    }

    /**
     * show toast message with given text
     * @param msg // message to show
     */
    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()

        // clear network subscription
        subscription.clear()
    }

    /**
     * navigate to country list screen
     */
    fun startCountryListActivity() {
        val intent = Intent(this, CountryListActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

    /**
     * navigate to country details screen
     */
    fun startCountryDetailsActivity() {
        val intent = Intent(this, CountryDetailActivity::class.java)
        startActivity(intent)
    }

    /**
     * set divider item decoration for given fast scroll recycler view
     * @param view // scroll view
     */
    fun setDividerItemDecoration(view: FastScrollRecyclerView?) {
        var dividerItemDecoration = DividerItemDecoration(
            view?.context,
            LinearLayoutManager(this).orientation
        )
        dividerItemDecoration.setDrawable(
            view?.context?.resources!!.getDrawable(
                R.drawable.divider
            )
        )
        view?.addItemDecoration(dividerItemDecoration)
    }

    /**
     * get string value from string resource id
     *
     * @param resId
     * @return
     */
    fun getStringFromResourceId(context: Context, resId: Int): String {
        return context.resources.getString(resId)
    }


}