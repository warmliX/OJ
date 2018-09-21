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
    <script src="<%=path%>/Web/js/auto-line-number.js"></script>

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

<div class="header head">
    <div class="container">
        <div class="heder-top">
            <div class="logo">
                <h1><a href="<%=path%>/Web/wrong.html<%--/Web/index.jsp--%>">Online Judge</a></h1>
            </div>
            <div class="nav-icon">
                <a href="#" class="navicon"></a>
                <div class="toggle">
                    <ul class="toggle-menu">
                        <li><a href="<%=path%>/Web/wrong.jsp<%--index.jsp--%>">主页</a></li>
                        <li><a  href="<%=path%>/Web/wrong.jsp<%--/com/zzkk/action/QuestionListServlet?page=1--%>">编程练习</a></li>
                        <li><a  href="<%=path%>/Web/cont.jsp"  class="active">代码测试</a></li>
                        <li><a  href="<%=path%>/Web/wrong.jsp<%--/com/zzkk/action/RecordServlet?page=1--%>">练习记录</a></li>
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
                        <%--<form id="form1" method="post">--%>
                            <div class="row1">
                                <label>代码：</label>
                                <textarea id="lang" name="language" style="height:0px;width:0px;visibility: hidden">c</textarea>
                                <textarea id="code" placeholder="请输入你的代码" onkeydown="tab(this)"></textarea>
                            </div>
                            <br/>
                            <button class="btn btn-success" id="subCode" onclick="sub()">确定</button>
                        <%--</form>--%>
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
                <br/>
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
<script>
    $(document).ready(function(){
        $("#subCode").click(function(){
            var oCode=document.getElementById("code").value;
            var language = document.getElementById("lang").value;
            var paramter = prompt("输入你的参数（用空格隔开哦）");
            if(oCode!=''||oCode!=null){
                document.getElementById("subCode").disabled=true;
                $.post("<%=path%>/com/zzkk/action/CodeTestServlet",
                    {
                        code:oCode,
                        language:language,
                        paramter:paramter
                    },
                    function(status){
                        document.getElementById("subCode").disabled=false;
                        document.getElementById('result').innerHTML=status;
                    });
            }
        });
    });
</script>

<script type="text/javascript">
    $("#code").setTextareaCount({
        width: "30px",
        bgColor: "#AFEEEE",
        color: "#000000",
        display: "inline-block"
    });
</script>

    <%--/*var xmlhttp;
    document.getElementById('subCode').onclick=function sub() {
        var oCode=document.getElementById('code').value;
        if (oCode!=''){
            var paramter = prompt("输入你的参数（用空格隔开哦）");
            var language = document.getElementById("lang").value;
            var url = "&lt;%&ndash;<%=path%>&ndash;%&gt;/com/zzkk/action/CodeTestServlet?code="+oCode+"&language="+language+"&paramter="+paramter;
            xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange=checkResult;
            xmlhttp.open("POST",url,true);
            xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
            xmlhttp.send("flag=true");
        }
    }

    function checkResult(){
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
            document.getElementById('result').innerHTML=xmlhttp.responseText;
    }*/--%>

<script type="text/javascript">
    var lis=document.getElementsByTagName('li');
    for(var i=0;i<lis.length;i++)
    {
        lis[i].onclick=function () {
            for(var j=0;j<lis.length;j++)
            {
                lis[j].className='';
            }
            this.className='active';
            document.getElementById("lang").innerHTML=this.getAttribute("id");
            if(this.getAttribute("id")=="java"){
                alert("请将类名设置为 Main !");
                document.getElementById("code").innerHTML="public class Main{\n" +
                    "   public static void main(String[] args){\n" +
                    "\n" +
                    "   }\n" +
                    "}\n"
            }else{
                document.getElementById("code").innerHTML="";
            }
        }
    }
</script>

<script type="text/javascript">
    function tab(obj){
        if (event.keyCode == 9)
        {
            obj.value = obj.value + "   "; // 跳几格由你自已决定
            event.returnValue = false;
        }
    }
</script>

</html>
