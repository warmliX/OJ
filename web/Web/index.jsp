<%--
  Created by IntelliJ IDEA.
  User: 李玮
  Date: 2018/8/11
  Time: 21:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getContextPath();%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
    <title>Online Judge</title>
    <!---css--->
    <link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
    <link href="css/style.css" rel='stylesheet' type='text/css' />
    <!---css--->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!---js--->
    <script src="js/jquery-1.11.1.min.js"></script>
    <script src="js/bootstrap.js"></script>
    <!---js--->
    <link href="css/styles.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/component.css" />
    <!--web-fonts-->
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Josefin+Sans:400,700italic,700,600italic,600,400italic,300italic,300,100italic,100' rel='stylesheet' type='text/css'>
    <!--//web-fonts-->
    <script type="text/javascript" src="js/numscroller-1.0.js"></script>
    <!--JS for animate-->
    <link href="css/animate.css" rel="stylesheet" type="text/css" media="all">
    <script src="js/wow.min.js"></script>
    <script>
        new WOW().init();
    </script>
    <!--//end-animate-->

</head>
<body>
<div class="header">
    <div class="container">
        <div class="heder-top">
            <div class="logo wow fadeInDownBig animated animated" data-wow-delay="0.4s">
                <h1><a href="#">Online Judge</a></h1>
            </div>
            <div class="nav-icon">
                <a href="#" class="navicon"></a>
                <div class="toggle">
                    <ul class="toggle-menu">
                        <li><a class="active" href="index.jsp">主页</a></li>
                        <li><a  href="<%=path%>/com/zzkk/action/QuestionListServlet?page=1">编程练习</a></li>
                        <li><a  href="<%=path%>/Web/cont.jsp">代码测试</a></li>
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
        <div class="banner-text wow fadeInLeft animated animated" data-wow-delay="0.3s">
            <h3>编程练习</h3>
            <p>c语言、c++、Java代码测试和在线练习平台</p>
        </div>
    </div>
</div>
<!--header--->
<div class="content">
    <div class="welcome-section">
        <div class="container">
            <h2> Welcome </h2>
            <div class="welcome-grids">
                <div class="col-md-4 wel-grid wow fadeInLeft animated animated" data-wow-delay="0.4s">
                    <img src="images/w1.jpg" class="img-responsive" alt=""/>
                    <br>
                    <div class="wel-text hvr-shutter-out-horizontal">
                        <h4>C语言</h4>
                    </div><br>
                </div>
                <div class="col-md-4 wel-grid wow fadeInDownBig animated animated" data-wow-delay="0.4s">
                    <div class="wel-text hvr-shutter-out-horizontal wel1">
                        <h4>C++</h4>
                    </div>
                    <br>
                    <img src="images/w2.jpg" class="img-responsive" alt=""/><br>
                </div><br>
                <div class="col-md-4 wel-grid wow fadeInRight animated animated" data-wow-delay="0.4s">
                    <br><img src="images/w3.jpg" class="img-responsive" alt=""/><br>
                    <div class="wel-text hvr-shutter-out-horizontal">
                        <h4>Java</h4>
                    </div>
                </div>
                </a>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>
    <div class="clearfix"> </div>
</div>
</div>
<br>
<div class="footer-section wow fadeInDownBig animated animated" data-wow-delay="0.4s">
    <div class="copy">
        <p>感谢使用 </p>
    </div>

</div>

</body>
</html>
