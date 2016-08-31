package com.bob.core.utils.cert;

import org.apache.commons.codec.binary.Base64;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

/**
 * Created by Bob on 2016/4/27.
 */
public class RSADemo {

  public static void main(String[] args) throws Exception {
    createKeyPairs();
  }

  public static void createKeyPairs() throws Exception {

    Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    // create the keys
    KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "BC");
    generator.initialize(512, new SecureRandom());
    KeyPair pair = generator.generateKeyPair();
    PublicKey pubKey = pair.getPublic();
    PrivateKey privKey = pair.getPrivate();

    byte[] pk = pubKey.getEncoded();
    byte[] privk = privKey.getEncoded();

    String strpk = new String(Base64.encodeBase64(pk));
    String strprivk = new String(Base64.encodeBase64(privk));

    System.out.println("公钥:" + Arrays.toString(pk));
    System.out.println("私钥:" + Arrays.toString(privk));
    System.out.println("公钥Base64编码:" + strpk);
    System.out.println("私钥Base64编码:" + strprivk);

    X509EncodedKeySpec pubX509 = new X509EncodedKeySpec(Base64.decodeBase64(strpk.getBytes()));
    PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decodeBase64(strprivk.getBytes()));

    KeyFactory keyf = KeyFactory.getInstance("RSA", "BC");
    PublicKey pubkey2 = keyf.generatePublic(pubX509);
    PrivateKey privkey2 = keyf.generatePrivate(priPKCS8);

    System.out.println(pubKey.equals(pubkey2));
    System.out.println(privKey.equals(privkey2));
  }
}
