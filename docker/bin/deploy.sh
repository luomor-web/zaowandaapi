#!/bin/bash

cd /home/luomor/git/lillia-api
sudo docker-compose down
sudo docker-compose build
sudo docker image prune -f
sudo docker-compose up -d
