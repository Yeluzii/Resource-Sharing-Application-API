package fun.ychen.share.app.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@TableName(value = "t_resource", autoResultMap = true)
public class Resource implements Serializable {
    @Serial
    private static final long serialVersionUID = 2874034565655908150L;
    @TableId(value = "pk_id", type = IdType.AUTO)
    private Integer pkId;
    private String title;
    private Integer author;
    private Integer diskType;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Integer> resType;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Integer> tags;
    private String downloadUrl;
    private String detail;
    private Integer price;
    private Integer likeNum;
    /**
     * @see fun.ychen.share.app.enums.CommonStatusEnum
     */
    private Integer isTop;
    /**
     * @see fun.ychen.share.app.enums.ResourceStatusEnum
     */
    private Integer status;
    private String remark;
    @TableField(value = "delete_flag",fill = FieldFill.INSERT)
    @TableLogic
    private Integer deleteFlag;
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
