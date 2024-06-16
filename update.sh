#! /bin/bash
git pull
mkdir -p ../eladmin-web/src/views/question/today
cp -r docker/lillia/views/question/today/index.vue ../eladmin-web/src/views/question/today
cp -r docker/lillia/views/question/today/questionToday.js ../eladmin-web/src/api
mkdir -p ../eladmin-web/src/views/question/todayAnswer
cp -r docker/lillia/views/question/todayAnswer/index.vue ../eladmin-web/src/views/question/todayAnswer
cp -r docker/lillia/views/question/todayAnswer/questionTodayAnswer.js ../eladmin-web/src/api
mkdir -p ../eladmin-web/src/views/exam/info
cp -r docker/lillia/views/exam/info/index.vue ../eladmin-web/src/views/exam
#cp -r docker/lillia/views/exam/info/examInfo.js ../eladmin-web/src/api
cp -r docker/lillia/views/exam/info/*.js ../eladmin-web/src/api
mkdir -p ../eladmin-web/src/views/exam/menu
cp -r docker/lillia/views/exam/menu/index.vue ../eladmin-web/src/views/exam/menu
cp -r docker/lillia/views/exam/menu/*.js ../eladmin-web/src/api
mkdir -p ../eladmin-web/src/views/question/index
cp -r docker/lillia/views/question/info/index.vue ../eladmin-web/src/views/question/index
cp -r docker/lillia/views/question/info/*.js ../eladmin-web/src/api
mkdir -p ../eladmin-web/src/views/question/menu
cp -r docker/lillia/views/question/menu/index.vue ../eladmin-web/src/views/question/menu
cp -r docker/lillia/views/question/menu/*.js ../eladmin-web/src/api
mkdir -p ../eladmin-web/src/views/question/correct
cp -r docker/lillia/views/question/correct/index.vue ../eladmin-web/src/views/question/correct
cp -r docker/lillia/views/question/correct/*.js ../eladmin-web/src/api
mkdir -p ../eladmin-web/src/views/wx/user
cp -r docker/lillia/views/wx/user/index.vue ../eladmin-web/src/views/wx/user
cp -r docker/lillia/views/wx/user/*.js ../eladmin-web/src/api
mkdir -p ../eladmin-web/src/views/user/info
cp -r docker/lillia/views/user/info/index.vue ../eladmin-web/src/views/user/info
cp -r docker/lillia/views/user/info/*.js ../eladmin-web/src/api
mkdir -p ../eladmin-web/src/views/user/sign
cp -r docker/lillia/views/user/sign/index.vue ../eladmin-web/src/views/user/sign
cp -r docker/lillia/views/user/sign/*.js ../eladmin-web/src/api
mkdir -p ../eladmin-web/src/views/user/invite
cp -r docker/lillia/views/user/invite/index.vue ../eladmin-web/src/views/user/invite
cp -r docker/lillia/views/user/invite/*.js ../eladmin-web/src/api
mkdir -p ../eladmin-web/src/views/user/integral
cp -r docker/lillia/views/user/integral/index.vue ../eladmin-web/src/views/user/integral
cp -r docker/lillia/views/user/integral/*.js ../eladmin-web/src/api
mkdir -p ../eladmin-web/src/views/question/exportq
cp -r docker/lillia/views/question/exportq/index.vue ../eladmin-web/src/views/question/exportq
cp -r docker/lillia/views/question/exportq/*.js ../eladmin-web/src/api
mkdir -p ../eladmin-web/src/views/wx/app
cp -r docker/lillia/views/wx/app/index.vue ../eladmin-web/src/views/wx/app
cp -r docker/lillia/views/wx/app/*.js ../eladmin-web/src/api
mkdir -p ../eladmin-web/src/views/system/token
cp -r docker/lillia/views/system/token/index.vue ../eladmin-web/src/views/system/token
cp -r docker/lillia/views/system/token/*.js ../eladmin-web/src/api
mkdir -p ../eladmin-web/src/views/lillia/fileBatch
cp -r docker/lillia/views/lillia/fileBatch/index.vue ../eladmin-web/src/views/lillia/fileBatch
cp -r docker/lillia/views/lillia/fileBatch/*.js ../eladmin-web/src/api
mkdir -p ../eladmin-web/src/views/lillia/file
cp -r docker/lillia/views/lillia/file/index.vue ../eladmin-web/src/views/lillia/file
cp -r docker/lillia/views/lillia/file/*.js ../eladmin-web/src/api
#cp -r docker/lillia/siival-system/* siival-system/
#git add .
#git commit -a -m 'update'
#git push -u origin master
cd ../eladmin-web
git add .
git commit -a -m 'update'
git push -u origin master
