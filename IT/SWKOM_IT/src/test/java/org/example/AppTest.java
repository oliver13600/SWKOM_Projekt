package org.example;
//httpclient
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AppTest {


    @Test
    public void uploadDocument() {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            File file = new File("IntegrationTest.pdf");
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.addTextBody("title", "title", ContentType.MULTIPART_FORM_DATA);
            builder.addTextBody("created", "2021-05-05T12:00:00.000Z", ContentType.MULTIPART_FORM_DATA);
            builder.addTextBody("document_type", "1", ContentType.MULTIPART_FORM_DATA);
            builder.addTextBody("tags", "tag1,tag2", ContentType.MULTIPART_FORM_DATA);
            builder.addTextBody("correspondent", "1", ContentType.MULTIPART_FORM_DATA);
            builder.addBinaryBody("document", file, ContentType.create("application/pdf"), file.getName());

            HttpUriRequest request = RequestBuilder.post()
                    .setUri("http://localhost:8080/api/documents/post_document/")
                    .setHeader(HttpHeaders.ACCEPT, "application/json")
                    .setHeader(HttpHeaders.CONTENT_TYPE, "multipart/form; boundary=sdfadsfgsdg") // This line might be unnecessary
                    //.setHeader("Authorization", "Bearer 123token") // Uncomment if needed
                    .setEntity(builder.build())
                    .build();

            var response = httpClient.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();
            String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");

            assertEquals(200, statusCode);
            assertEquals("[]", responseBody);
        } catch (IOException e) {
            fail("Exception: " + e.getMessage());
        }
    }


}
