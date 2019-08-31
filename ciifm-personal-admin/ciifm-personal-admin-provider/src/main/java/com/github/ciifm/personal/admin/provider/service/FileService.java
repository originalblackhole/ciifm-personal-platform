package com.github.ciifm.personal.admin.provider.service;

import com.github.ciifm.handy.model.ResponseData;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    ResponseData<String> downloadFile(String fileUniqueCode);

    ResponseData<String> uploadPrivateFile(MultipartFile file, String dir) throws IOException;

    ResponseData<String> uploadPublicFile(MultipartFile file, String dir) throws IOException;

    ResponseData<String> uploadIcon(MultipartFile file, String dir) throws IOException;
}
