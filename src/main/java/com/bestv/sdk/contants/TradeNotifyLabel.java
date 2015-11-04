package com.bestv.sdk.contants;

/**
 * 支付通知字段.
 *
 * @author <a href="http://www.jiangzezhou.com">jiangzezhou</a>
 * @version 1.0.0.0, 6/18/15 16::16
 */
public class TradeNotifyLabel {

    // cp订单号
    public static final String CP_ORDER_ID = "orderId";

    // Bestv平台订单号
    public static final String TRADE_CODE = "tradeCode";

    // 对应渠道平台订单号,保留字段，暂不支持
    public static final String PLAT_SDK_TRADE_CODE = "platTradeCode";

    // 金额,单位分
    public static final String TRADE_PRICE = "price";

    // 完成时间,unix时间戳
    public static final String TRADE_COMPLETE_TIME = "completeTime";

    // sdk平台名称
    public static final String TRADE_PLATFORM_NAME = "platform";

    // 订单透传字段
    public static final String TRADE_PASS_DATA = "passData";

}
