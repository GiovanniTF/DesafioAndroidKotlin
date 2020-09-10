package br.com.giovanni.desafioandroidkotlinapp.pullrequests

import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.giovanni.desafioandroidkotlinapp.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PullRequestsFragmentTest {

    @get:Rule
    val mockWebServerRule = MockWebServerTest()
    private val robot = PullRequestRobot( mockWebServerRule.mockWebServer)


    @Test
    fun showPullRequestScroll(){
        robot.command {
            setupPullRequests_Response()
            launchPullRequestsActivity()
            performScroll(3)
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