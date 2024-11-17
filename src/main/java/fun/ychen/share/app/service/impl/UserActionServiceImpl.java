package fun.ychen.share.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.ychen.share.app.common.cache.RequestContext;
import fun.ychen.share.app.common.exception.ServerException;
import fun.ychen.share.app.enums.BonusActionEnum;
import fun.ychen.share.app.enums.UserActionEnum;
import fun.ychen.share.app.mapper.ResourceMapper;
import fun.ychen.share.app.mapper.UserActionMapper;
import fun.ychen.share.app.model.entity.Resource;
import fun.ychen.share.app.model.entity.User;
import fun.ychen.share.app.model.entity.UserAction;
import fun.ychen.share.app.model.vo.UserActionListInfo;
import fun.ychen.share.app.service.BonusLogService;
import fun.ychen.share.app.service.UserActionService;
import fun.ychen.share.app.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class UserActionServiceImpl extends ServiceImpl<UserActionMapper, UserAction> implements UserActionService {
    private final ResourceMapper resourceMapper;
    private final UserService userService;
    private final BonusLogService bonusLogService;

    @Override
    public void insertUserAction(Integer userId, Integer resourceId, UserActionEnum userActionEnum) {
        UserAction userAction = new UserAction();
        userAction.setUserId(userId);
        userAction.setResourceId(resourceId);
        userAction.setType(userActionEnum.getCode());
        save(userAction);
    }

    @Override
    public void collectResource(Integer resourceId) {
        actionResource(RequestContext.getUserId(), resourceId, UserActionEnum.COLLECT);
    }

    @Override
    public void likeResource(Integer resourceId) {
        actionResource(RequestContext.getUserId(), resourceId, UserActionEnum.LIKE);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void exchangeResource(Integer resourceId) {
        Resource resource = resourceMapper.selectById(resourceId);
        Integer price = resource.getPrice();
        Integer userId = RequestContext.getUserId();
        User user = userService.getById(userId);
        if (user.getBonus() < price) {
            throw new ServerException("积分不足");
        }
        actionResource(userId, resourceId, UserActionEnum.EXCHANGE);
        // 兑换人扣减积分
        bonusLogService.addBonusLog(userId, BonusActionEnum.RESOURCE_EXCHANGE, -price);
        // 资源作者增加积分
        bonusLogService.addBonusLog(resource.getAuthor(), BonusActionEnum.RESOURCE_BE_EXCHANGED);
    }

    @Override
    public UserActionListInfo selectResourceListByUserIdAndType(Integer userId, UserActionEnum userActionEnum, Page<UserAction> page) {
        LambdaQueryWrapper<UserAction> queryWrapper = new LambdaQueryWrapper<>();
        Page<UserAction> pageResult = page(page, queryWrapper.eq(UserAction::getUserId, userId).eq(UserAction::getType, userActionEnum.getCode()));
        return new UserActionListInfo(pageResult.getTotal(),
                pageResult.getRecords()
                        .stream()
                        .map(UserAction::getResourceId)
                        .collect(Collectors.toList()));
    }

    @Override
    public Integer selectCountByResourceIdAndType(Integer resourceId, UserActionEnum userActionEnum) {
        LambdaQueryWrapper<UserAction> queryWrapper = new LambdaQueryWrapper<>();
        return Math.toIntExact(count(queryWrapper.eq(UserAction::getResourceId, resourceId).eq(UserAction::getType, userActionEnum.getCode())));
    }

    @Override
    public Boolean resourceIsAction(Integer userId, Integer resourceId, UserActionEnum userActionEnum) {
        LambdaQueryWrapper<UserAction> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserAction::getResourceId, resourceId)
                .eq(UserAction::getUserId, userId)
                .eq(UserAction::getType, userActionEnum.getCode());
        return baseMapper.selectCount(queryWrapper) > 0;
    }

    private void actionResource(Integer userId, Integer resourceId, UserActionEnum userActionEnum) {
        LambdaQueryWrapper<UserAction> queryWrapper = new LambdaQueryWrapper<>();
        // 根据资源id和行为枚举值来查询用户行为
        queryWrapper.eq(UserAction::getResourceId, resourceId)
                .eq(UserAction::getUserId, userId)
                .eq(UserAction::getType, userActionEnum.getCode());
        // 存在就删除（如取消点赞、取消收藏）
        if (baseMapper.selectCount(queryWrapper) > 0) {
            // TODO 兑换资源处理
            baseMapper.delete(queryWrapper);
        } else {
            // 如新增点赞、收藏操作
            insertUserAction(userId, resourceId, userActionEnum);
        }
    }

}
