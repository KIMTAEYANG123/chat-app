const name = document.querySelector(".name")
const enter = document.querySelector(".enter")
const talk = document.querySelector(".talk")

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
       stomp.subscribe("/sub/chat/room/" + roomId, function (chat) {
           var content = JSON.parse(chat.body);

           var writer = content.writer;
           var str = '';

           if(writer === username){
               str = "<div class='col-6'>";
               str += "<div class='alert alert-secondary'>";
               str += "<b>" + writer + " : " + message + "</b>";
               str += "</div></div>";
               $("#msgArea").append(str);
           }
           else{
               str = "<div class='col-6'>";
               str += "<div class='alert alert-warning'>";
               str += "<b>" + writer + " : " + message + "</b>";
               str += "</div></div>";
               $("#msgArea").append(str);
           }

           $("#msgArea").append(str);
       });

       //3. send(path, header, message)로 메세지를 보낼 수 있음
       stomp.send('/pub/chat/enter', {}, JSON.stringify({roomId: roomId, writer: username}))
    });
});