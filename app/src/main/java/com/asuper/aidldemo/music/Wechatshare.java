package com.asuper.aidldemo.music;

import android.content.Context;

/**
 * @author super
 * @date 2017/12/28
 */
public class Wechatshare {
    private Context mContext;

//    public void share () {
//        WXMusicObject music = new WXMusicObject();
//        music.musicUrl="http://staff2.ustc.edu.cn/~wdw/softdown/index.asp/0042515_05.ANDY.mp3";
//        WXMediaMessage msg = new WXMediaMessage();
//        msg.mediaObject = music;
//        msg.title = "Music Title";
//        msg.description = "Music Album";
//
//        Bitmap thumb = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.kk_room_beauty_thumb);
//        msg.thumbData = Util.bmpToByteArray(thumb, true);
//
//        SendMessageToWX.Req req = new SendMessageToWX.Req();
//        req.transaction = buliderTransaction("music");
//        req.message = msg;
//        //是否是朋友圈 前者是后者不是
//        req.scene = isTimelineCb.isChecked() ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
//        //第六步:发送给客户端
//        if (api.sendReq(req)) {//发送成功返回true，否则返回false
//            Toast.makeText(mContext, "分享网络音乐成功", Toast.LENGTH_LONG).show();
//        }else{
//            Toast.makeText(mContext, "分享网络音乐失败", Toast.LENGTH_LONG).show();
//        }
//    }
//
//    private String buliderTransaction(final String type){
//        return (type == null) ? String.valueOf(System.currentTimeMillis()):type+System.currentTimeMillis();
//    }


}
