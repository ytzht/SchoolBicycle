package com.school.bicycle.global;


/**
 * Created by zht on 2017/04/19 17:09
 */

public interface APIService {

//    //1.1首页
//    @POST("pub/c/homePage")
//    Observable<HttpResult<HomePage>> getHomePage();
//
//    //1.2免费咨询提交
//    @Multipart
//    @POST("c/freeask")
//    Observable<HttpResult<AskResult>> freeUpload(@PartMap Map<String, RequestBody> params);
//
//    //1.3打赏咨询提交
//    @Multipart
//    @POST("c/makeMoneyAskOrderAndGetPayInfo")
//    Observable<HttpResult<AskResult>> payUpload(@PartMap Map<String, RequestBody> params);
//
//
//    //1.4快速咨询类别
//    @POST("c/freeAskCategory")
//    Observable<HttpResult<FreeAskCategory>> getTags();
//
//    //1.5免费咨询价格和用户当前积分
//    @POST("c/my/pointsInfo")
//    Observable<HttpResult<PointsInfo>> getPointsInfo(@Query("userId") String userId);
//
//    //1.6获取id对应的图片
//    @GET("pic/{picId}")
//    Observable<BaseResult> getPicById(@Path("picId") String id);
//
//    //1.7获取单个会话列表
//    @GET("c/conversation/getList")
//    Observable<HttpResult<ConversationList>> getConversationList(@Query("userId") String userId,
//                                                                 @Query("chatId") String chatId,
//                                                                 @Query("freeaskId") String freeaskId,
//                                                                 @Query("conversationId") long conversationId,
//                                                                 @Query("direction") String direction,
//                                                                 @Query("size") int size);
//
//    //1.8获取免费提问详情
//    @GET("c/freeaskDetail")
//    Observable<HttpResult<FreeaskDetail>> getFreeaskDetail(@Query("userId") String userId,
//                                                           @Query("freeaskId") String freeaskId);//免费提问Id
//
//    //1.9评价律师
//    @FormUrlEncoded
//    @POST("c/comment")
//    Observable<BaseResult> getCommentLawyer(@FieldMap Map<String, String> params);
//
//    //1.10常用评价标签列表
//    @GET("c/commonTagList")
//    Observable<HttpResult<CommonTagList>> getCommonTagList();
//
//    //1.11送心意
//    @FormUrlEncoded
//    @POST("c/giveMoneyOrderAndGetPayInfo")
//    Observable<HttpResult<GiveMoneyOrderAndGetPayInfo>> getGiveMoneyOrderAndGetPayInfo(@FieldMap Map<String, String> params);
//
//    //1.12是否已收藏（关注）律师
//    @GET("c/isFavorite")
//    Observable<HttpResult<IsFavorite>> getIsFavorite(@Query("userId") String userId,
//                                                     @Query("lawyerId") String lawyerId);
//
//    //1.13收藏（关注）律师
//    @GET("c/favoriteLawyer")
//    Observable<HttpResult<IsFavorite>> getFavoriteLawyer(@Query("userId") String userId,
//                                                         @Query("lawyerId") String lawyerId,
//                                                         @Query("favorite") boolean favorite);
//
//    //1.14 服务列表
//    @GET("c/conversation/chatList")
//    Observable<HttpResult<ChatList>> getChatList(@Query("userId") long userId,
//                                                 @Query("page") int page,//页数
//                                                 @Query("size") int size,//行数
//                                                 @Query("status") String status,
//                                                 @Query("type") int type);
//
//    //1.15 发送对话
//    @FormUrlEncoded
//    @POST("c/conversation/send")
//    Observable<HttpResult<SendMsg>> getSendMsg(@Field("userId") String userId,
//                                               @Field("chatId") String chatId,//订单ID
//                                               @Field("content") String content);
//    @Multipart
//    @POST("c/conversation/send")
//    Observable<HttpResult<SendMsg>> getSendPic(@PartMap Map<String, RequestBody> params);
//
//    @GET("pub/payForTest")
//    Observable<BaseResult> payForeTest(@Query("orderId") String orderId);
//
//    @GET("c/my/balanceInfo")
//    Observable<HttpResult<PriceList>> getPriceList(@Query("userId") long userId);
//
//    @GET("c/lawyerbasic")
//    Observable<HttpResult<LawyerBasic>> getLawyerBasic(@Query("lawyerId") long lawyerId);
}
