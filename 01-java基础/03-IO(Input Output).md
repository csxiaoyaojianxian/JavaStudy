# 系统学习javaweb-03-IO(Input Output)
## 1 File
IO技术主要的作用是解决设备与设备之间的数据传输问题
File类可以描述一个文件或者一个文件夹
### 1.1 File构造方法
>构造方法：
----File(String pathname)  指定文件或者文件夹的路径创建一个File文件。
----File(File parent, String child)  根据 parent 抽象路径名和 child 路径名字符串创建一个新 File 实例。 
----File(String parent, String child) 

### 1.2 路径问题
目录分隔符：在windows机器上的目录分隔符是 \，在linux机器上的目录分隔符是 /
，在windows上 \ 与 / 都可以使用作为目录分隔符，而且如果写 / 的时候只需要写一个即可
>路径问题：
绝对路径：该文件在硬盘上的完整路径，一般都是以盘符开头的。
相对路径：相对路径就是资源文件相对于当前程序所在的路径。
. 当前路径
.. 上一级路径

~~~
public class test {
	public static void main(String[] args) {
		System.out.println("目录分隔符："+ File.separator);
		File file1 = new File("E:"+File.separator+"a.txt");
		File file2 = new File("E:/a.txt"); 
		File parentFile = new File("E:\\");
		File file3 = new File("E:\\","a.txt");
		System.out.println("存在吗？ "+ file3.exists());  // exists 判断该文件是否存在，存在返回true，否则返回false。
		File file4 = new  File(".");
		System.out.println("当前路径是："+ file4.getAbsolutePath());//D:\Workspaces\java_test\.
		File file5 = new File("..\\..\\a.txt");
		System.out.println("存在吗？"+ file5.exists());
	}
}
~~~
### 1.3 File常用方法
>常用方法
【创建】
----createNewFile()	  在指定位置创建一个空文件，成功就返回true，如果已存在就不创建然后返回false
----mkdir()  在指定位置创建目录，这只会创建最后一级目录，如果上级目录不存在就抛异常
----mkdirs()  在指定位置创建目录，这会创建路径中所有不存在的目录
【修改】
----renameTo(File dest)  重命名文件或文件夹，也可以操作非空的文件夹，文件不同时相当于文件的剪切，剪切时候不能操作非空的文件夹。移动/重命名成功则返回true，失败则返回false
【删除】
----delete()	  删除文件或一个空文件夹，如果是文件夹且不为空，则不能删除，成功返回true，失败返回false
----deleteOnExit()  在虚拟机终止时，请求删除此抽象路径名表示的文件或目录，保证程序异常时创建的临时文件也可以被删除
【判断】
----exists()  文件或文件夹是否存在。
----isFile()  是否是一个文件，如果不存在，则始终为false。
----isDirectory()  是否是一个目录，如果不存在，则始终为false。
----isHidden()  是否是一个隐藏的文件或是否是隐藏的目录。
----isAbsolute()  测试此抽象路径名是否为绝对路径名。

