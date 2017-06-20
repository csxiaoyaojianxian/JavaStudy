# javaweb学习笔记-SSH整合开发demo
链接：[https://csxiaoyaojianxian.github.io/javaweb_study06](https://csxiaoyaojianxian.github.io/javaweb_study06 )  
名称：SSH整合开发demo，实现CRUD和登陆验证  
版本说明：  
>struts - 2.3.4.1  
>spring - 3.2.5  
>hibernate - 3.6  

## 【知识点】
## 1 模型驱动
### 1.1 实现接口
~~~
 implements ModelDriven<Employee>
~~~
### 1.2 封装数据
EmployeeAction中数据的自动封装（Employee）与手动封装（deptId）
~~~
private Employee employee = new Employee();   // 【模型驱动】
// 封装请求的部门id(下拉列表的实际的值)
private int deptId;
public void setEmployee(Employee employee) {
	this.employee = employee;
}
public Employee getEmployee() {
	return employee;
}
public void setDeptId(int deptId) {
	this.deptId = deptId;
}
public int getDeptId() {
	return deptId;
}
~~~
### 1.3 实现RequestAware接口
~~~
//实现RequestAware接口
public class EmployeeAction extends ActionSupport implements ModelDriven<Employee>, RequestAware{
	// 接收框架运行时候传入的代表request对象的map
	private Map<String, Object> request;
	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
}
~~~
## 2 注入，使用接口接收
~~~
// 注入Dao，【此处一定要用接口接收】
private IDeptDao deptDao;//JDK代理
public void setDeptDao(IDeptDao deptDao) {
	this.deptDao = deptDao;
}
~~~
此处声明的deptDao是一个IDeptDao接口，而非DeptDao类对象，原因在于：setDeptDao(IDeptDao deptDao)中传入的deptDao为Proxy代理类(继承了IDeptDao接口)，如果为setDeptDao(DeptDao deptDao)，则类型不匹配，无法传入  
## 3 抽取BaseDao（反射泛型的应用）
将【IOC容器(依赖)注入SessionFactory对象】抽取到BaseDao，作为对比，EmployeeDao继承了BaseDao，其他Dao未继承
## 4 页面包含
~~~
<!-- 包含头部页面 -->
<jsp:include page="/head.jsp"></jsp:include>
~~~
## 5 拦截器配置
struts.xml、UserInterceptor.java
## 6 全局视图、全局异常配置
struts.xml