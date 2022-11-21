package com.application.rssreader

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.adevinta.android.barista.assertion.BaristaRecyclerViewAssertions.assertRecyclerViewItemCount
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.application.rssreader.presentation.di.idlingResource
import com.application.rssreader.presentation.ui.MainActivity
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test


class RSSFeedsFeature : BaseUITest() {

    val mActivityRule = ActivityScenarioRule(MainActivity::class.java)
        @Rule get

    @Test
    fun displayScreenTitle() {
        assertDisplayed("RSS Feeds")
    }

    @Test
    fun displaysLoaderWhileFetchingTheLists() {
        IdlingRegistry.getInstance().unregister(idlingResource)
    }

    @Test
    fun displaysListOfRSSFeeds() {
        assertRecyclerViewItemCount(R.id.rv_rss_feeds, 20)

        onView(allOf(withId(R.id.tv_title),
            isDescendantOfA(nthChildOf(withId(R.id.rv_rss_feeds), 0))))
            .check(matches(withText("Maestrov zadnji SP: Ništa nije veće od igranja za Hrvatsku!")))
            .check(matches(isDisplayed()))

        onView(allOf(withId(R.id.tv_description),
            isDescendantOfA(nthChildOf(withId(R.id.rv_rss_feeds), 0))))
            .check(matches(withText("Najbolji igrač u povijesti \"vatrenih\" sad u Kataru čeka svoje osmo veliko natjecanje. Iza njega su četiri europska i tri svjetska prvenstva (četvrto mu stiže u Kataru), a Luka s reprezentacijom pamti i dobre i loše trenutke")))
            .check(matches(isDisplayed()))

    }
}