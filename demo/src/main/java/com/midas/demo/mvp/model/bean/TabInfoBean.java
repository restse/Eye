package com.midas.demo.mvp.model.bean;

import java.io.Serializable;
import java.util.List;

public class TabInfoBean implements Serializable {


    /**
     * tabInfo : {"tabList":[{"id":0,"name":"周排行","apiUrl":"http://baobab.kaiyanapp.com/api/v4/rankList/videos?strategy=weekly","tabType":0,"nameType":0,"adTrack":null},{"id":1,"name":"月排行","apiUrl":"http://baobab.kaiyanapp.com/api/v4/rankList/videos?strategy=monthly","tabType":0,"nameType":0,"adTrack":null},{"id":2,"name":"总排行","apiUrl":"http://baobab.kaiyanapp.com/api/v4/rankList/videos?strategy=historical","tabType":0,"nameType":0,"adTrack":null}],"defaultIdx":0}
     */

    private TabInfo tabInfo;

    public TabInfo getTabInfo() {
        return tabInfo;
    }

    public void setTabInfo(TabInfo tabInfo) {
        this.tabInfo = tabInfo;
    }

    public static class TabInfo {
        /**
         * tabList : [{"id":0,"name":"周排行","apiUrl":"http://baobab.kaiyanapp.com/api/v4/rankList/videos?strategy=weekly","tabType":0,"nameType":0,"adTrack":null},{"id":1,"name":"月排行","apiUrl":"http://baobab.kaiyanapp.com/api/v4/rankList/videos?strategy=monthly","tabType":0,"nameType":0,"adTrack":null},{"id":2,"name":"总排行","apiUrl":"http://baobab.kaiyanapp.com/api/v4/rankList/videos?strategy=historical","tabType":0,"nameType":0,"adTrack":null}]
         * defaultIdx : 0
         */

        private List<Tab> tabList;

        public List<Tab> getTabList() {
            return tabList;
        }

        public void setTabList(List<Tab> tabList) {
            this.tabList = tabList;
        }

        public static class Tab {
            /**
             * id : 0
             * name : 周排行
             * apiUrl : http://baobab.kaiyanapp.com/api/v4/rankList/videos?strategy=weekly
             * tabType : 0
             * nameType : 0
             * adTrack : null
             */

            private Long id;
            private String name;
            private String apiUrl;

            public Long getId() {
                return id;
            }

            public void setId(Long id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getApiUrl() {
                return apiUrl;
            }

            public void setApiUrl(String apiUrl) {
                this.apiUrl = apiUrl;
            }
        }
    }
}
