package fun.ychen.share.app.controller;

import fun.ychen.share.app.common.result.PageResult;
import fun.ychen.share.app.common.result.Result;
import fun.ychen.share.app.model.dto.UserEditDTO;
import fun.ychen.share.app.model.query.Query;
import fun.ychen.share.app.model.query.UserActionResourceQuery;
import fun.ychen.share.app.model.vo.BonusLogVO;
import fun.ychen.share.app.model.vo.ResourceItemVO;
import fun.ychen.share.app.model.vo.UserInfoVO;
import fun.ychen.share.app.service.BonusLogService;
import fun.ychen.share.app.service.ResourceService;
import fun.ychen.share.app.service.UserActionService;
import fun.ychen.share.app.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Tag(name = "用户接口")
public class UserController {
    private final UserService userService;
    private final BonusLogService bonusLogService;
    private final UserActionService userActionService;
    private final ResourceService resourceService;

    @GetMapping("info")
    @Operation(summary = "查询用户信息")
    public Result<UserInfoVO> userInfo() {
        return Result.ok(userService.userInfo());
    }

    @PostMapping("update")
    @Operation(summary = "修改用户信息")
    public Result<UserInfoVO> update(@RequestBody UserEditDTO userEditDTO) {
        return Result.ok(userService.updateInfo(userEditDTO));
    }

    @PostMapping("bonus/page")
    @Operation(summary = "积分明细")
    public Result<PageResult<BonusLogVO>> bonusPage(@RequestBody @Valid Query query) {
        return Result.ok(bonusLogService.page(query));
    }

    @PostMapping("dailyCheck")
    @Operation(summary = "每日签到")
    public Result<Object> dailyCheck() {
        bonusLogService.dailyCheck();
        return Result.ok();
    }

    @PostMapping("resource/collect")
    @Operation(summary = "收藏资源")
    public Result<Object> collectResource(@RequestParam Integer resourceId) {
        userActionService.collectResource(resourceId);
        return Result.ok();
    }

    @PostMapping("resource/like")
    @Operation(summary = "点赞资源")
    public Result<Object> likeResource(@RequestParam Integer resourceId) {
        userActionService.likeResource(resourceId);
        return Result.ok();
    }

    @PostMapping("resource/exchange")
    @Operation(summary = "兑换下载资源")
    public Result<Object> exchangeResource(@RequestParam Integer resourceId) {
        userActionService.exchangeResource(resourceId);
        return Result.ok();
    }

    @PostMapping("resource")
    @Operation(summary = "资源行为列表")
    public Result<PageResult<ResourceItemVO>> resourcePage(@RequestBody UserActionResourceQuery query) {
        return Result.ok(resourceService.getUserActionResourcePage(query));
    }

}
