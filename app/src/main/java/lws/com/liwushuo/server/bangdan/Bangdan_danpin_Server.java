package lws.com.liwushuo.server.bangdan;

import lws.com.liwushuo.bean.bangdan.Bangdan_danpinBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by YaYun on 2016/10/15.
 */

public interface Bangdan_danpin_Server {
    @GET("/v2/items/{id}")
    Call<Bangdan_danpinBean> getBangdan_danpinBean(@Path("id") int id);
}
