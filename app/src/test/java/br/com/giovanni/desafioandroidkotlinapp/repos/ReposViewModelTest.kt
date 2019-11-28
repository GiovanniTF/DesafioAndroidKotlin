package br.com.giovanni.desafioandroidkotlinapp.repos

import br.com.giovanni.desafioandroidkotlinapp.InstantExecutionRule
import br.com.giovanni.desafioandroidkotlinapp.api.ApiResponse
import br.com.giovanni.desafioandroidkotlinapp.api.Owner
import br.com.giovanni.desafioandroidkotlinapp.api.Posts
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

class ReposViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val instantExecutionRule = InstantExecutionRule()

    private val interactor = mockk<GetReposInteractor>()

    private val page: Int = 1

    private fun createApiResponse() = ApiResponse(
        listOf(
            Posts(
                12, "Teste Mock Post API",
                Owner("Login String", "Avatar URL"),
                "Nome teste", "Descrição Teste Mock",
                128, 2280
            )
        )
    )

    @Test
    fun callsReposState() {
        val apiResponse = createApiResponse()

        coEvery { interactor.execute(page) } returns Response.success(apiResponse)

        val viewModel = ReposViewModel(interactor)

        assertEquals(
            viewModel.getPostViewState().value,
            ReposViewState.Repos(apiResponse.response)
        )
    }

    @Test
    fun callsReposEmptyState() {
        coEvery { interactor.execute(page) } returns Response.success(ApiResponse(emptyList()))

        val viewModel = ReposViewModel(interactor)

        assertEquals(
            viewModel.getPostViewState().value,
            ReposViewState.Empty
        )
    }

    @Test
    fun callsReposErrorState() {
        val errorBody = "".toResponseBody("application/json".toMediaType())
        coEvery { interactor.execute(page) } returns Response.error(400, errorBody)

        val viewModel = ReposViewModel(interactor)

        assertEquals(
            viewModel.getPostViewState().value,
            ReposViewState.Error
        )
    }

    @Test
    fun callsReposErrorTimeOutState() {
        coEvery { interactor.execute(page) } throws IOException()

        val viewModel = ReposViewModel(interactor)

        assertEquals(
            viewModel.getPostViewState().value,
            ReposViewState.ErrorTimeOut
        )
    }
}