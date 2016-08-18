# androidWebsocketDemo
android使用websocket进行长链接的一个简单的demo，可以用来收发消息或别的操作，里面用到了autobahn的jar包

##基本操作都在WebSocketService 这个类中
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
