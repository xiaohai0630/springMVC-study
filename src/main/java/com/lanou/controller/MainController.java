package com.lanou.controller;

import com.lanou.bean.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by dllo on 17/9/29.
 */
@Controller
public class MainController {

    /**
     * Spring MVC 4 中常用的注解：
     * @Controller ：允许自动检测定义在类路径下的组件并自动注册
     *             需要在spring－servlet.xml的头文件中引入spring－context
     *             给全部的controller配置一个bean
     *
     * @RequestMapping ：将URL映射到整个类或特定的处理方法上，一般来说，
     *                   用于类中，表示类中的所有响应请求的方法都是以该地址作为父路径，
     *                   而方法级别的注解只是映射为一个特定的HTTP方法请求（get、post等）
     *                   或HTTP请求参数
     *
     * @PathVariable ：可以使用它注解方法参数并将其绑定到URI模版变量的值上
     *                 支持使用正则表达式
     *
     * @RequestParam ：用于在SpringMVC后台控制层获取参数，多个参数之间用逗号隔开
     *                 @RequestParam("username") String name
     *
     * @ResponseBody ：作用是将返回类型直接输入到HTTP response body中，
     *                 在输出JSON格式的数据时，会经常用到
     *                 不通过视图解析器，不会跳转jsp页面
     */

    // /index-->home.jsp

    // 写给springMVC的，给它调用（自己是不调用这个方法的）
    // 返回的字符串给：bean id="jspViewResolver" ，作为字符拼接用（找目标文件）

    /**
     * springMVC特有的注解，规定了访问home.jsp的value值
     * 每一个方法对应一个jsp或html等
     * value的值是不能重复的！！！
     * 默认的访问页面是value中的值只写一个斜杠／
     * return的返回值可以相同，就是多个方法都跳到同一个jsp页面
     */
    @RequestMapping(value = "/index")
    public String frontPage() {
        // 对应了WEB－INF中想要访问的xxx.jsp
        // 访问的时候写：index（不是去找index.jsp！！！）
        // 拼接在WEB－INF后面的是home，不是index
        return "home";
    }

    @RequestMapping(value = "/login")
    public String frontPage2() {
        return "login";
    }

    @RequestMapping(value = "/success")
    public String frontPage3() {
        return "success";
    }

    // 要访问的jsp文件包含在一个文件夹下
    @RequestMapping(value = "error")
    public String frontPage4() {
        return "/errors/error";
    }

    // 启动tamcat之后默认访问的
    @RequestMapping(value = "/")
    public String mainPage() {
        return "home";
    }

    /**
     * SpringMVC的原理：
     * 1、用户发送请求给服务器（浏览器-->服务器）：URI：/index
     * （URI：统一资源标识符、Uniform Resource Identifier）
     * （URL：全球资源定位器、Uniform Resource Locator）
     * <p>
     * 2、请求会发送给DispatcherServlet
     * （因为在web.xml中配置了，所有的请求都会交给dispatcherServlet处理）
     * <p>
     * 3、在dispatcherServlet内部，通过HandlerMapping检查是否有方法和URI对应
     * （方法中注解@RequestMapping的value）
     * <p>
     * 4、如果有，就调用该方法，活动JSP文件；否则就是404
     * <p>
     * 5、ViewResolver根据文件名获取相应的jsp文件，转化为视图对象
     * <p>
     * 6、dispatcherServlet将视图对象发给服务器，由服务器最终发送给用户的浏览器
     */

    // nickname：昵称
    // /infopage/用户名/昵称-->格式是固定的，大括号中是用来放值的
    // 大括号中的值，会被赋值给@PathVariable这个注解中的值
    @RequestMapping(value = "/infoTest/{username}/{nickname}")
    public String test1(@PathVariable("username") String name,
                        @PathVariable("nickname") String nick) {

        System.out.println(name);
        System.out.println(nick);
        return "info";
    }

    // 传递参数：不同的方式
    // /infopage?username=2222
    // /infopage/2222/333/444

    // 在地址栏输入：/params?username=222&password=333 --> 参数的个数和名称必须相同
    // 这次的注解是：@RequestParam
    @RequestMapping(value = "/params")
    public String paramPage(@RequestParam("username") String name,
                            @RequestParam("password") String pwd) {

        System.out.println(name);
        System.out.println(pwd);

        return "param";
    }

    /**
     * 用map和实体类的方式比较常用
     *
     * 参数比较多的时候-->参数的个数是动态的
     * 多个参数之间用&连接
     * 在地址栏输入：/content?a=111&b=222：前面是key，后面是value
     * 什么都不写的时候map就是空的
     */
    @RequestMapping(value = "/content")
    public String contentPage(@RequestParam Map<String, String> params) {

        System.out.println(params);

        return "home";
    }

    /**
     * get/post
     * 配置一个实体类
     * 传递的参数是实体类中的值，参数名要对应（错的不会显示），可以只写其中几个
     * post请求不能直接通过浏览器访问
     *
     * get请求的时候可以直接通过地址栏访问
     * 地址栏输入：/stuinfo?username=1&password=2&hobby=a
     * 哪一项不写，哪一项就是空的（null）
     *
     * @param student
     * @return
     */
    @RequestMapping(value = "/stuinfo", method = RequestMethod.GET)
    public String studentPage(Student student) {

        System.out.println(student);
        return "home";
    }

    // json/xml
    // 怎么样使用springMVC返回/json/xml数据
    // springMVC需要jackson库（默认的，也可以用其它的库，但是需要额外配置）

    // 1、返回json数据
    // 返回json的标志，不通过视图解析器，不跳转jsp页面：@ResponseBody
    // 将需要的类返回就可以了
    @ResponseBody
    @RequestMapping(value = "/studentinfo")
    public Student studentInfo(Student student) {
        // 接收参数
        System.out.println("Ajax的student：---" + student);

        // 把接收的json数据又当成参数传回去，实际项目中不能这样写！！！
        // 实际应该有一些处理的过程等
        return student;
    }

    // 进行json数据解析和构成必须的两个参数（配置在pom.xml中）：
    // jackson-databind
    // jackson-core

    // 报错的时候用的，可以不加
    // annotation

    /**
     * Restful标准（json格式的标准规范！）
     *
     * 1、必须是map或实体类，也就是必须要是key－value形式
     *    最外层一定是key－value的形式，里面可以包含其它形式的
     * 2、必须要有反馈信息：errorCode（错误代码）、msg（具体的错误信息）
     *    错误代码是自己定义的
     * 3、必须要精简，不要把没有意义、将来也不会用到的字段放到json中
     * 4、文档必须要详细，数据接口文档，前端页面流程文档，
     */

}
