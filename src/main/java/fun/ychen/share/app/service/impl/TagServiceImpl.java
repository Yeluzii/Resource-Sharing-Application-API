package fun.ychen.share.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.ychen.share.app.convert.TagConvert;
import fun.ychen.share.app.mapper.TagMapper;
import fun.ychen.share.app.model.entity.Tag;
import fun.ychen.share.app.model.vo.TagVO;
import fun.ychen.share.app.service.TagService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Override
    public List<TagVO> getTagList() {
        return TagConvert.INSTANCE.convert(list());
    }
}
