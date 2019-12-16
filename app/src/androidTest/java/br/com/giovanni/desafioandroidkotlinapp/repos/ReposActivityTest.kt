package br.com.giovanni.desafioandroidkotlinapp.repos

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import br.com.giovanni.desafioandroidkotlinapp.MainActivity
import br.com.giovanni.desafioandroidkotlinapp.MockWebServerRule
import br.com.giovanni.desafioandroidkotlinapp.R
import br.com.giovanni.desafioandroidkotlinapp.retryer
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ReposActivityTest {
    @get:Rule
    val activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(
        MainActivity::class.java,
        false,
        false
    )

    @get:Rule
    val mockWebServerRule = MockWebServerRule()

    private val robot = ReposRobot(activityRule, mockWebServerRule.mockWebServer)

    @Test
    fun showsRepos() {
        robot.command {
            setupReposResponse()
            launchReposActivity()
            retryer {
                performScroll(10)
                checkReposDisplayed("retrofit") }
        }
    }

    @Test
    fun errorRepos() {
        robot.command {
            setupReposResponse()
            launchReposActivity()
            setupReposErrorResponse()
            retryer { checkReposDisplayed(R.string.error_api_response) }
        }
    }

    @Test
    fun errorTimeOutRepos() {
        robot.command {
            setupReposResponse()
            launchReposActivity()
            setupReposTimeOutResponse()
            retryer { checkReposDisplayed(R.string.error_conection) }
        }
    }
}
