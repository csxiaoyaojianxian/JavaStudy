# javaweb学习速查案例02
链接：[https://csxiaoyaojianxian.github.io/javaweb_study02](https://csxiaoyaojianxian.github.io/javaweb_study02 )   
名称：酒店订餐系统  
目的：常见的javaweb的一些写法总结  
说明：未使用javaweb框架，数据使用mysql，根目录下有sql脚本，用户名密码写在c3p0配置文件中
## 【知识点】 
### 1 连接池c3p0的配置及操作
工具类JdbcUtils中实现常用操作  
c3p0配置中，创建连接时指定数据库发送sql语句采用的编码
~~~
jdbc:mysql:///hotel?useUnicode=true&characterEncoding=utf8
~~~
具体写法：(1)转义字符``&amp;`` (2)CDATA批量转义
~~~
<property name="jdbcUrl">jdbc:mysql://localhost:3306/hotel?useUnicode=true&amp;characterEncoding=utf8</property>
<![CDATA[jdbc:mysql://localhost:3306/hotel?useUnicode=true&characterEncoding=utf8]]>
~~~
### 2 重写Object的hashCode、equals、toString方法
Food类
### 3 通过ResourceBundle对properties配置文件的读取
BeanFactory类中实现
### 4 service层实例化Dao的优化：通过配置文件提供的类名反射实例化对象
BeanFactory工厂类中定义getInstance方法读取对应的Dao实现类名，供service层调用创建实例
~~~
FoodTypeDao dao = new FoodTypeDao();//直接实例化
IFoodTypeDao dao = new FoodTypeDao();//对象创建不能写死
IFoodTypeDao dao = BeanFactory.getInstance("foodTypeDao", IFoodTypeDao.class) ;//声明接口的引用变量，指向通过配置文件实例化的实现该接口的类
~~~
### 5 抽取转发、重定向的共用方法
WebUtils中定义，servlet调用
~~~
//定义方法
private void goTo(HttpServletRequest request, HttpServletResponse response,Object uri)
		throws ServletException, IOException {
	if(uri instanceof RequestDispatcher){
		((RequestDispatcher) uri).forward(request, response);
	}else{
		response.sendRedirect(request.getContextPath()+(String)uri);
	}
}
//定义参数
uri = request.getRequestDispatcher("/sys/foodtype/cuisineList.jsp");
uri = "/error/error.jsp";
//调用方法
goTo(request, response, uri);
~~~
### 6 servlet抽取出BaseServlet
抽取出BaseServlet优化servlet  
BaseServlet继承HttpServlet，其他所有servlet继承BaseServlet继承HttpServlet
父类通过反射getDeclaredMethod方法调用子类方法，只要子类中不重写父类中方法，则调用父类中方法  
注意，父类中首先实例化的service对象用protected修饰，private修饰需要对得到的参数method设置method.setAccessible(true);
### 7 分页bean与查询条件封装类Condition的实现
查询条件封装类Condition再次封装在PageBean中作为参数，在FoodServlet中封装参数，传递给FoodDao实现查询
### 8 sql语句的组装 FoodDao
sql语句格式化，参数list、条件append
### 9 工具类beanutils、dbutils的应用
BeanUtils：（FoodTypeDao类）BeanHandler、BeanListHandler
### 10 枚举类型的简单应用
定义TableStatus实体类（枚举），在DinnerTableDao中判断
### 11 前端路径问题
${pageContext.request.contextPath }，写成相对路径
### 12 JSTL的简单应用
foodtype_list.jsp
### 13 过滤器（编码、浏览器类型）
web.xml中配置拦截条件filter-mapping和filter，一个特殊的servlet
EncodingFilter中需要区分GET和POST请求
### 14 多文件的上传（fileupload）
FoodServlet中update方法仅用来说明文件的上传
## 【其他未应用的知识点】
### 1 单例和非单例的判断
没有全局变量即可设为单例，防止线程安全问题，如FoodTypeDao可设为单例
### 2 监听器
### 3 JavaMail邮件
### 4 国际化