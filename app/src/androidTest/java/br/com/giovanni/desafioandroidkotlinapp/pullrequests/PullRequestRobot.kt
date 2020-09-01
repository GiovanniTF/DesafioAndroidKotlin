package br.com.giovanni.desafioandroidkotlinapp.pullrequests

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import br.com.giovanni.desafioandroidkotlinapp.R
import okhttp3.mockwebserver.MockWebServer

fun PullRequestRobot.command(func: PullRequestRobot.() -> Unit) = this.apply { func() }

class PullRequestRobot(
    private val mockWebServer: MockWebServer
) {

    lateinit var scenario: FragmentScenario<PullRequestFragment>

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
        val bundle = PullRequestFragmentArgs("CyC2018", "CS-Notes").toBundle()
        scenario = launchFragmentInContainer<PullRequestFragment>(bundle)
    }

    fun performScroll(position: Int) {
            onView(withId(R.id.recyclerViewDetailId))
                .perform(scrollToPosition<RecyclerView.ViewHolder>(position))
    }

    fun checkPullRequestsDisplayed(title: Int) {
            onView(withText(title))
                .check(matches(isDisplayed()))
    }

    fun checkPullRequestsDisplayed(title: String) {
            onView(withText(title))
                .check(matches(isDisplayed()))
    }

}