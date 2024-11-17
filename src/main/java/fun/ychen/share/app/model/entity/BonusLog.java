package fun.ychen.share.app.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("t_bonus_log")
public class BonusLog {
    @TableId(type = IdType.AUTO)
    private Integer pkId;

    private Integer userId;
    /**
     * @see fun.ychen.share.app.enums.BonusActionEnum
     */
    private String content;

    private Integer bonus;

    @TableField(value = "delete_flag", fill = FieldFill.INSERT)
    @TableLogic
    private Integer deleteFlag;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
