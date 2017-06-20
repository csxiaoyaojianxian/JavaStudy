# 系统学习javaweb-02-java面相对象
## 1 面相对象细节
成员属性有默认的初始值，其中String等引用类型为null，而局部变量没有初始值。
## 2 匿名对象
>作用：
1. 调用某对象某方法仅一次
new Student().fun();
2. 作为实参调用某个函数
a.fun(new Student());

## 3 构造代码块
构造代码块的作用：给对象进行统一的初始化
构造函数的作用：给对应的对象进行初始化
经过java编译器编译后，构造代码块的代码块就会被移到构造函数中执行，在构造函数之前执行，构造函数的中代码是最后执行的。
~~~
class Baby{
	int id;  //身份证
	String  name;  //名字
	//构造代码块
	{
		System.out.println("构造代码块执行......");
	}
	//带参构造函数
	public  Baby(int i , String n){
		id  = i;
		name = n;
	}
	//无参构造方法
	public Baby(){
	}
	public void cry(){
		System.out.println(name+"哭...");
	}	
}
public class test {
	public static void main(String[] args) 
	{
		//构造代码块执行3次
		Baby b1 = new Baby(10,"sunshine");
		System.out.println("编号："+ b1.id + " 名字："+b1.name);
		new Baby(11,"sun");
		new Baby();		
	}
}
~~~

## 4 常见关键字
### 4.1 this 
this关键字代表了所属函数的调用者对象
>this关键字作用：
1. 同名成员变量与局部变量，方法内部默认访问局部变量（就近原则），可通过this关键字指定访问成员变量的数据（若只存在成员变量，java编译器会在该变量前面添加this关键字）。
2. 在一个构造函数中可以调用另外一个构造函数来初始化对象（this关键字必须位于构造函数中的第一个语句，且不能出现互相调用）。

~~~
class Animal{
	String name ;  //成员变量
	String color;
	public Animal(String name , String color){	
		this(name); //调用一个参数构造方法
		this.color = color;
	}
	public Animal(String name){
		this(); //调用无参构造方法。
		this.name = name;
	}
	public Animal(){
		System.out.println("调用无参构造方法");
	}
	public void eat(){
		String name = "老鼠"; //局部变量
		System.out.println(name+"在吃..."); //老鼠在吃
		System.out.println(this.name+"在吃..."); //猫在吃
	}
}
public class test {
	public static void main(String[] args) 
	{
		Animal cat = new Animal("猫","黑色");
		cat.eat();
	}
}
~~~
### 4.2 static
>两种访问方式：
1. 对象.属性名(对象.静态函数名)
2. 类名.属性名(类名.静态函数名)

静态变量作用：共享相同变量

>静态函数注意事项：
1. 静态函数只可访问静态成员。
2. 非静态函数可直接访问静态与非静态成员。
3. 静态函数不能出现this或者super关键字。
4. 静态数据的生命周期：静态成员变量数据是优先于对象存在的。
5. 静态函数只要存在对象，也可以访问非静态数据，只是不能直接访问。
6. 一个函数没有直接访问非静态成员时，可以使用static修饰，一般用于工具类型的方法。

~~~
class Student{
	String name;
	//使用static修饰country，country变为共享变量
	static String country  = "中国";	//国籍
	//构造函数
	public Student(String name){
		this.name = name;
	}
}
public class test {
	public static void main(String[] args) 
	{
		//直接通过类名.变量名取值
		System.out.println(Student.country);
		Student s1 = new Student("张三");
		Student s2 = new Student("陈七");’
		//通过对象.变量名取值
		s1.country = "China";
		//输出的国籍都被修改
		System.out.println("姓名："+s1.name+" 国籍："+ s1.country);  
		System.out.println("姓名："+s2.name+" 国籍："+ s2.country);
	}
}
~~~
### 4.3 super
>super关键字的作用：
1. 子父类存在同名成员时，子类默认访问子类的成员，可通过super关键字指定访问父类成员。
2. 创建子类对象时，默认会先调用父类无参的构造方法，可以通过super关键字指定调用父类的构造方法。

