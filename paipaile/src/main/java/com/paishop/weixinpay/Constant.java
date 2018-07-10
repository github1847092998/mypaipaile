package com.paishop.weixinpay;



public class Constant {
    //访问域
    public static final String DOMAIN = "https://i-test.com.cn";
    //商户appid
    public static final String APP_ID = "";
    //app密钥
    public static final String APP_SECRET = "";
    //
    public static final String APP_KEY = "";
   //商户号
    public static final String MCH_ID = "";  
    //统一下单接口
    public static final String URL_UNIFIED_ORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    //支付通知接口
    public static final String URL_NOTIFY = Constant.DOMAIN + "/wxpay/views/payInfo.jsp";
    //时间格式
    public static final String TIME_FORMAT = "yyyyMMddHHmmss";
    //有效期限
    public static final int TIME_EXPIRE = 2;  //单位是day

}
