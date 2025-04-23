package com.Quiz_Application_Project.Quiz_Application.Service;

import com.Quiz_Application_Project.Quiz_Application.Entity.Attachment;
import com.Quiz_Application_Project.Quiz_Application.Repo.AttachmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AttachmentService
{
    @Autowired
    AttachmentRepo attachmentRepo;

    public Attachment saveAttachment(MultipartFile file , String quizType)
    {
        Attachment obj=new Attachment();
        obj.setQuizType(quizType);

        String filename= StringUtils.cleanPath(file.getOriginalFilename());
        try{
            if(filename.contains(".."))
            {
                throw new Exception("Filename contains invalid path sequence");
            }
            Attachment attachment=new Attachment(filename,
                    file.getContentType(),
                    obj.getQuizType(),
                    file.getBytes());

            return attachmentRepo.save(attachment);


        }catch(Exception e)
        {
            throw new RuntimeException(e);
        }
    }
    public Attachment getAttachment(String quizType)
    {
        Attachment byQuizType = attachmentRepo.findByQuizType(quizType);
        return byQuizType;
    }
}
