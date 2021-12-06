const enter = document.querySelector(".enter")
const name = document.querySelector(".name")
enter.addEventListener('click' , (e)=>{
    e.preventDefault();
    console.log(name.value)
    if(name.value === ''){
        alert('방 제목을 입력하세요')
    }else{
        const form = document.querySelector("form")
        form.submit();
        alert(`${name.value} 방이 생성 되었습니다.`)
    }

})