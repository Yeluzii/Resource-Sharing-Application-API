package fun.ychen.share.app.convert;

import fun.ychen.share.app.model.entity.Tag;
import fun.ychen.share.app.model.vo.TagVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TagConvert {
    TagConvert INSTANCE = Mappers.getMapper(TagConvert.class);

    List<TagVO> convert(List<Tag> tags);
}
