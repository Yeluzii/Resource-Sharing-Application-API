package fun.ychen.share.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fun.ychen.share.app.model.dto.WxLoginDTO;
import fun.ychen.share.app.model.entity.User;
import fun.ychen.share.app.model.vo.UserLoginVO;

public interface AuthService extends IService<User> {
    /**
     * 登录
     *
     * @param phone 电话
     * @param code  验证码
     * @return {@link UserLoginVO}
     */
    UserLoginVO loginByPhone(String phone, String code);

    /**
     * 微信登录
     *
     * @param loginDTO DTO
     * @return {@link UserLoginVO}
     */
    UserLoginVO weChatLogin(WxLoginDTO loginDTO);

    /**
     * 检查用户是否启用
     *
     * @param userId 用户 ID
     * @return boolean
     */
    boolean checkUserEnabled(Integer userId);

    /**
     * 登出
     */
    void logout();
    void bindPhone(String phone,String code,String accessToken);
}
