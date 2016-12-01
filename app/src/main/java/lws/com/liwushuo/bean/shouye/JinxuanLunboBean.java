package lws.com.liwushuo.bean.shouye;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ZQ on 2016/10/13.
 */

public class JinxuanLunboBean {

    /**
     * code : 200
     * data : {"banners":[{"ad_monitors":[],"channel":"all","id":726,"image_url":"http://img03.liwushuo.com/image/160923/iwedeg4nk.jpg-w720","order":700,"status":0,"target_id":1045899,"target_type":"url","target_url":"liwushuo:///page?page_action=navigation&login=false&type=post&post_id=1045899","type":"post","webp_url":"http://img03.liwushuo.com/image/160923/iwedeg4nk.jpg?imageView2/2/w/720/q/85/format/webp"},{"ad_monitors":[],"channel":"all","id":730,"image_url":"http://img01.liwushuo.com/image/160929/68bib1c1a.jpg-w720","order":600,"status":0,"target_id":null,"target_url":"liwushuo:///page?type=dailylucky","type":"url","webp_url":"http://img01.liwushuo.com/image/160929/68bib1c1a.jpg?imageView2/2/w/720/q/85/format/webp"},{"ad_monitors":[],"channel":"all","id":733,"image_url":"http://img01.liwushuo.com/image/161001/bvftzkor6.jpg-w720","order":300,"status":0,"target":{"banner_image_url":"http://img03.liwushuo.com/image/161001/d50iog9eh.jpg-w300","banner_webp_url":"http://img03.liwushuo.com/image/161001/d50iog9eh.jpg?imageView2/2/w/300/q/85/format/webp","cover_image_url":"http://img01.liwushuo.com/image/161001/vn25u2xq9.jpg-w720","cover_webp_url":"http://img01.liwushuo.com/image/161001/vn25u2xq9.jpg?imageView2/2/w/720/q/85/format/webp","created_at":1475327399,"id":358,"posts_count":7,"status":1,"subtitle":"这个秋季有Ta，让爱恒温相伴","title":"初秋保温杯来袭","updated_at":1475327565},"target_id":358,"target_type":"url","target_url":"liwushuo:///page?page_action=navigation&login=false&type=topic&topic_id=358","type":"collection","webp_url":"http://img01.liwushuo.com/image/161001/bvftzkor6.jpg?imageView2/2/w/720/q/85/format/webp"},{"ad_monitors":[],"channel":"all","id":732,"image_url":"http://img01.liwushuo.com/image/161001/5bk3k6eo5.jpg-w720","order":0,"status":0,"target":{"banner_image_url":"http://img01.liwushuo.com/image/161001/jut2ofrw1.jpg-w300","banner_webp_url":"http://img01.liwushuo.com/image/161001/jut2ofrw1.jpg?imageView2/2/w/300/q/85/format/webp","cover_image_url":"http://img02.liwushuo.com/image/161001/py6yuemqe.jpg-w720","cover_webp_url":"http://img02.liwushuo.com/image/161001/py6yuemqe.jpg?imageView2/2/w/720/q/85/format/webp","created_at":1475327041,"id":357,"posts_count":7,"status":1,"subtitle":"初秋乱穿衣指南，回头率up，up！","title":"初秋换新装","updated_at":1475327214},"target_id":357,"target_type":"url","target_url":"liwushuo:///page?page_action=navigation&login=false&type=topic&topic_id=357","type":"collection","webp_url":"http://img01.liwushuo.com/image/161001/5bk3k6eo5.jpg?imageView2/2/w/720/q/85/format/webp"}]}
     * message : OK
     */
    @SerializedName("code")
    private int code;
    @SerializedName("data")
    private DataBean data;
    @SerializedName("message")
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * ad_monitors : []
         * channel : all
         * id : 726
         * image_url : http://img03.liwushuo.com/image/160923/iwedeg4nk.jpg-w720
         * order : 700
         * status : 0
         * target_id : 1045899
         * target_type : url
         * target_url : liwushuo:///page?page_action=navigation&login=false&type=post&post_id=1045899
         * type : post
         * webp_url : http://img03.liwushuo.com/image/160923/iwedeg4nk.jpg?imageView2/2/w/720/q/85/format/webp
         */
        @SerializedName("banners")
        private List<BannersBean> banners;

        public List<BannersBean> getBanners() {
            return banners;
        }

        public void setBanners(List<BannersBean> banners) {
            this.banners = banners;
        }

        public static class BannersBean {
            @SerializedName("channel")
            private String channel;
            @SerializedName("id")
            private int id;
            @SerializedName("image_url")
            private String image_url;
            @SerializedName("order")
            private int order;
            @SerializedName("status")
            private int status;
            @SerializedName("target_id")
            private int target_id;
            @SerializedName("target_type")
            private String target_type;
            @SerializedName("target_url")
            private String target_url;
            @SerializedName("type")
            private String type;
            @SerializedName("webp_url")
            private String webp_url;
            @SerializedName("ad_monitors")
            private List<?> ad_monitors;

            public String getChannel() {
                return channel;
            }

            public void setChannel(String channel) {
                this.channel = channel;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }

            public int getOrder() {
                return order;
            }

            public void setOrder(int order) {
                this.order = order;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getTarget_id() {
                return target_id;
            }

            public void setTarget_id(int target_id) {
                this.target_id = target_id;
            }

            public String getTarget_type() {
                return target_type;
            }

            public void setTarget_type(String target_type) {
                this.target_type = target_type;
            }

            public String getTarget_url() {
                return target_url;
            }

            public void setTarget_url(String target_url) {
                this.target_url = target_url;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getWebp_url() {
                return webp_url;
            }

            public void setWebp_url(String webp_url) {
                this.webp_url = webp_url;
            }

            public List<?> getAd_monitors() {
                return ad_monitors;
            }

            public void setAd_monitors(List<?> ad_monitors) {
                this.ad_monitors = ad_monitors;
            }

            @Override
            public String toString() {
                return "BannersBean{" +
                        "channel='" + channel + '\'' +
                        ", id=" + id +
                        ", image_url='" + image_url + '\'' +
                        ", order=" + order +
                        ", status=" + status +
                        ", target_id=" + target_id +
                        ", target_type='" + target_type + '\'' +
                        ", target_url='" + target_url + '\'' +
                        ", type='" + type + '\'' +
                        ", webp_url='" + webp_url + '\'' +
                        ", ad_monitors=" + ad_monitors +
                        '}';
            }
        }
    }
}
