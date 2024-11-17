package fun.ychen.share.app.convert;

import fun.ychen.share.app.model.entity.Category;
import fun.ychen.share.app.model.vo.CategoryVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryConvert {
    CategoryConvert INSTANCE = Mappers.getMapper(CategoryConvert.class);

    List<CategoryVO> convert(List<Category> list);
}
