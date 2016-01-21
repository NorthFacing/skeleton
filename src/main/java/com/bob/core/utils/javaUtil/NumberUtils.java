package com.bob.core.utils.javaUtil;

/**
 * Created by Bob on 2016/1/21.
 */
public class NumberUtils {

    public static void test(int number) {
        String[] cnZhNum = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
        String[] unit = new String[]{"元", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿"};

        String strNum = String.valueOf(number);

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < strNum.length(); i++) {
            String index = String.valueOf(strNum.charAt(i));
            sb = sb.append(cnZhNum[Integer.parseInt(index)]);
        }
        String sss = String.valueOf(sb);
        int i = 0;
        for (int j = sss.length(); j > 0; j--) {
            sb = sb.insert(j, unit[i++]);
        }
        System.out.println(sb);
    }

    public static void main(String[] args) {
        NumberUtils d = new NumberUtils();
        d.test(9847337);
    }

}