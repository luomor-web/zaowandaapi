```shell
cp siival-system/target/siival-system-2.6.jar docker/lillia-fe/lillia.jar

CREATE DATABASE lillia CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_general_ci';

CREATE DATABASE siival_admin CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_general_ci';
java -jar siival-system-2.6.jar
nohup java -jar siival-system-2.6.jar &
```