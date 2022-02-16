package com.indaco.testutils.utils

import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.io.IOException
import javax.inject.Singleton

/*
 * Interceptor for testing endpoints prior to being merged into develop
 */
@Singleton
class MockInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val endpointToTest = chain.request().url.pathSegments.last()

        val responseToTest = responseToTest(endpointToTest)

        if (!responseToTest.first) {
            if (responseToTest.second != null)
                Log.e(TAG,"error: ${responseToTest.second}")

            return chain.proceed(chain.request())
        } else {
            val mockResponse = responseToTest.second!!

            return chain.proceed(chain.request())
                .newBuilder()
                .code(code)
                .protocol(Protocol.HTTP_2)
                .message(mockResponse)
                .body(mockResponse.toByteArray().toResponseBody(CONTENT_TYPE_JSON.toMediaTypeOrNull()))
                .addHeader(CONTENT_TYPE, CONTENT_TYPE_JSON)
                .build()
        }
    }

    private fun responseToTest(capturedEndpoint: String?): Pair<Boolean, String?> {
        return when {
            interceptValuesNotSet() -> Pair(false, "intercept values not set")
            capturedEndpoint.isNullOrEmpty() -> Pair(false, "Null endpoint")
            capturedEndpoint == endpoint -> Pair(true, getJsonDataFromAsset(jsonFileName))
            else -> Pair(false, null)
        }
    }

    private fun getJsonDataFromAsset(fileName: String): String? {
        return try {
            InstrumentationRegistry.getInstrumentation().context.assets
                .open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            Log.e("TAG", "failed to get jsonData from string")
            null
        }
    }

    private fun interceptValuesNotSet() = endpoint.isEmpty() || jsonFileName.isEmpty()

    companion object {
        private const val TAG = "MockInterceptor"
        private const val NETWORK_RESPONSES_DIR = "network_responses"
        private const val CONTENT_TYPE = "content-type"
        private const val CONTENT_TYPE_JSON = "application/json"
        private const val SUCCESS_CODE = 200

        private var endpoint = ""
        private var jsonFileName = ""
        private var code = SUCCESS_CODE

        // Use this function in API Classes prior to "execute" to intercept the endpoint
        fun interceptEndpoint(_endpoint: String, _jsonFileName: String, code: Int = SUCCESS_CODE) {
            val endpoint = _endpoint.split("/").last()
            Log.d("TAG","endpoint: $endpoint")
            println("STEFAN endpoint: $endpoint")
            Companion.endpoint = endpoint
            jsonFileName = if(_jsonFileName.contains(".json")) _jsonFileName else "$_jsonFileName.json"
            Companion.code = code
        }
    }
}