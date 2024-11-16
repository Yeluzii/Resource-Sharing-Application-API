package fun.ychen.share.app.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("t_user")
public class User {
    @TableId(value = "pk_id", type = IdType.AUTO)
    private Integer pkId;
    private String phone;
    private String wxOpenId;
    private String avatar;
    private String nickname;
    private Integer gender;
    private String birthday;
    private Integer bonus;
    private String remark;

    /**
     * @see fun.ychen.share.app.enums.AccountStatusEnum
     */
    private Integer enabled;
    @TableField(value = "delete_flag", fill = FieldFill.INSERT)
    @TableLogic
    private Integer deleteFlag;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
