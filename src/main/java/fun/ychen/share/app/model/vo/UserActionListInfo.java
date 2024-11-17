package fun.ychen.share.app.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserActionListInfo {
    private long total;
    private List<Integer> resourceIdList;
}
