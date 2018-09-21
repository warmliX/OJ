<%@ page import="com.zzkk.bean.ContactBean" %><%--
  Created by IntelliJ IDEA.
  User: 李玮
  Date: 2018/8/24
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%String path = request.getContextPath();%>
<html>
<head>
    <title>题目</title>
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

</head>
<body>
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
                        <li><a href="<%=path%>/Web/index.jsp">主页</a></li>
                        <li><a class="active"  href="<%=path%>/com/zzkk/action/QuestionListServlet?page=1">编程练习</a></li>
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
    </div>
</div>
<!--header--->
<%
    String email = (String) request.getSession().getAttribute("usr");
    if(email == null){
        response.sendRedirect(path+"/Web/login/index.jsp");
    }

    ContactBean bean = (ContactBean) request.getAttribute("bean");
    if(bean != null){
%>
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

                <div class="container">
                    <div class="contact-grids">
                        <div class="col-md-6 contact-grid wow fadeInLeft animated animated" data-wow-delay="0.4s">
                            <h4>题目：</h4>
                            <span><%=bean.getContent()%></span><br>
                            <h4>问题描述：</h4>
                            <span><%=bean.getDecipt()%></span><br>
                            <h5>示例输入:<span><%=bean.getParamter()%></span></h5>
                            <h5>示例输出:<span><%=bean.getOutcome()%></span></h5>
                            <div class="row1">
                                <label>代码：</label>
                                <form action="<%=path%>/com/zzkk/action/SubServlet" method="post" id="form">
                                    <textarea name="qid" style="height:0px;width:0px;visibility: hidden"><%=request.getAttribute("qid")%></textarea>
                                    <textarea id="lang" name="language" style="height:0px;width:0px;visibility: hidden"></textarea>
                                    <textarea name="text" onkeydown="tab(this)" placeholder="请输入你的代码" id="text1"></textarea>
                                </form>
                            </div>
                            <button id="inp1" onclick="sub()" class="btn btn-success">确定</button>
                        </div>
                        <div class="col-md-6 contact-grid wow fadeInRight animated animated" data-wow-delay="0.4s">
                            <div class="col-md-6 contact-left" id="false" style="display: none">
                                <div class="alert alert-danger">
                                    <em>Error!</em><i>您的代码运行错误，仔细检查一下吧。</i>
                                </div>
                            </div>
                            <div class="col-md-6 contact-right" id="true" style="display: none">
                                <div class="alert alert-success">
                                    <em>Wait！</em><i>代码测试中！</i>
                                </div>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="clearfix"></div>
                    </div>
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
<%}%>
</body>
<script type="text/javascript">
    $("textarea").on(
        'keydown',
        function(e) {
            if (e.keyCode == 9) {
                e.preventDefault();
                var indent = '    ';
                var start = this.selectionStart;
                var end = this.selectionEnd;
                var selected = window.getSelection().toString();
                selected = indent + selected.replace(/\n/g, '\n' + indent);
                this.value = this.value.substring(0, start) + selected
                    + this.value.substring(end);
                this.setSelectionRange(start + indent.length, start
                    + selected.length);
            }
        })
    var lis=document.getElementsByTagName('li');
    for(var i=0;i<lis.length;i++)
    {
        lis[i].onclick=function () {
            for(var j=0;j<lis.length;j++)
            {
                lis[j].className='';
            }
            this.className='active';
            if(this.getAttribute("id")=="java"){
                alert("请将类名设置为 Main !");
                document.getElementById("text1").innerHTML="public class Main"
            }
        }
    }

    function sub(){
        var form = document.getElementById("form");
        var text = document.getElementById("text1").value;
        var otrue=document.getElementById('true');
        var ofalse=document.getElementById("false");
        ofalse.style.display='none';
        otrue.style.display='none';
        for(var j=0;j<lis.length;j++)
        {
            if(lis[j].getAttribute("class")=='active'){
                var language = lis[j].getAttribute("id");
                document.getElementById("lang").innerHTML = language;
            }
        }
        if(text.length < 50){
            ofalse.style.display='block';
        } else if(language == "java" & text.indexOf("Main") == -1){
            ofalse.style.display='block';
        }else{
            form.submit();
            otrue.style.display='block';
        }
    }
</script>
</html>