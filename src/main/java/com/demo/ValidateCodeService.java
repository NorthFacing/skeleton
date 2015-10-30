package com.demo;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service("validateCodeService")
public class ValidateCodeService {
    private final static Logger logger = LoggerFactory.getLogger(ValidateCodeService.class);

    // 失效时间
    public static final int EXPIRED_TIME = 2 * 60;

    // 配置信息
    public static final Map<String, String> PARAMS = new HashMap<String, String>() {
        private static final long serialVersionUID = 2602833692769865296L;

        {
            put("usr", "100001");
            put("pwd", "ytkj01");
            put("product", "11111");
//            put("sms", "你好，您的验证码是：{code}，2分钟失效。");
            put("sms", "场景：{scence}，验证码：{code}，2分钟失效。");
        }
    };

    /**
     * 获取有场景信息的验证码方法
     *
     * @param phone 手机号
     * @return
     */
    public boolean sendValidateCode(String phone, String scence) {
        String code = MobleUtil.createValidateCode();
        try {
            Map<String, String> params = Maps.newHashMap(PARAMS);
            params.put("mobile", phone);
            params.put("sms", params.get("sms").replace("{code}", code).replace("{scence}",scence));
            String back = SMSUtil.sendPost("http://115.236.18.150:8088/wmim/SMSSendYD", params);
            logger.info(back);
        } catch (Exception e) {
            logger.error("phone=" + phone, e);
            return false;
        }
        return true;
    }


    /**
     * 获取验证码方法
     *
     * @param phone 手机号
     * @return
     */
    @Deprecated
    public boolean sendValidateCode(String phone) {
        String code = MobleUtil.createValidateCode();
        try {
            Map<String, String> params = Maps.newHashMap(PARAMS);
            params.put("mobile", phone);
            params.put("sms", params.get("sms").replace("{code}", code));
            String back = SMSUtil.sendPost("http://115.236.18.150:8088/wmim/SMSSendYD", params);
            System.out.println(back);
        } catch (Exception e) {
            logger.error("phone=" + phone, e);
            return false;
        }
        return true;
    }

    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) {
        ValidateCodeService code = new ValidateCodeService();
//        code.sendValidateCode("18268029586,18626891049", "test");
        code.sendValidateCode("18268029586", "asdf");
    }

}
