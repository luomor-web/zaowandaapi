```shell
mvn package
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
git add lillia/question/
git add lillia/siival-system/

CREATE DATABASE siival_admin CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_general_ci';
java -jar siival-system-2.6.jar
nohup java -jar siival-system-2.6.jar &

http://82.157.54.206:8010/
https://xzs.7otech.com/

cd eladmin-web
cnpm install
bsc
npm run dev
npm run build:prod
```