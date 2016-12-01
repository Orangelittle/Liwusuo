package lws.com.liwushuo.server.fenlei;

import lws.com.liwushuo.bean.fenlei.DanpinBean;
import lws.com.liwushuo.bean.fenlei.DanpinDetailsBean;
import lws.com.liwushuo.bean.fenlei.GonglveListViewItemBean;
import lws.com.liwushuo.bean.fenlei.LanmuBean;
import lws.com.liwushuo.bean.fenlei.LanmuDetailsBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by zhangziyang on 2016/10/12.
 */

public interface FenleiService {
    @GET("/v2/columns")
    Call<LanmuBean> getLanmuBean();

    @GET("/v2/channel_groups/all")
    Call<GonglveListViewItemBean>  getGonglveListViewItemBean();

    @GET("/v2/item_categories/tree")
    Call<DanpinBean>  getDanpinBean();

   // http://api.liwushuo.com/v2/columns/5?limit=20&offset=0
    @GET("/v2/columns/{id}")
    Call<LanmuDetailsBean> getLanmuDetailsBean(@Path("id") int id, @Query("limit") int limit, @Query("offset") int offset);

    ///v2/item_subcategories/7/items
    @GET("/v2/item_subcategories/{id}/items")
    Call<DanpinDetailsBean> getDanpinDetailsBean(@Path("id") int id, @Query("limit") int limit, @Query("offset") int offset);

}
