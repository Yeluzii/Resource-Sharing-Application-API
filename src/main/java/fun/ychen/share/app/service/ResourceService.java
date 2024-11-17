package fun.ychen.share.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fun.ychen.share.app.common.result.PageResult;
import fun.ychen.share.app.model.dto.ResourcePublishDTO;
import fun.ychen.share.app.model.entity.Resource;
import fun.ychen.share.app.model.query.UserActionResourceQuery;
import fun.ychen.share.app.model.vo.ResourceDetailVO;
import fun.ychen.share.app.model.vo.ResourceItemVO;
import fun.ychen.share.app.model.query.ResourceQuery;

public interface ResourceService extends IService<Resource> {
    PageResult<ResourceItemVO> getUserActionResourcePage(UserActionResourceQuery query);

    PageResult<ResourceItemVO> getResourcePage(ResourceQuery query);

    ResourceDetailVO getResourceDetail(Integer resourceId, String accessToken);

    void publish(ResourcePublishDTO dto);
}