~~~
public class test {
	public static void main(String[] args) throws IOException {
		File file = new File("F:\\a");
		System.out.println("创建成功了吗？"+file.createNewFile());
		File dir = new  File("F:\\dir.txt");
		System.out.println("创建单级文件夹："+dir.mkdir());
		dir = new File("F:\\aa\\bb");
		System.out.println("创建多级文件夹："+ dir.mkdirs());	
		File destFile = new File("F:\\b");
		System.out.println("重命名成功吗？"+file.renameTo(destFile));
		System.out.println("删除成功吗？ "+ file.delete()); //delete方法不能用于删除非空的文件夹
		file.deleteOnExit();  //jvm退出的时候删除文件，一般用于删除临时 文件
		//判断
		File file2 = new File("..\\..\\a.txt");
		System.out.println("存在吗？"+ file2.exists());
		System.out.println("判断是否是一个文件："+file2.isFile()); //如果是文件返回true，否则返回false.
		System.out.println("判断是否是一个文件夹："+ file2.isDirectory()); // 是文件夹返回ture，否则返回false.
		System.out.println("是否隐藏文件："+ file2.isHidden());
		System.out.println("是否绝对路径："+ file2.isAbsolute());
	}
}
~~~
【获取】
----getName()  获取文件或文件夹的名称，不包含上级路径
----getPath()  返回绝对路径，可以是相对路径，但目录要指定
----getAbsolutePath()  获取文件的绝对路径，与文件是否存在没关系
----length()  获取文件的大小（字节数），如果文件不存在则返回0L，如果是文件夹也返回0L
----getParent()  返回此抽象路径名父目录的路径名字符串；如果此路径名没有指定父目录，则返回null
----lastModified()  获取最后一次被修改的时间
【文件夹相关】
----staic File[] listRoots()  列出所有的根目录（Window中就是所有系统的盘符）
----list()  返回目录下的文件或者目录名，包含隐藏文件，对于文件这样操作会返回null。
----listFiles()  返回目录下的文件或者目录对象（File类实例），包含隐藏文件，对于文件这样操作会返回null。
----list(FilenameFilter filter)  返回指定当前目录中符合过滤条件的子文件或子目录，对于文件这样操作会返回null。
----listFiles(FilenameFilter filter)  返回指定当前目录中符合过滤条件的子文件或子目录，对于文件这样操作会返回null。
~~~
public class test {
	public static void main(String[] args) throws IOException {
		File file = new File("..\\..\\a.txt");
		System.out.println("文件名："+ file.getName());// a.txt
		System.out.println("获取绝对路径："+ file.getPath());// ..\..\a.txt
		System.out.println("getAbsolutePath获取绝对路径："+file.getAbsolutePath());// D:\Workspaces\java_test\..\..\a.txt
		System.out.println("获取文件的的大小(字节为单位)："+ file.length());// 8
		System.out.println("获取文件的父路径："+ file.getParent());// ..\..
		//使用毫秒值转换成Date对象
		long lastModified = file.lastModified();
		Date date = new Date(lastModified);	
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
		System.out.println("获取最后一次的修改时间(毫秒值)："+ dateFormat.format(date) );// 2017年01月17日  00:25:47
		//获取
		File[] roots = File.listRoots(); //列出所有的根目录
		for(File f  : roots){
			System.out.println(f);//C:\  D:\  E:\
		}
		File file2 = new File("F:\\BaiduYunDownload");
		String[] fileNames = file2.list(); //把当前文件夹下面的所有子文件名与子文件夹名存储到一个String类型的数组中返回。
		for(String fileName : fileNames){
			System.out.println(fileName);
		}
		File[] files = file2.listFiles(); //把当前文件夹下面的所有子文件与子文件夹都使用了一个FIle对象描述，然后把这些File对象存储到一个FIle数组中返回
		for(File fileItem : files){
			System.out.println("文件名："+ fileItem.getName());
		}
	}
}
~~~
~~~
//自定义一个文件名过滤器
class MyFilter implements FilenameFilter{
	@Override
	public boolean accept(File dir, String name) {
		//System.out.println("文件夹:"+dir+" 文件名："+ name);
		return name.endsWith(".java");
	}
}
public class test {
	public static void main(String[] args) {
		File dir = new File("D:\\Workspaces\\java_test\\src\\java_test");
		listJava2(dir);
	}
	public static void listJava2(File dir){
		File[] files = dir.listFiles(new MyFilter()); //得到文件夹下面的所有子文件与文件夹。
		for(File file : files){
			System.out.println(file.getName());
		}
	}
	//列出所有的java文件
	public static void listJava(File dir){
		File[] files = dir.listFiles(); //获取到了所有的子文件
		for(File file : files){
			String fileName = file.getName();
			/*if(fileName.endsWith(".java")&&file.isFile()){
				System.out.println(fileName);
			}*/
			if(fileName.matches(".+\\.java")&&file.isFile()){
				System.out.println(fileName);
			}
		}
	}
	public static void listFile(File dir){
		File[] files = dir.listFiles();//获取到所有的子文件
		System.out.println("文件：");
		for(File fileItem : files){
			if(fileItem.isFile()){
				System.out.println("\t"+fileItem.getName());
			}
		}
		System.out.println("文件夹：");
		for(File fileItem : files){
			if(fileItem.isDirectory()){
				System.out.println("\t"+fileItem.getName());
			}
		}
	}
}
~~~
## 2 IO流
### 2.1 分类
按照数据的流向划分：
>1. 输入流
2. 输出流

按照处理的单位划分：
>1. 字节流：字节流读取的是文件中二进制数据，读取到二进制数据不会经过任何的处理
2. 字符流：字符流读取的数据是以字符为单位的，字符流也是读取文件中的二进制数据，不过会把这些二进制数据转换成我们能识别的字符。（字符流 = 字节流 + 解码）

### 2.2 字节流
>字节流
----| InputStream  所有输入字节流的基类（抽象类）
--------| FileInputStream  读取文件数据的输入字节流 
--------| BufferedInputStream  缓冲输入字节流（提高读取文件数据的效率），内部维护了一个8kb的字节数组
----| OutputStream  所有输出字节流的父类（抽象类）
--------| FileOutStream  向文件输出数据的输出字节流
--------| BufferedOutputStream  缓冲输出字节流（提高写数据的效率），内部维护了一个8kb的字节数组

#### 2.2.1 FileInputStream
输入字节流：
>使用FileInputStream读取文件数据的步骤：
1. 找到目标文件
2. 建立数据的输入通道
3. 读取文件中的数据
4. 关闭资源

