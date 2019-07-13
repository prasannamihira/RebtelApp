package com.rebtel.country.base

import android.content.Intent
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
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

open class BaseActivity: AppCompatActivity() {

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

    fun handleNetworkError(e: Throwable) {
        e.printStackTrace()
        if (e is ConnectException) {
            showToast("Please connect to Internet")
        } else {
            showToast("Please try again")
        }
    }

    fun getSectionCallback(people: List<ListRow>): RecyclerSectionItemDecoration.SectionCallback {
        return object : RecyclerSectionItemDecoration.SectionCallback {
            override fun isSection(position: Int): Boolean {
                return people[position].isHeader
            }

            override fun getSectionHeader(position: Int): CharSequence {
                return people[position].char.toString()
            }
        }
    }

    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()

        // clear network subscription
        subscription.clear()
    }

    // navigate to country list screen
    fun startCountryListActivity() {
        val intent = Intent(this, CountryListActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

    // navigate to country details screen
    fun startCountryDetailsActivity() {
        val intent = Intent(this, CountryDetailActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

    /**
     * set divider item decoration for given fast scroll recycler view
     */
    fun setDividerItemDecoration(view:FastScrollRecyclerView?) {
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

}