package com.Quiz_Application_Project.Quiz_Application.Controller;


import com.Quiz_Application_Project.Quiz_Application.Entity.Attachment;
import com.Quiz_Application_Project.Quiz_Application.Service.AttachmentService;
import com.Quiz_Application_Project.Quiz_Application.dto.ResponseData;
import jakarta.annotation.Resource;
import jakarta.servlet.Servlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins="http://127.0.0.1:5501")
@RestController
@RequestMapping("/api")
public class AttachmentController
{
    @Autowired
    AttachmentService attachmentService;
    
    @PostMapping("/upload/{quizType}")
    public ResponseData uploadFile(@RequestParam ("file")MultipartFile file , @PathVariable String quizType)
    {
        String downloadUrl=" ";
        Attachment attachment = attachmentService.saveAttachment(file, quizType);
        downloadUrl= ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/download/").path(attachment.getQuizType()).toUriString();

        return new ResponseData(attachment.getFilename(),downloadUrl,file.getContentType());
    }

    @GetMapping("/download/{quizType}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String quizType)
    {
        Attachment attachment = attachmentService.getAttachment(quizType);
        //convering in fileformat
      return ResponseEntity.ok().contentType(MediaType.parseMediaType(attachment.getFileType())).header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+attachment.getFilename()+"\"").body(new ByteArrayResource(attachment.getData()));

    }
}
