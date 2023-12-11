package com.paperless.services.impl;
import lombok.extern.slf4j.Slf4j;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
@Slf4j
@Service
public class OcrService {

    @Autowired
    private MinioClient minioClient;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${minio.bucketName}")
    private String bucketName;

    // Inject the Tesseract data path from the environment variable
    @Value("${TESSERACT_DATAPATH}")
    private String tessDataPath;
    private static final String OCR_DOCUMENT_OUT_QUEUE_NAME = "OCR_DOCUMENT_OUT";



    public void processDocument(String minioPath) {
        try {
            // Fetch the document from MinIO
            InputStream fileStream = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucketName)
                            .object(minioPath)
                            .build());

            // Save the stream to a temporary file
            File tempFile = File.createTempFile("ocr_", ".pdf");
            Files.copy(fileStream, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);


            // Perform OCR on the temporary file
            String ocrResult = doOcr(tempFile);
            log.info("OCR-Result: " + ocrResult);

            // Send OCR result to RabbitMQ queue
            rabbitTemplate.convertAndSend(OCR_DOCUMENT_OUT_QUEUE_NAME, ocrResult);

            // Clean up temporary file
            tempFile.delete();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error processing document: " + e.getMessage());
        }
    }

    public String doOcr(File imageFile) throws TesseractException {
        log.info("Performing OCR on file: " + imageFile.getAbsolutePath());

        Tesseract tesseract = new Tesseract(); // create a new instance of Tesseract
        tesseract.setDatapath(tessDataPath); // set the tessdata path
        tesseract.setLanguage("deu");
        // Perform OCR on the image
        return tesseract.doOCR(imageFile);
    }
}
