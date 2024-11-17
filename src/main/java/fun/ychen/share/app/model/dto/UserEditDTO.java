package fun.ychen.share.app.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "用户修改dto")
public class UserEditDTO {
    @Schema(description = "主键")
    private Integer pkId;
    @Schema(description = "头像")
    private String avatar;
    @Schema(description = "昵称")
    private String nickname;
    @Schema(description = "性别")
    private Integer gender;
    @Schema(description = "生日")
    private String birthday;
    @Schema(description = "描述")
    private String remark;
}
