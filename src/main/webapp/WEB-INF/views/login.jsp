<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html lang="ko">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${path}/resources/css/chatList.css" rel="stylesheet"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    </head>
    <body>
        <div class="container mt-4">
              <form action="/user/save" method="post">
                    <div class="mb-3 row">
                        <label for="staticId" class="col-sm-2 col-form-label">Id</label>
                        <div class="col-sm-10" >
                          <input type="text"  class="form-control" id="staticId" name="id" placeholder="아이디를 입력하세요.">
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
                        <div class="col-sm-10">
                          <input type="password" class="form-control" id="inputPassword" name="password" placeholder="비밀번호를 입력하세요.">
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="inputName" class="col-sm-2 col-form-label">Name</label>
                        <div class="col-sm-10">
                          <input type="text" class="form-control" id="inputName" name="name" placeholder="이름을 입력하세요.">
                        </div>
                    </div>
                    <div class="d-flex justify-content-end">
                        <button type="submit" class="btn btn-outline-primary">가입하기</button>
                    </div>


              </form>
        </div>
        <script>
            const id = document.querySelector("#staticId");
            id.addEventListener("blur", function( event ) {
              $.ajax({
              		url:'/user/existById/'+id.value+'/',
              		data : String,
              		type:'GET',
              		dataType:'json',
              		success:function(data){
              			if(data){
              			    const exist = id.parentNode
                            const p = document.createElement('p');
                            p.innerHTML = '이미 존재합니다.';
                            exist.appendChild(p);
              			}else{
              			    const exist = id.parentNode
                            const p = document.createElement('p');
                            p.innerHTML = '사용 가능한 아이디입니다.';
                            exist.appendChild(p);
              			}
              		},error:function(a,b,c){
              			console.log(a,b,c);
              		}
              	});
            });
        </script>
    </body>
</html>