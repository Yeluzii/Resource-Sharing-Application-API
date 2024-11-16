package fun.ychen.share.app.common.interceptor;

import fun.ychen.share.app.common.cache.RequestContext;
import fun.ychen.share.app.common.cache.TokenStoreCache;
import fun.ychen.share.app.common.constant.Constant;
import fun.ychen.share.app.common.exception.ErrorCode;
import fun.ychen.share.app.common.exception.ServerException;
import fun.ychen.share.app.model.vo.UserLoginVO;
import fun.ychen.share.app.service.AuthService;
import fun.ychen.share.app.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@AllArgsConstructor
@Component
public class TokenInterceptor implements HandlerInterceptor {
    private final TokenStoreCache tokenStoreCache;
    private final AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取token
        String accessToken = JwtUtil.getAccessToken(request);
        if (StringUtils.isBlank(accessToken)) {
            throw new ServerException(ErrorCode.UNAUTHORIZED);
        }
        // 校验token
        if (!JwtUtil.validate(accessToken)) {
            throw new ServerException(ErrorCode.UNAUTHORIZED);
        }
        // 验证⽤户登录状态是否正常
        UserLoginVO user = tokenStoreCache.getUser(accessToken);
        if (ObjectUtils.isEmpty(user)) {
            throw new ServerException(ErrorCode.LOGIN_STATUS_EXPIRE);
        }
        // 验证⽤户是否可⽤
        boolean enabledFlag = authService.checkUserEnabled(user.getPkId());
        if (!enabledFlag) {
            throw new ServerException(ErrorCode.ACCOUNT_DISABLED);
        }
        // 保存⽤户id到上下⽂
        RequestContext.put(Constant.USER_ID, user.getPkId());
        return true;
    }
}
