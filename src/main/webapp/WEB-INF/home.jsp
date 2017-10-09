<%--
  Created by IntelliJ IDEA.
  User: dllo
  Date: 17/9/29
  Time: 上午10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
</head>

<%--～～～～～～～～～～～～～～～～～～～～～～～～～～～～--%>
<body>

<%--用jq写ajax--%>

这是springMVC的首页！
<a href="/login">登录</a>

<div id="div1">啊啊啊啊啊啊</div>

<div id="div3">ajax</div>

</body>
<%--～～～～～～～～～～～～～～～～～～～～～～～～～～～～--%>

<%--jq和ajax--%>
<script src="/js/jquery-3.2.1.js"></script>

<script type="text/javascript">

    function func1() {

        /**
         * 1、$的使用
         * 1）、$（）：选择标签／获取标签
         * 2）、$.xxx：操作ajax／get／post方法
         *
         * 2、$（）的使用
         * 1）、$（""）：使用选择器获取标签（id选择器等）
         * 2）、$（js对象）：$（document）：将js对象转为jq对象
         *
         * 3、$（）的方法
         * 1）、带参数：赋新值
         *      $("#div1").html("aaaa")
         *      $("#div1").attr("id","div2")
         * 2）、不带参数：获取当前的值
         *      console.log($("div1").html())
         *      console.log($("#div1").attr("id"))
         *
         * 4、如果jq的方法没有明显的返回值，那这个方法会返回对象本身
         * 链式编程风格：依次调用（如果有返回值的时候就会结束了）
         */

        // 获取到的都是list集合（通过id标签的时候也是list，不过它只有一个元素）
        // 赋值的方法（括号中什么也不写就是取值）
        $("#div1").html("aaaa");

        // 给id赋值
        $("#div1").attr("id", "div2");

        // 获取id的值
        console.log($("#div1").attr("id"));

//        $("#div1").click(function () {
//            $(this).html("aaa")
//        })
    }

    // 调用方法
    func1();

    // ajax用法
    function func2() {

        // json本质上是一个js对象
        $.ajax({
            // 请求访问的地址
            url: "/studentinfo",
            // 参数列表：json形式
            // 不匹配的参数就不会显示
            data: {
                username: $("#div3").html(),
                password:"222",
                hobby:"333"
            },

            // 请求方式默认是get
            type: "GET",

            // 判断返回结果，后面是一个匿名函数，传回来的参数就是result（名字自己定义）
            success:function (result) {

                console.log(result)
            }

        })

    }

    func2()

</script>

</html>
