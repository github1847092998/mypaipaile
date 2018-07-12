package com.paishop.weixinpay;



public class Constant {
    //访问域
    public static final String DOMAIN = "https://www.qiushicx.com";
    //商户appid
    public static final String APP_ID = "wxf0699dbe93322d73";
    //app密钥
    public static final String APP_SECRET = "1078842306d035ad36bc68a3196ce045";
    //
    public static final String APP_KEY = "";
   //商户号
    public static final String MCH_ID = " sz99pai@163.com ";  
    //统一下单接口
    public static final String URL_UNIFIED_ORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    //支付通知返回的页面
    public static final String URL_NOTIFY = Constant.DOMAIN + "/wxpay/views/payInfo.jsp";
    //时间格式
    public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    //有效期限
    public static final int TIME_EXPIRE = 2;  //单位是day
}
