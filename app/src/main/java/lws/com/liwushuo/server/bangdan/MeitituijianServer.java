package lws.com.liwushuo.server.bangdan;

import lws.com.liwushuo.bean.bangdan.BangdanBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by zhangziyang on 2016/10/12.
 */

public interface MeitituijianServer {
    @GET("/v2/ranks_v2/ranks/{id}")
    Call<BangdanBean> getBandanBen(@Path("id") int id, @Query("limit") int limit, @Query("offset") int offset);
}
