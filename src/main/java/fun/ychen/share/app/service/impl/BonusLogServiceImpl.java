package fun.ychen.share.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.ychen.share.app.common.cache.RequestContext;
import fun.ychen.share.app.common.exception.ErrorCode;
import fun.ychen.share.app.common.exception.ServerException;
import fun.ychen.share.app.common.result.PageResult;
import fun.ychen.share.app.convert.BonusLogConvert;
import fun.ychen.share.app.enums.BonusActionEnum;
import fun.ychen.share.app.mapper.BonusLogMapper;
import fun.ychen.share.app.mapper.UserMapper;
import fun.ychen.share.app.model.entity.BonusLog;
import fun.ychen.share.app.model.entity.User;
import fun.ychen.share.app.model.query.Query;
import fun.ychen.share.app.model.vo.BonusLogVO;
import fun.ychen.share.app.service.BonusLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class BonusLogServiceImpl extends ServiceImpl<BonusLogMapper, BonusLog> implements BonusLogService {
    private final UserMapper userMapper;

    @Override
    public PageResult<BonusLogVO> page(Query query) {
        Integer userId = RequestContext.getUserId();
        Page<BonusLog> bonusLogPage = baseMapper.selectPage(
                new Page<>(query.getPage(), query.getLimit()),
                new LambdaQueryWrapper<BonusLog>()
                        .eq(BonusLog::getUserId, userId));
        List<BonusLogVO> voList = BonusLogConvert.INSTANCE.convert(bonusLogPage.getRecords());
        return new PageResult<>(voList, bonusLogPage.getTotal());
    }

    @Override
    public void addBonusLog(Integer userId, BonusActionEnum contentEnum,Integer bonus) {
        addBonusLogBase(userId, contentEnum.getDesc(), bonus);
    }

    @Override
    public void addBonusLog(Integer userId, BonusActionEnum contentEnum) {
        addBonusLogBase(userId, contentEnum.getDesc(), contentEnum.getBonus());
    }

    private void addBonusLogBase(Integer userId, String content, Integer bonus) {
        BonusLog bonusLog = new BonusLog();
        bonusLog.setUserId(userId);
        bonusLog.setContent(content);
        bonusLog.setBonus(bonus);
        save(bonusLog);
        User user = userMapper.selectById(userId);
        // 添加积分
        user.setBonus(user.getBonus() + bonus);
        userMapper.updateById(user);
    }

    @Override
    public void dailyCheck() {
        Integer userId = RequestContext.getUserId();
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new ServerException(ErrorCode.USER_NOT_EXIST);
        }
        // 检查今日是否签到
        boolean todayCheck = isTodayCheck(userId);
        if (todayCheck) {
            throw new ServerException(ErrorCode.ALREADY_HAS_CHECK);
        }
        // 签到成功，插入数据
        addBonusLog(user.getPkId(), BonusActionEnum.DAILY_SIGN);
    }

    @Override
    public boolean isTodayCheck(Integer userId) {
        Date now = new Date();
        // 设置开始时间和结束时间
        Date start = DateUtils.truncate(now, Calendar.DATE);
        Date end = DateUtils.addMilliseconds(DateUtils.ceiling(now, Calendar.DATE), -1);
        // 构建查询条件并执行查询
        BonusLog bonusLog = baseMapper.selectOne(new LambdaQueryWrapper<BonusLog>().eq(BonusLog::getUserId, userId).eq(BonusLog::getContent, BonusActionEnum.DAILY_SIGN.getDesc()).between(BonusLog::getCreateTime, start, end));
        // 判断查询结果
        return bonusLog != null;
    }

}