super关键字代表了父类空间的引用
>super关键字调用父类构造方法要注意的事项：
1. 如果子类的构造方法没有指定调用父类的构造方法，那么java编译器会在子类的构造方法加上super()语句。
2. super关键字调用父类的构造函数时，该语句必须要是子类构造函数中的第一个语句。
3. super与this关键字不能同时出现在同一个构造函数中调用其他的构造函数，因为两个语句都需要第一个语句。

super与this的区别
>super关键字与this关键字的区别：
1. 代表的事物不一致
  super关键字代表的是父类空间的引用。	
  this关键字代表的是所属函数的调用者对象。
2. 使用前提不一致
  super关键字必须要有继承关系才能使用。
  this关键字不需要存在继承关系也可使用。
3. 调用构造函数的区别
  super关键字是调用父类的构造函数。
  this关键字是调用本类的构造函数。

### 4.4 override & overload
方法的重写: 子父类的同名函数
>注意事项：
1. 方法名与形参列表必须一致。
2. 子类的权限修饰符必须要大于或等于父类的权限修饰符。
3. 子类的返回值类型必须要小于或等于父类的返回值类型。
4. 方法重写时，子类抛出的异常类型要小于或者等于父类抛出的异常类型。
  Exception(最坏)、RuntimeException(小坏)

方法的重载：在一个类中存在两个或两个以上的同名函数称为方法重载
>要求：
1. 函数名要一致。
2. 形参列表不一致（形参的个数或形参的类型不一致）
3. 与返回值类型无关。

### 4.5 instanceof
作用：判断一个对象是否属于指定的类别
使用前提：判断的对象与指定的类别必须存在继承或实现关系
使用格式：对象 instanceof 类别
### 4.6 final
>注意事项：
1. final关键字修饰一个基本类型的变量时，该变量不能重新赋值。
2. fianl关键字修饰一个引用类型变量时，该变量不能重新指向新的对象。
3. final关键字修饰一个函数的时候，该函数不能被重写。
4. final关键字修饰一个类的时候，该类不能被继承。

**常量修饰符一般为： public static final**

### 4.7 abstract class
>抽象类要注意的细节：
1. 如果一个函数没有方法体，那么该函数必须要使用abstract修饰,把该函数修饰成抽象函数。
2. 如果一个类出现了抽象的函数，那么该类也必须使用abstract修饰。
3. 如果一个非抽象类继承了抽象类，那么必须要把抽象类的所有抽象方法全部实现。
4. 抽象类可以存在非抽象方法，也可以存在抽象的方法.
5. 抽象类可以不存在抽象方法。 
6. 抽象类是不能创建对象的，因为抽象类存在抽象方法，如果能让抽象类创建对象，用抽象的对象调用抽象方法是没有任何意义的。
7. 抽象类是存在构造函数的，其构造函数是提供给子类创建对象的时候初始化父类的属性的。
8. abstract不能与private、static、final共同修饰一个方法。

### 4.8 interface
>注意事项：
1. 接口是一个特殊的类。
2. 接口的成员变量默认的修饰符为：public static final 。那么也就是说接口中的成员变量都是常量。
3. 接口中的方法都是抽象的方法，默认的修饰符为：public abstract。
4. 接口不能创建对象。
5. 接口没有构造方法。
6. 接口是给类去实现使用的，非抽象类实现一个接口的时候，必须要把接口中所有方法全部实现。
7. 抽象类实现一个接口时，可以实现也可以不实现接口中的方法。
8. 一个类可以实现多个接口，但只能继承一个类。
9. 一个接口可以继承多个接口。

## 5 多态
多态：一个对象具备多种形态。(父类的引用类型变量指向了子类的对象，或者是接口的引用类型变量指向了接口实现类的对象)
~~~
动物 a = new 狗();
~~~
>注意点：
1. 多态情况下，子父类存在同名的成员时，访问的都是父类的成员，除了在**同名非静态函数**时才是访问子类的。
2. 多态情况下，不能访问子类特有的成员。

>应用：
1. 多态用于形参类型的时候，可以接收更多类型的数据。
2. 多态用于返回值类型的时候，可以返回更多类型的数据。
提高了代码的拓展性

