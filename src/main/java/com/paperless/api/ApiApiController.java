package com.paperless.api;


import com.paperless.services.dto.*;
import com.paperless.services.impl.DocumentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Generated;
import javax.validation.Valid;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import org.openapitools.jackson.nullable.JsonNullable;


@Generated(value = "com.paperless.codegen.languages.SpringCodegen", date = "2023-10-22T12:32:07.613318Z[Etc/UTC]")
@Controller
@Slf4j
@RequestMapping("${openapi.paperlessRestServer.base-path:}")
public class ApiApiController implements ApiApi {
    private final DocumentServiceImpl documentServiceImpl;

    private final NativeWebRequest request;

    @Autowired
    public ApiApiController(NativeWebRequest request, DocumentServiceImpl documentServiceImpl) {
        this.request = request;
        this.documentServiceImpl = documentServiceImpl;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<GetDocument200Response> getDocument(Integer id, Integer page, Boolean fullPerms) {
        return ResponseEntity.ok(documentServiceImpl.getDocument(id, page, fullPerms));
    }

    @Override
    public ResponseEntity<Void> uploadDocument(String title, OffsetDateTime created, Integer documentType, List<Integer> tags, Integer correspondent, List<MultipartFile> document) {
        try {

            String name = document.get(0).getOriginalFilename();
            DocumentDTO documentDTO = new DocumentDTO();
            documentDTO.setTitle(JsonNullable.of(title == null ? name : title));
            documentDTO.setOriginalFileName(JsonNullable.of(name));
            documentDTO.setCreated(created);
            documentDTO.setDocumentType(JsonNullable.of(documentType));
            documentDTO.setTags(JsonNullable.of(tags));
            documentDTO.setCorrespondent(JsonNullable.of(correspondent));

            MultipartFile file = document.get(0);

            if(file == null || file.isEmpty()){
                return ResponseEntity.badRequest().build();
            }

            String[] fileExtension = name.split("\\.");
            if(!fileExtension[fileExtension.length-1].equalsIgnoreCase("pdf")){
                return new ResponseEntity<>(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
            }

            documentServiceImpl.uploadDocument(documentDTO, file);
            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

    }

    @Override
    public ResponseEntity<GetDocuments200Response> getDocuments(Integer page, Integer pageSize, String query, String ordering, List<Integer> tagsIdAll, Integer documentTypeId, Integer storagePathIdIn, Integer correspondentId, Boolean truncateContent) {
        return documentServiceImpl.getDocuments(page, pageSize, query, ordering, tagsIdAll, documentTypeId, storagePathIdIn, correspondentId, truncateContent);
    }

    @Override
    public ResponseEntity<UpdateDocument200Response> updateDocument(Integer id, UpdateDocumentRequest updateDocumentRequest) {
        return documentServiceImpl.updateDocument(id, updateDocumentRequest);
    }

    @Override
    public ResponseEntity<String> getDocumentPreview(Integer id) {
        try {
            // Retrieve the document by ID
            DocumentDTO document = documentServiceImpl.getDocumentById(id);
            if (document == null || document.getContent() == null) {
                return ResponseEntity.notFound().build();
            }

            // Extract a preview of the content
            String contentPreview = extractContentPreview(document.getContent().orElse(""));

            // Return the preview content
            return ResponseEntity.ok(contentPreview);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    private String extractContentPreview(String content) {
        // Define the preview length
        int previewLength = 100; // or any other logic to determine the preview size

        // Return a substring of the content based on the preview length
        return content.length() > previewLength ? content.substring(0, previewLength) : content;
    }

    @Override
    public ResponseEntity<Void> deleteDocument(Integer id) {
        try {
            log.info("deleteDocument at id ------------------------->" + id );
            documentServiceImpl.deleteDocument(id);
            return new ResponseEntity<>(HttpStatus.OK); // 204 No Content
        } catch (Exception e) {
            log.error("Error deleting documents", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteCorrespondent(Integer id) {
        try {
            log.info("deleteCorespondent at id ------------------------->" + id );
            documentServiceImpl.deleteDocument(id);
            return new ResponseEntity<>(HttpStatus.OK); // 204 No Content
        } catch (Exception e) {
            log.error("Error deleting documents", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<SelectionData200Response> selectionData(@Valid @RequestBody(required = false) SelectionDataRequest selectionDataRequest) {
        log.info("SelectionData CALLED: " + selectionDataRequest.toString());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Resource> getDocumentThumb(Integer id) {
        try {
            Resource thumbnail = documentServiceImpl.getDocumentThumbnail(id);
            if (thumbnail.exists()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_PNG) // or the appropriate media type
                        .body(thumbnail);
            } else {
                // Return default thumbnail
                log.info("Could not fetch Pdf-Thumbnail - using default pdf thumbnail");
                Resource defaultThumbnail = new ClassPathResource("pdficon.png");
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_PNG)
                        .body(defaultThumbnail);
            }
        } catch (Exception e) {
            // Log the exception and return an appropriate error response
            log.error("Error retrieving document thumbnail: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
