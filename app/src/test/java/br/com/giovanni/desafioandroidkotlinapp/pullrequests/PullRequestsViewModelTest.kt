package br.com.giovanni.desafioandroidkotlinapp.pullrequests

import br.com.giovanni.desafioandroidkotlinapp.InstantExecutionRule
import br.com.giovanni.desafioandroidkotlinapp.api.*
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class PullRequestsViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val instantExecutionRule = InstantExecutionRule()

    private val interactor = mockk<GetPullRequestInteractor>()

    private val page: Int = 1

    private fun createApiResponse() = ApiResponse(
        listOf(
            PullRequestPosts(
                4,
                "Title PullRequest API", User(
                    "Image User Avatar",
                    "Name Login"
                ),
                Head(
                    Repo(
                        "Descrição Pull Request",
                        "Fullname user pullrequest"
                    )
                )
            )
        )
    )

    @Test
    fun callsPullRequestState() {
        val apiResponse = createApiResponse()

        coEvery { interactor.execute("CyC2018", "CS-Notes") } returns Response.success()
    }
}