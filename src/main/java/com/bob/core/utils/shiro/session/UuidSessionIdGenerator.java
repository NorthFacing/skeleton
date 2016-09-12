package com.bob.core.utils.shiro.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Bob on 2016/9/12.
 */
public class UuidSessionIdGenerator implements SessionIdGenerator {

  public Serializable generateId(Session session) {
    return UUID.randomUUID().toString().replace("-", "").toUpperCase();
  }
}
