# javaweb学习速查案例01
链接：[https://csxiaoyaojianxian.github.io/javaweb_study01](https://csxiaoyaojianxian.github.io/javaweb_study01 )  
名称：通讯录系统  
目的：常见的javaweb的一些写法总结  
说明：数据使用xml，格式见data文件夹下文件，访问login.html，用户名：sunshine，密码：19931128，若使用用户名：sun，则跳转404页面  
## 【知识点】  
### 1 MVC
### 2 XML（dom4j）  
XMLUtil和ContactDaoImpl中
### 3 Servlet基本操作  
servlet域对象，servlet层
### 4 自定义标签
csxiaoyao.tld配置  
ShowIpTag类实现  
listContact.jsp中引入  
### 5 JSTL  
listContact.jsp中引入
### 6 EL表达式  
listContact.jsp中
### 7 JUnit
test包中  
### 8 session的操作
登陆校验，LoginServlet中设置，ListContactServlet中取出，LogoutServlet中删除 
### 9 cookie
存储上次访问时间，ListContactServlet中  
### 10 UUID
生成唯一ID，ContactDaoImpl中 
### 11 web.xml配置
servlet、错误页、首页
### 12 request、response操作
修改请求头、状态码、实体编码、根据referer头判断非法链接），LoginServlet中
### 13 注释模板