package fun.ychen.share.app.service;

import org.springframework.web.multipart.MultipartFile;

public interface CommonService {
    /**
     * 发送短信
     *
     * @param phone 手机号
     */
    void sendSms(String phone);

    /**
     * 文件上传
     *
     * @param file 文件
     * @return 上传后的 url
     */
    String upload(MultipartFile file);
}
