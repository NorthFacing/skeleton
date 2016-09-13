package com.bob.core.utils.shiro.realm;

import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;

/**
 * 这个类主要是为了替换SimpleByteSource之后能够序列化到Redis存储
 * Created by Bob on 2016/9/13.
 */
public class MySimpleByteSource extends SimpleByteSource implements Serializable {

  private static final long serialVersionUID = -6057993431941367624L;

  public MySimpleByteSource(byte[] bytes) {
    super(bytes);
  }

  public MySimpleByteSource(char[] chars) {
    super(chars);
  }

  public MySimpleByteSource(String string) {
    super(string);
  }

  public MySimpleByteSource(ByteSource source) {
    super(source);
  }

  public MySimpleByteSource(File file) {
    super(file);
  }

  public MySimpleByteSource(InputStream stream) {
    super(stream);
  }

}
