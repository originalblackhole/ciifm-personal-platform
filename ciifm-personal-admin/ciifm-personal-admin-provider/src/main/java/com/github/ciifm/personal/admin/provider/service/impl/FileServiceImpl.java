package com.github.ciifm.personal.admin.provider.service.impl;

import com.github.ciifm.file.service.IFileService;
import com.github.ciifm.handy.model.CommonMessageCode;
import com.github.ciifm.handy.model.ResponseData;
import com.github.ciifm.personal.admin.component.service.impl.UserComponentServiceImpl;
import com.github.ciifm.personal.admin.dao.dataobject.UserDO;
import com.github.ciifm.personal.admin.provider.component.CurrentContext;
import com.github.ciifm.personal.admin.provider.service.FileService;
import com.github.ciifm.personal.admin.provider.util.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;

/**
 * <p></p>
 *
 * @author rui.zhou
 * @date 2019/8/22 0022 23:29
 */
@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Value("${octopus.file.publicBucketName}")
    private String publicBucketName;

    @Value("${octopus.file.privateBucketName}")
    private String privateBucketName;

    @Value("${octopus.file.expire}")
    private Integer expire;

    @Autowired
    private IFileService fileService;

    @Autowired
    private UserComponentServiceImpl userComponentService;

    @Override
    public ResponseData<String> downloadFile(String fileUniqueCode) {
        log.info("fileUniqueCode--------------->{}",fileUniqueCode);
        if(StringUtils.isBlank(fileUniqueCode)){
            return new ResponseData<>(501,"fileUniqueCode can not null");
        }
        Calendar expiration = Calendar.getInstance();
        expiration.add(Calendar.MINUTE, expire);
        URL url = fileService.generatePresignedUrl(privateBucketName, fileUniqueCode, expiration.getTime());
        return new ResponseData<>(CommonMessageCode.SUCCESS, url.toString());
    }

    @Override
    public ResponseData<String> uploadPrivateFile(MultipartFile file, String dir) throws IOException {
        log.info("上传私有文件-------------------------->");
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        String key = FileUtils.createKey(dir,suffix);
        fileService.uploadFile(privateBucketName, file.getInputStream(), key, file.getContentType());
        return new ResponseData<>(CommonMessageCode.SUCCESS, key);
    }

    @Override
    public ResponseData<String> uploadPublicFile(MultipartFile file, String dir) throws IOException {
        log.info("上传公有文件-------------------------->");
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        String key = FileUtils.createKey(dir,suffix);
        String url = fileService.uploadFile(publicBucketName, file.getInputStream(), key, file.getContentType());
        return new ResponseData<>(CommonMessageCode.SUCCESS, url);
    }

    @Override
    public ResponseData<String> uploadIcon(MultipartFile file, String dir) throws IOException {
        UserDO user = userComponentService.findByUsername(CurrentContext.userName());
        ResponseData<String> response = this.uploadPublicFile(file,"icon");
        user.setIcon(response.getData());
        userComponentService.update(user);
        return new ResponseData<>(CommonMessageCode.SUCCESS, response.getData());
    }
}
