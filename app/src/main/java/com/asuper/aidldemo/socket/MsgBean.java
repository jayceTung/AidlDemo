package com.asuper.aidldemo.socket;

import java.util.List;

/**
 * Created by Super on 2016/12/22.
 */

public class MsgBean {

    /**
     * MsgTag : 10010368
     * type : 4
     * contents : [{"content":"恭喜【桃】：此生的唯一在","color":"#ffffff"},{"content":"头奖high翻天","color":"#ffff00","mobileUrl":"http://h5-game.kktv5.com/jewel","webUrl":"http://www.kktv5.com/show/swf/game/game_kbx.swf","type":3},{"content":"中获得金宝箱大奖，价值","color":"#ffffff"},{"content":"300,000","color":"#ffff00","mobileUrl":"http://h5-game.kktv5.com/jewel","webUrl":"http://www.kktv5.com/show/swf/game/game_kbx.swf","type":3},{"content":"秀币","color":"#ffffff"}]
     */

    private int MsgTag;
    private int type;
    /**
     * content : 恭喜【桃】：此生的唯一在
     * color : #ffffff
     */

    private List<ContentsBean> contents;

    public int getMsgTag() {
        return MsgTag;
    }

    public void setMsgTag(int MsgTag) {
        this.MsgTag = MsgTag;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<ContentsBean> getContents() {
        return contents;
    }

    public void setContents(List<ContentsBean> contents) {
        this.contents = contents;
    }

    public static class ContentsBean {
        private String content;
        private String color;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }
}
