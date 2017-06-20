# 系统学习javaweb-01-java基础语法
## 1 数据类型转换
### 1.1 小数据类型和大数据类型的转换
>小数据类型 --> 大数据类型  自动类型转换
大数据类型 --> 小数据类型  强制类型转换

小数据类型和大数据类型混合运算，结果取决于大数据类型
~~~
int i = 128;
//byte b = i;//错误，数据类型转换错误
byte b1 = (byte)i;
//b1= b1+1;//错误，数据类型转换错误
b1+=1;//正确，java编译器进行了强制类型转换
//byte b2 = 128;//错误，数据越界
byte b3 = 1+1;//正确，可以直接赋值确定的常量值
byte b4 = 2;
//byte b5 = b3 + b4;//错误，结果为int类型
byte b5 = (byte)(b3 + b4);
~~~
### 1.2 byte、short、char类型数据运算时会转换为int类型数据再运算
字符类型和字符串类型的区别
~~~
char a = 'a';
String s = "s";//字符串类型为引用类型
System.out.println(a + 1);//98
System.out.println(s + 1);//s1
~~~

## 2 运算符
### 2.1 自增运算 ++
~~~
int i = 0;
i = i++;//0
~~~
### 2.2 字符串连接 +
~~~
//每次运算只取两个数
int i1 = 1, i2 = 2, i3 = 3;
System.out.println(i1+i2+i3+"sunshine"+4+5+6);//6sunshine456
System.out.println(1+2+3+"sunshine"+4+5+6);//6sunshine456
~~~
### 2.3 求余运算 %
~~~
//正负号仅由被除数决定
System.out.println(10%3);//1
System.out.println(10.1%-3);//1.0999999999999996
System.out.println(-10%3);//-1
System.out.println(-10%-3.1);//-0.6999999999999997
~~~
### 2.4 短路问题
>区别“&”和“&&”(“|”和“||”)：
“&”，左边无论真假，右边都进行运算
“&&”，如果左边为真，右边参与运算，如果左边为假，右边不参与运算
“^”异或与“|”或的不同之处：当左右都为true时，结果为false

### 2.5 位运算符
交换变量值
~~~
int a=3,b=5;
a = a^b;
b = a^b;
a = a^b;
~~~
### 2.6 移位运算符
\>>> 无符号右移
## 3 数组与集合
### 3.1 数组
存储同一种数据类型的集合容器
>特点：
1. 只能存储同一种数据类型的数据
2. 一旦初始化，长度固定
3. 数组中的元素与元素之间的内存地址是连续的

数组初始化
~~~
//动态初始化
int[] arr1 = new int[10];
int[][] arr2 = new int[10][];
//静态初始化
int[] arr1 = {1,2,3,4,5};
int[][] arr2 = {{1,2,3},{4,5},{6,7,8,9}};
~~~
注意： Object类型的数组可以存储任意类型的数据
String[] arr = new String[1000];
~~~
public class test {
	public static void main(String[] args) {
		Object[] arr = new Object[5];
		arr[1] = "abc";
		arr[2] = 'a';
		arr[3] = 12;
		System.out.println(Arrays.toString(arr));//[null, abc, a, 12, null]
	}
}
~~~
### 3.2 单列集合
集合是存储对象数据的集合容器。
>集合对比数组的优势：
1. 集合可以存储任意类型的对象数据，数组只能存储同一种数据类型的数据
2. 集合的长度是会发生变化的，数组的长度是固定的

#### 3.2.1 单列集合关系
>单列集合关系
----| Collection  单列集合的根接口。 
--------| List  实现了List接口的集合类，特点：有序，可重复。
------------| ArrayList  底层维护了一个Object数组实现，特点：查询速度快，增删慢。
------------| LinkedList  底层使用了链表数据结构实现，特点： 查询速度慢，增删快。
------------| Vector  底层维护了一个Object数组实现，实现与ArrayList相同，但是Vector是线程安全的，操作效率低
--------| Set  实现了Set接口的集合类，特点：无序，不可重复。
------------| HashSet  底层使用哈希表支持，特点：存取速度快。
------------| TreeSet  底层使用红黑树（二叉树）数据结构实现，特点：会对元素进行排序存储。

#### 3.2.2 Collection接口方法
>接口方法
--增加
----add(E e)  添加成功返回true，添加失败返回false
----addAll(Collection c)  把一个集合的元素添加到另外一个集合中去
--删除
----clear() 
----remove(Object o) 
----removeAll(Collection  c) 
----retainAll(Collection  c) 
--查看
----size() 
--判断
----isEmpty() 
----contains(Object o) 
----containsAll(Collection<?> c) 
--迭代
----toArray() 
----iterator() 

~~~
public class test {
	public static void main(String[] args) {
		Collection c = new ArrayList();
		c.add("sunjianfeng");
		c.add("孙剑峰");
		c.add("sunshine");
		System.out.println("添加成功吗？"+c.add("sunshine"));//true, ArrayList可重复
		//创建集合
		Collection c2 = new ArrayList();
		c2.add("sun1");
		c2.add("sun2");
		c2.add("sun3");
		// 把c2的元素的添加到c集合中去
		c.addAll(c2);
		// ---删除方法---
		c.removeAll(c2); //删除c集合中与c2的交集元素
		c.retainAll(c2); //保留c集合与c2的交集元素，其他的元素一并删除
		System.out.println("删除成功吗？"+c.remove("sunshine")); // remove 指定集合中的元素删除，删除成功返回true，删除失败返回false，只删除一个
		c.clear(); //clear()清空集合中的元素
		System.out.println("查看元素个数："+c.size());
		System.out.println("集合的元素："+ c);
	}
}
~~~
~~~
class Person{
	int id; 
	String name;
	public Person(int id, String name) {
		this.id = id;
		this.name = name;
	}
	@Override
	public String toString() {
		return "{编号："+this.id+" 姓名："+ this.name+"}";
	}
	@Override
	public boolean equals(Object obj) {
		Person p = (Person)obj;
		return this.id == p.id ;
	}
	//java规范： 一般重写equlas方法都会重写hashCode方法
	@Override
	public int hashCode() {
		return this.id;
	}
}
public class test {
	public static void main(String[] args) {
		Collection c = new ArrayList();
		c.add("sun1");
		c.add("sun2");
		c.add("sun3");
		System.out.println("判断集合是否为空元素："+ c.isEmpty());
		System.out.println("判断集合中是否存在指定的元素："+ c.contains("sun4"));
		//集合添加自定义的元素
		Collection c1 = new ArrayList();
		c1.add(new Person(110,"sun1"));
		c1.add(new Person(119,"sun2"));
		c1.add(new Person(120,"sun3"));	
		Collection c2 = new ArrayList();
		c2.add(new Person(110,"sun3"));
		c2.add(new Person(121,"sun4"));
		System.out.println("c1集合有包含c2集合中的所有元素吗？"+ c1.containsAll(c2));
		//如果在现实生活中，只要身份证编号一致，那么就为同一个人
		System.out.println("存在该元素吗？"+c1.contains(new Person(120,"sun"))); // contains方法内部是依赖于equals方法进行比较的。
		System.out.println("集合的元素："+ c1);
		// 迭代
		Object[] arr = c1.toArray(); 
		System.out.println(Arrays.toString(arr));
		// 把编号是110的人信息输出
		for(int i = 0 ; i<arr.length ; i++){
			Person p = (Person) arr[i];  //从Object数组中取出的元素只能使用Object类型声明变量接收，如果需要其他的类型需要进行强制类型转换。
			if(p.id==110){
				System.out.println(p);
			}
		}
	}
}
~~~
#### 3.2.3 迭代器
抓取Collection中元素的迭代的方法：
>方法
----| toArray()   数组法
----| iterator()   迭代器法
--------| hasNext()  判断是否有元素可遍历
--------| next()  获取元素
--------| remove()  移除迭代器最后一次返回的元素

