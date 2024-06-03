#! /bin/bash
git pull
mkdir -p ../eladmin-web/src/views/question/today
cp -r docker/lillia/views/question/today/index.vue ../eladmin-web/src/views/question/today
cp -r docker/lillia/views/question/today/questionToday.js ../eladmin-web/src/api
mkdir -p ../eladmin-web/src/views/question/todayAnswer
cp -r docker/lillia/views/question/todayAnswer/index.vue ../eladmin-web/src/views/question/todayAnswer
cp -r docker/lillia/views/question/todayAnswer/questionTodayAnswer.js ../eladmin-web/src/api
mkdir -p ../eladmin-web/src/views/exam/info
cp -r docker/lillia/views/exam/info/index.vue ../eladmin-web/src/views/exam/info
#cp -r docker/lillia/views/exam/info/examInfo.js ../eladmin-web/src/api
cp -r docker/lillia/views/exam/info/*.js ../eladmin-web/src/api
cd ../eladmin-web
git add .
git commit -a -m 'update'
git push -u origin master
