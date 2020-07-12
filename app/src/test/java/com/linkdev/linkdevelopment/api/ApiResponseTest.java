package com.linkdev.linkdevelopment.api;

import com.linkdev.linkdevelopment.utils.Resource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import java.util.Objects;


@RunWith(JUnit4.class)
public class ApiResponseTest {


    @Test
    public void testForLoading() {
        Resource resource = Resource.loading("Loading...");
        Assert.assertEquals("Loading...", resource.data);
        Assert.assertEquals(Resource.Status.LOADING, resource.status);
    }


    @Test
    public void testForSuccess() {
        Resource resource = Resource.success("testForSuccess");
        Assert.assertEquals("testForSuccess", resource.data);
        Assert.assertEquals(Resource.Status.SUCCESS, resource.status);
    }

    @Test
    public void testForError() {
        Exception exception = new Exception("Network Error");
        Resource resource = Resource.error("testForError", Objects.requireNonNull(exception.getMessage()));
        Assert.assertEquals("Network Error", resource.data);
        Assert.assertEquals(Resource.Status.ERROR, resource.status);
    }
}
