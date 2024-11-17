package fun.ychen.share.app.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(name = "NoticeVO", description = "公告返回vo")
public class NoticeVO {
    @Schema(description = "主键")
    private Integer pkId;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "封面")
    private String cover;

    @Schema(description = "内容")
    private String content;

    @Schema(description = "是否置顶")
    private Integer isTop;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
}
