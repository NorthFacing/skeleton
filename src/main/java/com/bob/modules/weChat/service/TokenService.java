package com.bob.modules.weChat.service;

import com.bob.core.utils.EncoderHandler;
import com.bob.modules.weChat.vo.CheckModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;


@Service
public class TokenService {

    /**
     * 微信开发者验证
     *
     * @param wxAccount
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    @Transactional
    public String validate(String wxToken, CheckModel tokenModel) {
        String signature = tokenModel.getSignature();
        Long timestamp = tokenModel.getTimestamp();
        Long nonce = tokenModel.getNonce();
        String echostr = tokenModel.getEchostr();
        if (signature != null && timestamp != null & nonce != null) {
            String[] str = {wxToken, timestamp + "", nonce + ""};
            Arrays.sort(str); // 字典序排序
            String bigStr = str[0] + str[1] + str[2];
            // SHA1加密
            String digest = EncoderHandler.encode("SHA1", bigStr).toLowerCase();
            // 确认请求来至微信
            if (digest.equals(signature)) {
                // 最好此处将echostr存起来，以后每次校验消息来源都需要用到
                return echostr;
            }
        }
        return "error";
    }
}