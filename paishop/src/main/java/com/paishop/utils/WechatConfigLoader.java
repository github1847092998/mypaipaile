package com.paishop.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ����: WechatConfigLoader  * ����: ΢�ų�ʼ��������Ϣ * ������Ա�� weining  * ����ʱ�䣺  2017/5/5  */
public class WechatConfigLoader {
    //��־��¼����
    private static Logger log = LoggerFactory.getLogger(WechatConfigLoader.class);

    //�����ļ�·��
    private static String wechatPath = "wechat.properties";
    //����ƽ̨Ӧ��Ψһ��ʶ
    private static String appId;
    //����ƽ̨Ӧ����Կ
    private static String appSecret;
    //΢�ŵ������ص���ַ
    private static String backUrl;
    static {
        // ���ʼ������������ļ�
        InputStream in = WechatConfigLoader.class.getClassLoader()
                .getResourceAsStream(wechatPath);
        Properties props = new Properties();
        try {
            props.load(in);
        } catch (IOException e) {
            log.error("load wechat setting error,pleace check the file path:"
                    + wechatPath);
            log.error(e.toString(), e);
        }
        appId = props.getProperty("wechat.appId");
        appSecret = props.getProperty("wechat.appSecret");
        backUrl = props.getProperty("wechat.backUrl");
        log.debug("load wechat setting success,file path:" + wechatPath);
    }

    public static String getAppId() {
        return appId;
    }

    public static String getAppSecret() {
        return appSecret;
    }

    public static String getBackUrl() {
        return backUrl;
    }

    public static void setWechatPath(String wechatPath) {
        WechatConfigLoader.wechatPath = wechatPath;
    }

    public static String getWechatPath() {
        return wechatPath;
    }

    public static void setAppId(String appId) {
        WechatConfigLoader.appId = appId;
    }

    public static void setAppSecret(String appSecret) {
        WechatConfigLoader.appSecret = appSecret;
    }

    public static void setBackUrl(String backUrl) {
        WechatConfigLoader.backUrl = backUrl;
    }
}