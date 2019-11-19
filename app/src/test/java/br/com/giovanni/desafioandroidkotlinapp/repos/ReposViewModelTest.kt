package br.com.giovanni.desafioandroidkotlinapp.repos

import br.com.giovanni.desafioandroidkotlinapp.InstantExecutionRule
import br.com.giovanni.desafioandroidkotlinapp.api.ApiResponse
import br.com.giovanni.desafioandroidkotlinapp.api.Owner
import br.com.giovanni.desafioandroidkotlinapp.api.Posts
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class ReposViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val instantExecutionRule = InstantExecutionRule()

    private val interactor = mockk<GetReposInteractor>()

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

        val mockResponse = mockk<Response<ApiResponse<Posts>>>()

        every { mockResponse.isSuccessful } returns true
        every { mockResponse.body() } returns apiResponse
        coEvery { interactor.execute() } returns mockResponse

        val viewModel = ReposViewModel(interactor)

        assertEquals(
            viewModel.getItemViewState().value,
            ReposViewState.Repos(apiResponse.response)
        )

    }


}