#!/bin/bash

# 请注意
# 本脚本的作用是把本项目编译的结果保存到deploy文件夹中
# 1. 把项目数据库文件拷贝到docker/db/init-sql
# 2. 编译lillia-admin
# 3. 编译lillia-all模块，然后拷贝到docker/lillia

LILLIA_HOME=/home/luomor/git/lillia-api
echo "LILLIA_HOME $LILLIA_HOME"
cd $LILLIA_HOME

sudo -u luomor git pull

cd $LILLIA_HOME
mvn clean package
cp -f $LILLIA_HOME/target/lillia-api.jar $LILLIA_HOME/docker/lillia/lillia.jar
cp -f $LILLIA_HOME/target/lillia-api.jar $LILLIA_HOME/docker/lillia-fe/lillia.jar
cp -f $LILLIA_HOME/docker/lillia/Dockerfile $LILLIA_HOME/docker/lillia-fe/Dockerfile
cp -f $LILLIA_HOME/docker/lillia/application.yml $LILLIA_HOME/docker/lillia-fe/application.yml

cd $LILLIA_HOME/docker

sudo docker-compose build
sudo docker-compose up -d lillia