package com.bob.core.utils.http;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

class MyX509TrustManager implements X509TrustManager {

  public X509Certificate[] getAcceptedIssuers() {
    return null;
  }

  public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
  }

  public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
  }
}
