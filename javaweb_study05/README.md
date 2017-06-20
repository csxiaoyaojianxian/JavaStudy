# javaweb学习笔记-SSH整合
链接：[https://csxiaoyaojianxian.github.io/javaweb_study05](https://csxiaoyaojianxian.github.io/javaweb_study05 )  
名称：SSH整合  
版本说明：  
>struts - 2.3.4.1  
>spring - 3.2.5  
>hibernate - 3.6  

## 【整合步骤】  
### 1. 引入SSH Jar文件
struts-core  
hibernate-core  
spring  
&emsp;&emsp;Core  核心功能  
&emsp;&emsp;Web  对web模块支持  
&emsp;&emsp;Aop   aop支持  
&emsp;&emsp;Orm   对hibernate支持  
&emsp;&emsp;Jdbc/tx  jdbc支持包、事务相关包  
### 2. 配置
**web.xml**  
初始化struts功能、spring容器、OpenSessionInView模式  
**struts.xml**  
配置请求路径与映射action的关系  
***Spring.xml***  
IOC容器配置  
&emsp;&emsp;bean-base.xml     【公用信息配置】  
&emsp;&emsp;bean-service.xml  
&emsp;&emsp;bean-dao.xml  
&emsp;&emsp;bean-action.xml  
### 3. 开发demo
Entity/Dao/service/action
### 4 测试
运行并访问：http://localhost:8080/javaweb_study05/show.action