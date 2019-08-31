package com.github.ciifm.personal.admin.share.service;

import com.github.ciifm.handy.model.ResponseData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public interface IFileShareService {

    @RequestMapping(value = { "/file/downloadFile" })
    ResponseData<String> downloadFile(String fileUniqueCode);

    @RequestMapping(value = { "/file/uploadPrivateFile" }, method = { RequestMethod.POST })
    ResponseData<String> uploadPrivateFile(MultipartFile file, String dir) throws IOException;

    @RequestMapping(value = { "/file/uploadPublicFile" }, method = { RequestMethod.POST })
    ResponseData<String> uploadPublicFile(MultipartFile file, String dir) throws IOException;

    @RequestMapping(value = { "/file/uploadIcon" }, method = { RequestMethod.POST })
    ResponseData<String> uploadIcon(MultipartFile file, String dir) throws IOException;
}
