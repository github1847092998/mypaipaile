package com.paishop.utils;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * ����: MyX509TrustManager  * ����: ���ι�����   * ������Ա�� weining  * ����ʱ�䣺  2017/5/5  */
public class WechatX509TrustManager implements X509TrustManager {

    // ���ͻ���֤��
    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

    }

    // ����������֤��
    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

    }

    // ���������ε�X509֤������
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }
}
