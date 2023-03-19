package com.grozziie.testingApplications;

import android.graphics.Bitmap;

import com.google.common.xml.XmlEscapers;

import java.util.List;

import okhttp3.Response;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ShopeeApi {
   /*
    @GET("/api/v1/shop/get")
    Call<ShopResponse> getShopInfo(@Query("shopid") long shopId, @Query("partner_id") int partnerId,
                                   @Query("timestamp") long timestamp, @Query("sign") String sign);
    */
   @GET("api/v2/shop/get")
    Call<Response>getInFo(@Query("shopid")long shopid,@Query("partner_id")int partner_id,@Query("timestamp")long timestamp,@Query("sign")String sign);
   @GET("api/v2/message/partnerId/message/*")
    Call<Response>getMessageInfo(@Query("username")String username, @Query("userImage")Bitmap image,@Query("message_data")String message,@Query("timestamp")long timestamp,@Query("dateandtime")String dateandtime,
                                 @Query("status")String status);
   @GET("api/v2/shoplist")
    Call<Response>getallshop(@Query("shopname")String shopname,@Query("shop_address")String shopaddress,@Query("contact_number")String mumber);
   @GET("api/v2/userlist")
    Call<Response>getalldatafromhere(@Query("username")String username,@Query("main_name")String main_name,@Query("prifileImage")String image,@Query("contactNumber")String contactnumber);
   @GET("api/v2/user/shoplist")
    Call<Response>getAllInforThere(@Query("shopid")long shopid,@Query("partner_id")long partner_id,@Query("timestamp")long timestamp,@Query("sign")String sign);


}
