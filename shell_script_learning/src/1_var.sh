#!/bin/bash
# 变量
your_name="runoob.com"
echo $your_name
echo ${your_name}

#变量名外面的花括号是可选的，加不加都行，加花括号是为了帮助解释器识别变量的边界，比如下面这种情况：
for skill in Ada Coffe Action Java; do
    echo "I am good at ${skill}Script"
done

#只读变量
#使用 readonly 命令可以将变量定义为只读变量，只读变量的值不能被改变。
#readonly myUrl="https://www.google.com"
#myUrl="https://www.runoob.com"

#删除变量
myUrl="https://www.runoob.com"
unset myUrl
echo $myUrl
#以上实例执行将没有任何输出。

#Shell 字符串
#字符串是shell编程中最常用最有用的数据类型（除了数字和字符串，也没啥其它类型好用了），字符串可以用单引号，也可以用双引号，也可以不用引号。
#单引号字符串的限制：
#
#单引号里的任何字符都会原样输出，单引号字符串中的变量是无效的；
#单引号字串中不能出现单独一个的单引号（对单引号使用转义符后也不行），但可成对出现，作为字符串拼接使用。

#双引号
your_name='runoob'
str="Hello, I know you are \"$your_name\"! \n"
echo -e $str

#双引号的优点：
#
#双引号里可以有变量
#双引号里可以出现转义字符

#拼接字符串
your_name="runoob"
# 使用双引号拼接
greeting="hello, "$your_name" !"
greeting_1="hello, ${your_name} !"
echo $greeting  $greeting_1
# 使用单引号拼接
greeting_2='hello, '$your_name' !'
greeting_3='hello, ${your_name} !'
echo $greeting_2  $greeting_3
#输出结果为：
#
#hello, runoob ! hello, runoob !
#hello, runoob ! hello, ${your_name} !

#获取字符串长度
string="abcd"
echo ${#string} #输出 4

# 提取子字符串
# 以下实例从字符串第 2 个字符开始截取 4 个字符：
# 注意：第一个字符的索引值为 0。
string="runoob is a great site"
echo ${string:1:4} # 输出 unoo

#查找子字符串
#查找字符 i 或 o 的位置(哪个字母先出现就计算哪个)：
string="runoob is a great site"
echo `expr index "$string" io`  # 输出 4

#Shell 数组
#bash支持一维数组（不支持多维数组），并且没有限定数组的大小。
#
#类似于 C 语言，数组元素的下标由 0 开始编号。获取数组中的元素要利用下标，下标可以是整数或算术表达式，其值应大于或等于 0。

#定义数组
#在 Shell 中，用括号来表示数组，数组元素用"空格"符号分割开。定义数组的一般形式为：
#
#数组名=(值1 值2 ... 值n)
array_name=(value0 value1 value2 value3)
#或者
array_name=(
value0
value1
value2
value3
)
#还可以单独定义数组的各个分量：
array_name[0]=value0
array_name[1]=value1
array_name[10]=value10
#可以不使用连续的下标，而且下标的范围没有限制。

#读取数组
#读取数组元素值的一般格式是：
#
#${数组名[下标]}

#例如：
#
valuen=${array_name[n]}
#使用 @ 符号可以获取数组中的所有元素，例如：
#
echo ${array_name[@]}

#获取数组的长度
#获取数组长度的方法与获取字符串长度的方法相同，例如：
# 取得数组元素的个数
length=${#array_name[@]}
echo ${length}
# 或者
length=${#array_name[*]}
echo ${length}
# 取得数组单个元素的长度
lengthn=${#array_name[10]}
echo ${lengthn}

#多行注释
:<<EOF
注释内容...
注释内容...
注释内容...
EOF