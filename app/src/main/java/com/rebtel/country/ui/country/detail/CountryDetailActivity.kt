package com.rebtel.country.ui.country.detail

import android.graphics.Outline
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewOutlineProvider
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.rebtel.country.R
import com.rebtel.country.base.BaseActivity
import com.rebtel.country.data.model.CountryResponseDataModel
import com.rebtel.country.databinding.ActivityCountryDetailBinding
import com.rebtel.country.util.svg.SvgUtils

/**
 * CountryDetailActivity shows the country flag and related information
 */
class CountryDetailActivity : BaseActivity(), View.OnClickListener {

    override fun onClick(v: View?) {

        when (v?.id) {
            // toolbar back button click
            R.id.iv_top_tool_bar -> {
                finish()
            }
        }
    }

    var mBinding: ActivityCountryDetailBinding? = null

    companion object {
        lateinit var itemCountry: CountryResponseDataModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // data binding with activity layout
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_country_detail)

        mBinding?.topToolBar?.tvTopToolBar?.text = itemCountry.name
        mBinding?.topToolBar?.ivTopToolBar?.setOnClickListener(this)

        updateUI()
    }

    /**
     * setup ui controls with values
     */
    private fun updateUI() {
        mBinding?.tvCountryCapital?.text = itemCountry.capital
        mBinding?.tvCountryNativeName?.text = itemCountry.nativeName
        mBinding?.tvCountryRegion?.text = itemCountry.region
        mBinding?.tvCountrySubRegion?.text = itemCountry.subregion
        mBinding?.tvCountryPopulation?.text = itemCountry.population.toString()

        var currency = "Code: " + itemCountry.currencies?.get(0)?.code.toString() +
                "\n Name: " + itemCountry.currencies?.get(0)?.name.toString() +
                "\n Symbol: " + itemCountry.currencies?.get(0)?.symbol.toString()
        mBinding?.tvCountryCurrency?.text = currency
        mBinding?.tvCountryTimezone?.text = itemCountry.timezones?.get(0)

        // bind svg image from url with layout imageview
        SvgUtils.fetchSvg(this, itemCountry.flag, mBinding?.ivCountryFlag)

        // set image corner radius
        val curveRadius = 18F

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            mBinding?.ivCountryFlag?.outlineProvider = object : ViewOutlineProvider() {

                @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
                override fun getOutline(view: View?, outline: Outline?) {
                    outline?.setRoundRect(0, 0, view!!.width, view?.height, curveRadius)
                }
            }
            mBinding?.ivCountryFlag?.clipToOutline = true
        }
    }
}