~~~
public class test {
	public static void main(String[] args) throws IOException {
		readTest2();
	}

	// 方式1  使用循环读取文件的数据
	public static void readTest1() throws IOException {
		long startTime = System.currentTimeMillis();
		// 找到目标文件
		File file = new File("F:\\a.txt");
		// 建立数据的输入通道
		FileInputStream fileInputStream = new FileInputStream(file);
		// 读取文件的数据
		int content = 0; // 声明该变量用于存储读取到的数据
		// read() 读取一个字节的数据，把读取的数据返回。
		while ((content = fileInputStream.read()) != -1) {
			System.out.print((char) content);
		}
		// 关闭资源
		fileInputStream.close();
		long endTime = System.currentTimeMillis();
		System.out.println("读取的时间是：" + (endTime - startTime)); // 446
	}
	// 方式2  使用缓冲数组配合循环一起读取
	public static void readTest2() throws IOException {
		long startTime = System.currentTimeMillis();
		// 找到目标文件
		File file = new File("F:\\a.txt");
		// 建立数据的输入通道
		FileInputStream fileInputStream = new FileInputStream(file);
		// 建立缓冲数组配合循环读取文件的数据。
		int length = 0; // 保存每次读取到的字节个数。
		byte[] buf = new byte[1024]; // 存储读取到的数据缓冲数组的长度一般是1024的倍数，理论上缓冲数组越大，效率越高
		// 如果使用read读取数据传入字节数组，那么数据是存储到字节数组中的，而这时候read方法的返回值是表示的是本次读取了几个字节数据到字节数组中。
		while ((length = fileInputStream.read(buf)) != -1) { // read方法如果读取到了文件的末尾，那么会返回-1表示。
			System.out.print(new String(buf, 0, length));
		}
		// 关闭资源
		fileInputStream.close();
		long endTime = System.currentTimeMillis();
		System.out.println("读取的时间是：" + (endTime - startTime)); // 446
	}
}
~~~
#### 2.2.2 FileOutStream
输出字节流：
>使用FileOutStream读取文件数据的步骤：
1. 找到目标文件
2. 建立数据的输出通道
3. 把数据转换成字节数组写出
4. 关闭资源

FileOutputStream
>FileOutputStream要注意的细节：
1. 使用FileOutputStream的时候，如果目标文件不存在，那么会自动创建目标文件对象。 
2. 使用FileOutputStream写数据的时候，如果目标文件已经存在，那么会先清空目标文件中的数据，然后再写入数据，如果需要在原来数据基础上追加数据应该使用new FileOutputStream(file,true)构造函数，第二参数为true。
3. 使用FileOutputStream的write方法写数据的时候，虽然接收的是一个int类型的数据，但是真正写出的只是一个字节的数据，只是把低八位的二进制数据写出，其他二十四位数据全部丢弃。
00000000-000000000-00000001-11111111   511
11111111 --> -1

~~~
public class test {
	public static void main(String[] args) throws IOException {
		writeTest();
	}
	// 使用字节数组把数据写出。
	public static void writeTest() throws IOException {
		// 找到目标文件
		File file = new File("F:\\b.txt");
		// 建立数据输出通道，追加
		FileOutputStream fileOutputStream = new FileOutputStream(file, true);
		// 每次写一个字节的数据出去
		fileOutputStream.write('c');
		fileOutputStream.write('s');//cs
		// 数据写出（只写出低八位的二进制数据）
		fileOutputStream.write(511);
		// 写出数据
		String data = "\r\nhello world";
		// 写法一：使用字节数组把数据写出
		fileOutputStream.write(data.getBytes());//hello world
		// 写法二：使用字节数组把数据写出
		byte[] buf = data.getBytes();
		fileOutputStream.write(buf, 0, 7);
		// 关闭资源
		fileOutputStream.close();//hello
	}
}
~~~
~~~
//图片拷贝
public class test {
	public static void main(String[] args) throws IOException {
		// 找到目标文件
		File inFile = new File("F:\\a\\sunshine.png");
		File destFile = new File("F:\\1.png");
		// 建立数据的输入输出通道
		FileInputStream fileInputStream = new FileInputStream(inFile);
		FileOutputStream fileOutputStream = new FileOutputStream(destFile); // 追加数据...
		// 每新创建一个FileOutputStream的时候，默认情况下FileOutputStream 的指针是指向了文件的开始的位置。
		// 每写出一次，指向都会出现相应移动。
		// 建立缓冲数据，边读边写
		byte[] buf = new byte[1024];
		int length = 0;
		while ((length = fileInputStream.read(buf)) != -1) { // 最后一次只剩下了824个字节
			fileOutputStream.write(buf, 0, length); // 写出很多次数据，所以就必须要追加。
		}
		// 关闭资源 原则： 先开后关，后开先关。
		fileOutputStream.close();
		fileInputStream.close();
	}
}
~~~
#### 2.2.3 BufferedInputStream、BufferedOutputStream
缓冲输入输出字节流
BufferedInputStream、BufferedOutputStream缓冲输入字节流提高读取、写文件数据的效率，内部维护了一个8kb的字节数组
注意：缓冲流本身不具备读写文件的能力，借助FileInputStream、FileOutputStream
>使用步骤	:
1. 找到目标文件。
2. 建立数据通道
3. 建立缓冲字节流
4. 关闭资源

