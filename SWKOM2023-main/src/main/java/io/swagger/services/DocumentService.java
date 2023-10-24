package io.swagger.services;


import org.springframework.transaction.annotation.Transactional;
import io.swagger.model.Document;
import java.util.List;

public interface DocumentService {

    Document getDocumentById(Long id);

    List<Document> getAllDocuments();

    Document createDocument(Document documentDTO);

    Document updateDocument(Long id, Document documentDTO);

    void deleteDocument(Long id);


}
