# androidWebsocketDemo
android使用websocket进行长链接的一个简单的demo，可以用来收发消息或别的操作，里面用到了autobahn的jar包

##基本操作都在WebSocketService 这个类中，websocketHost要填写自己服务器的，ws开头的url，代码中我有说
```java
webSocketConnection.connect(websocketHost,new WebSocketHandler(){


                //websocket启动时候的回调
                @Override
                public void onOpen() {
                    Log.d(TAG,"open");
                
                }


                //websocket接收到消息后的回调
                @Override
                public void onTextMessage(String payload) {
                    Log.d(TAG, "payload = " + payload);
                }

                //websocket关闭时候的回调
                @Override
                public void onClose(int code, String reason) {
                    Log.d(TAG, "code = " + code + " reason = " + reason);
                    switch (code) {
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3://手动断开连接
                            
                            break;
                        case 4:
                            break;
                        
                        case 5://网络断开连接
                        
                            break;
                    }
                }
            } , options);
```
##发送消息时的注意
我们公司里是用json进行数据传输的，里面有个cmd字段，是用来区别消息类型，我们传给后台的时候也要用json。具体还是要看手机端和后台商量决定，这里就不写出来了。
##心跳包
如果想保持websocket连接的稳定性，建议加上心跳包。可以每隔一段时间就发个简单的字符串给后台，让后台知道你在线，可以用timetask配合CountDownTimer来写
