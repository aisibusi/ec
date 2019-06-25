package com.ec.upload.service.impl;

import com.ec.upload.config.UploadProperties;
import com.ec.upload.service.UploadService;
import com.lh.ec.common.enums.ExceptionEnum;
import com.lh.ec.common.exception.EcException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Slf4j
@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private UploadProperties props;

    @Override
    public String uploadImage(MultipartFile file) {

        //Validate file's type
        String fileType = file.getContentType();
        if(!props.getAllowTypes().contains(fileType))
            throw new EcException(ExceptionEnum.INVALID_FILE_FORMAT);

        //Validate file's content
        try {
            BufferedImage read = ImageIO.read(file.getInputStream());
            if(read == null){
                log.info("[Upload File]: The type of the file is incorrect");
                throw new EcException(ExceptionEnum.INVALID_FILE_FORMAT);
            }

        } catch (IOException e) {
            log.info("[Upload File]: Unexpected issues happened when reading the file", e);
            throw new EcException(ExceptionEnum.INVALID_FILE_FORMAT);
        }

        //Save the file to the target place
        String path = this.getClass().getClassLoader().getResource("/").getFile();
        log.trace("The path is " + path);
        File dir = new File(path);
        if(!dir.exists())
            dir.mkdirs();
        try {
            file.transferTo(new File(dir,file.getOriginalFilename()));
        } catch (IOException e) {
            log.info("[Upload File]: Unexpected issues happened when saving the file", e);
            throw new EcException(ExceptionEnum.UPLOAD_IMAGE_EXCEPTION);
        }

        return props.getBaseUrl() + file.getOriginalFilename();
    }
}
