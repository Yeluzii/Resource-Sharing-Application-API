package fun.ychen.share.app.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("t_category")
public class Category {
    @TableId(value = "pk_id",type = IdType.AUTO)
    private Integer pkId;
    private String title;
    /**
     * 0-网盘，1-资源
     */
    private Integer type;
    private String description;

    @TableField(value = "delete_flag",fill = FieldFill.INSERT)
    @TableLogic
    private Integer deleteFlag;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
