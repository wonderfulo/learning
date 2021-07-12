#!/bin/bash
:<< EOF
Shell 中的中括号用法总结
EOF

#Shell 里面的中括号（包括单中括号与双中括号）可用于一些条件的测试：
#
#算术比较, 比如一个变量是否为0, [ $var -eq 0 ]。
#文件属性测试，比如一个文件是否存在，[ -e $var ], 是否是目录，[ -d $var ]。
#字符串比较, 比如两个字符串是否相同， [[ $var1 = $var2 ]]。

#算术比较
#对变量或值进行算术条件判断：
var=0
if [ $var -eq 0 ] # 当 $var 等于 0 时，返回真
then
  echo "真";
fi
#[ $var -ne 0 ]  # 当 $var 不等于 0 时，返回真
#需要注意的是 [ 与 ] 与操作数之间一定要有一个空格，否则会报错。比如下面这样就会报错:
#
#[$var -eq 0 ]  或 [ $var -ne 0]

#其他比较操作符：
#
#操作符	意义
#-gt	大于
#-lt	小于
#-ge	大于或等于
#-le	小于或等于

#可以通过 -a (and) 或 -o (or) 结合多个条件进行测试：
#
#[ $var1 -ne 0 -a $var2 -gt 2 ]  # 使用逻辑与 -a
#[ $var1 -ne 0 -o $var2 -gt 2 ]  # 使用逻辑或 -o

:<< EOF
文件系统属性测试
使用不同的条件标志测试不同的文件系统属性。

操作符	意义
[ -f $file_var ]	变量 $file_var 是一个正常的文件路径或文件名 (file)，则返回真
[ -x $var ]	变量 $var 包含的文件可执行 (execute)，则返回真
[ -d $var ]	变量 $var 包含的文件是目录 (directory)，则返回真
[ -e $var ]	变量 $var 包含的文件存在 (exist)，则返回真
[ -c $var ]	变量 $var 包含的文件是一个字符设备文件的路径 (character)，则返回真
[ -b $var ]	变量 $var 包含的文件是一个块设备文件的路径 (block)，则返回真
[ -w $var ]	变量 $var 包含的文件可写(write)，则返回真
[ -r $var ]	变量 $var 包含的文件可读 (read)，则返回真
[ -L $var ]	变量 $var 包含是一个符号链接 (link)，则返回真
EOF

fpath="/etc/passwd"
if [ -e $fpath ]; then
  echo File exits;
else
  echo Does not exit;
fi

#字符串比较
#在进行字符串比较时，最好使用双中括号 [[ ]]. 因为单中括号可能会导致一些错误，因此最好避开它们。
#
#检查两个字符串是否相同：[[ $str1 = $str2 ]]
#当 str1等于str1等于str2 时，返回真。也就是说，str1 和 str2 包含的文本是一样的。其中的单等于号也可以写成双等于号，也就是说，上面的字符串比较等效于 [[ $str1 == $str2 ]]。
#
#注意 = 前后有一个空格，如果忘记加空格, 就变成了赋值语句，而非比较关系了。
:<<EOF
字符串的其他比较情况：

操作符	意义
[[ $str1 != $str2 ]]	如果 str1 与 str2 不相同，则返回真
[[ -z $str1 ]]	如果 str1 是空字符串，则返回真
[[ -n $str1 ]]	如果 str1 是非空字符串，则返回真
EOF

#使用逻辑运算符 && 和 || 可以轻松地将多个条件组合起来, 比如：
str1="Not empty"
str2=""
if [[ -n $str1 ]] && [[ -z $str2 ]];
then
  echo str1 is nonempty and str2 is empty string.
fi

#test 命令也可以从来执行条件检测，用 test 可以避免使用过多的括号，[] 中的测试条件同样可以通过 test 来完成。
if [ $var -eq 0 ]; then echo "True"; fi
#等价于:
if test $var -eq 0; then echo "True"; fi