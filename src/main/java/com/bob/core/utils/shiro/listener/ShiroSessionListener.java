package com.bob.core.utils.shiro.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 保留类，当前并未使用
 */
public class ShiroSessionListener implements SessionListener {

  private static final Logger logger = LoggerFactory.getLogger(ShiroSessionListener.class);

  @Override
  public void onStart(Session session) {
    logger.debug("session {} onStart", session.getId());
  }

  @Override
  public void onStop(Session session) {
    logger.debug("session {} onStop", session.getId());
  }

  @Override
  public void onExpiration(Session session) {
    logger.debug("session {} onExpiration", session.getId());
  }

}
