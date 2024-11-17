package fun.ychen.share.app.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.ychen.share.app.common.result.PageResult;
import fun.ychen.share.app.mapper.NoticeMapper;
import fun.ychen.share.app.model.entity.Notice;
import fun.ychen.share.app.model.query.NoticeQuery;
import fun.ychen.share.app.model.vo.NoticeVO;
import fun.ychen.share.app.service.NoticeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

    @Override
    public List<NoticeVO> indexPageNotice() {
        return baseMapper.indexPageNotice();
    }

    @Override
    public PageResult<NoticeVO> getNoticeList(NoticeQuery query) {
        Page<NoticeVO> page = new Page<>(query.getPage(), query.getLimit());
        List<NoticeVO> list = baseMapper.getNoticePage(page, query);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public NoticeVO detail(Integer id) {
        return baseMapper.getNoticeDetail(id);
    }

    @Override
    public List<NoticeVO> swiperNotice() {
        return baseMapper.swiperNotice();
    }
}
