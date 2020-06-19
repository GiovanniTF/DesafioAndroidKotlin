package br.com.giovanni.desafioandroidkotlinapp.pullrequests

import br.com.giovanni.desafioandroidkotlinapp.InstantExecutionRule
import br.com.giovanni.desafioandroidkotlinapp.api.Head
import br.com.giovanni.desafioandroidkotlinapp.api.PullRequestPosts
import br.com.giovanni.desafioandroidkotlinapp.api.Repo
import br.com.giovanni.desafioandroidkotlinapp.api.User
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import retrofit2.Response
import java.io.IOException

class PullRequestsViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val instantExecutionRule = InstantExecutionRule()
    private val interactor = mockk<GetPullRequestInteractor>()
    private val userMock = "CyC2018"
    private val repositoryMock = "CS-Notes"
    private val viewModel = PullRequestViewModel(interactor)

    private fun createApiResponse() =
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


    @Test
    fun callsPullRequestsState() {
        val apiResponse = createApiResponse()

        coEvery { interactor.execute(userMock, repositoryMock) } returns Response.success(
            apiResponse
        )

        viewModel.getPullRequest(userMock, repositoryMock)

        assertEquals(
            viewModel.getPullRequestViewState().value,
            PullRequestViewState.Request(apiResponse)
        )
    }

    @Test
    fun callsPullRequests_EmptyState() {
        coEvery { interactor.execute(userMock, repositoryMock) } returns Response.success(
            emptyList()
        )

        viewModel.getPullRequest(userMock,repositoryMock)

        assertEquals(
            viewModel.getPullRequestViewState().value,
            PullRequestViewState.Empty
        )
    }

    @Test
    fun callsPullRequests_ErrorState() {
        val errorbody = "".toResponseBody("aplication/json".toMediaType())

        coEvery { interactor.execute(userMock, repositoryMock) } returns Response.error(
            400, errorbody
        )

        viewModel.getPullRequest(userMock,repositoryMock)

        assertEquals(
            viewModel.getPullRequestViewState().value,
            PullRequestViewState.Error
        )
    }

    @Test
    fun callsPullRequests_TimeOutSate(){
        coEvery { interactor.execute(userMock,repositoryMock) } throws IOException()

        viewModel.getPullRequest(userMock,repositoryMock)

        assertEquals(
            viewModel.getPullRequestViewState().value,
            PullRequestViewState.ErrorTimeOut
        )
    }
}