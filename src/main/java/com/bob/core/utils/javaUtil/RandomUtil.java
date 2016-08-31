package com.bob.core.utils.javaUtil;

public class RandomUtil {

  public static long getRandom(Integer max, Integer min) {
    return Math.round(Math.random() * (max - min) + min);
  }

  public static void main(String[] args) {
    for (int i = 0; i < 100; i++) {
      System.out.println(getRandom(100000, 999999));
    }
  }
}
