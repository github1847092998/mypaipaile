package com.paishop.weixinpay;



public class Constant {
    //������
    public static final String DOMAIN = "https://www.qiushicx.com";
    //�̻�appid
    public static final String APP_ID = "wxf0699dbe93322d73";
    //app��Կ
    public static final String APP_SECRET = "1078842306d035ad36bc68a3196ce045";
    //
    public static final String APP_KEY = "";
   //�̻���
    public static final String MCH_ID = " sz99pai@163.com ";  
    //ͳһ�µ��ӿ�
    public static final String URL_UNIFIED_ORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    //֧��֪ͨ���ص�ҳ��
    public static final String URL_NOTIFY = Constant.DOMAIN + "/wxpay/views/payInfo.jsp";
    //ʱ���ʽ
    public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    //��Ч����
    public static final int TIME_EXPIRE = 2;  //��λ��day
}
