package com.rebtel.country.ui.splash


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.rebtel.country.R
import com.rebtel.country.ui.country.list.CountryListActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class CountryListUITest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(CountryListActivity::class.java)

    @Test
    fun testCountryListViewAvailable(){
        val recyclerView = onView(
            allOf(
                withId(R.id.rv_country),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.view.ViewGroup::class.java),
                        1
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        recyclerView.check(matches(isDisplayed()))
    }

    @Test
    fun testToolBarAndItsValuesAvailable() {

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

        val textView2 = onView(
            allOf(
                withId(R.id.tv_top_tool_bar), withText("Countries"),
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
        textView2.check(matches(isDisplayed()))

        val textView3 = onView(
            allOf(
                withId(R.id.tv_top_tool_bar), withText("Countries"),
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
        textView3.check(matches(withText("Countries")))
    }

    @Test
    fun testImageFlagIsAvailable() {
        val imageView = onView(
            allOf(
                withId(R.id.iv_item_flag),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        imageView.check(matches(isDisplayed()))
    }

    @Test
    fun testCountryNameWithPosition() {
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
