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

CREATE DATABASE siival_admin CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_general_ci';
java -jar siival-system-2.6.jar
nohup java -jar siival-system-2.6.jar &
```