NoSuchElementException 没有元素的异常
~~~
public class test {
	public static void main(String[] args) {
		Collection c = new ArrayList();
		c.add("sun1");
		c.add("sun2");
		c.add("sun3");
		//遍历集合的元素
		//方式一：使用toArray方法
		Object[] arr = c.toArray();
		for(int i = 0 ; i<arr.length ; i++){
			System.out.print(arr[i]+",");
		}
		//方式二：使用iterator迭代器
		Iterator it1 = c.iterator();  //返回一个迭代器
		while(it1.hasNext()){// hasNext() 判断是否有元素可以遍历
			System.out.println("元素："+ it1.next()); //获取元素
		}
		//删除
		Iterator it2 = c.iterator(); 
		it2.next();it2.next();
		it2.remove();  //删除迭代器最后一次返回的元素。
		//清空集合的元素
		while(it2.hasNext()){
			it2.next();
			it2.remove();
		}
		System.out.println("集合的元素："+ c);
	}
}
~~~
#### 3.2.4 List
List接口中特有方法：
>方法
--添加
----add(int index, E element) 
----addAll(int index, Collection<? extends E> c) 
--获取
----get(int index) 
----indexOf(Object o) 
----lastIndexOf(Object o) 
----subList(int fromIndex, int toIndex) 
--修改
----set(int index, E element) 
--迭代
----listIterator()
--------hasPrevious()  判断是否存在上一个元素
--------previous()  当前指针先向上移动一个单位，然后再取出当前指针指向的元素
--------next()  先取出当前指针指向的元素，然后指针向下移动一个单位
--------add(E e)  把当前有元素插入到当前指针指向的位置上
--------set(E e)  替换迭代器最后一次返回的元素

迭代器在遍历元素的过程中（迭代器创建到使用结束），不允许使用集合对象改变集合中的元素个数，如果需要添加或者删除只能使用迭代器的方法进行操作，否则会出现ConcurrentModificationException异常。	
~~~
public class test {
	public static void main(String[] args) {
		List list=  new ArrayList();
		list.add("sun1"); //添加元素到集合末尾
		list.add("sun3");
		//添加方法
		list.add(1, "sun2"); // 把元素添加到集合中指定索引值位置上
		List list2 = new ArrayList();
		list2.add("sun2-1");
		list2.add("sun2-2");
		list.addAll(2,list2); //把list2的元素添加到list集合指定索引值的位置上
		//获取的方法 
		System.out.println("get方法获取元素："+list.get(1)); //根据索引值获取集合中的元素
		//使用get方法遍历集合的元素：
		for (int i = 0; i < list.size() ; i++) {
			System.out.print(list.get(i)+",");
		}
		System.out.println("找出指定元素第一次出现在集合中的索引值："+ list.indexOf("sunshine"));
		System.out.println("找指定的元素最后一次出现在集合中的索引值："+list.lastIndexOf("sun2"));
		List subList = list.subList(1, 3); //指定开始与结束的索引值截取集合中的元素。
		System.out.println("子集合的元素是："+ subList);
		list.set(1, "sun2-0"); //使用指定的元素替换指定索引值位置的元素。
		System.out.println("集合的元素："+list);
		ListIterator it = list.listIterator(); //返回的是一个List接口中特有的迭代器
		while(it.hasNext()){
			it.next();//遍历到末尾
		}
		while(it.hasPrevious()){
			System.out.println("元素："+ it.previous());//从末尾向前输出
		}
		it.add("sun0");
		it.next();
		it.set("sun1-0");
		System.out.println("集合的元素："+ list);
	}
}
~~~
##### 3.2.4.1 ArrayList
ArrayList底层维护了一个Object数组实现，使用无参构造函数时，Object数组默认的容量是10，当长度不够时，自动增长0.5倍。
>特有的方法：
ensureCapacity(int minCapaci上ty)
trimToSize() 

##### 3.2.4.2 Linkedlist
>特有的方法：
1. 方法
addFirst(E e) 
addLast(E e) 
getFirst() 
getLast() 
removeFirst() 
removeLast() 
2. 数据结构
 2.1. 栈 （1.6）：用于实现堆栈数据结构的存储方式。
先进后出
push() 
pop()
 2.2. 队列（双端队列1.5）：使可以模拟队列数据结构的存储方式。
先进先出
offer()
poll()
3. 返回逆序的迭代器对象      
descendingIterator()  返回逆序的迭代器对象