使用BufferedOutputStream写数据的时候，它的write方法是是先把数据写到它内部维护的字节数组中，如果需要把数据真正写到硬盘上面，需要调用flush方法或者是close方法、或者是内部维护的字节数组已经填满数据的时候。
~~~
public class test {
	public static void main(String[] args) throws IOException {
		//拷贝图片
		//找到目标文件
		File  inFile = new File("F:\\a\\sunshine.png");
		File outFile = new File("F:\\1.png");
		//建立数据输入输出通道
		FileInputStream fileInputStream = new FileInputStream(inFile);
		FileOutputStream fileOutputStream = new FileOutputStream(outFile);
		//建立缓冲输入输出流
		BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
		//边读边写
		int content = 0; 
		// int length = bufferedInputStream.read(buf);   如果传入了缓冲数组，内容是存储到缓冲数组中，返回值是存储到缓冲数组中的字节个数。
		while((content = bufferedInputStream.read())!=-1){ // 如果使用read方法没有传入缓冲数组，那么返回值是读取到的内容。
			//bufferedOutputStream.write("hello world".getBytes()); 
			bufferedOutputStream.write(content);
			//把缓冲数组中内部的数据写到硬盘上面
			//bufferedOutputStream.flush();
		}
		//关闭资源
		bufferedInputStream.close();//调用BufferedInputStream的close方法实际上关闭的是FileinputStream
		bufferedOutputStream.close();
	}
	// BufferedInputStream的实现，读取文件使用缓冲数组读取效率更高
	public static void readTest() throws IOException{
		File file = new File("F:\\a.txt");
		//建立数组通道
		FileInputStream fileInputStream = new FileInputStream(file);
		//建立缓冲数组读取数据
		byte[] buf = new byte[1024*8];
		int length = 0; 
		while((length = fileInputStream.read(buf))!=-1){
			System.out.print(new String(buf,0,length));
		}
		//关闭资源
		fileInputStream.close();
	}
}
~~~
### 2.3 字符流
字符流会把读取到的二进制的数据进行对应的编码与解码
字符流 = 字节流 + 编码(解码)
>字符流
----| Reader  输入字符流的基类（抽象类）
--------| FileReader  读取文件的输入字符流
--------| BufferedReader  缓冲输入字符流，提高读取文件的效率，拓展了FileReader的功能（readLine()），内部维护了一个字符数组
----| Writer  输出字符流的基类（抽象类）
--------| FileWriter  向文件输出数据的输出字符流
--------| BufferedWriter 缓冲输出字符流，提高写数据效率，拓展FileWriter的功能（newLine()），内部维护一个8192长度的字符数组作为缓冲区

#### 2.3.1 FileReader、FileWriter
缓冲流不具备读写文件的能力
>FileReader、FileWriter的用法：
1. 找到目标文件
2. 建立数据的输入通道
3. 读写数据
4. 关闭资源

FileWriter
>FileWriter注意事项：
1. 使用FileWriter写数据的时候，FileWriter内部维护了一个1024字符数组，写数据的时候会先写入到它内部维护的字符数组中，如果需要把数据真正写到硬盘上，需要调用flush或者close方法或者填满了内部的字符数组。
2. 使用FileWriter的时候，如果目标文件不存在，那么会自动创建目标文件。
3. 使用FileWriter的时候， 如果目标文件已经存在了，那么默认情况会先清空文件中的数据，然后再写入数据，如果需要在原来的基础上追加数据，需要使用“new FileWriter(File , boolean)”的构造方法，第二参数为true。

