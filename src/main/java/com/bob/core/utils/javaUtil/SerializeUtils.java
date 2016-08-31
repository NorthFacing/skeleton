package com.bob.core.utils.javaUtil;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by johny on 15/9/24.
 */
public class SerializeUtils {

  public static Logger logger = LoggerFactory.getLogger(SerializeUtils.class);

  public static byte[] serialize(Object object) {
    ObjectOutputStream oos = null;
    ByteArrayOutputStream baos = null;
    try {
      // 序列化
      baos = new ByteArrayOutputStream();
      oos = new ObjectOutputStream(baos);
      oos.writeObject(object);
      byte[] bytes = baos.toByteArray();
      return bytes;
    } catch (Exception e) {
      logger.error("序列化出错：", e);
    }
    return null;
  }

  public static Object deserialize(byte[] bytes) {
    ByteArrayInputStream bais = null;
    try {
      // 反序列化
      bais = new ByteArrayInputStream(bytes);
      ObjectInputStream ois = new ObjectInputStream(bais);
      return ois.readObject();
    } catch (Exception e) {
      logger.error("反序列化出错：key ==> " + new String(bytes), e);
    }
    return null;
  }

  public static void main(String[] args) {
    System.out.println(serialize(new String("Hello world")));
    System.out.println(deserialize(serialize(new String("Hello world"))));
  }
}
