package com.audio.commons.exception;

/**
 * author daigai
 */
public class ErrorCode
{
    public static final String SUCCESS = "000000";

    public static final String ERROR = "000001";

    public static final String SYSTEM_BUSY = "200001";

    public static final String SYSTEM_DB_ERROR = "200002";

    public static final String SYSTEM_HOME_LOAD_ERROR = "200003";

    public interface SystemMessage
    {
        /**
         * 修改密码失败
         */
        public static final String SYSTEM_MESSAGE_EDITPWD = "600000";

        /**
         * 添加用户失败
         */
        public static final String SYSTEM_MESSAGE_ADDADMIN = "600001";

        /**
         * 查询所有角色失败
         */
        public static final String SYSTEM_MESSAGE_QUERYROLELIST = "600002";

        /**
         * 查询所有的管理员信息失败
         */
        public static final String SYSTEM_MESSAGE_QUERYALLUSER = "600003";

        /**
         * 单个和批量删除失败
         */
        public static final String SYSTEM_MESSAGE_DELADMIN = "600004";

        /**
         * 查询所有的角色信息失败
         */
        public static final String SYSTEM_MESSAGE_QUERYALLROLE = "600005";

        /**
         * 添加角色信息失败
         */
        public static final String SYSTEM_MESSAGE_ADDROLE = "600006";

        /**
         * 删除角色信息失败
         */
        public static final String SYSTEM_MESSAGE_DELROLE = "600007";

        /**
         * 根据角色信息查询权限集合失败
         */
        public static final String SYSTEM_MESSAGE_QUERYRIGHTLISTBYROLE = "600008";

        /**
         * 返回角色信息失败
         */
        public static final String SYSTEM_MESSAGE_QUERYROLEBYID = "600009";

        /**
         * 修改角色信息失败
         */
        public static final String SYSTEM_MESSAGE_EDITROLE = "600010";

        /**
         * 根据loginName获取用户基本信息失败
         */
        public static final String SYSTEM_MESSAGE_QUERYUSERINFOBYLOGINNAME = "600011";

        /**
         * 根据loginName获取用户权限ID失败
         */
        public static final String SYSTEM_MESSAGE_QUERYUSESRROLEIDBYLOGINNAME = "600012";

        /**
         * 查询所有的权限失败
         */
        public static final String SYSTEM_MESSAGE_QUERYALLRIGHT = "600013";

        /**
         * 保存修改的信息失败
         */
        public static final String SYSTEM_MESSAGE_SAVAEIDTADMIN = "600014";

        /**
         * 查询对应的角色下的用户失败
         */
        public static final String SYSTEM_MESSAGE_QUERYALLUSERBYROLE = "600015";

        /**
         * 验证角色名是否存在失败
         */
        public static final String SYSTEM_MESSAGE_CHEACKROLENAME = "600016";

        /**
         * 验证用户名是否存在失败
         */
        public static final String SYSTEM_MESSAGE_CHEACKADMINNAME = "600017";
    }

    public interface LeaveMessage
    {
        /**
         * 无法连接留言服务器
         */
        public static final String MESSAGE_CONNNET_SERVER_ERROR = "230001";

        /**
         * 真实姓名填写不正确，请输入正确的中文姓名
         */
        public static final String MESSAGE_FILL_NAME_ERROR = "230002";

        /**
         * 手机号码填写不正确，请输入正确的手机号码
         */
        public static final String MESSAGE_FILL_FHONE_NUM_ERROR = "230003";

        /**
         * 详细地址填写不正确，请输入正确的详细地址
         */
        public static final String MESSAGE_FILL_ADDRESS_ERROR = "230004";

        /**
         * 邮箱地址填写不正确，请输入正确的邮箱地址
         */
        public static final String MESSAGE_FILL_EMAIL_ERROR = "230005";

        /**
         * 留言内容填写不正确，请输入正确的留言内容
         */
        public static final String MESSAGE_FILL_CONTENT_ERROR = "230006";

        /**
         * 留言添加到数据库失败
         */
        public static final String MESSAGE_DB_ADD_ERROR = "230007";

        /**
         * 查询未发送到服务器消息失败
         */
        public static final String MESSAGE_QUERY_NOT_SEND_ERROR = "230008";

        /**
         * 更新未发送消息失败
         */
        public static final String MESSAGE_UPDATE_NOT_SEND_ERROT = "230009";
    }

