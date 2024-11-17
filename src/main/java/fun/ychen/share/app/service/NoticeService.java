package fun.ychen.share.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fun.ychen.share.app.common.result.PageResult;
import fun.ychen.share.app.model.entity.Notice;
import fun.ychen.share.app.model.query.NoticeQuery;
import fun.ychen.share.app.model.vo.NoticeVO;

import java.util.List;

public interface NoticeService extends IService<Notice> {
    List<NoticeVO> indexPageNotice();

    PageResult<NoticeVO> getNoticeList(NoticeQuery query);

    NoticeVO detail(Integer id);

    List<NoticeVO> swiperNotice();
}