使用字节流的应用场景：如果读写的数据都不需要转换成字符的时候，则使用字节流
使用字符流的应用场景：如果是读写字符数据的时候则使用字符流
~~~
public class test {
	public static void main(String[] args) throws IOException {
		readTest2();
		writeTest();
	}
	//使用缓冲字符数组读取文件。
	public static void readTest2() throws IOException{
		//找到目标文件
		File file = new File("F:\\a.txt");
		// 建立数据的输入通道
		FileReader fileReader = new FileReader(file);
		//建立缓冲字符数组读取文件数据
		char[] buf = new char[1024];
		int length = 0 ; 
		while((length = fileReader.read(buf))!=-1){
			System.out.print(new String(buf,0,length));
		}
	}
	public static void readTest1() throws IOException{
		//找到目标文件
		File file = new File("F:\\a.txt");
		//建立数据的输入通道
		FileReader fileReader = new FileReader(file);
		int content = 0 ;
		while((content = fileReader.read())!=-1){ //每次只会读取一个字符，效率低。
			System.out.print((char)content);
		}
		//关闭资源
		fileReader.close();
	}
	public static void  writeTest() throws IOException{
		//找到目标文件
		File file = new File("F:\\a.txt");
		//建立数据输出通道
		FileWriter fileWriter = new FileWriter(file,true);
		//准备数据，把数据写出
		String data = "孙剑峰~";
		fileWriter.write(data);  //字符流具备解码的功能
		//刷新字符流
		fileWriter.flush();
		//关闭资源
		fileWriter.close();
	}
}
~~~
~~~
//使用字节流读取中文
public class test {
	public static void main(String[] args) throws IOException {
		File file = new File("F:\\a.txt");
		FileInputStream fileInputStream = new FileInputStream(file);
		byte[] buf = new byte[1024];
		int length = 0;
		while((length = fileInputStream.read(buf))!=-1){
			System.out.println(new String(buf,0,length)); //借用字符串的解码功能。
		}
	}
}
~~~
~~~
//使用字符流拷贝文件
public class test {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader("F:\\Test.txt"));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("E:\\Test.exe"));
		String line=null;
		while((line = bufferedReader.readLine())!=null){
			bufferedWriter.write(line);
		}
		bufferedWriter.close();
		bufferedReader.close();
	}
}
~~~
#### 2.3.2 BufferedReader、BufferedWriter
>BufferedReader、BufferedWriter使用步骤：
1. 找到目标文件
2. 建立数据的输入输出通道

~~~
public class test {
	public static void main(String[] args) throws IOException {
		readTest();
		File file  = new File("F:\\a.txt");
		//建立数据的输入通道。
		FileReader fileReader = new FileReader(file);
		String line =  null;
		while((line = myReadLine(fileReader))!=null){
			System.out.println(line);
		}
	}
	//自己去实现readLine方法。
	public static String myReadLine(FileReader fileReader) throws IOException{
		//创建一个字符串缓冲类对象
		StringBuilder sb = new StringBuilder();  //StringBuilder主要是用于存储读取到的数据
		int content = 0 ;
		while((content = fileReader.read())!=-1){
			if(content=='\r'){
				continue;
			}else if(content=='\n'){
				break;
			}else{
				//普通字符
				sb.append((char)content);
			}
		}
		//代表已经读取完毕了。
		if(content ==-1){
			return null;
		}
		return sb.toString();  
	}
	public static void readTest() throws IOException{
		//找到目标文件
		File file  = new File("F:\\a.txt");
		//建立数据的输入通道。
		FileReader fileReader = new FileReader(file);
		//建立缓冲输入字符流
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		//读取数据
		int content = bufferedReader.read();  //读到了一个字符
		System.out.println((char)content);
		//使用BUfferedReader拓展的功能，readLine()  一次读取一行文本，如果读到了文件的末尾返回null表示。
		String line =  null;
		while((line = bufferedReader.readLine())!=null){ // 虽然readLine每次读取一行数据，但是单行的line是不包含\r\n的、
			System.out.println(Arrays.toString(line.getBytes()));
		}
		//关闭资源
		bufferedReader.close();
	}
}
~~~
#### 2.3.3 ObjectInputStream、ObjectOutputStream
对象的输入输出流主要作用是用于写对象的信息与读取对象的信息（对象信息持久化），写到文件上
对象的输入流：ObjectInputStream
对象的输出流：ObjectOutputStream

>对象输入输出流注意细节：
1. 如果对象需要被写出到文件上，对象所属的类必须要实现Serializable接口，Serializable接口没有任何的方法，是一个标识接口而已。
2. 对象的反序列化创建对象的时候并不会调用到构造方法。
3. serialVersionUID用于记录class文件的版本信息，serialVersionUID这个数字是通过一个类的类名、成员、包名、工程名算出的一个数字。
4. 使用ObjectInputStream反序列化的时候，ObjectInputStream会先读取文件中的serialVersionUID，然后与本地的class文件的serialVersionUID进行对比，如果这两个id不一致，那么反序列化失败。
5. 如果序列化与反序列化的时候可能会修改类的成员，最好一开始就给这个类指定一个serialVersionUID，如果一类已经指定的serialVersionUID，然后在序列化与反序列化的时候，jvm都不会再算这个class的serialVersionUID了。
6. 如果一个对象某个数据不想被序列化到硬盘上，可以使用关键字transient修饰。
7. 如果一个类维护了另外一个类的引用，那么另外一个类也需要实现Serializable接口。

