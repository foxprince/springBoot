package com.bestv.sdk.contants;
/**
 *  需要从百视通获取的参数.
 * @author jia.zhichao jia.zhichao@bestv.com.cn
 * @date 2015年8月18日 上午11:27:17
 * @version V1.0.0.0
 * 
BESTV_APP_ID(应用ID)	105
BESTV_CHANNEL_ID（渠道号）	64
APPKEY(应用秘钥)	3a54fed60940c4c2
BESTV_DEV_ID(开发者编号)	38

product_id(计费点代码)	计费点名称	价格(分)
10501	初级攻击药水	500
10502	高级攻击药水	600
10503	终级攻击药水	800
10504	双倍证	1200
10505	许愿水滴	10
10506	财源滚滚拳	300
10507	六六无穷阵	200
10508	招财进宝拳	100
 */
public class BestvParams {

	
	private BestvParams(){
		
	}
	/**
     * 应用编号.
     */
    public static final String APPID = "105";

    /**
     * 应用密钥.
     */
    public static final String APPKEY = "3a54fed60940c4c2";

    /**
     * 渠道id.
     */
    public static final String CHANNEL_ID = "64";

    /**
     * 开发者id.
     */
    public static final String DEV_ID = "38";

    /**
     * 计费点id.
     */
    public static final String PRODUCT_ID = "1206";

}