~~~
//使用LinkedList模拟栈的存储方式
class StackList{
	LinkedList list;
	public StackList(){
		list = new LinkedList();
	}
	//进栈
	public void add(Object o){
		list.push(o);
	}
	//弹栈 : 把元素删除并返回。
	public Object pop(){
		return list.pop();
	} 
	//获取元素个数
	public int size(){
		return list.size();
	}
}
//使用LinkedList模拟队列的存储方式
class TeamList{
	LinkedList list;
	public TeamList(){
		list = new LinkedList();
	}
	public void add(Object o){
		list.offer(o);
	}
	public Object remove(){
		return list.poll();
	}
	//获取元素个数
	public int size(){
		return list.size();
	}
}
public class test {
	public static void main(String[] args) {
		LinkedList list= new LinkedList();
		list.add("sun1");
		list.add("sun2");
		list.add("sun3");
		list.addFirst("sun0"); //把元素添加到集合的首位置上。
		list.addLast("sun4");  //把元素添加到集合的末尾处。
		System.out.println("获取集合中首位置的元素:"+list.getFirst());
		System.out.println("获取集合中末尾的元素："+ list.getLast());
		System.out.println("删除集合中的首位置元素并返回："+ list.removeFirst());
		System.out.println("删除集合中的末尾素并返回："+ list.removeLast());
		list.push("sun0");   //将该元素插入此集合的开头处。 
		System.out.println("删除集合的首元素："+list.pop()); // 移除并返回集合中的第一个元素 
		list.offer("sun4");   //将该元素插入此集合的末尾处。 
		System.out.println("删除集合的首元素: "+list.poll());
		System.out.println("集合中的元素："+ list);
		Iterator  it = list.descendingIterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		//模拟队列
		TeamList teamList=  new TeamList();
		teamList.add("TeamListItem1");
		teamList.add("TeamListItem2");
		teamList.add("TeamListItem3");
		int size = teamList.size();
		for(int i = 0 ; i<size ; i++){
			System.out.println(teamList.remove());
		}
	}
}
~~~
##### 3.2.4.3 Vector
ArrayLsit与Vector底层都是使用Object数组实现，但ArrayList是线程不同步的，操作效率高，而Vector是线程同步的，操作效率低。
~~~
public class test {
	public static void main(String[] args) {
		Vector v  =  new Vector();
		//添加元素
		v.addElement("sun1");
		v.addElement("sun2");
		v.addElement("sun3");
		//迭代该集合
		Enumeration e = v.elements(); //获取迭代器
		while(e.hasMoreElements()){
			System.out.println(e.nextElement());
		}
	}
}
~~~
#### 3.2.5 set
##### 3.2.5.1 HashSet
hashSet的实现原理：
添加元素的时候，HashSet会先调用元素的hashCode方法得到元素的哈希值 ，然后通过元素的哈希值经过移位等运算，算出该元素在哈希表中的存储位置。
情况1： 如果算出元素存储的位置目前没有任何元素存储，那么该元素可以直接存储到该位置上。
情况2： 如果算出该元素的存储位置目前已经存在有其他的元素了，那么会调用该元素的equals方法与该位置的元素再比较一次，如果equals返回的是true，那么该元素与这个位置上的元素就视为重复元素，不允许添加，如果equals方法返回的是false，那么该元素运行添加。
~~~
class Person{
	int id;
	String name;
	public Person(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	@Override
	public String toString() {
		return "{ 编号:"+ this.id+" 姓名："+ this.name+"}";
	}
	@Override
	public int hashCode() {
		System.out.println("===hashCode===");
		return this.id;
	}
	@Override
	public boolean equals(Object obj) {
		System.out.println("===equals===");
		Person p = (Person)obj;
		return this.id==p.id;
	}
}
public class test {
	public static void main(String[] args) {
		String str1 = "hello";
		String str2 = new String("hello");
		System.out.println("两个是同一个对象吗？"+(str1==str2));//false
		System.out.println("str1的hashCode："+ str1.hashCode());//两个hashcode相同
		System.out.println("str2的hashCode:"+ str2.hashCode());
		HashSet set1 = new HashSet();
		set1.add("sun1");
		set1.add("sun2");
		set1.add("sun3");
		System.out.println("集合的元素："+ set1);
		HashSet set2 = new HashSet();
		set2.add(new Person(110,"sun1"));
		set2.add(new Person(220,"sun2"));
		set2.add(new Person(330,"sun3"));
		//在现实生活中只要编号一致就为同一个人.
		System.out.println("添加成功吗？"+set2.add(new Person(110,"sun4")));
		System.out.println("集合的元素："+set2);
		//用户注册
		Scanner scanner = new Scanner(System.in);
		System.out.println("ID:");
		String id = scanner.next();
		System.out.println("姓名:");
		String name = scanner.next();
		Person person = new Person(Integer.parseInt(id), name);
		if( set2.add(person) ){
			System.out.println("success");
		}else{
			System.out.println("error");
		}
		System.out.println(set2);
	}
}
~~~
##### 3.2.5.2 TreeSet
自然顺序存储
注意事项：
1. 往TreeSet添加元素的时候，如果元素本身具备了自然顺序的特性(String、int等)，那么就按照元素自然顺序的特性进行排序存储。
2. 往TreeSet添加元素的时候，如果元素本身不具备自然顺序的特性，那么该元素所属的类必须要实现Comparable接口（推荐），把元素的比较规则定义在compareTo(T o)方法上。 
3. 如果比较元素的时候，compareTo方法返回 的是0，那么该元素就被视为重复元素，不允许添加。(注意：TreeSet与HashCode、equals方法没有任何关系)
4. 往TreeSet添加元素的时候, 如果元素本身没有具备自然顺序的特性，而元素所属的类也没有实现Comparable接口，那么必须要在创建TreeSet的时候传入一个比较器。
5.  往TreeSet添加元素的时候，如果元素本身不具备自然顺序的特性，而元素所属的类已经实现了Comparable接口， 在创建TreeSet对象的时候也传入了比较器，那么是以比较器的比较规则优先使用。

~~~
class  Emp implements Comparable<Emp>{
	int id;
	String name;
	int salary;
	public Emp(int id, String name, int salary) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "{ 编号："+  this.id+" 姓名："+ this.name+" 薪水："+ this.salary+"}";
	}
	//@Override //元素与元素之间的比较规则。
	// 负整数、零或正整数，根据此对象是小于、等于还是大于指定对象。 
	public int compareTo(Emp o) {
		return this.salary- o.salary;
	}	
}
//自定义一个比较器
class MyComparator implements Comparator<Emp>{
	@Override
	public int compare(Emp o1, Emp o2) {
		return o1.id-o2.id;
	}
}
public class test {
	public static void main(String[] args) {
		//创建一个比较器对象，根据id比较
		MyComparator comparator = new MyComparator();
		//创建TreeSet的时候传入比较器
		TreeSet tree = new TreeSet(comparator);
		tree.add(new Emp(110, "sun1", 100));
		tree.add(new Emp(113, "sun2", 200));
		tree.add(new Emp(220, "sun3", 300));
		tree.add(new Emp(120, "sun4", 500));
		System.out.println("集合的元素："+tree);	
	}
}
~~~
### 3.3 双列集合
>双列集合关系
----| Map  特点：存储的数据都是以键值对的形式存在的，键不可重复，值可以重复。
--------| HashMap  底层基于哈希表实现
--------| TreeMap  基于红黑树（二叉树）数据结构实现
--------| Hashtable

#### 3.3.1 Map
>Map接口方法
--添加
----put(K key, V value) 
----putAll(Map<? extends K,? extends V> m) 
--删除
----remove(Object key) 
----clear() 
--获取
----get(Object key) 
----size() 
--判断
----containsKey(Object key) 
----containsValue(Object value) 
----isEmpty() 
--迭代
----keySet() 
----values() 
----entrySet()

