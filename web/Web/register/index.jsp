<%--
  Created by IntelliJ IDEA.
  User: 李玮
  Date: 2018/8/11
  Time: 21:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String path = request.getContextPath();%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
    <meta http-equiv="Content-Type" content="text/html;  charset=utf-8">
    <title>注册</title>
    <link rel="stylesheet" media="screen" href="<%=path%>/Web/register/css/css.css" />
</head>
<body>
<form action="<%=path%>/com/zzkk/action/RegServlet"  id="msform"  method="post" onsubmit="return GVerify()">
    <fieldset>
        <h2 class="fs-title">注册</h2>
        <input type="text" id="email" name="email" onblur="sel()" required=""  placeholder="邮箱" />
        <input type="text" name="name" id="name" required=""  placeholder="昵称" />
        <input type="password" name="pass" id="pass" required=""  placeholder="密码" />
        <input type="password" name="cpass"  id="cpass" required=""  placeholder="确认密码" />

        <input type="text" name="que" required=""  placeholder="密保问题" />
        <input type="text" name="ans" required=""  placeholder="密保答案" />
        <input type="text" placeholder="验证码" id="code_input" />
        <div id="v_container" style="width: 43%;height: 35px;"></div>
        <input type="submit"  id="sub-register"  class="submit action-button" value="确定" />
    </fieldset>
</form>
<br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br>

</body>
<script src="<%=path%>/Web/register/js/gVerify.js"></script>
<script>
    var flag;
    var xmlhttp = null;
    var email = null;
    function sel(){
        email = document.getElementById("email").value;
        xmlhttp = new XMLHttpRequest();
        var url = "<%=path%>/com/zzkk/action/VerifyServlet?email="+email;
        xmlhttp.onreadystatechange=selResult;
        xmlhttp.open("GET" ,url ,true);
        xmlhttp.send(null);
    }
    function selResult(){
        if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
            alert(xmlhttp.responseText);
            if(xmlhttp.responseText == "The email is already in use!"){
                flag = false;
            } else if(xmlhttp.responseText == "The email is usable!"){
                flag = true;
            }
        }
    }

    var verifyCode = new GVerify("v_container");

    document.getElementById("sub-register").onclick = function aaa(){
        var res = verifyCode.validate(document.getElementById("code_input").value);
        if(res==false){
            alert("验证码输入错误");
            document.getElementById("code_input").value='';
            return false;
        }

        if(document.getElementById("pass").value != document.getElementById("cpass").value){
            alert("The password is not same as the repassword!");
            return false;
        }

        if(email.length <= 10){
            alert("email too short!");
            flag = false;
        }

        if(flag == true){
            return true;
        } else{
            return false;
        }
    }
</script>
</html>
