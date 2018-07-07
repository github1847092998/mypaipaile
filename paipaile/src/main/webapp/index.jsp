<%-- <%@ page language="java" pageEncoding="UTF-8" import="com.paishop.dao.MyUser"%>
  <!DOCTYPE html>
  <html>
<head>
      <title>Java后端WebSocket的Tomcat实现</title>
  </head>
  <body>
      Welcome<br/>
      <table>
         <tr>
           <td><input id="username" type="text"/></td>
         </tr>
        <tr>
           <td><input id="password" type="text"/></td>
         </tr> 
         <!-- <tr>
           <td><input id="text2" type="text"/></td>
         </tr> 
         <tr>
           <td><input id="text3" type="text"/></td>
         </tr> -->
         <tr>
           <td><button onclick="send()">发送消息</button></td>
         </tr>  
      </table>
     <hr/>
    <button onclick="closeWebSocket()">关闭WebSocket连接</button>
    <hr/>
      <div id="message"></div> 
      <div id="message1"></div>
     <!-- <div id="message1"></div> -->
 </body>
 
<script type="text/javascript">
     var websocket = null;
     //判断当前浏览器是否支持WebSocket
     if ('WebSocket' in window) {
         websocket = new WebSocket("ws://localhost:8080/paishop/websocket");
     }
     else {
         alert('当前浏览器 Not support websocket')
}
 
     //连接发生错误的回调方法
     websocket.onerror = function () {
         setMessageInnerHTML("WebSocket连接发生错误");
     };
 
     //连接成功建立的回调方法
     websocket.onopen = function () {
         setMessageInnerHTML("WebSocket连接成功");
     }
 
    //接收到消息的回调方法
     websocket.onmessage = function (event) {
        setMessageInnerHTML(event.data);
    }
 
    //连接关闭的回调方法
    websocket.onclose = function () {
        setMessageInnerHTML("WebSocket连接关闭");
     }
  //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
     window.onbeforeunload = function () {
        closeWebSocket();
     }

    //将消息显示在网页上
    function setMessageInnerHTML(innerHTML) {
         document.getElementById('message').innerHTML += innerHTML + '<br/>';
         document.getElementById('message1').innerHTML += innerHTML + '<br/>';
        // document.getElementById('message1').innerHTML += innerHTML + '<br/>';
     }
 
     //关闭WebSocket连接
     function closeWebSocket() {
         websocket.close();
     }
 
     //发送消息
     function send() {
        var username = document.getElementById('username').value;
        var password = document.getElementById('password').value;
        
        //var message1 = document.getElementById('text1').value;
        //var message2 = document.getElementById('text2').value;
        //var message3 = document.getElementById('text3').value;
        //text=message+message1;
       // var message1 = document.getElementById('password').value;
         websocket.send(username);
         websocket.send(password);
         /* websocket.send(message1);
         websocket.send(message2);
         websocket.send(message3); */
         //websocket.send(message1);
     }
 </script>
 </html> --%>