package fun.ychen.share.app.controller;

import fun.ychen.share.app.common.result.Result;
import fun.ychen.share.app.model.dto.WxLoginDTO;
import fun.ychen.share.app.model.vo.UserLoginVO;
import fun.ychen.share.app.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "认证接口")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "手机号登录")
    public Result<UserLoginVO> loginByPhone(@RequestParam("phone") String phone, @RequestParam("code") String code) {
        return Result.ok(authService.loginByPhone(phone, code));
    }

    @PostMapping("weChatLogin")
    public Result<UserLoginVO> weChatLogin(@RequestBody WxLoginDTO dto) {
        return Result.ok(authService.weChatLogin(dto));
    }

    @PostMapping("/logout")
    @Operation(summary = "登出")
    public Result<Object> logout() {
        authService.logout();
        return Result.ok();
    }

    @PostMapping("/bindPhone")
    @Operation(summary = "绑定⼿机号")
            public Result<String> bindPhone(@RequestParam("phone") String phone, @RequestParam("code") String code,@RequestHeader("Authorization") String accessToken) {
        authService.bindPhone(phone, code, accessToken);
        return Result.ok();
    }

}
