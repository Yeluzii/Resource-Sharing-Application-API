package fun.ychen.share.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fun.ychen.share.app.model.dto.UserEditDTO;
import fun.ychen.share.app.model.entity.User;
import fun.ychen.share.app.model.vo.UserInfoVO;

public interface UserService extends IService<User> {
    /**
     * 用户信息
     * @return {@link UserInfoVO}
     */
    UserInfoVO userInfo();

    /**
     * 更新信息
     * @param userEditDTO 用户编辑 DTO
     * @return UserInfoVO
     */
    UserInfoVO updateInfo(UserEditDTO userEditDTO);
}
