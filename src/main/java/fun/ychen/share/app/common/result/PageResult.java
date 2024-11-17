package fun.ychen.share.app.common.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Schema(name = "分页数据")
public class PageResult<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = -4353168213766210440L;

    @Schema(name = "总记录数")
    private int total;

    @Schema(name = "列表数据")
    private List<T> list;

    /**
     * 分页
     * @param list 列表数据
     * @param total 总记录数
     */
    public PageResult(List<T> list,long total) {
        this.list = list;
        this.total = (int) total;
    }
}
