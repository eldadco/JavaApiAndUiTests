package Tests;


import Infra.Api.HttpProcess;
import Infra.Api.ResponseValidation;
import Infra.Api.SingletonHttpClient;
import Model.UserPost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class ApiTests {
    HttpProcess httpProcess;

    public ApiTests() {

    }

    @BeforeClass
    public void beforeClass() {
        this.httpProcess = new HttpProcess(SingletonHttpClient.getInstance().getClient());

    }


    @Test(description = "Verify that when get all posts, the status code equals '200' and the returned response body structure is UserPost[] model")
    public void positiveTestGetAllUserPosts() throws IOException, InterruptedException, URISyntaxException {

        HttpGet request = new HttpGet(new URI("https://jsonplaceholder.typicode.com/posts"));

        HttpResponse response = this.httpProcess.executeRequest(request);

        ResponseValidation responseValidation = this.httpProcess.validateJsonResponse(200, UserPost[].class, response);
        Assert.assertTrue(responseValidation.getStatusCodeResult() && (responseValidation.getBodyResult() != null));

    }

    @Test(description = "Verify that when get specific post,the returned status code equals '200' and the returned response body structure is UserPost model")
    public void positiveTestGetSpecificUserPost() throws IOException, InterruptedException, URISyntaxException {
        String body = "ullam et saepe reiciendis voluptatem adipisci\nsit amet autem assumenda provident rerum culpa\nquis hic commodi nesciunt rem tenetur doloremque ipsam iure\nquis sunt voluptatem rerum illo velit";

        UserPost expectedUserPost = new UserPost(1, 4, "eum et est occaecati", body);

        HttpGet request = new HttpGet(new URI(String.format("https://jsonplaceholder.typicode.com/posts/%d", expectedUserPost.getId())));

        HttpResponse response = this.httpProcess.executeRequest(request);

        ResponseValidation<UserPost> responseValidation = this.httpProcess.validateJsonResponse(200, UserPost.class, response);

        Assert.assertTrue(responseValidation.getStatusCodeResult() && responseValidation.getBodyResult().equals(expectedUserPost));
    }

    @Test(description = "Verify that when get specific post by wrong id, the returned status code equals '404' and the returned response body structure is an empty object")
    public void negativeTestGetSpecificUserPostByWrongId() throws IOException, URISyntaxException {

        HttpGet request = new HttpGet(new URI(String.format("https://jsonplaceholder.typicode.com/posts/%d", -1)));

        HttpResponse response = this.httpProcess.executeRequest(request);

        ResponseValidation<UserPost> responseValidation = this.httpProcess.validateJsonResponse(404, UserPost.class, response);

        Assert.assertTrue(responseValidation.getStatusCodeResult() && responseValidation.getBodyResult().getId() == -1);

    }

}