##### 3.3.1.1 HashMap
~~~
public class test {
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		//添加方法
		map.put("sun1-1", "sun1-2");
		map.put("sun2-1", "sun2-2");
		map.put("sun3-1", "sun3-2");
		//添加
		System.out.println("返回值："+map.put("sun3-1","sun3-3"));  //sun3-2  如果之前没有存在该键，那么返回的是null，如果之前就已经存在该键了，那么就返回该键之前对应的值。
		Map<String,String> map2 = new HashMap<String, String>();
		map2.put("sunshine1-1", "sunshine1-2");
		map2.put("sunshine2-1", "sunshine2-2");
		map.putAll(map2); // 把map2的元素添加到map集合中。
		//删除
		System.out.println("删除的数据是:"+map.remove("sun2-1")) ;  // 根据键删除一条map中的数据，返回的是该键对应a的值。
//		map.clear(); //清空集合中的所有数据。
		//获取
		System.out.println("根据指定的键获取对应的值:"+ map.get("sun3-1"));//sun3-3
		System.out.println("获取map集合键值对个数："+ map.size());
		//判断
		System.out.println("判断map集合是否包含指定的键："+ map.containsKey("sun1-1"));
		System.out.println("判断map集合中是否包含指定的值："+ map.containsValue("sun1-2"));
		System.out.println("集合的元素："+ map);// {sun1-1=sun1-2, sunshine2-1=sunshine2-2, sun3-1=sun3-3, sunshine1-1=sunshine1-2}
		map.clear();
		System.out.println("判断map集合是否为空元素："+ map.isEmpty());
	}
}
~~~
~~~
HashMap<Person, String> map = new HashMap<Person, String>();
map.put(new Person(110,"sun1"), "001");
map.put(new Person(220,"sun2"), "002");
map.put(new Person(110,"sun3"), "003");  //如果出现了相同键，那么后添加的数据的值会取代之前的值。
~~~
~~~
public class test {
	public static void main(String[] args) {
		Map<String,String> map = new HashMap<String, String>();
		//添加方法
		map.put("sun1-1", "sun1-2");
		map.put("sun2-1", "sun2-2");
		map.put("sun3-1", "sun3-2");
		//map集合中遍历方式一： 使用keySet方法进行遍历   缺点： keySet方法只是返回了所有的键，没有值
		Set<String> keys = map.keySet();  //keySet() 把Map集合中的所有键都保存到一个Set类型的集合对象中返回。
		Iterator<String> it1 = keys.iterator();
		while(it1.hasNext()){
			String key = it1.next();
			System.out.println("键："+ key+" 值："+ map.get(key));
		}
		//map集合的遍历方式二： 使用values方法进行遍历   缺点： values方法只能返回所有的值，没有键
		Collection<String>  c = map.values(); //values() 把所有的值存储到一个Collection集合中返回。
		Iterator<String> it2 = c.iterator();
		while(it2.hasNext()){
			System.out.println("值："+ it2.next());
		}
		//map集合的遍历方式三： entrySet方法进行遍历
		Set<Map.Entry<String,String>>  entrys = map.entrySet(); 
		Iterator<Map.Entry<String,String>> it = entrys.iterator();
		while(it.hasNext()){
			Map.Entry<String,String> entry = it.next();
			System.out.println("键："+ entry.getKey()+" 值："+ entry.getValue());
		}
	}
}
~~~
说明：
~~~
class Map{
	//静态内部类 
	static class Entry<K ,V>{
		K  key;
		V value;
	}
}
~~~
##### 3.3.1.2 TreeMap
~~~
class Emp {//implements Comparable<Emp>{
	String name;
	int salary;
	public Emp(String name, int salary) {
		super();
		this.name = name;
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "[姓名："+this.name+" 薪水："+ this.salary+"]";
	}
	//	@Override
	public int compareTo(Emp o) {
		return this.salary - o.salary;
	}
}
//自定义一个比较器
class MyComparator implements Comparator<Emp>{
	@Override
	public int compare(Emp o1, Emp o2) {
		return o1.salary - o2.salary;
	}	
}
public class test {
	public static void main(String[] args) {
	    TreeMap<Character, Integer> tree = new TreeMap<Character, Integer>();
		tree.put('c',10);
		tree.put('b',2);
		tree.put('a',5);
		tree.put('d',12);
		System.out.println(tree);		
		//创建一个自定义比较器
		MyComparator comparator = new MyComparator();
		TreeMap<Emp, String> tree2 = new TreeMap<Emp, String>(comparator);
		tree2.put(new Emp("sun1", 2000),"001");
		tree2.put(new Emp("sun2", 1000),"002");
		tree2.put(new Emp("sun3", 3000),"003");
		tree2.put(new Emp("sun4", 5000),"005");
		tree2.put(new Emp("sun5", 5000),"008");
		System.out.println(tree2);
	}
}
~~~
## 4 字符串
字符串是常量，创建后值不能更改，内容一旦发生变化，会创建一个新对象。字符串的内容不适宜频繁修改，因为一旦修改会创建一个新的对象。需要频繁修改字符串的内容，使用字符串缓冲类（StringBuffer），StringBuffer是一个存储字符的容器。
~~~
public class test {
	public static void main(String[] args) {
		String str1 = "hello";
		String str2 = str1;
		System.out.println("str1与str2是同一个对象吗？"+(str1==str2));//true
		//字符串值修改，重新创建一个对象
		str2 = "new string";
		System.out.println("字符串修改后内容为："+str2);
		System.out.println("str1与str3是同一个对象吗？"+(str1==str2));//false
	}
}
~~~
### 4.1 String字符串类
new String("abc")创建了两个对象，一个对象位于字符串常量池中，一个对象位于堆内存中。
String类重写了Object的equals方法，比较的是两个字符串对象的内容是否一致。
"=="用于比较引用数据类型数据的时候比较的是两个对象 的内存地址，equals方法默认情况下比较也是两个对象的内存地址。
~~~
public class test {
	public static void main(String[] args) {
		String str1 = "hello";
		String str2 = "hello";
		String str3 = new String("hello");
		String str4 = new String("hello");
		System.out.println("str1==str2?"+(str1==str2));  // true  
		System.out.println("str2==str3?"+(str2==str3));  //false
		System.out.println("str3==str4?"+(str3==str4));  // false
		System.out.println("str3.equals(str2)?"+(str3.equals(str4))); //true
	}
}
~~~
### 4.2 String的构造方法
>构造方法
----String()  创建一个空内容的字符串对象。
----String(byte[] bytes)  使用一个字节数组构建一个字符串对象
----String(byte[] bytes, int offset, int length) 
--------bytes： 要解码的数组
--------offset： 指定从数组中那个索引值开始解码
--------length： 要解码多个元素
----String(char[] value)  使用一个字符数组构建一个字符串。	
----String(char[] value, int offset, int count)  使用一个字符数组构建一个字符串，指定开始的索引值，与使用字符个数
----String(int[] codePoints,int offset,int count)
----String(String original) 

