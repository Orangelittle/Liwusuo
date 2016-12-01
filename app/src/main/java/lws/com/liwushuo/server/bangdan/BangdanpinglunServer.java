package lws.com.liwushuo.server.bangdan;

import lws.com.liwushuo.bean.bangdan.BangdanPinglunBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by YaYun on 2016/10/14.
 */

public interface BangdanpinglunServer {
    @GET("/v2/items/{id}/comments")
    Call<BangdanPinglunBean> getBangdanPinglunBean(@Path("id") int id, @Query("limit") int limit, @Query("offset") int offset);
}
