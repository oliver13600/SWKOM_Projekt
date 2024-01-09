package org.example;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    @Test
    public void testFileUpload() throws IOException {
        // Replace with the actual path to your PDF file
        String filePath = "IntegrationTest.pdf";

        // Create a CloseableHttpClient
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
             // Create an HttpPost request
             HttpPost httpPost = new HttpPost("http://localhost:8080/api/documents/post_document/");

             // Create a MultipartEntityBuilder for the form data
             MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();

             // Read the PDF file into a byte array
             byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));
             entityBuilder.addBinaryBody("document", fileBytes, ContentType.APPLICATION_OCTET_STREAM, "IntegrationTest.pdf");

             // Set the form data as the request entity
             HttpEntity httpEntity = entityBuilder.build();
             httpPost.setEntity(httpEntity);

             // Execute the request and get the response
             HttpResponse response = httpClient.execute(httpPost);

             // Assert the response status code is 201 CREATED
             assertEquals(201, response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void testFileDownload() throws IOException {
        // Replace with the actual document ID
        int documentId = 10000;


        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet("http://localhost:8080/api/documents/" + documentId + "/download/");

            HttpResponse response = httpClient.execute(httpGet);

            // Assert the response status code is 200 OK
            assertEquals(200, response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void testFilePreview() throws IOException {
        // Replace with the actual document ID
        int documentId = 10000;

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet("http://localhost:8080/api/documents/" + documentId + "/preview/");

            HttpResponse response = httpClient.execute(httpGet);

            // Assert the response status code is 200 OK
            assertEquals(200, response.getStatusLine().getStatusCode());
        }
    }

}