注意：使用字节数组或者字符数组都可以构建一个字符串对象。
~~~
public class test {
	public static void main(String[] args) {
		String str = new String();
		byte[] buf = {97,98,99};
		str = new String(buf);//使用一个字节数组构建一个字符串对象
		System.out.println("字符串的内容："+str);//abc
		str = new String(buf,1,2);//使用字节数组,指定开始解码的索引值和解码的个数
		System.out.println("字符串的内容："+str);//bc
		char[] arr = {'孙','剑','峰'};
		str = new String(arr); //使用字符数组构建一个字符串
		System.out.println("字符串的内容："+str);
		str = new String(arr,1,2);
		System.out.println("字符串的内容："+str);
		int[] 	buf2 = {65,66,67};
		str = new String(buf2,0,3);
		System.out.println("字符串的内容："+str);
		str = new String("abc");
		System.out.println("字符串的内容："+str);	
	}
}
~~~
### 4.3 String方法
>String方法
--获取
----int length()  获取字符串的长度
----char charAt(int index) 获取特定位置的字符 (角标越界)
----int indexOf(String str) 查找子串第一次出现的索引值,如果子串没有出现 在字符串中，那么则返回-1表示。
----int lastIndexOf(String str) 查找子串最后一次出现的索引值 , 如果子串没有出现 在字符串中，那么则返回-1表示
--判断
----boolean endsWith(String str) 是否以指定字符结束
----boolean isEmpty() 是否长度为0 如： "" null
----boolean contains(CharSequences) 是否包含指定序列
----boolean equals(Object anObject) 是否相等
----boolean equalsIgnoreCase(String anotherString) 忽略大小写是否相等
--转换   
----char[] toCharArray() 将字符串转换为字符数组
----byte[] getBytes();字节数组、字符数组、字符串三者间可以相互转换
--其他
----String replace(String oldChar, String newChar) 替换
----String[] split(String regex) 切割
----String substring(int beginIndex)   指定开始 的索引值截取子串
----String substring(int beginIndex, int endIndex)指定开始 与结束的索引值截取子串
----String toUpperCase() 转大写
----String toLowerCase() 转小写
----String trim() 去除字符串首尾的空格

~~~
import java.util.*;
public class test {
	public static void main(String[] args) {
		//获取方法
		String str1 = "abc中国ab中国";
		System.out.println("字符串的字符个数：" + str1.length());//9
		System.out.println("根据索引值获取对应的字符："+ str1.charAt(3));//中
		System.out.println("查找子串第一次出现的索引值：" + str1.indexOf("中国"));//3
		System.out.println("查找子串最后一次出现的索引值：" + str1.lastIndexOf("中国"));//7
		//判断方法
		String str2 = "Sunshine.java";
		System.out.println("是否以指定的字符串结束:"+ str2.endsWith("ja"));//false
		//str2 = "";
		System.out.println("判断字符串是否为空内容："+str2.isEmpty());//true
		System.out.println("判断字符串是否包含指定的内容："+ str2.contains("Sunshine"));
		System.out.println("判断两个 字符串的内容是否一致："+ "SUNSHINE.JAVA".equals(str2));
		System.out.println("判断两个字符串的内容是否一致(忽略大小写比较):"+ "SUNSHINE.JAVA".equalsIgnoreCase(str2));
		//转换的方法
		char[] buf = str2.toCharArray();  //把字符串转换字符数组
		System.out.println("字符数组："+ Arrays.toString(buf));
		byte[] buf2 = str2.getBytes();	//把字符串转字节数组
		System.out.println("字节数组："+ Arrays.toString(buf2));
		String str3 = "sunshine";
		System.out.println("指定新内容替换旧的内容:"+ str3.replace("shine", "jianfeng"));
		str3 = "Sun-shine-studio";
		String[] arr = str3.split("-"); //根据指定的字符进行切割
		System.out.println("字符串数组的内容："+ Arrays.toString(arr));
		System.out.println("指定开始的索引值截取子串:"+ str3.substring(10));
		System.out.println("指定开始的索引值截取子串:"+ str3.substring(0,3));
		System.out.println("转大写：" + str3.toUpperCase());
		System.out.println("转小写："+ str3.toLowerCase());
		str3 = "    sunshine studio   ";
		System.out.println("去除字符串首尾的空格："+ str3.trim());
		//提取文件名
		String str4 =  "D:\\2017\\workspace\\test.java";
		getFileName(str4);
		//反序
		str4 = "习主席";
		System.out.println("翻转后的字符串："+ reverse(str4));
		//统计出现次数
		str4 = "welcomesunjianfengtosunshinestudio";
		getCount(str4, "sun");
	}
	//统计子串出现次数
	public static void getCount(String str,String target){
		int count = 0 ; //用于记录出现的次数
		int fromIndex  = 0; // 记录从那个索引值开始寻找目标子串
		while((fromIndex = str.indexOf(target, fromIndex))!=-1){
			//如果indexof方法返回不是-1，则已经找到目标元素
			count++;
			fromIndex = fromIndex+target.length();
		}
		System.out.println("出现的次数："+ count);
	}
	//反序
	public static String reverse(String str){
		char[] arr = str.toCharArray();
		for(int startIndex = 0 , endIndex=arr.length-1 ; startIndex<endIndex; startIndex++,endIndex--){
				char temp = arr[startIndex];
				arr[startIndex] = arr[endIndex];
				arr[endIndex] = temp;
		}
		//使用字符数组构建一个字符串。
		return new String(arr);
	}
	//提取文件名
	public static void getFileName(String path){
		int index = path.lastIndexOf("\\");
		String fileName = path.substring(index+1);
		System.out.println("文件名："+ fileName);
	}
}
~~~
### 4.4 StringBuffer
StringBuffer依赖一个字符数组存储字符数据，默认初始容量是16，如果字符数组长度不够用，自动增长1倍。
>StringBuffer方法
--增加
----append(boolean b)    可以添加任意类型的数据到容器中
----insert(int offset, boolean b)  指定插入的索引值，插入对应的内容。 
--删除
----delete(int start, int end)  根据指定的开始与结束的索引值删除对应的内容。
----deleteCharAt(int index)   根据指定 的索引值删除一个字符。
--修改
----replace(int start, int end, String str) 根据指定 的开始与结束索引值替代成指定的内容。
----reverse()   翻转字符串缓冲类的内容。  abc--->cba
----setCharAt(int index, char ch)  把指定索引值的字符替换指定的字符。 
----substring(int start, int end)  根据指定的索引值截取子串。
----ensureCapacity(int minimumCapacity)  指定StringBuffer内部的字符数组长度的。
--查看
----indexOf(String str, int fromIndex) 查找指定的字符串第一次出现的索引值,并且指定开始查找的位置。
----lastIndexOf(String str) 
----capacity() 查看当前字符数组的长度。 
----length() 
----charAt(int index) 
----toString()   把字符串缓冲类的内容转成字符串返回。

### 4.5 StringBuffer 与 StringBuilder 的比较
>相同点：
 1. 两个类都是字符串缓冲类。
 2. 两个类的方法都是一致的。

>不同点：
1. StringBuffer是线程安全的,操作效率低 ，StringBuilder是线程非安全的,操作效率高。
2. StringBuffer是jdk1.0出现 的，StringBuilder 是jdk1.5的时候出现的。