~~~
class Address implements Serializable{
	String country; 
	String city;
	public Address(String country,String city){
		this.country = country;
		this.city = city;
	}
}
class User implements Serializable{
	private static final long serialVersionUID = 1L;
	String userName ;	
	String password;	
	transient int age;  // transient 透明	
	Address address ;
	public User(String userName , String passwrod) {
		this.userName = userName;
		this.password = passwrod;
	}
	public User(String userName , String passwrod,int age,Address address) {
		this.userName = userName;
		this.password = passwrod;
		this.age = age;
		this.address = address;
	}
	@Override
	public String toString() {
		return "用户名："+this.userName+ " 密码："+ this.password+" 年龄："+this.age+" 地址："+this.address.city;
	}
}
public class test {
	public static void main(String[] args) throws IOException, Exception {
		writeObj();
		readObj();
	}
	//把文件中的对象信息读取出来  -->  对象的反序列化
	public static void readObj() throws  IOException, ClassNotFoundException{
		//找到目标文件
		File file = new File("F:\\obj.txt");
		//建立数据的输入通道
		FileInputStream fileInputStream = new FileInputStream(file);
		//建立对象的输入流对象
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		//读取对象信息
		User user = (User) objectInputStream.readObject(); //创建对象肯定要依赖对象所属的class文件。
		System.out.println("对象的信息："+ user);
	}
	//定义方法把对象的信息写到硬盘上  -->  对象的序列化
	public static void writeObj() throws IOException{
		//把user对象的信息持久化存储
		Address address = new Address("中国","泰州");
		User user = new User("sunshine","19931128",24,address);
		//找到目标文件
		File file = new File("F:\\obj.txt");
		//建立数据输出流对象
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		//建立对象的输出流对象
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		//把对象写出
		objectOutputStream.writeObject(user);
		//关闭资源
		objectOutputStream.close();
	}
}
~~~
### 2.4 转换流(InputStreamReader、OutputStreamWriter)
输入字节流的转换流：InputStreamReader是字节流通向字符流的桥梁
输出字节流的转换流：OutputStreamWriter把输出字节流转换成输出字符流
>转换流的作用：
1. 字节流 --> 字符流
2. 指定编码表进行读写文件

