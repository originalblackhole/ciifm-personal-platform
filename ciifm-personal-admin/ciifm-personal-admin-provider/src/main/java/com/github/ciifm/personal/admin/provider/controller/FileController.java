package com.github.ciifm.personal.admin.provider.controller;

import com.github.ciifm.handy.model.ResponseData;
import com.github.ciifm.personal.admin.provider.service.FileService;
import com.github.ciifm.personal.admin.share.service.IFileShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p></p>
 *
 * @author rui.zhou
 * @date 2019/8/22 0022 23:26
 */
@RestController
public class FileController implements IFileShareService {

    @Autowired
    private FileService fileService;

    @Override
    public ResponseData<String> downloadFile(String fileUniqueCode){
        return fileService.downloadFile(fileUniqueCode);
    }

    @Override
    public ResponseData<String> uploadPrivateFile(MultipartFile file, String dir) throws IOException {
        return fileService.uploadPrivateFile(file,dir);
    }

    @Override
    public ResponseData<String> uploadPublicFile(MultipartFile file, String dir) throws IOException {
        return fileService.uploadPublicFile(file,dir);
    }

    @Override
    public ResponseData<String> uploadIcon(MultipartFile file, String dir) throws IOException {
        return fileService.uploadIcon(file,dir);
    }
}
