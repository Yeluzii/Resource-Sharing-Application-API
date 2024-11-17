package fun.ychen.share.app.controller;

import fun.ychen.share.app.common.result.PageResult;
import fun.ychen.share.app.common.result.Result;
import fun.ychen.share.app.model.dto.ResourcePublishDTO;
import fun.ychen.share.app.model.query.ResourceQuery;
import fun.ychen.share.app.model.vo.ResourceDetailVO;
import fun.ychen.share.app.model.vo.ResourceItemVO;
import fun.ychen.share.app.service.ResourceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/resource")
@Tag(name = "资源接口")
@AllArgsConstructor
public class ResourceController {
    private final ResourceService resourceService;

    @PostMapping("page")
    @Operation(summary = "分页查询资源")
    public Result<PageResult<ResourceItemVO>> page(@RequestBody ResourceQuery query) {
        return Result.ok(resourceService.getResourcePage(query));
    }

    @GetMapping("detail/{id}")
    @Operation(summary = "资源详情")
    public Result<ResourceDetailVO> detail(@PathVariable("id") Integer id, @RequestHeader("Authorization") String accessToken) {
        return Result.ok(resourceService.getResourceDetail(id, accessToken));
    }

    @PostMapping("/publish")
    @Operation(summary = "发布资源")
    public Result<Object> publish(@RequestBody ResourcePublishDTO dto) {
        resourceService.publish(dto);
        return Result.ok();
    }
}
