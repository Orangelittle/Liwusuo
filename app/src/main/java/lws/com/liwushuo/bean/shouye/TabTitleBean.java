package lws.com.liwushuo.bean.shouye;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ZQ on 2016/10/12.
 */

public class TabTitleBean {
    @Override
    public String toString() {
        return "TabTitleBean{" +
                "code=" + code +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }

    /**
     * code : 200
     * data : {"candidates":[{"editable":true,"id":10,"name":"送女票","url":""},{"editable":true,"id":129,"name":"海淘","url":null},{"editable":true,"id":125,"name":"创意生活","url":null},{"editable":true,"id":26,"name":"送基友","url":null},{"editable":true,"id":6,"name":"送爸妈","url":null},{"editable":true,"id":17,"name":"送同事","url":null},{"editable":true,"id":24,"name":"送宝贝","url":""},{"editable":true,"id":127,"name":"设计感","url":null},{"editable":true,"id":14,"name":"文艺风","url":null},{"editable":true,"id":126,"name":"奇葩搞怪","url":null},{"editable":true,"id":28,"name":"科技范","url":null},{"editable":true,"id":11,"name":"萌萌哒","url":null}],"channels":[{"editable":false,"id":101,"name":"精选"},{"editable":true,"id":10,"name":"送女票","url":""},{"editable":true,"id":129,"name":"海淘","url":null},{"editable":true,"id":125,"name":"创意生活","url":null},{"editable":true,"id":26,"name":"送基友","url":null},{"editable":true,"id":6,"name":"送爸妈","url":null},{"editable":true,"id":17,"name":"送同事","url":null},{"editable":true,"id":24,"name":"送宝贝","url":""},{"editable":true,"id":127,"name":"设计感","url":null},{"editable":true,"id":14,"name":"文艺风","url":null},{"editable":true,"id":126,"name":"奇葩搞怪","url":null},{"editable":true,"id":28,"name":"科技范","url":null},{"editable":true,"id":11,"name":"萌萌哒","url":null}]}
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
         * editable : true
         * id : 10
         * name : 送女票
         * url :
         */

        @SerializedName("candidates")
        private List<CandidatesBean> candidates;
        /**
         * editable : false
         * id : 101
         * name : 精选
         */
        @SerializedName("channels")
        private List<ChannelsBean> channels;

        public List<CandidatesBean> getCandidates() {
            return candidates;
        }

        public void setCandidates(List<CandidatesBean> candidates) {
            this.candidates = candidates;
        }

        public List<ChannelsBean> getChannels() {
            return channels;
        }

        public void setChannels(List<ChannelsBean> channels) {
            this.channels = channels;
        }

        public static class CandidatesBean {
            @SerializedName("editable")
            private boolean editable;
            @SerializedName("id")
            private int id;
            @SerializedName("name")
            private String name;
            @SerializedName("url")
            private String url;

            public boolean isEditable() {
                return editable;
            }

            public void setEditable(boolean editable) {
                this.editable = editable;
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

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class ChannelsBean {
            @SerializedName("editable")
            private boolean editable;
            @SerializedName("id")
            private int id;

            @Override
            public String toString() {
                return "ChannelsBean{" +
                        "editable=" + editable +
                        ", id=" + id +
                        ", name='" + name + '\'' +
                        '}';
            }

            @SerializedName("name")
            private String name;

            public boolean isEditable() {
                return editable;
            }

            public void setEditable(boolean editable) {
                this.editable = editable;
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
        }
    }
}
