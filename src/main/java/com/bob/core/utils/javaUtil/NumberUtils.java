package com.bob.core.utils.javaUtil;

/**
 * Created by Bob on 2016/1/21.
 */
public class NumberUtils {

    public static String test(int number) {
        String[] cnZhNum = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
        String[] unit = new String[]{"元", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿"};

        String strNum = String.valueOf(number);

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < strNum.length(); i++) {
            String index = String.valueOf(strNum.charAt(i));
            sb = sb.append(cnZhNum[Integer.parseInt(index)]);
        }

        int i = 0;
        for (int j = strNum.length(); j > 0; j--) {
            sb = sb.insert(j, unit[i++]);
        }
        return sb.toString();
    }

    public static String week(int number) {
        String strWeek;
        switch (number) {
            case 1:
                strWeek = "星期一";
                break;
            case 2:
                strWeek = "星期二";
                break;
            case 3:
                strWeek = "星期三";
                break;
            case 4:
                strWeek = "星期四";
                break;
            case 5:
                strWeek = "星期五";
                break;
            case 6:
                strWeek = "星期六";
                break;
            case 7:
                strWeek = "星期天";
                break;
            default:
                strWeek = "数据错误！";
                break;
        }
        return strWeek;
    }

    public static void main(String[] args) {
        System.out.println(test(9847337));
        System.out.println(week(2));
        System.out.println(week(7));
        System.out.println(week(9));
    }

}