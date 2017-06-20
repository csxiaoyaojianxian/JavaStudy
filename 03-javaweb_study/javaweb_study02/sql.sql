-- 创建数据库
CREATE DATABASE hotel CHARACTER SET utf8;
USE hotel;
-- 1. 餐桌表
CREATE TABLE dinnerTable(
   id INT PRIMARY KEY AUTO_INCREMENT,  -- 餐桌主键
   tableName VARCHAR(20),	       -- 餐桌名
   tableStatus INT DEFAULT 0,          -- 餐桌状态：0，空闲； 1，预定
   orderDate DATETIME
);


-- 2. 菜类别表
CREATE TABLE foodType(
    id INT PRIMARY KEY AUTO_INCREMENT,  -- 类别主键
    typeName VARCHAR(20)		-- 类别名称
);


-- 3. 菜品种表
CREATE TABLE food(
  id INT PRIMARY KEY AUTO_INCREMENT,  -- 主键
  foodName VARCHAR(20),		      -- 菜名称
  foodType_id INT,		      -- 所属菜系, 外键字段
  price DOUBLE,			      -- 价格
  mprice DOUBLE,		      -- 会员价格
  remark VARCHAR(200),		      -- 简介
  img VARCHAR(100) 		      -- 图片
);


-- 4. 订单表      (订单基本信息)
CREATE TABLE orders(
   id INT PRIMARY KEY AUTO_INCREMENT,  -- 主键
   table_id INT,		       -- 外键： 餐桌编号
   orderDate DATETIME,		       -- 下单日期
   totalPrice DOUBLE,		       -- 订单所有菜需要的总金额
   orderStatus INT DEFAULT 0           -- 订单状态： 0,未结账； 1,已结账
);

-- 5. 订单明细表  (主要是菜品种)
CREATE TABLE orderDetail(
   id INT PRIMARY KEY AUTO_INCREMENT,  -- 主键
   orderId INT,			       -- 外键：引入的是订单表的主键
   food_id INT,			       -- 外键：引用的是菜信息表的主键
   foodCount INT                       -- 菜的数量
   
);


-- 添加菜品与菜类别的关系约束
ALTER TABLE food ADD CONSTRAINT fk_food_foodType_id FOREIGN KEY(foodType_id) REFERENCES foodType(id);
-- 订单表： 与餐桌表的关系
ALTER TABLE orders ADD CONSTRAINT order_table_id FOREIGN KEY(table_id) REFERENCES dinnertable(id);
-- 订单明细： 与订单表的关系
ALTER TABLE orderDetail ADD CONSTRAINT orderDetail_order_id FOREIGN KEY(orderId) REFERENCES orders(id);
-- 订单明细： 与菜信息的关系
ALTER TABLE orderDetail ADD CONSTRAINT orderDetail_food_id FOREIGN KEY(food_id) REFERENCES food(id);
