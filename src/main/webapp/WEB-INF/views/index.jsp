<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html lang="ko">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${path}/resources/css/index.css" rel="stylesheet"/>
    </head>
    <body>
        <% String roomId= "${chatRoom.roomId}"; %>

        <div><h3>${chatRoom.name}</h3></div>
        <div>
            <div>
                <input type="text" class="name" aria-label="Recipient's username" />
                <button class="enter">참가</button>
            </div>
            <div class="hidden">
               <input type="text" class="msg" aria-label="Recipient's username" />
               <button class="talk">전송</button>
            </div>
            <div class="msg-area">

            </div>

        </div>
        <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
        <script>

            const name = document.querySelector(".name")
            const enter = document.querySelector(".enter")
            const talk = document.querySelector(".talk")
            const id = "${chatRoom.roomId}";
            console.log(id);
            let userName;
            let websocket;
            let stomp;

            enter.addEventListener('click',()=>{
                const hidden = document.querySelector(".hidden")
                hidden.classList.remove("hidden")
                const div = name.parentNode;
                div.classList.add("hidden");
                userName = name.value;


              const sock = new SockJS("/ws-stomp");
              stomp = Stomp.over(sock);

              stomp.connect({}, function(frame) {
                console.log("STOMP Connection")

               //4. subscribe(path, callback)으로 메세지를 받을 수 있음
               stomp.subscribe("/sub/chat/room/" + id, function (chat) {
                   const content = JSON.parse(chat.body);
                   console.log(content);
                   const writer = content.sender;
                   const message = content.message
                   if(writer == userName){
                       const userMsg = document.createElement("div");
                       userMsg.innerHTML = `<b>\${writer} : \${message}</b>`
                       const msgArea = document.querySelector('.msg-area');
                       msgArea.appendChild(userMsg);
                   }
                   else{
                       const userMsg = document.createElement("div");
                       userMsg.innerHTML = `\${writer} : \${message}`
                       const msgArea = document.querySelector('.msg-area');
                       msgArea.appendChild(userMsg);
                   }
               });
                   //3. send(path, header, message)로 메세지를 보낼 수 있음
                   stomp.send('/pub/chat/message', {}, JSON.stringify({type: 'ENTER', roomId: id, sender: userName}))
             });
            });

            talk.addEventListener('click' , ()=>{
                const msg = document.querySelector(".msg");
                stomp.send("/pub/chat/message", {}, JSON.stringify({type: 'TALK', roomId: id, sender: userName, message: msg.value}));
                msg.value = '';
            });
        </script>
    </body>
</html>