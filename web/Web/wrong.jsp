<%--
  Created by IntelliJ IDEA.
  User: 李玮
  Date: 2018/9/20
  Time: 23:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <%String path = request.getContextPath();%>
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
    <link rel="stylesheet" type="text/css" href="<%=path%>/Web/css/component.css" />
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

        .big {
            min-height: 74%;
            padding-top: 15%;
            box-sizing: border-box;

        }
        .content  h3{
            text-align: center;
        }
    </style>
</head>
<body>

<div class="header head">
    <div class="container">
        <div class="heder-top">
            <div class="logo">
                <h1><a href="#">Online Judge</a></h1>
            </div>
            <div class="nav-icon">
                <a href="#" class="navicon"></a>
                <div class="toggle">
                    <ul class="toggle-menu">
                        <li><a href="#">主页</a></li>
                        <li><a  href="#">编程练习</a></li>
                        <li><a  href="<%=path%>/Web/cont.jsp"  class="active">代码测试</a></li>
                        <li><a  href="#">练习记录</a></li>
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
<div class="big">
    <div class="content">
        <div class="contact">
            <h3>页面正在维护，请稍后重试</h3>
        </div>
    </div>
</div>
</body>
</html>
