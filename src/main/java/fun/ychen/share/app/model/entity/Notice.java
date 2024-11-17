package fun.ychen.share.app.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("t_notice")
public class Notice {
    @TableId(value = "pk_id", type = IdType.AUTO)
    private Integer pkId;
    private String title;
    private String cover;
    private String content;
    /**
     * @see fun.ychen.share.app.enums.CommonStatusEnum
     */
    private Integer isTop;
    /**
     * @see fun.ychen.share.app.enums.CommonStatusEnum
     */
    private Integer isSwiper;

    @TableField(value = "delete_flag", fill = FieldFill.INSERT)
    @TableLogic
    private Integer deleteFlag;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
