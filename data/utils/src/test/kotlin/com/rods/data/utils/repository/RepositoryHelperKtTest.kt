package com.rods.data.utils.repository

import com.rods.domain.utils.ErrorResponse
import com.rods.domain.utils.ResultWrapper
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

@ExperimentalCoroutinesApi
class RepositoryHelperKtTest {

    private val dataCall = mockk<suspend () -> String>()

    @Test
    fun safeDataCall_returnSuccess() = runBlockingTest {
        coEvery { dataCall.invoke() } returns "success"
        val dataCallResponse = safeApiCall(dataCall)
        assertEquals(dataCallResponse, ResultWrapper.Success("success"))
    }

    @Test
    fun safeDataCall_returnGenericError() = runBlockingTest {
        val errorResponse = mockk<Response<*>>()
        every { errorResponse.code() } returns 123
        every { errorResponse.message() } returns "message"
        every { errorResponse.errorBody() } returns ResponseBody.create(
            MediaType.parse("application/json"),
            "{\"code\":\"InvalidCredentials\",\"message\":\"The passed API key is invalid.\"}".toByteArray()
        )
        coEvery { dataCall.invoke() } throws HttpException(errorResponse)
        val dataCallResponse = safeApiCall(dataCall)

        val internalErrorResponse = ErrorResponse("InvalidCredentials","The passed API key is invalid.", null)
        assertEquals(dataCallResponse, ResultWrapper.GenericError(123, internalErrorResponse))
    }

    @Test
    fun safeDataCall_returnEmptyGenericError() = runBlockingTest {
        coEvery { dataCall.invoke() } throws ClassNotFoundException("")
        val dataCallResponse = safeApiCall(dataCall)
        assertEquals(dataCallResponse, ResultWrapper.GenericError(null, null))
    }

    @Test
    fun safeDataCall_returnNetworkError() = runBlockingTest {
        coEvery { dataCall.invoke() } throws IOException("")
        val dataCallResponse = safeApiCall(dataCall)
        assertEquals(dataCallResponse, ResultWrapper.NetworkError)
    }
}