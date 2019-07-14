package com.rebtel.country.ui.splash


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.rebtel.country.R
import com.rebtel.country.ui.country.detail.CountryDetailActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class CountryDetailViewUITest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(CountryDetailActivity::class.java)


    @Test
    fun testCountryListViewIsAvailable() {
        val textView = onView(
            allOf(
                withId(R.id.txtName), withText("Andorra"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView.check(matches(isDisplayed()))
    }

    @Test
    fun testCountryItemClick() {
        val linearLayout2 = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.rv_country),
                        childAtPosition(
                            withClassName(`is`("android.widget.RelativeLayout")),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        linearLayout2.perform(click())
    }

    @Test
    fun testToolBarIsAvailable() {
        val viewGroup = onView(
            allOf(
                withId(R.id.tool_bar),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.top_tool_bar),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        viewGroup.check(matches(isDisplayed()))
    }

    @Test
    fun testToolBarTextTitleIsCorrect() {

        val textView = onView(
            allOf(
                withId(R.id.tv_top_tool_bar), withText("Andorra"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tool_bar),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView.check(matches(isDisplayed()))
    }

    @Test
    fun testTopToolBarTitleIsCorrect() {
        val textView2 = onView(
            allOf(
                withId(R.id.tv_top_tool_bar), withText("Andorra"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tool_bar),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("Andorra")))
    }

    @Test
    fun testToolBarBackIconIsDisplayed() {
        val imageView = onView(
            allOf(
                withId(R.id.iv_top_tool_bar),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tool_bar),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        imageView.check(matches(isDisplayed()))
    }

    @Test
    fun testFlagIsDisplaying() {
        val imageView2 = onView(
            allOf(
                withId(R.id.iv_country_flag),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        imageView2.check(matches(isDisplayed()))
    }

    @Test
    fun testTextFields() {
        val textView3 = onView(
            allOf(
                withText("Region"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView3.check(matches(isDisplayed()))

        val textView4 = onView(
            allOf(
                withText("Region"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView4.check(matches(withText("Region")))

        val textView5 = onView(
            allOf(
                withText("Sub Region"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                        2
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView5.check(matches(isDisplayed()))

        val textView6 = onView(
            allOf(
                withText("Sub Region"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                        2
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView6.check(matches(withText("Sub Region")))

        val textView7 = onView(
            allOf(
                withText("Capital"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                        4
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView7.check(matches(isDisplayed()))

        val textView8 = onView(
            allOf(
                withText("Capital"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                        4
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView8.check(matches(withText("Capital")))

        val textView9 = onView(
            allOf(
                withText("Population"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                        6
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView9.check(matches(isDisplayed()))

        val textView10 = onView(
            allOf(
                withText("Population"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                        6
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView10.check(matches(withText("Population")))

        val textView11 = onView(
            allOf(
                withText("Native Name"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                        8
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView11.check(matches(isDisplayed()))

        val textView12 = onView(
            allOf(
                withText("Native Name"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                        8
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView12.check(matches(withText("Native Name")))

        val textView13 = onView(
            allOf(
                withText("Currency"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                        10
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView13.check(matches(isDisplayed()))

        val textView14 = onView(
            allOf(
                withText("Currency"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                        10
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView14.check(matches(withText("Currency")))

        val textView15 = onView(
            allOf(
                withText("Time Zone"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                        12
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView15.check(matches(isDisplayed()))

        val textView16 = onView(
            allOf(
                withText("Time Zone"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                        12
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView16.check(matches(withText("Time Zone")))

        val textView17 = onView(
            allOf(
                withId(R.id.tv_country_region), withText("Europe"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView17.check(matches(isDisplayed()))

        val textView18 = onView(
            allOf(
                withId(R.id.tv_country_sub_region), withText("Southern Europe"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                        2
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView18.check(matches(isDisplayed()))

        val textView19 = onView(
            allOf(
                withId(R.id.tv_country_capital), withText("Andorra la Vella"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                        4
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView19.check(matches(isDisplayed()))

        val textView20 = onView(
            allOf(
                withId(R.id.tv_country_population), withText("78014"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                        6
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView20.check(matches(isDisplayed()))

        val textView21 = onView(
            allOf(
                withId(R.id.tv_country_native_name), withText("Andorra"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                        8
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView21.check(matches(isDisplayed()))

        val textView22 = onView(
            allOf(
                withId(R.id.tv_country_currency), withText("Code: EUR\n Name: Euro\n Symbol: €"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                        10
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView22.check(matches(isDisplayed()))

        val textView23 = onView(
            allOf(
                withId(R.id.tv_country_timezone), withText("UTC+01:00"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                        12
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView23.check(matches(isDisplayed()))

        val textView24 = onView(
            allOf(
                withId(R.id.tv_country_timezone), withText("UTC+01:00"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                        12
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView24.check(matches(isDisplayed()))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
