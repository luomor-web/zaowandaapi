#! /bin/bash
git pull
mkdir -p ../eladmin-web/src/views/question/today
cp -r docker/lillia/question/today/index.vue ../eladmin-web/src/views/question/today
cp -r docker/lillia/question/today/questionToday.js ../eladmin-web/src/api
mkdir -p ../eladmin-web/src/views/question/todayAnswer
cp -r docker/lillia/question/todayAnswer/index.vue ../eladmin-web/src/views/question/todayAnswer
cp -r docker/lillia/question/todayAnswer/questionTodayAnswer.js ../eladmin-web/src/api
