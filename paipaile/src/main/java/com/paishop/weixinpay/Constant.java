package com.paishop.weixinpay;



public class Constant {
    //������
    public static final String DOMAIN = "https://i-test.com.cn";
    //�̻�appid
    public static final String APP_ID = "";
    //app��Կ
    public static final String APP_SECRET = "";
    //
    public static final String APP_KEY = "";
   //�̻���
    public static final String MCH_ID = "";  
    //ͳһ�µ��ӿ�
    public static final String URL_UNIFIED_ORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    //֧��֪ͨ�ӿ�
    public static final String URL_NOTIFY = Constant.DOMAIN + "/wxpay/views/payInfo.jsp";
    //ʱ���ʽ
    public static final String TIME_FORMAT = "yyyyMMddHHmmss";
    //��Ч����
    public static final int TIME_EXPIRE = 2;  //��λ��day

}
