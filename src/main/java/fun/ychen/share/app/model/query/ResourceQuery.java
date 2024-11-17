package fun.ychen.share.app.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "ResourceQuery",description = "资源查询")
public class ResourceQuery extends Query{
    @Schema(description = "关键词")
    private String keyword;
    @Schema(description = "网盘类型")
    private Integer diskType;
    @Schema(description = "资源类型")
    private Integer resType;
    @Schema(description = "标签")
    private Integer tagId;
}
