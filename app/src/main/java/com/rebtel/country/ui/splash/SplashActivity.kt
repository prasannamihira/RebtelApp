package com.rebtel.country.ui.splash

import android.os.Bundle
import android.os.Handler
import com.rebtel.country.R
import com.rebtel.country.base.BaseActivity

/**
 * SplashActivity
 */
class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        goToCountryListActivity()
    }

    // start activity - country list
    // with one sec delay
    private fun goToCountryListActivity() {
        Handler().postDelayed({
            startCountryListActivity()

        }, 1000)
    }
}
