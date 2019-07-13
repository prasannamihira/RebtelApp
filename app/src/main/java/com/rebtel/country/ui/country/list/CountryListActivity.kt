package com.rebtel.country.ui.country.list

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.rebtel.country.R
import com.rebtel.country.app.App
import com.rebtel.country.base.BaseActivity
import com.rebtel.country.data.model.CountryResponseDataModel
import com.rebtel.country.data.model.ListRow
import com.rebtel.country.databinding.ActivityCountryListBinding
import com.rebtel.country.ui.country.detail.CountryDetailActivity
import com.rebtel.country.ui.custom.RecyclerSectionItemDecoration
import com.rebtel.country.util.Config
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * CountryListActivity for list countries with alphabetical scroll view
 */
class CountryListActivity : BaseActivity(), AlphabeticalAdapter.OnItemClickListener {

    @Inject
    lateinit var countryListVM: CountryListVM

    var mBinding: ActivityCountryListBinding? = null

    override fun onItemClicked(listRow: ListRow) {
        var intent = Intent(this, CountryDetailActivity::class.java)

        CountryDetailActivity.itemCountry = listRow.item
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_country_list)

        mBinding?.topToolBar?.tvTopToolBar?.text = resources.getString(R.string.title_countries)
        mBinding?.topToolBar?.ivTopToolBar?.visibility = View.INVISIBLE

        App.appComponent.inject(this)

        fetchAllCountries()
    }

    /**
     * fetch all the countries
     */
    private fun fetchAllCountries() {

        var dataCountryList: List<CountryResponseDataModel>

        // subscribe country list
        subscription.add(
            countryListVM.fetchAllCountries()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { showProgress(mBinding!!.pbCountryList, true) }
                .doOnTerminate { showProgress(mBinding!!.pbCountryList, false) }
                .subscribe(
                    {
                        if (it != null && countryListVM.dataRetrieved) {

                            Timber.d("Maintenance requests data received")
                            dataCountryList = countryListVM.dataCountryList!!

                            // check the country list data available
                            if (countryListVM.dataRetrieved && dataCountryList.isNotEmpty()) {

                                // sort country list by using alpha code
                                var list = dataCountryList.sortedWith(compareBy({ it.alpha2Code }, { it.name?.trim() }))
                                sortByAlphabetic(list)

                                var adapter = CountryListAdapter(
                                    list
                                )
                                mBinding?.rvCountry?.adapter = adapter
                                mBinding?.rvCountry?.layoutManager = LinearLayoutManager(this)
                                mBinding?.rvCountry?.adapter?.notifyDataSetChanged()

                                setDividerItemDecoration(mBinding?.rvCountry)

                            } else {
                                showProgress(mBinding!!.pbCountryList, false)
                                // no data retrieved, then display empty data message
                                this?.runOnUiThread {
                                    var builder = AlertDialog.Builder(this)
                                        .setMessage(resources.getString(R.string.error_empty_countries))
                                        .setPositiveButton("OK") { dialogInterface, i -> dialogInterface.dismiss() }
                                    var dialog: AlertDialog = builder.create()
                                    dialog.show()
                                }
                            }
                        } else {
                            showProgress(mBinding!!.pbCountryList, false)

                            // server error, then display error message
                            this?.runOnUiThread {
                                var builder = AlertDialog.Builder(this)
                                    .setMessage(resources.getString(R.string.error_problem_request))
                                    .setPositiveButton("OK") { dialogInterface, i -> dialogInterface.dismiss() }
                                var dialog: AlertDialog = builder.create()
                                dialog.show()
                            }
                        }
                    },
                    {
                        handleNetworkError(it)
                    }
                )
        )
    }

    private val listRow = ArrayList<ListRow>()
    /**
     * sort the list of countries by first letter of country code
     * @param list // country list
     */
    private fun sortByAlphabetic(list: List<CountryResponseDataModel>) {
        var lastKey = ""
        subscription.add(
            Observable.fromIterable(list)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .groupBy { item ->
                    val notNullName = item.alpha3Code
                    val chr = notNullName!![0]

                    if (chr.isDigit()) {
                        '#'
                    } else {
                        notNullName!![0].toUpperCase()
                    }
                }
                .doOnSubscribe { showProgress(mBinding!!.pbCountryList, true) }
                .doOnTerminate { showProgress(mBinding!!.pbCountryList, false) }
                .doOnNext { group ->
                    println(group.key!!.plus(" "))
                    group.subscribe { element ->
                        println(group.key!!.plus(" ") + if (!element.alpha3Code.isNullOrEmpty()) element.alpha3Code else element.alpha3Code)
                        val row = ListRow(false, group.key!!.toChar(), element)
                        if (!lastKey.equals(group.key.toString())) {
                            row.isHeader = true
                        }
                        listRow.add(row)
                        lastKey = group.key.toString()
                    }
                }.doOnComplete {
                    // set alphabetical adapter on country list
                    val adapter = AlphabeticalAdapter(listRow)
                    adapter.onItemClickListener = this@CountryListActivity
                    mBinding?.rvCountry?.adapter = adapter

                    val sectionItemDecoration = RecyclerSectionItemDecoration(
                        resources.getDimensionPixelSize(R.dimen.padding_30dp),
                        true,
                        getSectionCallback(listRow)
                    )
                    mBinding?.rvCountry?.addItemDecoration(sectionItemDecoration)

                }
                .subscribe({

                }, {
                    it.printStackTrace()
                    handleNetworkError(it)
                })
        )
    }
}
