package fun.ychen.share.app.convert;

import fun.ychen.share.app.model.entity.BonusLog;
import fun.ychen.share.app.model.vo.BonusLogVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BonusLogConvert {
    BonusLogConvert INSTANCE = Mappers.getMapper(BonusLogConvert.class);

    List<BonusLogVO> convert(List<BonusLog> list);
}
