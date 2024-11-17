package fun.ychen.share.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fun.ychen.share.app.model.entity.Notice;
import fun.ychen.share.app.model.query.NoticeQuery;
import fun.ychen.share.app.model.vo.NoticeVO;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

public interface NoticeMapper extends BaseMapper<Notice> {
    List<NoticeVO> indexPageNotice();
    List<NoticeVO> getNoticePage(Page<NoticeVO> page, @Param("query") NoticeQuery query);
    NoticeVO getNoticeDetail(Integer id);
    List<NoticeVO> swiperNotice();
}
