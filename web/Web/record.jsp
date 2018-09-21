<%@ page import="com.zzkk.bean.PageBean" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  UserBean: 李玮
  Date: 2018/8/11
  Time: 21:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%String path = request.getContextPath();%>
<head>
    <title>练习记录</title>
    <!---css--->
    <link href="<%=path%>/Web/css/bootstrap.css" rel='stylesheet' type='text/css' />
    <link href="<%=path%>/Web/css/style.css" rel='stylesheet' type='text/css' />
    <link href="<%=path%>/Web/css/new.css" rel="stylesheet" type="text/css">
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
    <link rel="stylesheet" href="<%=path%>/Web/css/lightbox.css" type="text/css" media="all" />
    <script src="<%=path%>/Web/js/easyResponsiveTabs.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#horizontalTab').easyResponsiveTabs({
                type: 'default', //Types: default, vertical, accordion
                width: 'auto', //auto or any width like 600px
                fit: true   // 100% fit in a container
            });
        });

    </script>
    <!--JS for animate-->
    <link href="<%=path%>/Web/css/animate.css" rel="stylesheet" type="text/css" media="all">
    <script src="<%=path%>/Web/js/wow.min.js"></script>
    <script>
        new WOW().init();
    </script>
    <!--//end-animate-->

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
            <div class="logo wow fadeInDownBig animated animated" data-wow-delay="0.4s">
                <h1><a href="<%=path%>/Web/index.jsp">Online Judge</a></h1>
            </div>
            <div class="nav-icon">
                <a href="#" class="navicon"></a>
                <div class="toggle">
                    <ul class="toggle-menu">
                        <li><a href="<%=path%>/Web/index.jsp">主页</a></li>
                        <li><a  href="<%=path%>/com/zzkk/action/QuestionListServlet?page=1">编程练习</a></li>
                        <li><a  href="<%=path%>/Web/cont.jsp">代码测试</a></li>
                        <li><a href="<%=path%>/com/zzkk/action/RecordServlet?page=1" class="active">练习记录</a></li>
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
    <div class="container">
        <h2>练习记录</h2>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>题目ID</th>
                <th>题目名称</th>
                <th>状态</th>
                <th>日期</th>
                <th>用时</th>
                <th>所用语言</th>
                <th>内存占用</th>
                <th>代码长度</th>
            </tr>
            </thead>
            <tbody id="ref">
                <%
                    PageBean bean = (PageBean) request.getAttribute("bean");
                    if(bean != null)
                    {
                        int currentPage = bean.getCurrentPage();//当前页
                        int pageCount = bean.getPageCount();//每页数据数
                        int totalCount = bean.getTotalCount();//数据总数
                        int totalPage = bean.getTotalPage();//总页数
                        ArrayList<String[]> list = bean.getList();
                        if(list != null){
                        for(int i=0 ;i< list.size() ;i++){
                        String[] data = list.get(i);
                        String language = new String();
                        switch(data[4]){
                            case "1":language = "C";break;
                            case "2":language = "C++";break;
                            case "3":language = "JAVA";break;
                        }
                        String stat = new String();
                        switch (data[2]){
                            case "0":stat = "Compiling";break;
                            case "1":stat = "Running";break;
                            case "2":stat = "Out of compile";break;
                            case "3":stat = "Compile Error";break;
                            case "4":stat = "Out of runtime";break;
                            case "5":stat = "Wrong answer";break;
                            case "6":stat = "Accept";break;
                        }
                        int qid = Integer.parseInt(data[0]);
                        %>
                <tr>
                    <td><%=data[0]%></td>
                    <td><a href= "<%=path%>/com/zzkk/action/QuestionContentServlet?qid=<%=qid%>"><%=data[1]%></a></td>
                    <td><%=stat%></td>
                    <td><%=data[3]%></td>
                    <td><%=data[5]+"ms"%></td>
                    <td><%=language%></td>
                    <td><%=data[6]+"KB"%></td>
                    <td><%=data[7]%></td>
                </tr>
                        <%
                        }
                            }
                        %>
            </tbody>
        </table>
        <div class="nav1">
            <nav id="nav1">
                <ul id="ul" class="pagination pagination-lg">
                    <p id="page" CurrentPage="<%=currentPage%>"></p>
                    <li><a href="javascript:sub(1);"><i class="fa fa-angle-left">«</i></a></li>
                    <%
                        if(currentPage <= 3){
                            for(int i = 0 ; i < 5 ; i++){
                                if(currentPage == (i+1)){
                %>
                    <li class="active"><a href="javascript:sub(<%=i+1%>);"><%=i+1%></a></li>
                <%
                                }else{
                                %>
                    <li><a href="javascript:sub(<%=i+1%>);"><%=i+1%></a></li>
                    <%
                                }
                        }
                    } else {
                            for(int i = 0 ; i < 5 ; i++){
                                if(i==2){
                                    %>
                    <li class="active"><a href="javascript:sub(<%=currentPage-(2-i)%>);"><%=currentPage-(2-i)%></a></li>
                    <%
                                }else if(currentPage-(2-i) <= totalPage){
                    %>
                                <li><a href="javascript:sub(<%=currentPage-(2-i)%>);"><%=currentPage-(2-i)%></a></li>
                    <%
                                    }
                            }
                                }
                                %>
                    <li><a href="javascript:sub(<%=totalPage%>);"><i class="fa fa-angle-right">»</i></a></li>
                    <script type="text/javascript">
                        var xmlhttp;
                        var int = setInterval("ref()" ,"1000");

                        function ref() {
                            xmlhttp = new XMLHttpRequest();
                            var url = "<%=path%>/com/zzkk/action/RefurbishServlet?page=" +<%=currentPage%>;
                            xmlhttp.onreadystatechange = checkResult;
                            xmlhttp.open("GET", url, true);
                            xmlhttp.send(null);
                        }

                         function checkResult(){
                             if(xmlhttp.readyState==4 && xmlhttp.status==200) {
                                 if(xmlhttp.responseText.charAt(xmlhttp.responseText.length-1) != "#")
                                    document.getElementById("ref").innerHTML = xmlhttp.responseText;
                                 else{
                                     document.getElementById("ref").innerHTML = xmlhttp.responseText.substr(0 ,xmlhttp.responseText.length-1);
                                     clearInterval(int);
                                 }
                             }
                         }
                    </script>
                    <%
                    }
                %>
                </ul>
            </nav>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    function sub(page){
        var Currentpage = document.getElementById("page").getAttribute("CurrentPage");
        var aimPage = parseInt(page);
        if(aimPage != Currentpage){
            var url = "<%=path%>/com/zzkk/action/RecordServlet?page="+aimPage;
            document.getElementById("page").setAttribute("CurrentPage" ,aimPage);
            window.location.href = url;
        }
    }

</script>
</html>
