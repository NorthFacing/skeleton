package com.bob.core.contants;

/**
 * 操作结果码表
 * 
 * @since v0.0.1
 * @author Bob
 * @created 2015年7月6日 下午1:00:48
 */
public class ResultCode {

    /** 操作成功 */
    public static final int SUCCESS = 200;
    public static final String STR_SUCCESS = "SUCCESS";
    /** 操作失败通用码 */
    public static final int FAILED = 444;
    public static final String STR_FAILED = "FAILED";

    /** 参数错误 */
    public static final int ARGS_ERROR = 401;
    /** 找不到资源 */
    public static final int NOT_FOUND = 404;
}