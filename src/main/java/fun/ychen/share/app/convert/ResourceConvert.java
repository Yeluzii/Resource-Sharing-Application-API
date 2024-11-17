package fun.ychen.share.app.convert;

import fun.ychen.share.app.model.dto.ResourcePublishDTO;
import fun.ychen.share.app.model.entity.Resource;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ResourceConvert {
    ResourceConvert INSTANCE = Mappers.getMapper(ResourceConvert.class);

    Resource convert(ResourcePublishDTO dto);
}