    public static interface Login
    {
        public static final String USER_FIND_PWD_SUCCESS = "210000";

        public static final String USER_LOGIN_NAME_ERROR = "210001";

        public static final String USER_LOGIN_PASS_ERROR = "210002";

        public static final String USER_LOGIN_CODE_ERROR = "210003";

        public static final String USER_LOGIN_MOBILE_ERROR = "210004";

        public static final String USER_LOGIN_ERROR = "210005";

        public static final String USER_LOGIN_ADD_LOG_ERROR = "210006";

        public static final String USER_QUERY_LOGIN_TIME_ERROR = "210007";

        public static final String USER_FIND_PWD_ERROR = "210008";

        public static final String USER_REGISTER_ERROR = "210009";

        public static final String USER_ROLE_ERROR = "210010";

        public static final String SMS_SEND_ERROR = "210011";

        public static final String USER_LOGIN_VIP_ERROR = "210012";

    }

    public interface QuickMsg
    {
        /**
         * 快捷留言添加到数据库失败
         */
        public static final String QUICK_MESSAGE_DB_ADD_ERROR = "250001";

        /**
         * 查询快捷留言失败
         */
        public static final String QUICK_MESSAGE_QUERY_FAILED = "250002";

        /**
         * 修改快捷留言失败
         */
        public static final String QUICK_MESSAGE_EDIT_FAILED = "250003";

        /**
         * 删除快捷留言失败
         */
        public static final String QUICK_MESSAGE_DELETE_FAILED = "250004";

        /**
         * 配置自动回复失败
         */
        public static final String QUICK_MESSAGE_AUTO_REPLY_FAILED = "250005";

        /**
         * 查询自动回复快捷留言失败
         */
        public static final String QUICK_MESSAGE_AUTO_REPLY_QUERY_FAILED = "250006";

    }

    public static interface Order
    {
        /**
         * 按条件查询订单出错
         */
        public static final String ORDER_QUERYBYCONDITION_ERROR = "400001";

        /**
         * 查询订单详情失败
         */
        public static final String ORDER_QUERYBYID_FAILED = "400002";

        /**
         * 取消订单失败
         */
        public static final String ORDER_CANCEL_FAILED = "400003";

        /**
         * 增加订单
         */
        public static final String ORDER_ADD_ERROR = "400004";

        /**
         * 查询订单数量失败
         */
        public static final String QUERY_ORDER_CNT_ERR0R = "400005";

        /**
         * 修改订单失败
         */
        public static final String ORDER_EDIT_ERROR = "400006";

    }

    public static interface Message
    {
        /**
         * 按条件查询留言错误
         */
        public static final String MESSAGE_QUERYALLMESSAGE_ERROR = "800001";

        /**
         * 根据留言ID批量还原留言或删除留言错误
         */
        public static final String MESSAGE_HANDLEMESSAGE_ERROR = "800002";

        /**
         * 回复留言错误
         */
        public static final String MESSAGE_REPLYCONTENT_ERROR = "800003";

        /**
         * 查询快捷留言错误
         */
        public static final String MESSAGE_QUERYMESSAGE_ERROR = "800004";

        /**
         * 根据ID 查询 留言详细信息错误
         */
        public static final String MESSAGE_QUERYMESSAGEEVTBYID_ERROR = "800005";

        /**
         * 查询未回复留言错误
         */
        public static final String MESSAGE_NOTREPLYMESSAGE_ERROR = "800006";

        /**
         * 获取当前品牌留言总数信息错误
         */
        public static final String MESSAGE_BRAND_COUNT_ERROR = "800007";

        /**
         * 获取当前品牌未回复留言总数信息错误
         */
        public static final String MESSAGE_UNREPL_YBRAND_COUNT_ERROR = "800008";

        /**
         * 获取当前品牌的最新5条留言错误
         */
        public static final String MESSAGE_FIVE_BRAND_ERROR = "800009";

        /**
         * 注册留言错误
         */
        public static final String MESSAGE_REGISTER_ERROR = "800010";

        /**
         * 获取最新10条已导留言错误
         */
        public static final String MESSAGE_TEN_EXPORT_ERROR = "800011";

        /**
         * 获取今天留言总数错误
         */
        public static final String MESSAGE_TODAY_COUNT_ERROR = "800012";

        /**
         * 添加定制留言失败
         */
        public static final String MESSAGE_ADD_CUSTOMIZE_ERROR = "800013";

    }

