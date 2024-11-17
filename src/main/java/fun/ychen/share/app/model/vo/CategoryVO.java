package fun.ychen.share.app.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "CategoryVO", description = "分类返回vo")
public class CategoryVO {
    @Schema(name = "pkId", description = "主键")
    private Integer pkId;

    @Schema(name = "title", description = "标题")
    private String title;

    @Schema(name = "description", description = "描述")
    private String description;

    @Schema(name = "type", description = "类型")
    private Integer type;
}
