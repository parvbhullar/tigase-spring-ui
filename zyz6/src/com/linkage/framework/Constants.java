package com.linkage.framework;

public  class  Constants {
	

	private Constants(){}
	
	public final static String WEBROOT="ecmmerce"; 
	
	/*
	 * 前台缓存
	 */
	//供店铺的前台使用该缓存对应的对象为SHOP(包含该Shop对应的有效优惠券)组成的List
	//该缓存的失效时间为24小时,这样可以保证每天失效的优惠券数据同步
	//无论是店铺的状态变化还是优惠券的状态变化都需要重新加载该缓存
	public final static String EC_WEBPREFERENTIAL_SHOPS_COUPONS="ec_webpreferential_shops_coupons";
	//有效的优惠券,关联店铺
	public final static String EC_WEBPREFERENTIAL_COUPONS_SHOPS="ec_webpreferential_coupons_shops";
	//优惠券广告缓存(改变状态时缓存清空)
	public final static String EC_WEBPREFERENTIAL_COUPON_AD="ec_webpreferential_coupon_ad";
	//静态广告缓存(改变状态时缓存清空)
	public final static String EC_WEBPREFERENTIAL_STATIC_AD="ec_webpreferential_static_ad";
	
	/*
	 *后台缓存 
	 */
	//店铺缓存(当店铺被创建、删除、修改、上下线操作时,该缓存要被清空)
	public final static String EC_WEBPREFERENTIAL_SHOPS="ec_webpreferential_shops";
	//最新店铺缓存（当店铺被修改、上下线、删除操作时该缓存要被清空）
	public final static String EC_WEBPREFERENTIAL_SHOPS_NEW="ec_webpreferential_shops_new";
	//优惠券最多店铺缓存(当店铺修改、上下线、删除、优惠券状态变化时该缓存要被清空)
	public final static String EC_WEBPREFERENTIAL_SHOPS_MUCH="ec_webpreferential_shops_much";
	//下载量最大店铺缓存(当店铺修改、上下线、删除时该缓存要被清空,同时由于前台用户下载操作影响该数据的变化因此该缓存要设置一个合理的失效时间,暂定未5分钟)
	public final static String EC_WEBPREFERENTIAL_SHOPS_HOT="ec_webpreferential_shops_hot";
	
	
	//优惠券缓存(当优惠券被创建、修改、上下线操作、删除时该缓存要被清空)
	public final static String EC_WEBPREFERENTIAL_COUPONS="ec_webpreferential_conpous";
	//下载最多优惠券缓存(当优惠券修改、上下线、删除时该缓存要被清空,同时由于前台用户下载操作影响该数据的变化因此该缓存要设置一个合理的失效时间,暂定未5分钟)
	public final static String EC_WEBPREFERENTIAL_COUPONS_HOT="ec_webpreferential_hotconpous";
	//最新优惠券缓存 （当优惠券被修改、上下线、删除操作时该缓存要被清空）
	public final static String EC_WEBPREFERENTIAL_COUPONS_NEW="ec_webpreferential_newconpous";
	//优惠券分页每页显示显示条数
	public final static int EC_WEBPREFERENTIAL_COUPONS_SIZE=4;
	//商户后台管理每页显示条数
	public final static int EC_WEBPREFERENTIAL_MANAGE_SHOPS_SIZE=9;
	//商户分页每页显示显示条数
	public final static int EC_WEBPREFERENTIAL_SHOPS_SIZE=4;
	//每个店铺详细页面显示优惠券条数
	public final static int EC_WEBPREFERENTIAL_SHOPS_COUPONS_SIZE=3;
	
	//短信接口表用户名
	public final static String IT_MT_USER="tong";
}
