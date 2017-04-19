package com.bob.core.contants;

/**
 * Created by Bob on 2015/12/16.
 */
public class BizConfig {

  public static String algorithmName;

  public static int hashIterations;

  public String getAlgorithmName() {
    return algorithmName;
  }

  public void setAlgorithmName(String algorithmName) {
    this.algorithmName = algorithmName;
  }

  public int getHashIterations() {
    return hashIterations;
  }

  public void setHashIterations(int hashIterations) {
    this.hashIterations = hashIterations;
  }
}
