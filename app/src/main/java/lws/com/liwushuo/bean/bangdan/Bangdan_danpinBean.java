package lws.com.liwushuo.bean.bangdan;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by YaYun on 2016/10/15.
 */

public class Bangdan_danpinBean {


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
        @SerializedName("ad_monitors")
        private Object ad_monitors;
        @SerializedName("authentic")
        private Object authentic;
        @SerializedName("brand_id")
        private Object brand_id;
        @SerializedName("brand_order")
        private Object brand_order;
        @SerializedName("category_id")
        private int category_id;
        @SerializedName("comments_count")
        private int comments_count;
        @SerializedName("cover_image_url")
        private String cover_image_url;
        @SerializedName("cover_webp_url")
        private String cover_webp_url;
        @SerializedName("created_at")
        private int created_at;
        @SerializedName("description")
        private String description;
        @SerializedName("detail_html")
        private String detail_html;
        @SerializedName("editor_id")
        private int editor_id;
        @SerializedName("favorited")
        private boolean favorited;
        @SerializedName("favorites_count")
        private int favorites_count;
        @SerializedName("id")
        private int id;
        @SerializedName("keywords")
        private String keywords;
        @SerializedName("liked")
        private boolean liked;
        @SerializedName("likes_count")
        private int likes_count;
        @SerializedName("name")
        private String name;
        @SerializedName("price")
        private String price;
        @SerializedName("purchase_id")
        private String purchase_id;
        @SerializedName("purchase_shop_id")
        private Object purchase_shop_id;
        @SerializedName("purchase_status")
        private int purchase_status;
        @SerializedName("purchase_type")
        private int purchase_type;
        @SerializedName("purchase_url")
        private String purchase_url;

        @SerializedName("rank")
        private RankBean rank;
        @SerializedName("shares_count")
        private int shares_count;
        @SerializedName("short_description")
        private String short_description;
        /**
         * button_title : 去天猫购买
         * name : 天猫
         * page_title : 商品详情
         * type : tmall
         */
        @SerializedName("source")
        private SourceBean source;
        @SerializedName("subcategory_id")
        private int subcategory_id;
        @SerializedName("updated_at")
        private int updated_at;
        @SerializedName("url")
        private String url;
        @SerializedName("image_urls")
        private List<String> image_urls;
        @SerializedName("post_ids")
        private List<?> post_ids;
        @SerializedName("webp_urls")
        private List<String> webp_urls;

        public Object getAd_monitors() {
            return ad_monitors;
        }

        public void setAd_monitors(Object ad_monitors) {
            this.ad_monitors = ad_monitors;
        }

        public Object getAuthentic() {
            return authentic;
        }

        public void setAuthentic(Object authentic) {
            this.authentic = authentic;
        }

        public Object getBrand_id() {
            return brand_id;
        }

        public void setBrand_id(Object brand_id) {
            this.brand_id = brand_id;
        }

        public Object getBrand_order() {
            return brand_order;
        }

        public void setBrand_order(Object brand_order) {
            this.brand_order = brand_order;
        }

