#!/bin/bash
:<< EOF
Shell 文件包含
和其他语言一样，Shell 也可以包含外部脚本。这样可以很方便的封装一些公用的代码作为一个独立的文件。

Shell 文件包含的语法格式如下：

. filename   # 注意点号(.)和文件名中间有一空格

或

source filename
EOF

#实例
#执行/9_input_output.sh 脚本文件
#
. ./9_input_output.sh