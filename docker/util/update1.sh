#!/bin/bash

# 请注意
# 本脚本的作用是把本项目编译的结果保存到deploy文件夹中
# 1. 把项目数据库文件拷贝到docker/db/init-sql
# 2. 编译lillia-admin
# 3. 编译lillia-all模块，然后拷贝到docker/lillia

git pull
mvn clean package
cp siival-system/target/siival-system-2.6.jar docker/lillia-fe/lillia.jar
cp docker/lillia/Dockerfile docker/lillia-fe/
cp docker/lillia/application.yml docker/lillia-fe/
cd docker/

sudo docker-compose build
sudo docker-compose up -d