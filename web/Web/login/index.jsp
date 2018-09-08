<%--
  Created by IntelliJ IDEA.
  User: 李玮
  Date: 2018/8/11
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String path = request.getContextPath();%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/Web/login/css/style.css" />
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="<%=path%>/Web/login/css/body.css"/>
</head>
<body>
<div class="container">
    <section id="content">
        <!-- ../index.html -->
        <form action="<%=path%>/com/zzkk/action/LoginServlet"  method="post">
            <h1>登录</h1>
            <div>
                <input type="text" placeholder="邮箱" required="" id="username" name="name"/>
            </div>
            <div>
                <input type="password" placeholder="密码" required="" id="password" name="password"/>
            </div>
            <div>
                <input type="text" placeholder="验证码" required="" id="code_input"  />
                <div id="v_container" style="width: 40%;height: 47px;"></div>

            </div>
            <div class="">
                <span class="help-block u-errormessage" id="js-server-helpinfo">&nbsp;</span>			</div>
            <div>
                <input type="submit" value="登录" class="btn btn-primary" id="js-btn-login"/>
                <a href="<%=path%>/Web/register/index.jsp">注册</a>
                <a href="<%=path%>/Web/login/key.jsp">忘记密码</a>
            </div>
        </form><!-- form -->
    </section><!-- content -->
</div>
</body>
<script src="js/gVerify.js"></script>
<script>
    var verifyCode = new GVerify("v_container");

    document.getElementById("js-btn-login").onclick = function(){
        var res = verifyCode.validate(document.getElementById("code_input").value);
        if(res==false){
            alert("验证码输入错误");
            return false;
        }
    }

    var errori ='<%=request.getParameter("error")%>';
    if(errori=='isLoad'){
        alert("账号已登陆!");
    }
    else if(errori=='noUser'){
        alert("用户不存在!");
    }
    else if(errori=="yes"){
        alert("密码错误!");
    }
</script>
</html>