推荐使用：StringBuilder，因为操作效率高。
~~~
public class test {
	public static void main(String[] args) {
		//使用StringBuffer无参构造函数创建一个字符串缓冲类。
		StringBuffer sb = new StringBuffer(); 
		//添加 
		sb.append("sunshine");
		sb.append(true);
		sb.append(3.14f);
		//插入
		sb.insert(8, "studio");
		//删除
		sb.delete(3, 7); 
		sb.deleteCharAt(3);
		//修改	
		sb.replace(0, 3, "my");
		sb.reverse(); //翻转字符串的内容
		sb.setCharAt(3, 's');
		String subString = sb.substring(2, 4);
		System.out.println("子串的内容："+ subString);
		//查看
		int index = sb.indexOf("abc", 3);
		System.out.println("索引值为："+index);
		System.out.println("查看字符数组的长度："+ sb.capacity());
		System.out.println("存储的字符个数："+sb.length());
		System.out.println("索引指定的索引值查找字符："+sb.charAt(2) );
		System.out.println("字符串缓冲类的内容："+ sb);
		String content = sb.toString();
		System.out.println(content);
	}
}
~~~

## 5 枚举
枚举类的定义格式：
~~~
enum 类名{
	//枚举值
}
~~~
>注意细节：
1. 枚举类也是一个特殊的类。
2. 枚举值默认的修饰符是public static final。
3. 枚举值就是是枚举值所属的类的类型， 然后枚举值是指向了本类的对象的。
4. 枚举类的构造方法默认的修饰符是private的。
5. 枚举类可以定义自己的成员变量与成员函数。
6. 枚举类可以自定义构造函数，但是构造函数的修饰符必须是private。
7. 枚举类可以存在抽象方法，但是枚举值必须要实现抽象方法。
8. 枚举值必须要位于枚举类的第一个语句。

~~~
//自定义一个枚举类
enum Sex{
	man("男"){
		@Override
		public void run() {
			System.out.println("男人在跑...");
		}
	},woman("女"){
		@Override
		public void run() {
			System.out.println("女人在跑...");
		}
	}; //枚举值
	String value; //成员变量
	//	public static final Sex man = new Sex();
	//构造函数
	private Sex(String  value){
		this.value = value;
	}
	//成员函数
	public void getValue(){
		System.out.println("value :"+ value);
	}
	public abstract void run();
}
public class test {
	public static void main(String[] args) {
		Sex sex = Sex.man; //获取到了枚举类的对象
//		sex.value = "男";
		sex.getValue();
		sex.run();
	}
}
~~~
switch适用的数据类型：byte \ char \ short \ int \ String \ 枚举类型
case语句后面跟的枚举值，只需要单写枚举值即可，不需要再声明该枚举值属于哪个枚举类
~~~
//季节枚举类
enum Season{
	spring,summer,autumn,winter;
}
public class test {
	public static void main(String[] args) {
		Season season = Season.summer;
		switch(season){
			case spring:
				System.out.println("春天...");
				break;
			case summer:
				System.out.println("夏天...");
				break;
			case autumn:
				System.out.println("秋天...");
				break;
			case winter:
				System.out.println("冬天...");
				break;		
		}
	}
}
~~~
## 6 增强for循环
作用：简化迭代器的书写格式(增强for循环底层还是使用迭代器遍历)
适用范围：实现了Iterable接口的对象或数组对象都可以使用增强for循环
格式：
~~~
for(数据类型  变量名  :遍历的目标){

}
~~~
>注意事项：
1. 增强for循环底层也是使用迭代器获取，只不过获取迭代器由jvm完成，所以在使用增强for循环遍历元素的过程中不准使用集合（对象对集合的元素个数进行修改）
2. 迭代器遍历元素与增强for循环遍历元素的区别：使用迭代器遍历集合的元素时可以删除集合的元素，而增强for循环变量集合的元素时，不能调用迭代器的remove方法删除元素。
3. 普通for循环与增强for循环的区别：普通for循环可以没有变量的目标，而增强for循环一定要有变量的目标。

~~~
public class test {
	public static void main(String[] args) {
		HashSet<String> set = new HashSet<String>();
		//添加元素
		set.add("sun1");
		set.add("sun2");
		set.add("sun3");
		//使用迭代器遍历Set的集合.
		Iterator<String> it  = set.iterator();
		while(it.hasNext()){
			String temp = it.next();
			System.out.println("元素："+ temp);
			it.remove();
		}
		//使用增强for循环解决
		for(String item : set){
			System.out.println("元素："+ item);
		}
		int[] arr = {12,5,6,1};
	 	//普通for循环的遍历方式
	 	for(int i =  0 ; i<arr.length ; i++){
			System.out.println("元素："+ arr[i]);
		}
		//使用增强for循环实现
		for(int item :arr){
			System.out.println("元素："+ item);
		}
		//注意： Map集合没有实现Iterable接口，所以map集合不能直接使用增强for循环，如果需要使用增强for循环需要借助于Collection的集合。
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("001","张三");
		map.put("002","李四");
		map.put("003","王五");
		map.put("004","赵六");
		Set<Map.Entry<String, String>> entrys = map.entrySet();
		for(Map.Entry<String, String> entry  :entrys){
			System.out.println("键："+ entry.getKey()+" 值："+ entry.getValue());
		}
	}
}
~~~
## 7 泛型
### 7.1 应用1：确定集合元素类型
>泛型的好处：
1. 将运行时的异常提前至了编译时。
2. 避免了无谓的强制类型转换 。

泛型在集合中的常见应用：
~~~
ArrayList<String> list = new ArrayList<String>();  //true  推荐使用。
ArrayList<Object> list = new ArrayList<String>();  //false
ArrayList<String> list = new ArrayList<Object>();  //false
~~~
以下两种写法主要是为了兼顾新老系统的兼用性问题。
~~~
ArrayList<String> list = new ArrayList();  true   
ArrayList list = new ArrayList<String>();  true
~~~
注意：泛型没有多态的概念，左右两边的数据类型必须要一致，或者只是写一边的泛型类型，推荐两边都写泛型。
~~~
public class test {
	public static void main(String[] args) {
		ArrayList<String>  list = new ArrayList<String>();  //<String> 表示该容器只能存储字符串类型的数据。
		list.add("aa");
		list.add("bb");
		list.add("cc");
		for(int i = 0 ; i < list.size() ; i++){
			String str =  list.get(i);
			System.out.println("大写："+ str.toUpperCase());
		}
	}
}
~~~
### 7.2 应用2：接收自定义泛型类型的参数
定义一个方法可以接收任意类型的参数，而且返回值类型必须要与实参的类型一致。
自定义泛型：自定义泛型就是一个数据类型的占位符或者是一个数据类型的变量。
方法上自定义泛型：
~~~
修饰符  <声明自定义的泛型>返回值类型  函数名(使用自定义泛型 ...){

}
~~~
在泛型中不能使用基本数据类型，如果需要使用基本数据类型，那么就使用基本数据类型对应的包装类型。
>基本数据类型 --> 包装类型
byte  -->  Byte
short  -->  Short 
int  -->  Integer
long  -->  Long 
double  -->  Double 
float  -->  Float
boolean  -->  Boolean
char  -->  Character 

>方法泛型注意事项：
1. 在方法上自定义泛型，这个自定义泛型的具体数据类型是在调用该方法的时候传入实参时确定具体的数据类型的。
2. 自定义泛型只要符合标识符的命名规则即可，但是自定义泛型我们一般都习惯使用一个大写字母表示。（T  Type、E  Element）

