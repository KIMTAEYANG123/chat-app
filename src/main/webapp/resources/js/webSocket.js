const name = document.querySelector(".name")
const enter = document.querySelector(".enter")
const talk = document.querySelector(".talk")
let userName;
let websocket;

enter.addEventListener('click',()=>{
    const hidden = document.querySelector(".hidden")
    hidden.classList.remove("hidden")
    const div = name.parentNode;
    div.classList.add("hidden");
    userName = name.value;

    websocket = new WebSocket("ws://localhost:8080/ws/chat");
    websocket.onmessage = onMessage;
    websocket.onopen = onOpen;
    websocket.onclose = onClose;
})

talk.addEventListener('click',()=>{
    send();
})

function send(){
    let msg = document.querySelector(".msg");
    console.log(userName+ ":" + msg.value);
    websocket.send(userName + ":" + msg.value);
    msg.value = '';
}

//채팅창에서 나갔을 때
function onClose(evt) {
    const str = userName + ": 님이 방을 나가셨습니다.";
    websocket.send(str);
}

//채팅창에 들어왔을 때
function onOpen(evt) {
    const str = userName + ": 님이 입장하셨습니다.";
    websocket.send(str);
}

function onMessage(msg) {
    const data = msg.data;
    let sessionId = null;

    //데이터를 보낸 사람
    let message = null;
    let arr = data.split(":");

    for(var i=0; i<arr.length; i++){
        console.log('arr[' + i + ']: ' + arr[i]);
    }

    let cur_session = userName;

    //현재 세션에 로그인 한 사람
    console.log("cur_session : " + cur_session);
    sessionId = arr[0];
    message = arr[1];

    console.log("sessionID : " + sessionId);
    console.log("cur_session : " + cur_session);

    //로그인 한 클라이언트와 타 클라이언트를 분류하기 위함
    if(sessionId == cur_session){
        const userMsg = document.createElement("div");
        userMsg.innerHTML = `<b>\${sessionId} : \${message}</b>`
        const msgArea = document.querySelector('.msg-area');
        msgArea.appendChild(userMsg);
    }
    else{
        const userMsg = document.createElement("div");
        userMsg.innerHTML = `\${sessionId} : \${message}`
        const msgArea = document.querySelector('.msg-area');
        msgArea.appendChild(userMsg);
    }
}