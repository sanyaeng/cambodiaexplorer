/**
 * 
 */
package com.ce.service.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ce.service.json.JBlob;
import com.ce.service.json.JUpload;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

/**
 * @author sanya
 * 
 */
@Controller
@RequestMapping(value = "/blob/api/")
public class BlobController {

    static final Logger      LOG         = LoggerFactory.getLogger(BlobController.class);

    // blob service
    private BlobstoreService blobService = BlobstoreServiceFactory.getBlobstoreService();

    @RequestMapping(value = "upload", method = RequestMethod.GET)
    public ResponseEntity<JUpload> createUploadUrl(HttpServletRequest request, HttpServletResponse response) {
        final JUpload jUpload = new JUpload();
        final String uploadUrl = blobService.createUploadUrl("/blob/api/upload");
        jUpload.setUploadUrl(uploadUrl);
        return new ResponseEntity<JUpload>(jUpload, HttpStatus.OK);
    }

    @RequestMapping(value = "upload", method = RequestMethod.GET, params = {"key"})
    public void getBlob(HttpServletRequest request, HttpServletResponse response, @RequestParam String key) throws IOException {
        blobService.serve(new BlobKey(key), response);
    }

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public ResponseEntity<List<JBlob>> upload(HttpServletRequest request, HttpServletResponse response) {
        final Map<String, List<BlobKey>> blobMap = blobService.getUploads(request);
        final List<JBlob> jblob = new ArrayList<JBlob>();
        for(List<BlobKey> keys : blobMap.values()) {
            for(BlobKey blobKey : keys) {
                JBlob blob = new JBlob(blobKey.getKeyString(), String.format("%s://%s/blob/api/upload?key=%s",
                        request.getScheme(), request.getHeader("Host"), blobKey.getKeyString()));
                jblob.add(blob);
            }
        }
        return new ResponseEntity<List<JBlob>>(jblob, HttpStatus.OK);
    }

}
