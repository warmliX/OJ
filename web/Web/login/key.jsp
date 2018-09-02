<%--
  Created by IntelliJ IDEA.
  User: 李玮
  Date: 2018/8/11
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String path = request.getContextPath();%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/Web/login/css/style.css" />
    <title>忘记密码</title>
    <link rel="stylesheet" type="text/css" href="<%=path%>/Web/login/css/body.css"/>
</head>
<body>
<div class="container">
    <section id="content">
        <form action="<%=path%>/com/zzkk/action/AlterServlet"  method="post">
            <h1>修改密码</h1>
            <div>
                <input type="text" placeholder="邮箱" required="" onchange="sel()" id="username" name="name"/>
            </div>
            <div>
                <select>
                    <option value ="que" id="question">密保问题</option>
                </select>
            </div>
            <div>
                <input type="text" placeholder="密保答案" required="" id="ans" name="ans"/>
            </div>
            <div>
                <input type="password" placeholder="新密码" required="" id="password" name="password"/>
            </div>
            <div>
                <input type="password" placeholder="确认密码" required="" id="password1" name="password1"/>
            </div>
            <div>
                <input type="text" placeholder="验证码" required="" id="code_input"  />
                <div id="v_container" style="width: 40%;height: 47px;"></div>

            </div>
            <div class="">
                <span class="help-block u-errormessage" id="js-server-helpinfo">&nbsp;</span>			</div>
            <div>
                <input type="submit" value="确定修改" class="btn btn-primary" id="js-btn-login"/>
            </div>
        </form><!-- form -->
    </section><!-- content -->
</div>
</body>
<script src="<%=path%>/Web/login/js/gVerify.js"></script>
<script>
    var verifyCode = new GVerify("v_container");

    document.getElementById("js-btn-login").onclick = function(){
        var answer = document.getElementById("question").innerHTML;
        if(answer.length == 0){
            alert("用户不存在");
            return false;
        }

        var ans = document.getElementById("password").value;
        var rans = document.getElementById("password1").value;
        if(ans != rans){
            alert("密码不一致");
            return false;
        }

        var res = verifyCode.validate(document.getElementById("code_input").value);
        if(res==false){
            alert("验证码输入错误");
            return false;
        }

    }

    var xmlhttp;
    function sel(){
        var email = document.getElementById("username").value;
        var url = "<%=path%>/com/zzkk/action/FindServlet?email="+email;

        xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = selectResult;
        xmlhttp.open("GET",url,true);
        xmlhttp.send(null);
    }
    function selectResult() {
        if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
            document.getElementById("question").innerHTML=xmlhttp.responseText;
        }
    }
</script>
</html>