        public int getCategory_id() {
            return category_id;
        }

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
        }

        public int getComments_count() {
            return comments_count;
        }

        public void setComments_count(int comments_count) {
            this.comments_count = comments_count;
        }

        public String getCover_image_url() {
            return cover_image_url;
        }

        public void setCover_image_url(String cover_image_url) {
            this.cover_image_url = cover_image_url;
        }

        public String getCover_webp_url() {
            return cover_webp_url;
        }

        public void setCover_webp_url(String cover_webp_url) {
            this.cover_webp_url = cover_webp_url;
        }

        public int getCreated_at() {
            return created_at;
        }

        public void setCreated_at(int created_at) {
            this.created_at = created_at;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDetail_html() {
            return detail_html;
        }

        public void setDetail_html(String detail_html) {
            this.detail_html = detail_html;
        }

        public int getEditor_id() {
            return editor_id;
        }

        public void setEditor_id(int editor_id) {
            this.editor_id = editor_id;
        }

        public boolean isFavorited() {
            return favorited;
        }

        public void setFavorited(boolean favorited) {
            this.favorited = favorited;
        }

        public int getFavorites_count() {
            return favorites_count;
        }

        public void setFavorites_count(int favorites_count) {
            this.favorites_count = favorites_count;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public boolean isLiked() {
            return liked;
        }

        public void setLiked(boolean liked) {
            this.liked = liked;
        }

        public int getLikes_count() {
            return likes_count;
        }

        public void setLikes_count(int likes_count) {
            this.likes_count = likes_count;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getPurchase_id() {
            return purchase_id;
        }

        public void setPurchase_id(String purchase_id) {
            this.purchase_id = purchase_id;
        }

        public Object getPurchase_shop_id() {
            return purchase_shop_id;
        }

        public void setPurchase_shop_id(Object purchase_shop_id) {
            this.purchase_shop_id = purchase_shop_id;
        }

        public int getPurchase_status() {
            return purchase_status;
        }

        public void setPurchase_status(int purchase_status) {
            this.purchase_status = purchase_status;
        }

        public int getPurchase_type() {
            return purchase_type;
        }

        public void setPurchase_type(int purchase_type) {
            this.purchase_type = purchase_type;
        }

        public String getPurchase_url() {
            return purchase_url;
        }

        public void setPurchase_url(String purchase_url) {
            this.purchase_url = purchase_url;
        }

        public RankBean getRank() {
            return rank;
        }

        public void setRank(RankBean rank) {
            this.rank = rank;
        }

        public int getShares_count() {
            return shares_count;
        }

        public void setShares_count(int shares_count) {
            this.shares_count = shares_count;
        }

        public String getShort_description() {
            return short_description;
        }

        public void setShort_description(String short_description) {
            this.short_description = short_description;
        }

        public SourceBean getSource() {
            return source;
        }

        public void setSource(SourceBean source) {
            this.source = source;
        }

        public int getSubcategory_id() {
            return subcategory_id;
        }

        public void setSubcategory_id(int subcategory_id) {
            this.subcategory_id = subcategory_id;
        }

        public int getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(int updated_at) {
            this.updated_at = updated_at;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<String> getImage_urls() {
            return image_urls;
        }

        public void setImage_urls(List<String> image_urls) {
            this.image_urls = image_urls;
        }

        public List<?> getPost_ids() {
            return post_ids;
        }

        public void setPost_ids(List<?> post_ids) {
            this.post_ids = post_ids;
        }

        public List<String> getWebp_urls() {
            return webp_urls;
        }

        public void setWebp_urls(List<String> webp_urls) {
            this.webp_urls = webp_urls;
        }

        public static class RankBean {
            @SerializedName("cover_image")
            private String cover_image;
            @SerializedName("cover_url")
            private Object cover_url;
            @SerializedName("id")
            private int id;
            @SerializedName("mark_style")
            private int mark_style;
            @SerializedName("name")
            private String name;
            @SerializedName("order")
            private int order;
            @SerializedName("status")
            private int status;
            @SerializedName("title")
            private String title;
            @SerializedName("total")
            private int total;

            public String getCover_image() {
                return cover_image;
            }

            public void setCover_image(String cover_image) {
                this.cover_image = cover_image;
            }

            public Object getCover_url() {
                return cover_url;
            }

            public void setCover_url(Object cover_url) {
                this.cover_url = cover_url;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getMark_style() {
                return mark_style;
            }

            public void setMark_style(int mark_style) {
                this.mark_style = mark_style;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
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

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }
        }

        public static class SourceBean {
            @SerializedName("button_title")
            private String button_title;
            @SerializedName("name")
            private String name;
            @SerializedName("page_title")
            private String page_title;
            @SerializedName("type")
            private String type;

            public String getButton_title() {
                return button_title;
            }

            public void setButton_title(String button_title) {
                this.button_title = button_title;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPage_title() {
                return page_title;
            }

            public void setPage_title(String page_title) {
                this.page_title = page_title;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}
