```shell
mvn clean package
cp siival-system/target/siival-system-2.6.jar docker/lillia-fe/lillia.jar
cp docker/lillia/Dockerfile docker/lillia-fe/
cp docker/lillia/application.yml docker/lillia-fe/
cd docker/

sudo docker-compose build
sudo docker-compose up -d
sudo docker-compose up down
sudo docker-compose logs -f

mysql -h127.0.0.1 -uroot -p -P 3310
lillia123456
CREATE DATABASE lillia CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_general_ci';
mysql -h127.0.0.1 -uroot -p -P 3310 lillia < ./sql/test.sql

sudo docker exec -it lillia-fe bash
git add lillia/views/
git add lillia/siival-system/

git add docker/lillia
git commit -a -m 'update'
git push -u origin master

mkdir -p ../eladmin-web/src/views/question/today
cp -r docker/lillia/views/question/today/index.vue ../eladmin-web/src/views/question/today
cp -r docker/lillia/views/question/today/questionToday.js ../eladmin-web/src/api
mkdir -p ../eladmin-web/src/views/question/todayAnswer
cp -r docker/lillia/views/question/todayAnswer/index.vue ../eladmin-web/src/views/question/todayAnswer
cp -r docker/lillia/views/question/todayAnswer/questionTodayAnswer.js ../eladmin-web/src/api

http://82.157.54.206:8010/
https://xzs.7otech.com/

cd eladmin-web
cnpm install
bsc
npm run dev
npm run build:prod

CREATE DATABASE siival_admin CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_general_ci';
java -jar siival-system-2.6.jar
nohup java -jar siival-system-2.6.jar &
#查看程序是否启动
sh spring-boot-simple.sh status 
#启动程序
sh spring-boot-simple.sh start 
#停止
sh spring-boot-simple.sh stop 
#重启
sh spring-boot-simple.sh restart
```

```shell
#Debian
apt-get install realpath
#Ubuntu
apt-get install realpath
#Alpine
apk add coreutils
#Arch Linux
pacman -S coreutils
#Kali Linux
apt-get install coreutils
#CentOS
yum install coreutils
#Fedora
dnf install coreutils
#OS X
brew install realpath
#Raspbian
apt-get install coreutils
#Docker
docker run cmd.cat/realpath realpath
```