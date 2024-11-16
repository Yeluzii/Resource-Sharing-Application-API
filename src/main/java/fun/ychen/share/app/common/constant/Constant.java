package fun.ychen.share.app.common.constant;

public interface Constant {
    // 创建时间
    String CREATE_TIME = "createTime";
    // 更新时间
    String UPDATE_TIME = "updateTime";
    // 逻辑删除
    String DELETE_FLAG = "deleteFlag";
    // 用户id
    String USER_ID = "userId";

    // 微信小程序 appId
    String APP_ID = "wxe55f19cd9b0d6d92";
    // appSecret
    String APP_SECRET = "8cd77d0a0dfc5767dc45b2d004506dfe";
    // 微信返回参数中的属性名
    String WX_ERR_CODE = "errcode";
    // 返回参数中的属性名
    String WX_OPENID = "openid";
    // 返回参数中的属性名
    String WX_SESSION_KEY = "session_key";
    // 前端没有登录的时候会携带的token，后续会⽤到
    String NO_TOKEN = "no-token";
}
