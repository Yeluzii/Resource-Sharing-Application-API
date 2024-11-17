package fun.ychen.share.app.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "NoticeQuery", description = "公告查询")
public class NoticeQuery extends Query{
    @Schema(description = "标题")
    private String title;
}
