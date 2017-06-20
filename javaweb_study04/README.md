# javaweb学习笔记-Hibernate的配置与api操作
链接：[https://csxiaoyaojianxian.github.io/javaweb_study04](https://csxiaoyaojianxian.github.io/javaweb_study04 )  
名称：Hibernate的配置与api操作、关联映射  
说明：直接执行代码，自动建表
## 【知识点】
## 1 代码说明
【package1】：com.csxiaoyao.crud  
hibernate的CRUD的api操作  
【package2】：com.csxiaoyao.query  
四种查询：主键查询、HQL（详解多种情况）、Criteria、本地SQL  
【package3】：com.csxiaoyao.mapping  
关联映射、inverse控制反转、cascade级联操作、一对一、组件、继承  
【package4】：com.csxiaoyao.session  
session缓存的使用  
【package5】：com.csxiaoyao.second_cache  
二级缓存的使用  
【package6】：com.csxiaoyao.compositeKey  
复合主键映射  
【package7】：com.csxiaoyao.utils  
自动建表、创建Session的工厂及获取Session对象的抽取  
【配置文件1】：hibernate.cfg.xml  
主配置文件说明  
【配置文件2】：Employee.hbm.xml  
单列主键映射及普通字段类型说明  
【配置文件3】：User.hbm.xml  
多列主键映射配置  
【配置文件4】：mapping下所有配置文件  
关联映射  
## 2 Hibernate的api操作
### 2.1 分页
List<Employee> getAll(int index, int count);  
此外，在query.App_page中有详细的实现
### 2.2 Hibernate  Api
|---- Configuration   配置管理类对象  
 -------- config.configure();    加载主配置文件的方法(hibernate.cfg.xml)，默认加载src/hibernate.cfg.xml  
 -------- config.configure(“cn/config/hibernate.cfg.xml”);   加载指定路径下指定名称的主配置文件  
 -------- config.buildSessionFactory();   创建session的工厂对象  
|---- SessionFactory   session的工厂（代表这个hibernate.cfg.xml配置文件）  
 -------- sf.openSession();   创建一个sesison对象  
 -------- sf.getCurrentSession();   创建session或取出session对象  
|---- Session   session对象维护了一个连接(Connection), 代表与数据库连接的会话，Hibernate最重要的对象，只要使用hibernate与数据库操作，都用到这个对象  
 -------- session.beginTransaction();   开启一个事务，hibernate要求所有的与数据库的操作必须有事务的环境，否则报错  
|---- Transaction   hibernate事务对象  
【CRUD】  
session.save(obj);   保存一个对象（EmployeeDaoImpl.save(obj)）  
session.update(obj);  更新一个对象（EmployeeDaoImpl.update(obj)）  
session.saveOrUpdate(obj);  保存或者更新（EmployeeDaoImpl.update(obj)）  
没有设置主键，执行保存；有设置主键，执行更新操作；如果设置主键不存在报错
session.delete(id);  删除一个对象（EmployeeDaoImpl.delete(id)）  
【主键查询】  
session.get(Entity.class, id);   主键查询（EmployeeDaoImpl.findById(id)）  
session.load(Entity.class, id);   主键查询 (支持懒加载)  
【HQL查询】  
HQL、SQL查询区别：  
SQL: (结构化查询语句)查询的是表以及字段；不区分大小写  （EmployeeDaoImpl.getAll()、getAll(String)）  
HQL: hibernate query language 即hibernate提供的面向对象的查询语言，查询的是对象及对象属性；区分大小写  
【Criteria查询】  
完全面向对象的查询  
【本地SQL查询】  
复杂的查询，使用原生sql查询 (缺点： 不能跨数据库平台)  
### 2.3 测试类
com.csxiaoyao.crud.HibernateTest.java
## 3 Hibernate的配置
### 3.1 Hibernate.cfg.xml 主配置文件
主配置文件中主要配置：数据库连接信息、其他参数、映射信息  
常用配置查看源码：hibernate-distribution-3.6.0.Final\project\etc\hibernate.properties  
【详细配置信息注释见代码】  
自动建表说明：
~~~
#hibernate.hbm2ddl.auto create-drop  每次在创建sessionFactory时候执行创建表；当调用sesisonFactory的close方法的时候，删除表
#hibernate.hbm2ddl.auto create  每次都重新建表； 如果表已经存在就先删除再创建
#hibernate.hbm2ddl.auto update  如果表不存在就创建； 表存在就不创建；
#hibernate.hbm2ddl.auto validate  (生成环境时候) 执行验证： 当映射文件的内容与数据库表结构不一样的时候就报错
~~~
### 3.2 映射配置
1. 普通字段类型
2. 主键映射(单列、多列)
3. 复合主键映射

### 3.3 自动加载映射文件
~~~
sf = new Configuration()
	.configure()
	.addClass(User.class)  //（测试） 会自动加载映射文件：User.hbm.xml
	.buildSessionFactory();
~~~
## 4 Hibernate中的映射
>一对多、多对一映射（one2many）  
多对多映射（many2many）  
一对一映射（one2one 多对一的特殊应用）  
组件映射（component 多个bean合成一张表、基于主键/基于外键）  
继承映射（extends 所有子类映射到一张表、每个类映射一张表、每个子类映射一张表）  

### 4.1 集合映射
（collection）用户与收货地址，一个用户对应多个地址
### 4.2 多对一与一对多映射
在一对多与多对一的关联关系中，保存数据最好的通过多的一方来维护关系，这样可以减少update语句的生成，从而提高hibernate的执行效率。  
配置一对多与多对一：“双向关联”  
只配置一对多：“单项一对多”  
只配置多对一：“单项多对一”  
（配置了哪一方，哪一方才有维护关联关系的权限）

【Inverse控制反转属性】  
Inverse属性，控制反转，用于维护关联关系，表示控制权是否转移(在一的一方起作用)  
~~~
Inverse = false  不反转，当前方有控制权
Inverse = true  反转，当前方没有控制权
~~~
维护关联关系中，是否设置inverse属性：  
**1. 保存数据**  
如果设置控制反转，即inverse=true，然后通过部门方维护关联关系。在保存部门的时候，同时保存员工， 数据会保存，但关联关系不会维护，即外键字段为NULL  
**2. 获取数据**  
无影响  
**3. 解除关联关系**  
~~~
inverse=false，可以解除关联
inverse=true，当前方(部门)没有控制权，不能解除关联关系(不会生成update语句,也不会报错)
~~~

**4. 删除数据**  
~~~
inverse=false，有控制权，可以删除。先清空外键引用，再删除数据。
inverse=true，没有控制权: 如果删除的记录有被外键引用，会报错，违反主外键引用约束。如果删除的记录没有被引用，可以直接删除。
~~~

【cascade级联操作属性】
~~~
none		不级联操作， 默认值
save-update		级联保存或更新
delete		级联删除
save-update,delete		级联保存、更新、删除
all		同上。级联保存、更新、删除
~~~
### 4.3 多对多映射
维护关联关系  
设置inverse属性，在多对多种维护关联关系的影响  
**1. 保存数据**  
~~~
inverse=false，有控制权，可以维护关联关系，保存数据的时候会把对象关系插入中间表
inverse=true，没有控制权，不会往中间表插入数据
~~~

**2. 获取数据**  
无影响  
**3. 解除关系**  
~~~
inverse=false，有控制权，解除关系就是删除中间表的数据
inverse=true，没有控制权，不能解除关系
~~~

**4. 删除数据**  
~~~
inverse=false，有控制权，先删除中间表数据，再删除自身
inverse=true，没有控制权，如果删除的数据有被引用，会报错！ 否则，才可以删除
~~~
### 4.4 一对一映射
**1. 基于外键**  
mapping.one2one  
多对一映射添加unique="true"属性  
**2. 基于主键**  
mapping.one2one2
### 4.5 组件映射
mapping.component
### 4.6 继承映射
**1. 所有子类映射到一张表**  
mapping.extends1  
**2. 每个类映射一张表**  
mapping.extends3  
**3. 每个子类映射一张表**  
mapping.extends2、mapping.extends4  
## 5 缓存
Hibernate中缓存分：一级缓存、二级缓存
### 5.1 一级缓存
【概念】  
1. Hibenate中一级缓存，也叫做session的缓存，可以在session范围内减少数据库的访问次数，只在session范围有效，Session关闭，一级缓存失效  
2. 当调用session的save/saveOrUpdate/get/load/list/iterator方法的时候，都会把对象放入session的缓存中  
3. Session的缓存由hibernate维护， 用户不能操作缓存内容。如果想操作缓存内容，必须通过hibernate提供的evit/clear方法操作  

【特点】  
只在(当前)session范围有效，作用时间短，效果不是特别明显；在短时间内多次操作数据库，效果比较明显  
【api】
~~~
session.flush();       让一级缓存与数据库同步
session.evict(obj);    清空一级缓存中指定的对象
session.clear();       清空一级缓存中缓存的所有对象
~~~
【使用案例】  
批量操作使用使用：
~~~
Session.flush();   // 先与数据库同步
Session.clear();   // 再清空一级缓存内容
~~~
### 5.2 二级缓存
Hibernate提供了基于应用程序级别的缓存， 可以跨多个session，即不同的session都可以访问缓存数据  
【二级缓存使用步骤】  
（1）开启二级缓存  
（2）指定缓存框架  
（3）指定类加入二级缓存  
（4）测试二级缓存  
【缓存策略】
~~~
<class-cache usage="read-only"/>     放入二级缓存的对象，只读; 
<class-cache usage="nonstrict-read-write"/>  非严格的读写
<class-cache usage="read-write"/>    读写； 放入二级缓存的对象可以读、写；
<class-cache usage="transactional"/>   (基于事务的策略)
~~~
## 6 懒加载
``get``: 及时加载，只要调用get方法立刻向数据库查询  
``load``: 默认使用懒加载，当用到数据的时候才向数据库查询  
在真正使用数据的时候才向数据库发送查询的sql；调用集合的size()/isEmpty()方法，只是统计，不真正查询数据  
【解决session关闭后使用懒加载数据报错】  
// 方式1： 先使用一下数据  
dept.getDeptName();  
// 方式2：强迫代理对象初始化  
Hibernate.initialize(dept);  
// 方式3：关闭懒加载  
设置lazy=false;  
// 方式4： 在使用数据之后，再关闭session  
## 7 hibernate对连接池的支持
Hibernate 自带一个连接池（只有一个连接），且支持C3P0连接池
【Hbm对C3P0连接池支持的核心类】
~~~
#hibernate.connection.provider_class org.hibernate.connection.C3P0ConnectionProvider
~~~
【配置连接池参数】
~~~
###########################
### C3P0 Connection Pool###		   【Hbm对C3P0连接池支持】
###########################
#hibernate.c3p0.max_size 2				最大连接数
#hibernate.c3p0.min_size 2				最小连接数
#hibernate.c3p0.timeout 5000           	超时时间
#hibernate.c3p0.max_statements 100     最大执行的命令的个数
#hibernate.c3p0.idle_test_period 3000    空闲测试时间
#hibernate.c3p0.acquire_increment 2     连接不够用的时候， 每次增加的连接数
#hibernate.c3p0.validate false
~~~