~~~
public class test {
	public static void main(String[] args) {
		String str = getData("abc");
		Integer i = getData(123);
	}
	public static <T>T getData(T para){
		return para;
	}
}
~~~
### 7.3 应用3：泛型类
泛型类的定义格式：
~~~
class 类名<声明自定义泛型>{

}
~~~ 
>泛型类注意事项：
1. 在类上自定义泛型的具体数据类型是在使用该类的时候创建对象时候确定的。
2. 如果一个类在类上已经声明了自定义泛型，如果使用该类创建对象的时候没有指定泛型的具体数据类型，那么默认为Object类型
3. 在类上自定义泛型不能作用于静态的方法，如果静态的方法需要使用自定义泛型，那么需要在方法上自己声明使用。

~~~
class MyArrays<T>{
	//元素翻转
	public void reverse(T[] arr){
		for(int startIndex = 0, endIndex = arr.length-1 ; startIndex<endIndex ; startIndex++,endIndex--){
			T temp  = arr[startIndex];
			arr[startIndex] = arr[endIndex];
			arr[endIndex] = temp;
		}
	}
	public String toString(T[] arr){
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < arr.length ; i++){
			if(i==0){
				sb.append("["+arr[i]+",");
			}else if(i==arr.length-1){
				sb.append(arr[i]+"]");
			}else{
				sb.append(arr[i]+",");
			}
		}
		return sb.toString();
	}
	public static <T>void print(T[] t){}
}
public class test {
	public static void main(String[] args) {
		Integer[] arr = {10,12,14,19};	
		MyArrays<Integer> tool = new MyArrays<Integer>();
		tool.reverse(arr);
		System.out.println("数组的元素："+tool.toString(arr));
		MyArrays<String> tool2 = new MyArrays<String>();
		String[] arr2 = {"aaa","bbb","ccc"};
		tool2.reverse(arr2);
	}
}
~~~
### 7.4 应用4： 泛型接口
泛型接口的定义格式: 
~~~
interface 接口名<声明自定义泛型>{

}
~~~
>泛型接口注意事项：
1. 接口上自定义的泛型的具体数据类型是在实现一个接口的时候指定的。
2. 在接口上自定义的泛型如果在实现接口的时候没有指定具体的数据类型，那么默认为Object类型。

~~~
interface Dao<T>{
	public void add(T t);
}
public class test<T> implements Dao<T> {
	public static void main(String[] args) {
		test<String> d = new test<String>();
	}
	public void add(T t){
		
	}
}
~~~
### 7.5 泛型的上下限：
>需求1： 定义一个函数可以接收接收任意类型的集合对象， 要求接收的集合对象只能存储Integer或者是Integer的父类类型数据。
需求2： 定义一个函数可以接收接收任意类型的集合对象， 要求接收的集合对象只能存储Number或者是Number的子类类型数据。

泛型中通配符：? 
~~~
? super Integer：只能存储Integer或者是Integer父类元素（泛型下限）
? extends Number：只能存储Number或者是Number类型的子类数据（泛型上限）
~~~
~~~
public class test {
	public static void main(String[] args) {
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		ArrayList<Number> list2 = new ArrayList<Number>();
		HashSet<String> set = new HashSet<String>();
		//getData(set);
	}
	//泛型的上限
	public static void getData(Collection<? extends Number> c){
		
	}
	//泛型的下限
	public static void print(Collection<? super Integer> c){
	
	}
}
~~~
## 8 可变参数
格式：
~~~
  数据类型... 变量名
~~~
>可变参数注意细节： 
1. 如果一个函数的形参使用上了可变参数之后，那么调用该方法的时候可以传递参数也可以不传递参数。
2. 可变参数实际上是一个数组对象。
3. 可变参数必须位于形参中的最后一个参数。
4. 一个函数最多只能有一个可变参数，因为可变参数要位于形参中最后一个位置上。

~~~
public class test {
	public static void main(String[] args) {
		int[] arr = {1,2,3,4};
		add(arr);//10
		add();//0
	}
	public static void add(int... arr){ //长度是0
		int result = 0;
		for(int item : arr){
			result+=item;
		}
		System.out.println("总和："+ result);
	}
}
~~~
## 9 时间类
Date、Calendar、SimpleDateFormat
~~~
public class test {
	public static void main(String[] args) throws ParseException {
		// Date获取当前的系统时间，过时
		Date date = new Date();
		System.out.println("年份："+ (date.getYear()+1900));
		// Calendar获取当前的系统时间
		Calendar calendar = Calendar.getInstance();
		System.out.println("年："+ calendar.get(Calendar.YEAR));
		System.out.println("月："+ (calendar.get(Calendar.MONTH)+1));
		System.out.println("日："+ calendar.get(Calendar.DATE));
		System.out.println("时："+ calendar.get(Calendar.HOUR_OF_DAY));
		System.out.println("分："+ calendar.get(Calendar.MINUTE));
		System.out.println("秒："+ calendar.get(Calendar.SECOND));
		// SimpleDateFormat格式化日期
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss") ; //使用了默认的格式创建了一个日期格式化对象。
		String time = dateFormat.format(date);  //可以把日期转换转指定格式的字符串
		System.out.println("当前的系统时间："+ time);
		String birthday = "1993年11月28日 00:00:00";
		Date date2 = dateFormat.parse(birthday);  //注意： 指定的字符串格式必须要与SimpleDateFormat的模式要一致。
		System.out.println(date2);// Sun Nov 28 00:00:00 CST 1993
	}
}
~~~
## 10 Math类
提供很多数学公式
>Math类
----abs(double a)  获取绝对值
----ceil(double a)  向上取整
----floor(double a)  向下取整
----round(float a)  四舍五入
----random()  产生一个随机数，大于等于 0.0 且小于 1.0 的伪随机 double 值

