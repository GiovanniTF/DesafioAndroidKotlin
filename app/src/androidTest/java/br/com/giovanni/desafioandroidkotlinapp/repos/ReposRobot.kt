package br.com.giovanni.desafioandroidkotlinapp.repos

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import br.com.giovanni.desafioandroidkotlinapp.MainActivity
import br.com.giovanni.desafioandroidkotlinapp.R
import okhttp3.mockwebserver.MockWebServer

fun ReposRobot.command(func:ReposRobot.() -> Unit) = this.apply { func() }

class ReposRobot(
    private val activityRule: ActivityTestRule<MainActivity>,
    private val mockWebServer: MockWebServer
) {

    fun setupReposResponse(){
        mockWebServer.dispatcher = ReposDispatcher()
    }

    fun setupReposErrorResponse(){
        mockWebServer.dispatcher = ReposDispatcher(isError = true)
    }

    fun setupReposTimeOutResponse(){
        mockWebServer.dispatcher = ReposDispatcher(isTimeOut = true)
    }

    fun launchReposActivity(){
        activityRule.launchActivity(Intent())
    }

    fun performScroll(position: Int){
        onView(withId(R.id.recyclerViewReposId))
            .perform(scrollToPosition<RecyclerView.ViewHolder>(position))
    }

    fun checkReposDisplayed(title: Int){
        onView(withText(title))
            .check(matches(isDisplayed()))
    }

    fun checkReposDisplayed(title: String){
        onView(withText(title))
            .check(matches(isDisplayed()))
    }

}