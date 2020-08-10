package br.com.giovanni.desafioandroidkotlinapp.pullrequests

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import br.com.giovanni.desafioandroidkotlinapp.MainActivity
import br.com.giovanni.desafioandroidkotlinapp.R
import okhttp3.mockwebserver.MockWebServer

fun PullRequestRobot.command(func: PullRequestRobot.() -> Unit) = this.apply { func() }

class PullRequestRobot(
    private val activityRule: ActivityTestRule<MainActivity>,
    private val mockWebServer: MockWebServer
) {

//    fun setAssets():String{
//        val fileName = "pullRequestJsonAsset.txt"
//        return activityRule.activity.assets.open(fileName).bufferedReader().use {
//            it.readText()
//        }
//    }

    fun setupPullRequests_Response() {
        mockWebServer.dispatcher = PullRequestsDispatcher()
    }

    fun setupPullRequests_ErrorResponse() {
        mockWebServer.dispatcher = PullRequestsDispatcher(isError = true)
    }

    fun setupPullRequests_TimeOutResponse() {
        mockWebServer.dispatcher = PullRequestsDispatcher(isTimeOut = true)
    }

    fun launchPullRequestsActivity() {
        activityRule.launchActivity(Intent())
    }

    fun performScroll(position: Int){
        onView(withId(R.id.recyclerViewDetailId))
            .perform(scrollToPosition<RecyclerView.ViewHolder>(position))
    }

    fun checkPullRequestsDisplayed(title: Int){
        onView(withText(title))
            .check(matches(isDisplayed()))
    }

    fun checkPullRequestsDisplayed(title: String){
        onView(withText(title))
            .check(matches(isDisplayed()))
    }

}