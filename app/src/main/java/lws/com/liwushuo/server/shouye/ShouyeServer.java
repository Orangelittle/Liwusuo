package lws.com.liwushuo.server.shouye;

import lws.com.liwushuo.bean.shouye.GonglueDetaiBottomRecommendBean;
import lws.com.liwushuo.bean.shouye.GonglueDetialBean;
import lws.com.liwushuo.bean.shouye.JinxuanLiwuBean;
import lws.com.liwushuo.bean.shouye.JinxuanLunboBean;
import lws.com.liwushuo.bean.shouye.TabTitleBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ZQ on 2016/10/12.
 */

public interface ShouyeServer {

    @GET("/v2/channels/preset")
    Call<TabTitleBean> getTitleBean(@Query("gender") String gender,@Query("generation") String generation);
    @GET("/v2/banners")
    Call<JinxuanLunboBean> getLunboBean();
   // http://api.liwushuo.com/v2/channels/101/items_v2?ad=2&gender=1&generation=2&limit=20&offset=0
    @GET("/v2/channels/{channelsId}/items_v2")
    Call<JinxuanLiwuBean> getLiwuBean(@Path("channelsId") int channelsId,
                                      @Query("ad") int ad,
                                      @Query("gender") int gender,
                                      @Query("generation") int generation,
                                      @Query("limit") int limit,
                                      @Query("offset") int offset);
    //http://api.liwushuo.com/v2/posts_v2/1046055
    @GET("/v2/posts_v2/{urlId}")
    Call<GonglueDetialBean> getGonglueBean(@Path("urlId") String urlId);

    @GET("api/posts/{urlId}/recommend")
    Call<GonglueDetaiBottomRecommendBean> getBottomRecommendBean(@Path("urlId") String urlId);
}
