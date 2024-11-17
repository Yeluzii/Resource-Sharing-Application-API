package fun.ychen.share.app.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "UserActionResourceQuery")
public class UserActionResourceQuery extends Query{
    /**
     * @see fun.ychen.share.app.enums.UserActionEnum
     */
    @Schema(description = "用户行为")
    private Integer type;
}
