package com.demo;

import com.google.common.collect.Maps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Map;

public class SMSUtil {


    public static final String GB2312 = "GB2312";
    public static final String UTF8 = "UTF-8";

    /**
     * 发送POST请求
     *
     * @param url        目的地址
     * @param parameters 请求参数，Map类型。
     * @return 远程响应结果
     */
    public static String sendPost(String url, Map<String, String> parameters) {
        String result = "";// 返回的结果
        BufferedReader in = null;// 读取响应输入流
        PrintWriter out = null;
        StringBuffer sb = new StringBuffer();// 处理请求参数
        String params = "";// 编码之后的参数

        long beginTime = System.currentTimeMillis();

        java.net.HttpURLConnection httpConn = null;
        try {
            // 编码请求参数
            if (parameters.size() == 1) {
                for (String name : parameters.keySet()) {
                    sb.append(name)
                            .append("=")
                            .append(URLEncoder.encode(parameters.get(name),
                                    GB2312));
                }
                params = sb.toString();
            } else {
                for (String name : parameters.keySet()) {
                    sb.append(name)
                            .append("=")
                            .append(URLEncoder.encode(parameters.get(name),
                                    GB2312)).append("&");
                }
                String temp_params = sb.toString();
                params = temp_params.substring(0, temp_params.length() - 1);
            }
//            System.out.println("params:" + url+ "" + params);
            // 创建URL对象
            java.net.URL connURL = new java.net.URL(url);
            // 打开URL连接
            httpConn = (java.net.HttpURLConnection) connURL.openConnection();

            // 设置通用属性
            httpConn.setRequestProperty("Accept", "*/*");
            httpConn.setRequestProperty("Connection", "Keep-Alive");
            httpConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
            // 设置POST方式
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);
            // 获取HttpURLConnection对象对应的输出流
            out = new PrintWriter(httpConn.getOutputStream());
            // 发送请求参数
            out.write(params);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应，设置编码方式
            in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), GB2312));
            String line;
            // 读取返回的内容
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }

                httpConn.getInputStream().close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        long sub = System.currentTimeMillis() - beginTime;
        System.out.println("Thread ==>  " + sub);

        return result;
    }

    public static void main(String[] args) throws Exception {
        Map<String, String> parameters = Maps.newHashMap();
        parameters.put("usr", "100001");
        parameters.put("pwd", "ytkj01");
        parameters.put("product", "11111");
        parameters.put("mobile", "13588270294");
        parameters.put("sms", "你好，您的验证码是：789321【远图医疗】");
// 		parameters.put("message", URLEncoder.encode("你好，您的验证码GBK是：789321【签名】","GBK"));
// 		parameters.put("message", URLEncoder.encode("你好，您的验证码UTF8是：789321【签名】","UTF-8"));
        System.out.println(parameters);

        for (int j = 0; j < 1; j++) {
            System.out.println(sendPost("http://115.236.18.150:8088/wmim/SMSSendYD", parameters));
        }



//        Map<String, String> parameters = Maps.newHashMap();
//        parameters.put("account", "jiekou-clcs-03");
//        parameters.put("pswd", "Admin888");
////        parameters.put("product", "11111");
//        parameters.put("needstatus", "false");
//        parameters.put("mobile", "13588270294");
//        parameters.put("sms", "您好，您的验证码是111 ");
//// 		parameters.put("message", URLEncoder.encode("你好，您的验证码GBK是：789321【签名】","GBK"));
//// 		parameters.put("message", URLEncoder.encode("你好，您的验证码UTF8是：789321【签名】","UTF-8"));
//        System.out.println(parameters);
//
//        for (int j = 0; j < 2; j++) {
//            System.out.println(sendPost("http://222.73.117.158/msg/HttpBatchSendSM", parameters));
//        }
    }
}
