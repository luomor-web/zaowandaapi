#!/bin/bash

# 请注意
# 本脚本的作用是把本项目编译的结果保存到deploy文件夹中
# 1. 把项目数据库文件拷贝到docker/db/init-sql
# 2. 编译lillia-admin
# 3. 编译lillia-all模块，然后拷贝到docker/lillia

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null && pwd )"
cd $DIR/../..
LILLIA_HOME=$PWD
echo "LILLIA_HOME $LILLIA_HOME"

# 复制数据库
# cat $LILLIA_HOME/lillia-db/sql/lillia_schema.sql > $LILLIA_HOME/docker/db/init-sql/lillia.sql
# cat $LILLIA_HOME/lillia-db/sql/lillia_table.sql >> $LILLIA_HOME/docker/db/init-sql/lillia.sql
# cat $LILLIA_HOME/lillia-db/sql/lillia_data.sql >> $LILLIA_HOME/docker/db/init-sql/lillia.sql
# cat $LILLIA_HOME/lillia-db/sql/lillia_chinatower.sql >> $LILLIA_HOME/docker/db/init-sql/lillia.sql

cd $LILLIA_HOME/lillia-admin
# 安装阿里node镜像工具
npm install -g cnpm --registry=https://registry.npm.taobao.org
# 安装node项目依赖环境
cnpm install
cnpm run build:dep

cd $LILLIA_HOME
mvn clean package
cp -f $LILLIA_HOME/lillia-all/target/lillia-all-*-exec.jar $LILLIA_HOME/docker/lillia/lillia.jar