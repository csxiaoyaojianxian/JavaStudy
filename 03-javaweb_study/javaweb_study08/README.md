# javaweb学习笔记-Lucene
链接：[https://csxiaoyaojianxian.github.io/javaweb_study08](https://csxiaoyaojianxian.github.io/javaweb_study08 )  
名称：Lucene使用api的分层开发及内存优化  
说明：封装类可直接调用  
## 1 环境配置
### 1.1 导入Lucene相关jar包
>【lucene-core-3.0.2.jar】Lucene核心  
【lucene-analyzers-3.0.2.jar】分词器  
【lucene-highlighter-3.0.2.jar】Lucene会将搜索出来的字高亮显示  
【lucene-memory-3.0.2.jar】索引库优化策略  

### 1.2 配置文件
【IKAnalyzer.cfg.xml】IK分词器词典配置文件  
【mydict.dic】配置用户自定义扩展字典  
【surname.dic】配置用户自定义扩展停止词字典  
## 2 LuceneUtil工具类
使用反射实现：  
（1）javabean对象转document对象  
（2）document对象转javabean对象  
## 3 Lucene的api测试
【LuceneCrud.java】  
已经整合好接口，包含的额外api：  
~~~
1. 合并cfs文本，设置合并策略  
2. 手动评分  
3. 内存优化同步硬盘  
4. 关键词高亮  
5. 排序策略  
~~~
## 4 分层开发
bean、dao、service以及【分页】的实现
