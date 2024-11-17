package fun.ychen.share.app.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.ychen.share.app.common.cache.RequestContext;
import fun.ychen.share.app.common.exception.ErrorCode;
import fun.ychen.share.app.common.exception.ServerException;
import fun.ychen.share.app.convert.UserConvert;
import fun.ychen.share.app.mapper.UserMapper;
import fun.ychen.share.app.model.dto.UserEditDTO;
import fun.ychen.share.app.model.entity.User;
import fun.ychen.share.app.model.vo.UserInfoVO;
import fun.ychen.share.app.service.BonusLogService;
import fun.ychen.share.app.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private final BonusLogService bonusLogService;
    @Override
    public UserInfoVO userInfo() {
        Integer userId = RequestContext.getUserId();
        // 查询数据库
        User user = baseMapper.selectById(userId);
        if (user == null) {
            log.error("用户不存在，userId：{}", userId);
            throw new ServerException(ErrorCode.USER_NOT_EXIST);
        }
        UserInfoVO userInfoVO = UserConvert.INSTANCE.convert(user);
        // 用户是否签到
        userInfoVO.setIsDailyCheck(bonusLogService.isTodayCheck(userId));
        return userInfoVO;
    }

    @Override
    public UserInfoVO updateInfo(UserEditDTO userEditDTO) {
        Integer userId = RequestContext.getUserId();
        userEditDTO.setPkId(userId);
        User user = UserConvert.INSTANCE.convert(userEditDTO);
        if (user.getPkId() == null) {
            throw new ServerException(ErrorCode.PARAMS_ERROR);
        }
        try {
            if (baseMapper.updateById(user) < 1) {
                throw new ServerException("修改失败");
            }
        } catch (Exception e) {
            throw new ServerException(e.getMessage());
        }
        return this.userInfo();
    }

}