~~~
//动物类
abstract class Animal{
	String name;
	String  color = "动物色";
	public Animal(String name){
		this.name = name;
	}
	public abstract void run();
	public static void eat(){
		System.out.println("动物在吃..");
	}
}
//老鼠
class  Mouse extends Animal{
	String color = "黑色";
	public Mouse(String name){
		super(name);
	}
	public  void run(){
		System.out.println(name+"四条腿慢慢的走!");
	}
	public static void eat(){
		System.out.println("老鼠在偷吃..");
	}
	//老鼠特有方法---打洞
	public void dig(){
		System.out.println("老鼠在打洞..");
	}
}
public class test {
	public static void main(String[] args) 
	{
		Animal a = new Mouse("老鼠");
		//不能访问子类特有的成员
		//a.dig();
		//静态方法，访问父类成员
		a.eat();//动物在吃..
		//非静态函数，访问子类
		a.run();//老鼠四条腿慢慢的走!
	}
}
~~~
~~~
// 定义一个函数可以接收任意类型的图形对象，并且打印图形面积与周长。
//图形类
abstract class MyShape{
	public abstract void getArea();
	public abstract void getLength();	
}
class Circle extends MyShape{
	public static final double PI = 3.14;
	double r;
	public Circle(double r){
		this.r =r ;	
	}
	public  void getArea(){
		System.out.println("圆形的面积："+ PI*r*r);
	}
	public  void getLength(){
		System.out.println("圆形的周长："+ 2*PI*r);
	}
}
class Rect  extends MyShape{
	int width;
	int height;
	public Rect(int width , int height){
		this.width = width;
		this.height = height;
	}
	public  void getArea(){
		System.out.println("矩形的面积："+ width*height);
	}
	public  void getLength(){
		System.out.println("矩形的周长："+ 2*(width+height));
	}
}
public class test {
	public static void main(String[] args) 
	{
		
		Circle c = new Circle(4.0);
		print(c);
		Rect r = new Rect(3,4);
		print(r);
		//调用了使用多态的方法，定义的变量类型要与返回值类型一致。
		MyShape m = getShape(0); 
		m.getArea();
		m.getLength();
	}
	//需求1： 定义一个函数可以接收任意类型的图形对象，并且打印图形面积与周长。
	public static void print(MyShape s){ // MyShpe s = new Circle(4.0);
		s.getArea();
		s.getLength();
	}
	// 需求2： 定义一个函数可以返回任意类型的图形对象。
	public static MyShape  getShape(int i){
		if (i==0){
			return new Circle(4.0);
		}else{
			return new Rect(3,4);
		}
	}
}
~~~
~~~
//针对接口
Dao d = new UserDao(); //接口的引用类型变量指向了接口实现类的对象。
d.add();
~~~
## 6 强制类型转换
使能够调用子类特有的方法，解决多态中不能访问子类特有方法的问题
~~~
Animal a = new Mouse("米老鼠");
if(a instanceof Mouse){
    Mouse m = (Mouse)a;
    m.dig();
}
~~~
## 7 内部类
内部类：一个类定义在另外一个类的内部
应用场景：我们在描述A事物的时候，发现描述的A事物内部还存在另外一个比较复杂的事物B时候，而且这个比较复杂事物B还需要访问A事物的属性等数据，这时候就可以使用内部类描述B事物。
好处：内部类可以直接访问外部类的所有成员。
>注意细节：
1. 如果外部类与内部类存在同名的成员变量时，在内部类中默认情况下是访问内部类的成员变量。可以通过"外部类.this.成员变量名" 指定访问外部类的成员。
2. 私有的成员内部类只能在外部类提供一个方法创建内部类的对象进行访问，不能在其他类创建对象。
3. 成员内部类一旦出现了静态的成员，那么该类也必须使用static修饰。

