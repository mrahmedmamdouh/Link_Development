package com.linkdev.linkdevelopment.api;

import com.linkdev.linkdevelopment.requests.ArticleApi;
import com.linkdev.linkdevelopment.utils.RetrofitCallAdapterFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@RunWith(JUnit4.class)
public class ApiAbstract<T> {

    private MockWebServer mockWebServer;


    @Before
    public void mockServer() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @After
    public void stopServer() throws IOException {
        mockWebServer.shutdown();
    }

    void enqueueResponse() throws IOException {
        enqueueResponse("test_repos.json");
    }


    private void enqueueResponse(String fileName) throws IOException {
        InputStream inputStream = Objects.requireNonNull(ApiAbstract.class.getClassLoader()).getResourceAsStream(fileName);
        Source source = Okio.buffer(Okio.source(inputStream));
        MockResponse mockResponse = new MockResponse();
        for (Map.Entry<String, String> entry : ((Map<String, String>) Collections.EMPTY_MAP).entrySet()) {
            mockResponse.addHeader(entry.getKey(), entry.getValue());
        }
        mockWebServer.enqueue(mockResponse.setBody(((BufferedSource) source).readString(StandardCharsets.UTF_8)));
    }

    T createService() {
        return new Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new RetrofitCallAdapterFactory())
                .build()
                .create((Class<T>) ArticleApi.class);
    }

}