~~~
public class test {
	public static void main(String[] args) {
		System.out.println("绝对值:"+Math.abs(-3));
		System.out.println("向上取整："+Math.ceil(3.14)); //4.0
		System.out.println("向下取整："+Math.floor(-3.14)); //-4.0
		System.out.println("四舍五入:"+Math.round(3.54)); //4
		System.out.println("随机数："+Math.random());
	}
}
~~~
## 11 Random类
~~~
public class test {
	public static void main(String[] args) {
		Random random = new Random();
		int randomNum = random.nextInt(10); //产生的随机数在0-9之间
		System.out.println("随机数："+ randomNum);
		char[] arr = {'孙','剑','峰','s','u','n'};
		StringBuilder sb = new StringBuilder();
		//产生四个arr中的值
		for(int i  = 0 ; i< 4 ; i++){
			//产生的随机数必须是数组的索引值范围之内的
			int index = random.nextInt(arr.length);
			sb.append(arr[index]);
		}
		System.out.println("4位验证码："+ sb);
	}
}
~~~
## 12 正则表达式
~~~
public class test {
	public static void main(String[] args) {
		//正则匹配
		String qq = "1724338257";
		System.out.println(qq.matches("\\d{5,11}"));//true
		//根据重叠词进行切割
		String str = "我叫叫叫孙剑剑峰峰峰";
		String[] data = str.split("(.)\\1+");//正则分组
		System.out.println(Arrays.toString(data));//[我, 孙]
		//替换
		String str2 = "我我我我要要要学学学习习";
		str2 = str2.replaceAll("(.)\\1+", "$1");
		System.out.println(str2);//我要学习
		//找出两个字母组成的单词
		String str3 = "Time is so precious.";
		String reg = "\\b[a-zA-Z]{2}\\b";
		//step1：把字符串正则编译成Pattern对象
		Pattern p = Pattern.compile(reg);//正则对象
		//step2：使用正则对象匹配字符串用于产生一个Matcher对象
		Matcher m = p.matcher(str3);//匹配器对象
		while(m.find()){
			System.out.println("获取结果："+m.group());
		}
	}
}
~~~
##  13 System系统类
主要用于获取系统的属性数据
>System类常用方法：
----arraycopy(Object src, int srcPos, Object dest, int destPos, int length) 
--------src - 源数组。
--------srcPos - 源数组中的起始位置。
--------dest - 目标数组。
--------destPos - 目标数据中的起始位置。
--------length - 要复制的数组元素的数量。
----currentTimeMillis()  获取当前系统时间
----exit(int status)  退出jvm，如果参数是0表示正常退出jvm，非0表示异常退出jvm，对一般使用没有区别
----gc()  建议jvm赶快启动垃圾回收期回收垃圾
----getenv(String name)  根据环境变量的名字获取环境变量
----getProperty(key) 
----finalize()  一个对象被垃圾回收器回收的时候，会先调用对象的finalize()方法

~~~
import java.util.*;
class Person{
	String name;
	public Person(String name) {
		this.name = name;
	}
	@Override
	public void finalize() throws Throwable {
		super.finalize();
		System.out.println(this.name+"被回收了");
	}
}
public class test {
	public static void main(String[] args) {
		int[] srcArr = {10,12,14,16,19};
		//把srcArr的数组元素拷贝 到destArr数组中。
		int[] destArr = new int[4];
		System.arraycopy(srcArr, 1, destArr, 0, 4);
//		System.exit(0);
		System.out.println("目标数组的元素："+ Arrays.toString(destArr)); // [12, 14, 16, 19]
		System.out.println("当前的系统时间：" + System.currentTimeMillis()); // 1484482029821
		System.out.println("环境变量："+System.getenv("JAVA_HOME")); // C:\Program Files\Java\jdk1.8.0_102
		for(int i = 0 ; i<4; i++){
			new Person("对象"+i);
			System.gc(); //建议马上启动垃圾回收期
		}
		Properties properties = System.getProperties();  //获取系统的所有属性。
		properties.list(System.out);
		String value = System.getProperty("os.name");//根据系统的属性名获取对应的属性值
		System.out.println("当前系统："+value);
	}
}
~~~
## 14 Runtime
RunTime类主要代表应用程序运行的环境
>常用方法
----getRuntime()  返回当前应用程序的运行环境对象
----exec(String command)  根据指定的路径执行对应的可执行文件
----freeMemory()  返回 Java 虚拟机中的空闲内存量，以字节为单位
----maxMemory()  返回 Java 虚拟机试图使用的最大内存量
----totalMemory()  返回 Java 虚拟机中的内存总量

~~~
public class test {
	public static void main(String[] args) throws IOException, InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		Process process = runtime.exec("C:\\Windows\\notepad.exe");
		Thread.sleep(3000); //让当前程序停止3秒。
		process.destroy();
		System.out.println(" Java虚拟机中的空闲内存量。"+runtime.freeMemory());
		System.out.println("Java 虚拟机试图使用的最大内存量:"+ runtime.maxMemory());
		System.out.println("返回 Java 虚拟机中的内存总量:"+ runtime.totalMemory());
	}
}
~~~
## 15 junit(单元测试框架)
>注意细节：
1. @Test测试的方法不能是static的，且不能带形参
2. 如果测试一个方法的时候需要准备测试的环境或者清理测试的环境，可以使用@Before、 @After 、@BeforeClass、 @AfterClass注解。@Before、@After 是在每个测试方法测试的时候都会调用一次，@BeforeClass、@AfterClass是在所有的测试方法测试之前与测试之后调用一次而已。

>junit使用规范：
1. 一个类如果需要测试，那么该类就应该对应着一个测试类，测试类的命名规范：被测试类的类名+ Test
2. 一个被测试的方法一般对应着一个测试的方法，测试的方法的命名规范是：test+ 被测试的方法的方法名

Demo1-被测试类
~~~
public class Tool {
	public static int getMax(){
		int a = 3;
		int b  =5; 
		int max = a>b?a:b;
		return max;
	}
}
~~~
Demo1-测试类
~~~
public class ToolTest {
	@Test
	public void testGetMax(){
		int max = Tool.getMax();
		if(max!=5){
			throw new RuntimeException();
		}else{
			System.out.println("最大值："+ max);
		}
		//断言
//		Assert.assertSame(5, max); // expected 期望   actual  真实     ==
//		Assert.assertSame(new String("abc"), "abc");
//		Assert.assertEquals(new String("abc"), "abc"); //底层是使用Equals方法比较的
//		Assert.assertNull("aa");
//		Assert.assertTrue(true);
	}
}
~~~
Demo2-被测试类
~~~
public class test1 {
	@Test //注解
	public void getMax(){
		int a = 3;
		int b = 5 ;
		int max = a>b?a:b;
		System.out.println("最大值："+max);
	}
	@Test
	public void sort(){
		int[] arr = {12,4,1,19};
		for(int i = 0 ; i  < arr.length-1 ; i++){
			for(int j = i+1 ; j<arr.length ; j++){
				if(arr[i]>arr[j]){
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
		System.out.println("数组的元素："+Arrays.toString(arr));
	}
}
~~~
Demo2-测试类
~~~
public class test2 {
	//准备测试的环境
	//@Before
	@BeforeClass
	public static void beforeRead(){
		System.out.println("准备测试环境成功...");
	}
	//读取文件数据，把把文件数据都
	@Test
	public void readFile() throws IOException{
		FileInputStream fileInputStream = new FileInputStream("F:\\a.txt");
		int content  = fileInputStream.read();
		System.out.println("内容："+content);
	}
	@Test
	public void sort(){
		System.out.println("读取文件数据排序..");
	}
	//清理测试环境的方法
//	@After 
	@AfterClass
	public static void afterRead(){
		System.out.println("清理测试环境..");
	}
}
~~~
## 16 bat批处理文件
>常用命令：
echo   向控制台输出指定的内容
echo off   隐藏echo off后面执行过的命令
@   隐藏当前行执行的命令
title   改变当前控制台窗口的标题
color   指定控制台的背景颜色与前景颜色
%   注释的内容%
pause   让当前控制台停留
%1~%9   给bat处理文件传入参数