~~~
//外部类
class Outer{
	//成员变量
	int x = 100; // Outer.class文件被加载到内存的时候存在内存中，静态的成员数据是不需要对象存在才能访问。
	//成员内部类
	static class Inner{
		static int i = 10;
		public void print(){
			System.out.println("这个是成员内部类的print方法！"+i);
		}
	}
	//在外部的方法中创建了内部类的对象，然后调用内部方法。
	public void instance(){
		Inner inner = new Inner();
		inner.print();
	}
}
public class test {
	public static void main(String[] args) 
	{
		System.out.println(Outer.Inner.i);
		Outer outer = new Outer();
		outer.instance();
		Outer.Inner inner = new Outer.Inner();
		inner.print();
	}
}
~~~
### 7.1 局部内部类
局部内部类：在一个类的方法内部定义另外一个类，称为局部内部类。
注意细节：如果局部内部类访问了一个局部变量，那么该局部变量必须使用final修饰
~~~
class  Outer{
	String name= "外部类的name";
	public void test(){
		//局部变量
		final	int y =100;
		//局部内部类
		class Inner{
			int x = 10;
			public void print(){
				System.out.println("这个是局部内部类的print方法.."+y);
			}	
		}
		//Inner对象的生命周期比局部变量y的生命周期要长
		Inner inner = new Inner();
		inner.print();
	}
}
public class test {
	public static void main(String[] args) 
	{
		Outer outer = new Outer();
		outer.test();
	}
}
~~~
### 7.2 匿名内部类
没有类名的类就称作匿名内部类，匿名内部类可以简化书写，一般用于实参。
~~~
abstract class Animal{
	public abstract Animal run();
	public abstract void sleep();
}
class Outer{
	public void print(){
		//在方法内部定义一个类继承Animal类，然后调用run方法与sleep()。
		//局部内部类
		class Dog extends Animal{	
			public Animal run(){
				System.out.println("狗在跑..");
				return this;
			}
			public void sleep(){
				System.out.println("狗趴在睁开眼睛睡..");
			}
		}
		Dog d = new Dog();
		d.run();	
		d.sleep();
		//匿名内部类 ：匿名内部类只是没有类名，其他的一概成员都是具备的
		//匿名内部类与Animal是继承的关系
		Animal a = new Animal(){  //多态
			//匿名内部类的成员 
			public Animal run(){
				System.out.println("狗在跑..");
				return this;
			}
			public void sleep(){
				System.out.println("狗趴在睁开眼睛睡..");
			}
			//特有的方法
			public void bite(){
				System.out.println("狗在咬人..");
			}
		};
		//}.run();
//		a.bite();//不可用
		a.run().sleep();
	}
}
public class test {
	public static void main(String[] args) 
	{
		Outer outer = new Outer();
		outer.print();
	}
}
~~~
~~~
//实现关系下匿名内部类
interface Dao{
	public void add();
}
class Outer{
	public void print(){
		//创建一个匿名内部类的对象
		new Dao(){   //接口不能创建对象，此处是借用接口名
			 public void add(){
				System.out.println("添加成功");
			 }
		}.add();
	}
}
public class test01 {
	public static void main(String[] args) 
	{
		test(new Dao(){
			public void add(){
				System.out.println("添加员工成功");
			}
		});
	}
	//调用这个方法...
	public static void  test(Dao d){  //形参类型是一个接口引用..
		d.add();
	}
}
~~~
## 8 异常
~~~
//自定义异常类
class DivException extends Exception{
	public DivException(String message){
		super(message);
	}
}
public class test {
	public static void main(String[] args) 
	{
		//捕获异常
		try{
			int[] arr = null;
			//调用声明抛出异常类型的方法
			div(4,0,arr); 
		}catch(Exception e){
			System.out.println("出现异常");
			e.printStackTrace();
		}finally {
			System.out.println("释放资源");
		}
	}
	public static void div(int a, int b,int[] arr) throws Exception,NullPointerException {
		if(b==0){
			//抛出异常对象
			throw new DivException("除法出错");
		}else if(arr==null){
			throw new  NullPointerException();
		}
		int c = a/b;
		System.out.println("c="+c);
	}
}
~~~
## 9 Object类
Object类是所有类的终极父类，任何一个类都继承了Object类。
>常用方法：
----toString();  重写toString之后，直接输出一个对象的时候，就会输出符合需求的格式数据。
----equals(Object obj);  用于比较两个对象的内存地址，判断两个对象是否为同一个对象。
----hashCode();  返回该对象的哈希码值

## 10 对象拷贝
>对象的浅克隆：
1. 如果一个对象需要调用clone的方法克隆，那么该对象所属的类必须要实现Cloneable接口。
2. Cloneable接口只不过是一个标识接口而已，没有任何方法。
3. 对象的浅克隆就是克隆一个对象的时候，如果被克隆的对象中维护了另外一个类的对象，这时候只是克隆另外一个对象的地址，而没有把另外一个对象也克隆一份。
4. 对象的浅克隆也不会调用到构造方法的。

