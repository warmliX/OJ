<%--
  Created by IntelliJ IDEA.
  User: 李玮
  Date: 2018/8/11
  Time: 21:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String path = request.getContextPath();%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
    <title>练习</title>
    <!---css--->
    <link href="<%=path%>/Web/css/bootstrap.css" rel='stylesheet' type='text/css' />
    <link href="<%=path%>/Web/css/style.css" rel='stylesheet' type='text/css' />
    <!---css--->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!---js--->
    <script src="<%=path%>/Web/js/jquery-1.11.1.min.js"></script>
    <script src="<%=path%>/Web/js/bootstrap.js"></script>
    <!---js--->
    <link href="<%=path%>/Web/css/styles.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/component.css" />
    <!--web-fonts-->
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Josefin+Sans:400,700italic,700,600italic,600,400italic,300italic,300,100italic,100' rel='stylesheet' type='text/css'>
    <!--//web-fonts-->
    <!--JS for animate-->
    <link href="<%=path%>/Web/css/animate.css" rel="stylesheet" type="text/css" media="all">
    <script src="<%=path%>/Web/js/wow.min.js"></script>
    <script>
        new WOW().init();
    </script>
    <!--//end-animate-->
    <style>
        #div1{margin: 0;
            padding: 0;}
    </style>
</head>
<body>
<%
String email = (String) request.getSession().getAttribute("usr");
if(email == null){
    response.sendRedirect(path+"/Web/login/index.jsp");
}
%>
<div class="header head">
    <div class="container">
        <div class="heder-top">
            <div class="logo">
                <h1><a href="<%=path%>/Web/index.jsp">Online Judge</a></h1>
            </div>
            <div class="nav-icon">
                <a href="#" class="navicon"></a>
                <div class="toggle">
                    <ul class="toggle-menu">
                        <li><a href="index.jsp">主页</a></li>
                        <li><a  href="<%=path%>/com/zzkk/action/QuestionListServlet?page=1">编程练习</a></li>
                        <li><a  href="<%=path%>/Web/cont.jsp"  class="active">代码测试</a></li>
                        <li><a  href="<%=path%>/com/zzkk/action/RecordServlet?page=1">练习记录</a></li>
                    </ul>
                </div>
                <script>
                    $('.navicon').on('click', function (e) {
                        e.preventDefault();
                        $(this).toggleClass('navicon--active');
                        $('.toggle').toggleClass('toggle--active');
                    });
                </script>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>
<!--header--->
<div class="content">
    <div class="contact">
        <div class="container" id="con">

            <h2>代码测试</h2>
            <div class="packt">
                <ul class="nav nav-tabs">
                    <li class="active" id="c">
                        <a href="#">
                            <span class="glyphicon glyphicon-pencil"></span> C语言
                        </a>
                    </li>
                    <li id="c++">
                        <a href="#">
                            <span class="glyphicon glyphicon-book"></span> C++
                        </a>
                    </li>
                    <li id="java">
                        <a href="#">
                            <span class="glyphicon glyphicon-edit"></span> Java
                        </a>
                    </li>
                </ul>
                <div class="contact-grids" id="div2">
                    <div class="col-md-6 contact-grid wow fadeInLeft animated animated" data-wow-delay="0.4s">
                        <form>
                            <div class="row1">
                                <label>代码：</label>
                                <textarea placeholder="请输入你的代码" id="code"></textarea>
                            </div>
                            <button class="btn btn-success" id="subCode">确定</button>
                        </form>
                    </div>
                    <div class="col-md-6 contact-grid  wow fadeInRight animated animated" data-wow-delay="0.4s">
                        <div class="row1">
                            <label>运行结果：</label>
                            <textarea id="result" disabled="true"></textarea>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                    <div class="clearfix"></div>
                </div>
                <div id="div1" style="float: right">
                    <button class="btn btn-success" style="margin-right: 1ex">搜索错误信息</button>
                    <button class="btn btn-success">分享到QQ</button>
                </div>
            </div>
        </div>
    </div>
</div>








<div class="footer-section wow fadeInDownBig animated animated" data-wow-delay="0.4s">
    <div class="container">
        <div class="copy">
            <p>感谢使用</p>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    document.getElementById('subCode').onclick=function () {
        var oCode=document.getElementById('code').value;
        if (oCode!=''){
            this.disabled=true;
            var xhr=new XMLHttpRequest();
            xhr.open('get','003.php?code='+oCode);
            xhr.onload=function () {
                document.getElementById('result').value=xhr.responseText;
                document.getElementById('subCode').disabled=false;
            }
            xhr.send(null);
        }
    }
    var lis=document.getElementsByTagName('li');
    for(var i=0;i<lis.length;i++)
    {
        lis[i].onclick=function () {
            for(var j=0;j<lis.length;j++)
            {
                lis[j].className='';
            }
            this.className='active';
        }
    }
</script>
</html>
