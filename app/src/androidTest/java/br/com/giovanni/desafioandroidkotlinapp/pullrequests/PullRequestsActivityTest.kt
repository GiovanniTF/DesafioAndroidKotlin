package br.com.giovanni.desafioandroidkotlinapp.pullrequests

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import br.com.giovanni.desafioandroidkotlinapp.MainActivity
import br.com.giovanni.desafioandroidkotlinapp.MockWebServerRule
import br.com.giovanni.desafioandroidkotlinapp.R
import br.com.giovanni.desafioandroidkotlinapp.repos.command
import br.com.giovanni.desafioandroidkotlinapp.retryer
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PullRequestsActivityTest {

    @get:Rule
    val activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(
        MainActivity::class.java,
        false,
        false

    )

    @get:Rule
    val mockWebServerRule = MockWebServerRule()

    private val robot = PullRequestRobot(activityRule, mockWebServerRule.mockWebServer)

    @Test
    fun showPullRequest(){
        robot.command {
            setupPullRequests_Response()
            launchPullRequestsActivity()
            checkPullRequestsDisplayed("修复打错的代码")
        }
    }

    @Test
    fun showPullRequestScroll(){
        robot.command {
            setupPullRequests_Response()
            launchPullRequestsActivity()
            performScroll(2)
            retryer { checkPullRequestsDisplayed("Serialization bugs") }
        }
    }

    @Test
    fun errorPullRequest(){
        robot.command {
            setupPullRequests_Response()
            launchPullRequestsActivity()
            setupPullRequests_ErrorResponse()
            retryer { checkPullRequestsDisplayed(R.string.error_api_response) }
        }
    }

    @Test
    fun errorTimeOutPullRequest(){
        robot.command {
            setupPullRequests_Response()
            launchPullRequestsActivity()
            setupPullRequests_TimeOutResponse()
            retryer { checkPullRequestsDisplayed(R.string.error_conection) }
        }
    }
}