~~~
public class Demo {
	public static void main(String[] args) throws Exception {
		Address address = new Address("江苏");
		Person p1 = new Person(110,"sun1",address);
		Person p2 = (Person) p1.clone(); //clone() 克隆了一个对象。
		p2.name = "sun2";
		p2.address.city ="苏州";
		System.out.println("p1:"+ p1);
		System.out.println("p2:"+ p2);
	}
}
~~~
>对象的深克隆：
对象的深克隆就是利用对象的输入输出流把对象先写到文件上，然后再读取对象的
信息这个过程称作为对象的深克隆（ObjectInputStream、ObjectOutputStream）

~~~
public class Demo {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Address address = new Address("江苏");
		Person p1 = new Person(110,"sun1",address);
		writeObj(p1);
		Person p2  =readObj();
		p2.address.city = "苏州";
		System.out.println("p1:"+ p1);
		System.out.println("p2:"+ p2);
	}
	//再从文件中读取对象的信息
	public static Person readObj() throws ClassNotFoundException, IOException{
		FileInputStream fileInputStream = new FileInputStream("F:\\obj.txt");
		//创建对象的输入流对象
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		return (Person) objectInputStream.readObject();
	}
	//先要把对象写到文件上。
	public static void writeObj(Person p) throws IOException{
		//建立一个文件 的输出流对象
		FileOutputStream fileOutputStream  = new FileOutputStream("F:\\obj.txt");
		//建立对象的输出流
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		//把对象写出
		objectOutputStream.writeObject(p);
		//关闭资源
		objectOutputStream.close();	
	}
}
~~~
## 11 反射
~~~
public class Person {
	private int id;
	String name;
	public Person(int id,String name){
		this.id = id;
		this.name = name;
	}
	public Person(){}
	public void eat(int num){
		System.out.println(name+"吃很"+num+"斤饭");
	}
	private static void sleep(int num){
		System.out.println("明天睡上"+num+"小时");
	}
	public static void  sum(int[] arr){
		System.out.println("长度是："+ arr.length);
	}
	@Override
	public String toString() {
		return " 编号："+ this.id +" 姓名："+ this.name;
	}
}
~~~
~~~
public class test extends Thread {
	public static void main(String[] args) throws Exception {
		/*
		 * 获取Class对象的方式
		 */
		// 推荐使用：获取Class对象的方式一
		Class clazz1 = Class.forName("java_test.Person");
		System.out.println("clazz1:" + clazz1);
		// 获取Class对象的方式二：通过类名获取
		Class clazz2 = Person.class;
		System.out.println("clazz1==clazz2?" + (clazz1 == clazz2));
		// 获取Class对象的方式三：通过对象获取
		Class clazz3 = new Person(110, "sun").getClass();
		System.out.println("clazz2==clazz3?" + (clazz2 == clazz3));
		/*
		 * 通过Class对象获取构造方法
		 */
		//通过Class对象getConstructors()获取一个类的所有公共的构造方法
		Constructor[] constructors1 = clazz1.getConstructors();
		for(Constructor constructor : constructors1){
			System.out.println(constructor);
		}
		//获取到一个类的所有构造方法，包括私有的
		Constructor[] constructors2 =  clazz1.getDeclaredConstructors();
		for(Constructor constructor : constructors2){
			System.out.println(constructor);
		}
		// getConstructor 获取单个指定的构造方法
		Constructor constructor3 = clazz1.getConstructor(int.class,String.class);
		Person p1  = (Person) constructor3.newInstance(110,"sun2"); // newInstance()创建一个对象
		System.out.println(p1);
		// 获取私有的构造函数
		Constructor constructor4 =  clazz1.getDeclaredConstructor(null);
		// 暴力反射
		constructor4.setAccessible(true);
		Person p2  =(Person) constructor4.newInstance(null);
		System.out.println(p2);
		/*
		 * 通过Class对象获取对应方法，在反射技术中使用Method类描述方法
		 */
		// getMethods() 获取所有公共方法
		Method[] methods1 = clazz1.getMethods();
		// 获取所有方法，但不包含父类方法
		Method[] methods2 = clazz1.getDeclaredMethods();
		for(Method method  : methods1){
			System.out.println(method);
		}
		Person p3 = new Person(110,"sun3");
		Method m1 = clazz1.getMethod("eat", int.class);
		// invoke 执行一个方法，第一个参数：方法的调用对象， 第二参数：方法所需要的参数
		m1.invoke(p3, 3);
		//执行私有的方法
		Method m2 =clazz1.getDeclaredMethod("sleep",int.class);
		//设置访问权限允许访问
		m2.setAccessible(true);
		m2.invoke(null, 6);
		Method m3 = clazz1.getMethod("sum", int[].class);
		m3.invoke(p3,new int[]{12,5,9});
		/*
		 * 通过反射获取对应的成员变量，在反射技术中使用Field类描述成员变量
		 */
		//获取所有成员变量
		Field[] fields = clazz1.getDeclaredFields();
		for(Field field  : fields){
			System.out.println(field);
		}
		Person p4 = new Person();
		Field field = clazz1.getDeclaredField("id");
		//设置访问权限可以访问
		field.setAccessible(true);
		field.set(p4, 110); //第一个参数： 设置该数据 的成员变量， 第二个参数：属性值。
		System.out.println(p4);
	}
}
~~~
## 12 静态导入
静态导入的作用：简化书写
静态导入可以作用一个类的所有静态成员，格式：
~~~
import static 包名.类名.静态的成员;
~~~
如果静态导入的成员与本类的成员存在同名，默认使用本类的静态成员，如果需要指定使用静态导入的成员，需要在静态成员前面加上类名
~~~
import static java.lang.System.out;
import static java.util.Collections.binarySearch;
import static java.util.Collections.max;
public class test {
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(13);
		list.add(9);
		list.add(10);
		list.add(19);
		//排序（sort重名，默认调用本类的方法）
		sort(list);//调用本类的方法
		Collections.sort(list);//调用Collections类的方法
		out.println("集合的元素："+ list);
		out.println("索引值："+ binarySearch(list,13));
		out.println("最大值："+ max(list));
	}
	public static void sort(ArrayList<Integer> list){
		System.out.println("本类的sort方法.....");
	}
}
~~~
## 13 自动装箱与自动拆箱
~~~
基本数据类型	包装类型
byte			Byte
short			Short
int				Integer
long			Long 
float			Float
double			Double 
boolean		Boolean 
char			Character
~~~
~~~
public class test {
	public static void main(String[] args) {
		String str = "12";
		//字符串转换成int类型数据。 可以把字符串转换成对应的数字
		int i = Integer.parseInt(str);
		System.out.println(i+1);//13
		//把数字转换成字符串
		System.out.println("把整数转换成对应的字符串："+Integer.toString(i));//12
		//把整数转换成对应的进制形式
		System.out.println("10的二进制："+ Integer.toBinaryString(10));//1010
		System.out.println("10的十六进制："+ Integer.toHexString(10));//a
		//可以把字符串当成对应的进行数据帮你转换
		String data = "10";
		int a = Integer.parseInt(data, 2);
		System.out.println("a="+a);//a=2
		//集合：集合是可以存储任意对象类型数据的容器
		ArrayList list = new ArrayList();
		list.add(1);
		list.add(2);
		list.add(3);
		//自动装箱：自动把java的基本数据类型数据转换成对象类型数据
		int temp = 10;  //基本数据类型
		Integer b =temp; //把a存储的值赋予给b变量。
		//自动拆箱： 把引用类型的数据转换成基本类型的数据
		Integer c = new Integer(13);
		int d = c; //
		System.out.println(d);//13
		//引用的数据类型
		Integer e = 128;
		Integer f = 128; 
		System.out.println("同一个对象吗？"+(e==f)); //false
		Integer g = 127;
		Integer h = 127; 
		System.out.println("同一个对象吗？"+(g==h));// true, Integer类内部维护了缓冲数组，该缓冲数组存储的-128~127 这些数据在一个数组中。如果你获取的数据是落入到这个范围之内的，那么就直接从该缓冲区中获取对应的数据。
	}
}
~~~