#!/bin/bash

LILLIA_HOME=/home/luomor/git/lillia-api
echo "LILLIA_HOME $LILLIA_HOME"
cd $LILLIA_HOME

sudo -u lillia git pull

cd $LILLIA_HOME/docker

# create user 'lillia'@'%' identified by 'lillia123456';
# grant all privileges on lillia.* to 'lillia'@'%';
# flush privileges;
# mysql -h127.0.0.1 -ulillia -p

sudo docker-compose down
sudo docker-compose build
sudo docker image prune -f
sudo docker-compose up -d
