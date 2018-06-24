1.@ComponentScan(basePackages={"com.spring"})  这个注解的意思是通过spring容
器去加载以com.spring开头的包下面的内容。

2.<#assign var="hello spring boot"/>
freemarker标签用于声明一个变量

3.@Configuration注解会默认加载resources下的配置文件。

4.freemarker不支持boolean类型的true/false。输出是会抛出异常！
但是可以转化：布尔值: ${booleanVar?string('yes','no')}


5.<#if myList??>  表示判断myList变量是不是存在或是不是空值
等同于<#if myList?exists>

6.freemarker内建指令以#开始，自定义指令以@开始
<@role user='123456' role='admin';result1,result2></@role>
user用户id,role用户权限

7.Freemarker的内建函数就是直接可以使用的函数，是freemarker封装好的函数。
有处理字符串，数字，list的内建函数。
处理字符串内建函数：
substring 字符串的截取
cap_first 首字母变大写
ends_with 以...结尾的函数
contains  字符串是否包含目标字符串的函数
如何把一个字符串转化为date 、datetime 、time类型的函数 date  datetime  time；  
starts_with  字符串以...开始
index_of   字符串所在的位置
last_index_of  字符串最后所在的位置
split  分割字符串
trim   去掉字符串两头的空格
等函数   
      
 处理数字的内建函数
string  x?string("0.##")  对数字进行格式化
round  四舍五入
floor  把小数点去掉 
ceiling 数字进1，变成整数

处理list内建函数：
first  取list第一个值
last  取list最后一个值
seq_contains 这个序列是否包含
seq_index_of  这个序列所在的位置
size list长度
reverse  倒序
sort  升序排序
sort_by  根据属性排序
trunk 把字符串分块处理

其他内建函数
is函数：判断变量的类型
is_string  字符串 
is_number   整数
is_method   方法

() 对变量进行判断
hs_content 判断对象是否是空值，是不是有内容
eval 求值函数

8.<#nested param1,"我的nested参数">  用来定义模板片段