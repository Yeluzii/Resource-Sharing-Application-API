package fun.ychen.share.app.enums;

import lombok.Getter;

@Getter
public enum ResourceStatusEnum {
    /**
     * 未审核
     */
    UNAUDITED(0, "未审核"),
    /**
     * 审核通过
     */
    AUDITED(1, "审核通过"),
    /**
     * 审核不通过
     */
    NOT_AUDITED(2, "审核不通过");
    private final Integer code;
    private final String status;

    ResourceStatusEnum(Integer code, String status) {
        this.code = code;
        this.status = status;
    }
}
