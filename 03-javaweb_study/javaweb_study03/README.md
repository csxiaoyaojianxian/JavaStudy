# javaweb基础增强
链接：[https://csxiaoyaojianxian.github.io/javaweb_study03](https://csxiaoyaojianxian.github.io/javaweb_study03 )  
名称：javaweb基础增强  
内容：反射泛型、注解、日志以及利用反射泛型和注解开发自定义框架（模拟Struts）
## 代码结构
【package1】：com.csxiaoyao.study  
使用注解对知识点1的BaseDao（所有dao的公用的方法）代码优化  
【package2】：com.csxiaoyao.utils  
注解工具类  
【package其他】  
自定义框架实现  
【配置文件1】：log4j.properties  
日志文件配置  
【配置文件2】：mystruts.xml  
自定义框架配置
##【知识点】
## 1 反射泛型
BaseDao.java
~~~
/**
 * 所有dao的公用的方法，都在这里实现
 */
public class BaseDao<T>{
	// 保存当前运行类的参数化类型中的实际的类型
	private Class clazz;
	// 表名
	private String tableName;
	// 构造函数： 1. 获取当前运行类的参数化类型； 2. 获取参数化类型中实际类型的定义(class)
	public BaseDao(){
		//  this  表示当前运行类  (AccountDao/AdminDao)
		//  this.getClass()  当前运行类的字节码(AccountDao.class/AdminDao.class)
		//  this.getClass().getGenericSuperclass();  当前运行类的父类，即为BaseDao<Account>
		//                                           其实就是“参数化类型”， ParameterizedType   
		Type type = this.getClass().getGenericSuperclass();
		// 强制转换为“参数化类型”  【BaseDao<Account>】
		ParameterizedType pt = (ParameterizedType) type;
		// 获取参数化类型中，实际类型的定义  【new Type[]{Account.class}】
		Type types[] =  pt.getActualTypeArguments();
		// 获取数据的第一个元素：Accout.class
		clazz = (Class) types[0];
		// 表名  (与类名一样，只要获取类名就可以)
		tableName = clazz.getSimpleName();
	}
	/**
	 * 主键查询
	 * @param id	主键值
	 * @return      返回封装后的对象
	 */
	public T findById(int id){
		/*
		 * 1. 知道封装的对象的类型
		 * 2. 表名【表名与对象名称一样， 且主键都为id】
		 * 
		 * 即，
		 * 	  ---》得到当前运行类继承的父类  BaseDao<Account>
		 *   ----》 得到Account.class
		 */
		String sql = "select * from " + tableName + " where id=? ";
		try {
			//自定义的连接池工具类
			return JdbcUtils.getQuerrRunner().query(sql, new BeanHandler<T>(clazz), id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 查询全部
	 * @return
	 */
	public List<T> getAll(){
		String sql = "select * from " + tableName ;
		try {
			return JdbcUtils.getQuerrRunner().query(sql, new BeanListHandler<T>(clazz));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
~~~
AccountDao.java
~~~
public class AccountDao extends BaseDao<Account> {
	// 只需要写父类没有实现的方法(个性化需求)
}
~~~
## 2 注解
### 2.1 常用的注解
~~~
// 重写父类的方法
@Override
public String toString() {
	return super.toString();
}
// 抑制编译器警告
@SuppressWarnings({"unused","unchecked"})
private void save() {
	List list = null;
}
// 标记方法以及过时
@Deprecated
private void save1() {
}
~~~
### 2.2 自定义注解
~~~
public @interface Author {
	/**
	 * 注解属性
	 * 	1. 修饰为默认或public
	 *    2. 不能有主体
	 */
	String name();
	int age() default 30;   // 带默认值的注解;  使用的时候可以不写此属性值
}
~~~
使用
~~~
@Author(name = "sun", age = 30)
public void save() {

}
~~~
### 2.3 默认名称的注解
1. 注解属性默认名称为value
~~~
public @interface Author {
	// 如果注解名称为value,使用时候可以省略名称，直接给值
	// (且注解只有一个属性时候才可以省略名称)
	String value();
}
~~~
使用
~~~
@Author("sun")
@Author(value = "sun")
~~~

2. 注解属性类型为数组
~~~
public @interface Author {
	String[] value() default {"test1","test2"};
}
~~~
使用
~~~
@Author（{""，""}）
public void save() {
}
~~~

### 2.4 元注解
元注解，表示注解的注解
~~~
// 1. 指定注解的可用范围
@Target({
	TYPE,     类
	FIELD,     字段
	METHOD,  方法
	PARAMETER,   参数
	CONSTRUCTOR, 构造器
	LOCAL_VARIABLE  局部变量
})
// 2. 指定注解的声明周期
@Retention(RetentionPolicy.SOURCE)    注解只在源码级别有效
@Retention(RetentionPolicy.CLASS)      注解在字节码级别有默认值
@Retention(RetentionPolicy.RUNTIME)    注解在运行时期有效
~~~
### 2.5 注解反射
~~~
// 获取注解信息： name/age/remark
@Id
@Author(remark = "保存信息!!!", age = 19)
public void save() throws Exception {	
	// 1. 先获取代表方法的Method类型;
	Class clazz = App_1.class;
	Method m = clazz.getMethod("save");
	// 2. 再获取方法上的注解
	Author author = m.getAnnotation(Author.class);
	// 获取输出注解信息
	System.out.println(author.authorName());
	System.out.println(author.age());
	System.out.println(author.remark());
}
~~~
### 2.6 注解优化BaseDao代码
当表名与数据库名称不一致，字段与属性不一样，主键不叫id，上面的BaseDao失效，具体写法见代码
## 3 log4j
~~~
# 通过根元素指定日志输出的级别、目的地： 
# 日志输出优先级： debug < info < warn < error 
log4j.rootLogger=info,console,file
############# 日志输出到控制台 #############
# 日志输出到控制台使用的api类
log4j.appender.console=org.apache.log4j.ConsoleAppender
# 指定日志输出的格式： 灵活的格式
log4j.appender.console.layout=org.apache.log4j.PatternLayout
# 具体格式内容
log4j.appender.console.layout.ConversionPattern=%d %p %c.%M()-%m%n
############# 日志输出到文件 #############
log4j.appender.file=org.apache.log4j.RollingFileAppender
# 文件参数： 指定日志文件路径
log4j.appender.file.File=../logs/MyLog.log
# 文件参数： 指定日志文件最大大小
log4j.appender.file.MaxFileSize=5kb
# 文件参数： 指定产生日志文件的最大数目
log4j.appender.file.MaxBackupIndex=100
# 日志格式
log4j.appender.file.layout=org.apache.log4j.PatternLayout
log4j.appender.file.layout.ConversionPattern=%d %c.%M()-%m%n
~~~
## 4 应用：自定义框架
详见代码
## 5 Struts框架
略