package lws.com.liwushuo.bean.bangdan;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by YaYun on 2016/10/12.
 */

public class BangdanBean {

    @SerializedName("code")
    private int code;

    @SerializedName("data")
    private DataBean data;

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

    public static class DataBean  {
        @SerializedName("cover_image")
        private String cover_image;
        @SerializedName("cover_webp")
        private String cover_webp;
        /**
         * next_url : http://api.liwushuo.com/v2/ranks_v2/ranks/1?limit=20&offset=20
         */
        @SerializedName("paging")
        private PagingBean paging;

        @SerializedName("items")
        private List<ItemsBean> items;

        public String getCover_image() {
            return cover_image;
        }

        public void setCover_image(String cover_image) {
            this.cover_image = cover_image;
        }

        public String getCover_webp() {
            return cover_webp;
        }

        public void setCover_webp(String cover_webp) {
            this.cover_webp = cover_webp;
        }

        public PagingBean getPaging() {
            return paging;
        }

        public void setPaging(PagingBean paging) {
            this.paging = paging;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }



        public static class PagingBean {
            @SerializedName("next_url")
            private String next_url;

            public String getNext_url() {
                return next_url;
            }

            public void setNext_url(String next_url) {
                this.next_url = next_url;
            }
        }

        public static class ItemsBean {
            @SerializedName("category_id")
            private int category_id;
            @SerializedName("cover_image_url")
            private String cover_image_url;
            @SerializedName("cover_webp_url")
            private String cover_webp_url;
            @SerializedName("created_at")
            private int created_at;
            @SerializedName("description")
            private String description;
            @SerializedName("editor_id")
            private int editor_id;
            @SerializedName("favorites_count")
            private int favorites_count;
            @SerializedName("id")
            private int id;
            @SerializedName("name")
            private String name;
            @SerializedName("order")
            private int order;
            @SerializedName("price")
            private String price;
            @SerializedName("purchase_id")
            private String purchase_id;
            @SerializedName("purchase_status")
            private int purchase_status;
            @SerializedName("purchase_type")
            private int purchase_type;
            @SerializedName("purchase_url")
            private String purchase_url;
            @SerializedName("short_description")
            private String short_description;
            @SerializedName("subcategory_id")
            private int subcategory_id;
            @SerializedName("updated_at")
            private int updated_at;
            @SerializedName("url")
            private String url;
            @SerializedName("image_urls")
            private List<String> image_urls;
            @SerializedName("detail_html")
            private String detail_html;

            public String getDetail_html() {
                return detail_html;
            }

            public void setDetail_html(String detail_html) {
                this.detail_html = detail_html;
            }

            public int getCategory_id() {
                return category_id;
            }

            public void setCategory_id(int category_id) {
                this.category_id = category_id;
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

            public int getEditor_id() {
                return editor_id;
            }

            public void setEditor_id(int editor_id) {
                this.editor_id = editor_id;
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

            public String getShort_description() {
                return short_description;
            }

            public void setShort_description(String short_description) {
                this.short_description = short_description;
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

        }
    }
}
