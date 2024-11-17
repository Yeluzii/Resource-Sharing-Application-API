package fun.ychen.share.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fun.ychen.share.app.model.entity.Tag;
import fun.ychen.share.app.model.vo.TagVO;

import java.util.List;

public interface TagService extends IService<Tag> {
    List<TagVO> getTagList();
}
