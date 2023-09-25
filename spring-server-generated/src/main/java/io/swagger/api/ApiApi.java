/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.46).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.Correspondent;
import io.swagger.model.DocTag;
import io.swagger.model.Document;
import io.swagger.model.DocumentType;
import io.swagger.model.NewCorrespondent;
import io.swagger.model.NewDocumentType;
import io.swagger.model.NewTag;
import org.threeten.bp.OffsetDateTime;
import org.springframework.core.io.Resource;
import io.swagger.model.UserInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CookieValue;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-09-25T10:52:03.827974241Z[GMT]")
@Validated
public interface ApiApi {

    @Operation(summary = "", description = "", tags={ "Login" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Success") })
    @RequestMapping(value = "/api",
        method = RequestMethod.GET)
    ResponseEntity<Void> apiGet();


    @Operation(summary = "", description = "", tags={ "Search" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Success") })
    @RequestMapping(value = "/api/search/autocomplete",
        method = RequestMethod.GET)
    ResponseEntity<Void> autoComplete(@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "term", required = false) String term, @Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "limit", required = false) Integer limit);


    @Operation(summary = "", description = "", tags={ "Correspondents" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Success") })
    @RequestMapping(value = "/api/correspondents",
        consumes = { "application/json", "text/json", "application/_*+json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> createCorrespondent(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody NewCorrespondent body);


    @Operation(summary = "", description = "", tags={ "DocumentTypes" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Success") })
    @RequestMapping(value = "/api/document_types",
        consumes = { "application/json", "text/json", "application/_*+json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> createDocumentType(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody NewDocumentType body);


    @Operation(summary = "", description = "", tags={ "Tags" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Success") })
    @RequestMapping(value = "/api/tags",
        consumes = { "application/json", "text/json", "application/_*+json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> createTag(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody NewTag body);


    @Operation(summary = "", description = "", tags={ "Correspondents" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Success") })
    @RequestMapping(value = "/api/correspondents/{id}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteCorrespondent(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("id") Integer id);


    @Operation(summary = "", description = "", tags={ "Documents" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Success") })
    @RequestMapping(value = "/api/documents/{id}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteDocument(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("id") Integer id);


    @Operation(summary = "", description = "", tags={ "DocumentTypes" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Success") })
    @RequestMapping(value = "/api/document_types/{id}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteDocumentType(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("id") Integer id);


    @Operation(summary = "", description = "", tags={ "Tags" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Success") })
    @RequestMapping(value = "/api/tags/{id}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteTag(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("id") Integer id);


    @Operation(summary = "", description = "", tags={ "Documents" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Success") })
    @RequestMapping(value = "/api/documents/{id}/download",
        method = RequestMethod.GET)
    ResponseEntity<Void> downloadDocument(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("id") Integer id, @Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "original", required = false) Boolean original);


    @Operation(summary = "", description = "", tags={ "Correspondents" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Success") })
    @RequestMapping(value = "/api/correspondents",
        method = RequestMethod.GET)
    ResponseEntity<Void> getCorrespondents();


    @Operation(summary = "", description = "", tags={ "Documents" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Success") })
    @RequestMapping(value = "/api/documents/{id}/metadata",
        method = RequestMethod.GET)
    ResponseEntity<Void> getDocumentMetadata(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("id") Integer id);


    @Operation(summary = "", description = "", tags={ "Documents" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Success") })
    @RequestMapping(value = "/api/documents/{id}/preview",
        method = RequestMethod.GET)
    ResponseEntity<Void> getDocumentPreview(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("id") Integer id);


    @Operation(summary = "", description = "", tags={ "Documents" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Success") })
    @RequestMapping(value = "/api/documents/{id}/thumb",
        method = RequestMethod.GET)
    ResponseEntity<Void> getDocumentThumb(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("id") Integer id);


    @Operation(summary = "", description = "", tags={ "DocumentTypes" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Success") })
    @RequestMapping(value = "/api/document_types",
        method = RequestMethod.GET)
    ResponseEntity<Void> getDocumentTypes();


    @Operation(summary = "", description = "", tags={ "Documents" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Success") })
    @RequestMapping(value = "/api/documents",
        method = RequestMethod.GET)
    ResponseEntity<Void> getDocuments(@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "Page", required = false) Integer page, @Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "page_size", required = false) Integer pageSize, @Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "query", required = false) String query, @Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "ordering", required = false) String ordering, @Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "tags__id__all", required = false) List<Integer> tagsIdAll, @Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "document_type__id", required = false) Integer documentTypeId, @Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "correspondent__id", required = false) Integer correspondentId, @Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "truncate_content", required = false) Boolean truncateContent);


    @Operation(summary = "", description = "", tags={ "Tags" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Success") })
    @RequestMapping(value = "/api/tags",
        method = RequestMethod.GET)
    ResponseEntity<Void> getTags();


    @Operation(summary = "", description = "", tags={ "Login" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Success") })
    @RequestMapping(value = "/api/token",
        consumes = { "application/json", "text/json", "application/_*+json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> getToken(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody UserInfo body);


    @Operation(summary = "", description = "", tags={ "Login" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Success") })
    @RequestMapping(value = "/api",
        method = RequestMethod.POST)
    ResponseEntity<Void> root();


    @Operation(summary = "", description = "", tags={ "Login" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Success") })
    @RequestMapping(value = "/api/statistics",
        method = RequestMethod.GET)
    ResponseEntity<Void> statistics();


    @Operation(summary = "", description = "", tags={ "Correspondents" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Success") })
    @RequestMapping(value = "/api/correspondents/{id}",
        consumes = { "application/json", "text/json", "application/_*+json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Void> updateCorrespondent(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("id") Integer id, @Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody Correspondent body);


    @Operation(summary = "", description = "", tags={ "Documents" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Success") })
    @RequestMapping(value = "/api/documents/{id}",
        consumes = { "application/json", "text/json", "application/_*+json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Void> updateDocument(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("id") Integer id, @Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody Document body);


    @Operation(summary = "", description = "", tags={ "DocumentTypes" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Success") })
    @RequestMapping(value = "/api/document_types/{id}",
        consumes = { "application/json", "text/json", "application/_*+json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Void> updateDocumentType(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("id") Integer id, @Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody DocumentType body);


    @Operation(summary = "", description = "", tags={ "Tags" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Success") })
    @RequestMapping(value = "/api/tags/{id}",
        consumes = { "application/json", "text/json", "application/_*+json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Void> updateTag(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("id") Integer id, @Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody DocTag body);


    @Operation(summary = "", description = "", tags={ "Documents" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Success") })
    @RequestMapping(value = "/api/documents/post_document",
        consumes = { "multipart/form-data" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> uploadDocument(@Parameter(in = ParameterIn.DEFAULT, description = "",schema=@Schema()) @RequestParam(value="title", required=false)  String title, @Parameter(in = ParameterIn.DEFAULT, description = "",schema=@Schema()) @RequestParam(value="created", required=false)  OffsetDateTime created, @Parameter(in = ParameterIn.DEFAULT, description = "",schema=@Schema()) @RequestParam(value="document_type", required=false)  Integer documentType, @Parameter(in = ParameterIn.DEFAULT, description = "",schema=@Schema()) @RequestParam(value="tags", required=false)  List<Integer> tags, @Parameter(in = ParameterIn.DEFAULT, description = "",schema=@Schema()) @RequestParam(value="correspondent", required=false)  Integer correspondent, @Parameter(in = ParameterIn.DEFAULT, description = "",schema=@Schema()) @RequestParam(value="document", required=false)  List<Resource> document);

}