~~~
public class test {
	public static void main(String[] args) throws IOException {
		readTest();
		readTest2();
		writeTest();
		writeTest2();	
	}
	//输入 字节流 --> 字符流  按行读
	public static void readTest() throws IOException{
		InputStream in = System.in; //获取了标准的输入字节流
        //System.out.println("读到的字符："+ (char)in.read());  //read()一次只能读取一个字节。
		//字节流 --> 字符流
		InputStreamReader inputStreamReader = new InputStreamReader(in);
		//使用字符流的缓冲类
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		String line = null;
		//字节流不能按行读，字符流可以
		while((line = bufferedReader.readLine())!=null){
			System.out.println("内容："+ line);
		}
	}
	//使用输入字节流的转换流指定码表进行读取文件数据
	public static void readTest2() throws IOException{
		File file = new File("F:\\a.txt");
		FileInputStream fileInputStream = new FileInputStream(file);
		//创建字节流的转换流并且指定码表进行读取
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream,"utf-8");
		char[] buf = new char[1024];
		int length = 0;
		while((length = inputStreamReader.read(buf))!=-1){
			System.out.println(new String(buf,0,length));
		}
	}
	//输出 字节流 --> 字符流
	public static void writeTest() throws IOException{
		File file = new File("F:\\a.txt");
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		//把输出字节流转换成输出字符流
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);	
		outputStreamWriter.write("大家好");
		outputStreamWriter.close();		
	}
	//使用输出字节流的转换流指定码表写出数据
	public static void writeTest2() throws IOException{
		File file = new File("F:\\a.txt");
		//建立数据的输出通道
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		//把输出字节流转换成字符流并且指定编码表。
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "utf-8");
		outputStreamWriter.write("新中国好啊");
		//关闭资源
		outputStreamWriter.close();	
	}
}
~~~
### 2.5 打印流(printStream)
打印流可以打印任意类型的数据，打印数据之前会先把数据转换成字符串
~~~
class Animal{	
	String name;
	String color;
	public Animal(String name,String color){
		this.name = name;
		this.color = color;
	}
	@Override
	public String toString() {
		return "名字："+this.name+ " 颜色："+ this.color;
	}
}
public class test {
	public static void main(String[] args) throws IOException {
		//打印流可以打印任何类型的数据，而且打印数据之前都会先把数据转换成字符串再进行打印。
		File file = new  File("F:\\a.txt");
		//创建一个打印流
		PrintStream printStream = new PrintStream(file);
		printStream.println(97);
		printStream.println(3.14);
		printStream.println('a');
		printStream.println(true);
		Animal a = new Animal("老鼠", "黑色");
		printStream.println(a);
		//默认标准的输出流向控制台输出
		System.setOut(printStream); //重新设置了标准的输出流对象
		System.out.println("sunshine");//向文件输出
		//收集异常的日志信息
		File logFile = new File("F:\\2017年1月17日.log");
		PrintStream logPrintStream = new PrintStream( new FileOutputStream(logFile,true) );
		try{
			int c = 4/0;
			System.out.println("c="+c);
			int[] arr = null;
			System.out.println(arr.length);		
		}catch(Exception e){
			e.printStackTrace(logPrintStream);
		}
	}
}
~~~
### 2.6 SequenceInputStream（文件分割与合并）
SequenceInputStream(序列流)
~~~
public class test {
	public static void main(String[] args) throws IOException {
		cutFile();
		mergeFlile();
	}
	//合并
	public static void mergeFlile() throws IOException{
		//找到目标文件
		File dir = new File("E:\\music");
		File dest = new File("F:\\合并.mp3");
		//建立对应的输入输出流对象
		FileOutputStream fileOutputStream = new FileOutputStream(dest);
		//通过目标文件夹找到所有的MP3文件，然后把所有的MP3文件添加到vector中。
		Vector<FileInputStream> vector = new Vector<FileInputStream>();
		File[] files = dir.listFiles();
		for(File file : files){
			if(file.getName().endsWith(".mp3")){
				vector.add(new FileInputStream(file));
			}
		}
		//通过Vector获取迭代器
		Enumeration<FileInputStream> e = vector.elements();
		//创建序列流对象
		SequenceInputStream inputStream = new SequenceInputStream(e);
		//建立缓冲数组读取文件
		byte[] buf = new byte[1024];
		int length = 0 ; 
		while((length =  inputStream.read(buf))!=-1){
			fileOutputStream.write(buf,0,length);
		}
		//关闭资源
		fileOutputStream.close();
		inputStream.close();
	}
	//切割MP3
	public static void cutFile() throws IOException{
		File file = new File("F:\\合并.mp3");
		//目标文件夹
		File dir = new File("E:\\music");
		//建立数据的输入通道
		FileInputStream fileInputStream = new FileInputStream(file);
		//建立缓冲数组读取
		byte[] buf = new byte[1024*1024];
		int length = 0;
		for(int i = 0 ;  (length = fileInputStream.read(buf))!=-1 ; i++){
			FileOutputStream fileOutputStream =	new FileOutputStream(new File(dir,"part"+i+".mp3"));
			fileOutputStream.write(buf,0,length);
			fileOutputStream.close();
		}
		//关闭资源
		fileInputStream.close();
	}
}
~~~
## 3 IO异常处理
~~~
public class test {
	public static void main(String[] args) {
		copyImage();
	}
	// 拷贝图片
	public static void copyImage() {
		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;
		try {
			// 找到目标文件
			File inFile = new File("F:\\a\\sunshine.png");
			File outFile = new File("E:\\1.png");
			// 建立输入输出通道
			fileInputStream = new FileInputStream(inFile);
			fileOutputStream = new FileOutputStream(outFile);
			// 建立缓冲数组，边读边写
			byte[] buf = new byte[1024];
			int length = 0;
			while ((length = fileInputStream.read(buf)) != -1) {
				fileOutputStream.write(buf, 0, length);
			}
		} catch (IOException e) {
			System.out.println("拷贝图片出错...");
			throw new RuntimeException(e);
		} finally {
			// 关闭资源
			try {
				if (fileOutputStream != null) {
					fileOutputStream.close();
					System.out.println("关闭输出流对象成功...");
				}
			} catch (IOException e) {
				System.out.println("关闭输出流资源失败...");
				throw new RuntimeException(e);
			} finally {
				if (fileInputStream != null) {
					try {
						fileInputStream.close();
						System.out.println("关闭输入流对象成功...");
					} catch (IOException e) {
						System.out.println("关闭输入流对象失败...");
						throw new RuntimeException(e);
					}
				}
			}
		}
	}
}
~~~
## 4 递归列出文件树形目录和递归删除文件夹
~~~
public class test {
	public static void main(String[] args) {
		File dir = new File("E:\\git");
		listFiles(dir,"|--");
		File dir2 = new File("E:\\syn");
		deleteDir(dir2);
	}
	// 列出一个文件夹的子孙文件与目录。
	public static void listFiles(File dir, String space) { // space 存储的是空格
		File[] files = dir.listFiles(); // 列出所有的子文件
		for (File file : files) {
			if (file.isFile()) {
				System.out.println(space + file.getName());
			} else if (file.isDirectory()) {
				System.out.println(space + file.getName());
				listFiles(file, "|   " + space);
			}
		}
	}
	// 删除了一个非空的目录
	public static void deleteDir(File dir) {
		File[] files = dir.listFiles(); // 列出了所有的子文件
		for (File file : files) {
			if (file.isFile()) {
				file.delete();
			} else if (file.isDirectory()) {
				deleteDir(file);
			}
		}
		dir.delete();
	}
}
~~~
## 5 编码与解码 
编码与解码一般使用统一的码表，否则非常容易出乱码
>常见码表：
ASCII：   美国标准信息交换码，用一个字节的7位表示。
ISO8859-1：   拉丁码表，欧洲码表，用一个字节的8位表示。又称Latin-1(拉丁编码)或“西欧语言”。ASCII码是包含的仅仅是英文字母，并且没有完全占满256个编码位置，所以它以ASCII为基础，在空置的0xA0-0xFF的范围内，加入192个字母及符号，藉以供使用变音符号的拉丁字母语言使用。从而支持德文，法文等。因而它依然是一个单字节编码，只是比ASCII更全面。
GB2312：   中国的中文编码表。
GBK：   中国的中文编码表升级，融合了更多的中文文字符号。
Unicode：   国际标准码，融合了多种文字。所有文字都用两个字节来表示，Java语言使用的就是unicode。
UTF-8：   最多用三个字节来表示一个字符。