    public interface Remind
    {
        /**
         * 留言提醒添加到数据库失败
         */
        public static final String REMIND_DB_ADD_ERROR = "260001";

        /**
         * 查询留言提醒失败
         */
        public static final String REMIND_QUERY_FAILED = "260002";

        /**
         * 修改留言提醒失败
         */
        public static final String REMIND_EDIT_FAILED = "260003";

        /**
         * 留言日志添加失败
         */
        public static final String REMIND_LOG_ADD_FAILED = "260004";

    }

    public interface Appeal
    {
        /**
         * 按条件查询信息申诉失败
         */
        public static final String APPEAL_QUERY_ERROR = "900001";

        /**
         * 按条件查询申诉记录失败
         */
        public static final String APPEAL_QUERY_RECORD_ERROR = "900002";

        /**
         * 提交申诉失败
         */
        public static final String APPEAL_ADD_ERROR = "900003";

        /**
         * 查询投资信息失败
         */
        public static final String APPEAL_QUERY_INVESTOR_ERROR = "900004";

        /**
         * 投诉处理失败
         */
        public static final String APPEAL_OPERATE_ERROR = "900008";

    }

    public interface FeedBack
    {
        /**
         * 查询意见反馈数量失败
         */
        public static final String FEEDBACK_QUERY_ERROR = "110001";

        /**
         * 提交意见失败
         */
        public static final String FEEDBACK_ADD_ERROR = "110002";

    }

    public interface Investor
    {
        /**
         * 投资者添加到数据库失败
         */
        public static final String INVESTOR_DB_ADD_ERROR = "460001";

        /**
         * 查询投资者失败
         */
        public static final String INVESTOR_QUERY_FAILED = "460002";

        /**
         * 修改投资者失败
         */
        public static final String INVESTOR_EDIT_FAILED = "460003";

    }

    public interface Cart
    {
        /**
         * 购物车添加到数据库失败
         */
        public static final String CART_DB_ADD_ERROR = "560001";

        /**
         * 查询购物车失败
         */
        public static final String CART_QUERY_FAILED = "560002";

        /**
         * 修改购物车失败
         */
        public static final String CART_EDIT_FAILED = "560003";

        /**
         * 删除购物车失败
         */
        public static final String CART_DELETE_FAILED = "560004";

    }

    public interface Account
    {
        /**
         * 查询充值记录失败
         */
        public static final String QUERY_RECHARGE_RECORD_FAILED = "70000";

        /**
         * 查询充值金额失败
         */
        public static final String QUERY_RECHARGE_MONEY_FAILED = "70001";

        public static final String QUERY_USER_INFO_FAILED = "70002";

        /**
         * 获取所有充值记录失败
         */
        public static final String ACCOUNT_QUERYALLALIPAYRECORD = "70003";

        /**
         * 添加充值记录失败
         */
        public static final String ACCOUNT_ADDALIPAYRECORD = "70004";

        /**
         * 账户充值或扣款失败
         */
        public static final String ACCOUNT_ACCOUNTRECHARGE = "70005";

        /**
         * 更新充值结果失败
         */
        public static final String ACCOUNT_UPDATEALIPAYRECORD = "70006";

        /**
         * 查询信任手机失败
         */
        public static final String ACCOUNT_QUERY_TRUST_FAILED = "70007";

        /**
         * 添加信任手机失败
         */
        public static final String ACCOUNT_ADD_TRUST_FAILED = "70008";

        /**
         * 修改信任手机失败
         */
        public static final String ACCOUNT_EDIT_TRUST_FAILED = "70009";

        /**
         * 删除信任手机失败
         */
        public static final String ACCOUNT_DELETE_TRUST_FAILED = "70010";

    }

    public interface Zone
    {

        /**
         * 查询城市失败
         */
        public static final String ZONE_QUERY_FAILED = "570002";

    }

    public interface DataStatistics
    {

        /**
         * 查询会员信息失败
         */
        public static final String QUERY_USER_DATASTATISTICS_ERROR = "580000";

        /**
         * 查询投诉信息失败
         */
        public static final String QUERY_APPEAL_DATASTATISTICS_ERROR = "580001";

        /**
         * 查询充值信息失败
         */
        public static final String QUERY_ALIPAY_DATASTATISTICS_ERROR = "580002";

        /**
         * 查询交易信息失败
         */
        public static final String QUERY_DEAL_DATASTATISTICS_ERROR = "580003";

    }

}
