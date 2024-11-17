package fun.ychen.share.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fun.ychen.share.app.common.result.PageResult;
import fun.ychen.share.app.enums.BonusActionEnum;
import fun.ychen.share.app.model.entity.BonusLog;
import fun.ychen.share.app.model.query.Query;
import fun.ychen.share.app.model.vo.BonusLogVO;

public interface BonusLogService extends IService<BonusLog> {
    PageResult<BonusLogVO> page(Query query);

    /**
     * 增加积分，直接用枚举类中定义的价格
     * @param userId
     * @param contentEnum
     */
    void addBonusLog(Integer userId, BonusActionEnum contentEnum);

    /**
     * 增加积分，使用入参进行增减
     * @param userId
     * @param contentEnum
     * @param bonus
     */
    void addBonusLog(Integer userId, BonusActionEnum contentEnum, Integer bonus);

    /**
     * 检查今日是否签到的方法
     */
    void dailyCheck();

    /**
     * 今日是否签到
     * @param userId
     * @return
     */
    boolean isTodayCheck(Integer userId);
}
