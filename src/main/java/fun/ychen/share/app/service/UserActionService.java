package fun.ychen.share.app.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import fun.ychen.share.app.enums.UserActionEnum;
import fun.ychen.share.app.model.entity.UserAction;
import fun.ychen.share.app.model.vo.UserActionListInfo;

public interface UserActionService extends IService<UserAction> {
    void insertUserAction(Integer userId, Integer resourceId, UserActionEnum userActionEnum);

    void collectResource(Integer resourceId);

    void likeResource(Integer resourceId);

    void exchangeResource(Integer resourceId);

    UserActionListInfo selectResourceListByUserIdAndType(Integer userId, UserActionEnum userActionEnum, Page<UserAction> page);

    Integer selectCountByResourceIdAndType(Integer resourceId, UserActionEnum userActionEnum);

    Boolean resourceIsAction(Integer userId, Integer resourceId, UserActionEnum userActionEnum);
}