~~~
public class test {
	public static void main(String[] args) throws Exception {
		String str1 = "中国";
		//编码成字节
		byte[] buf1 = str1.getBytes("utf-8");// 平台默认的编码表是gbk编码表
		System.out.println("数组的元素："+Arrays.toString(buf1));//[-28, -72, -83, -27, -101, -67]
		//从字节解码
		str1 = new String(buf1,"utf-8");  // 默认使用gbk码表去解码
		System.out.println("解码后的字符串："+ str1);
	}
}
~~~
## 6 Properties（配置文件类）
用于生产配置文件与读取配置文件的信息
>注意细节：
1. 如果配置文件的信息使用了中文，在使用store方法生成配置文件的时候只能使用字符流解决，如果使用字节流生成配置文件，默认使用iso8859-1码表编码存储，会出现乱码
2. 如果Properties中的内容发生了变化，一定要重新使用Properties生成配置文件，否则配置文件信息不会发生变化

~~~
public class test {
	public static void main(String[] args) throws IOException {
		creatProperties();
		readProperties();
	}
	//读取配置文件的信息 
	public static void readProperties() throws IOException{
		//创建Properties对象
		Properties properties = new Properties();
		//加载配置文件信息到Properties中
		properties.load(new FileReader("F:\\test.properties"));
		//遍历
		Set<Entry<Object, Object>> entrys = properties.entrySet();
		for(Entry<Object, Object> entry  :entrys){
			System.out.println("键："+ entry.getKey() +" 值："+ entry.getValue());
		}
		//修改sun1的密码
		//把修改后的Properties再生成一个配置文件
		properties.setProperty("sun1", "007");
		properties.store(new FileWriter("F:\\test.properties"), "new");
	}	
	//保存配置文件文件的信息。
	public static void creatProperties() throws IOException{
		// 创建Properties
		Properties properties = new Properties();
		properties.setProperty("sun1", "123");
		properties.setProperty("sun2","234");
		properties.setProperty("sun3","345");
		// 遍历Properties
		Set<Entry<Object, Object>> entrys = properties.entrySet();
		for(Entry<Object, Object> entry  :entrys){
			System.out.println("键："+ entry.getKey() +" 值："+ entry.getValue());
		}
		//使用Properties生成配置文件。
		//properties.store(new FileOutputStream("F:\\test.properties"), "information"); //第一个参数是一个输出流对象，第二参数是使用一个字符串描述这个配置文件的信息。
		properties.store(new FileWriter("F:\\test.properties"), "information");
	}
}
~~~
~~~
//配置文件实现软件限制试用三次
public class test {
	public static void main(String[] args) throws IOException {
		File file = new File("F:\\count.properties");
		if(!file.exists()){
			//如果配置文件不存在，则创建该配置文件
			file.createNewFile();
		}
		//创建Properties对象。
		Properties properties = new Properties();
		//把配置文件的信息加载到properties中
		properties.load(new FileInputStream(file));
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		int count = 0; //定义该变量是用于保存软件的运行次数的。
		//读取配置文件的运行次数
		String value = properties.getProperty("count");
		if(value!=null){
			count = Integer.parseInt(value);
		}
		//判断使用的次数是否已经达到了三次，
		if(count==3){
			System.out.println("你已经超出了试用次数，请购买正版软件！！");
			System.exit(0);
		}
		count++;
		System.out.println("你已经使用了本软件第"+count+"次");
		properties.setProperty("count",count+"");
		//使用Properties生成一个配置文件
		properties.store(fileOutputStream,"runtime");	
	}
}
~~~