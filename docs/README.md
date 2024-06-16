```shell
git pull
mvn clean package
cp siival-system/target/siival-system-2.6.jar docker/lillia-fe/lillia.jar
cp docker/lillia/Dockerfile docker/lillia-fe/
cp docker/lillia/application.yml docker/lillia-fe/
cd docker/

sudo docker-compose build
sudo docker-compose up -d
sudo docker-compose up down
sudo docker-compose logs -f

tail -f lillia/logs/*

mysql -h127.0.0.1 -uroot -p -P 3310
lillia123456
CREATE DATABASE lillia CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_general_ci';
mysql -h127.0.0.1 -uroot -p -P 3310 lillia < ./sql/test.sql

mysql -h127.0.0.1 -uroot -p -P 3310
use lillia
show tables;
insert into wx_app(app_id,app_secret,create_time) values ('wx4a331a648d210360','56c9088a3c2c0bd17d57ba7ec1654310',now());

sudo docker exec -it lillia-fe bash
git add lillia/views/
git add lillia/siival-system/

git pull
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

```shell
cp -r docker/lillia/siival-system/* siival-system/
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/domain/ExamInfo.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/domain/ExamMenu.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/domain/QuestionInfo.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/domain/QuestionMenu.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/domain/QuestionToday.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/domain/QuestionTodayAnswer.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/domain/SysToken.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/domain/UserCorrectQuestion.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/domain/UserExportLog.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/domain/UserInfo.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/domain/UserIntegralLog.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/domain/UserInviteLog.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/domain/UserSignLog.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/domain/WxApp.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/domain/WxUser.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/repository/ExamInfoRepository.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/repository/ExamMenuRepository.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/repository/QuestionInfoRepository.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/repository/QuestionMenuRepository.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/repository/QuestionTodayAnswerRepository.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/repository/QuestionTodayRepository.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/repository/SysTokenRepository.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/repository/UserCorrectQuestionRepository.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/repository/UserExportLogRepository.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/repository/UserInfoRepository.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/repository/UserIntegralLogRepository.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/repository/UserInviteLogRepository.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/repository/UserSignLogRepository.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/repository/WxAppRepository.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/repository/WxUserRepository.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/rest/ExamInfoController.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/rest/ExamMenuController.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/rest/QuestionInfoController.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/rest/QuestionMenuController.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/rest/QuestionTodayAnswerController.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/rest/QuestionTodayController.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/rest/SysTokenController.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/rest/UserCorrectQuestionController.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/rest/UserExportLogController.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/rest/UserInfoController.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/rest/UserIntegralLogController.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/rest/UserInviteLogController.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/rest/UserSignLogController.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/rest/WxAppController.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/rest/WxUserController.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/ExamInfoService.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/ExamMenuService.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/QuestionInfoService.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/QuestionMenuService.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/QuestionTodayAnswerService.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/QuestionTodayService.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/SysTokenService.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/UserCorrectQuestionService.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/UserExportLogService.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/UserInfoService.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/UserIntegralLogService.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/UserInviteLogService.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/UserSignLogService.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/WxAppService.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/WxUserService.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/dto/ExamInfoDto.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/dto/ExamInfoQueryCriteria.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/dto/ExamMenuDto.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/dto/ExamMenuQueryCriteria.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/dto/QuestionInfoDto.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/dto/QuestionInfoQueryCriteria.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/dto/QuestionMenuDto.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/dto/QuestionMenuQueryCriteria.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/dto/QuestionTodayAnswerDto.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/dto/QuestionTodayAnswerQueryCriteria.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/dto/QuestionTodayDto.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/dto/QuestionTodayQueryCriteria.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/dto/SysTokenDto.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/dto/SysTokenQueryCriteria.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/dto/UserCorrectQuestionDto.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/dto/UserCorrectQuestionQueryCriteria.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/dto/UserExportLogDto.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/dto/UserExportLogQueryCriteria.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/dto/UserInfoDto.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/dto/UserInfoQueryCriteria.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/dto/UserIntegralLogDto.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/dto/UserIntegralLogQueryCriteria.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/dto/UserInviteLogDto.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/dto/UserInviteLogQueryCriteria.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/dto/UserSignLogDto.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/dto/UserSignLogQueryCriteria.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/dto/WxAppDto.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/dto/WxAppQueryCriteria.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/dto/WxUserDto.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/dto/WxUserQueryCriteria.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/impl/ExamInfoServiceImpl.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/impl/ExamMenuServiceImpl.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/impl/QuestionInfoServiceImpl.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/impl/QuestionMenuServiceImpl.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/impl/QuestionTodayAnswerServiceImpl.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/impl/QuestionTodayServiceImpl.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/impl/SysTokenServiceImpl.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/impl/UserCorrectQuestionServiceImpl.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/impl/UserExportLogServiceImpl.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/impl/UserInfoServiceImpl.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/impl/UserIntegralLogServiceImpl.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/impl/UserInviteLogServiceImpl.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/impl/UserSignLogServiceImpl.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/impl/WxAppServiceImpl.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/impl/WxUserServiceImpl.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/mapstruct/ExamInfoMapper.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/mapstruct/ExamMenuMapper.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/mapstruct/QuestionInfoMapper.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/mapstruct/QuestionMenuMapper.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/mapstruct/QuestionTodayAnswerMapper.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/mapstruct/QuestionTodayMapper.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/mapstruct/SysTokenMapper.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/mapstruct/UserCorrectQuestionMapper.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/mapstruct/UserExportLogMapper.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/mapstruct/UserInfoMapper.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/mapstruct/UserIntegralLogMapper.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/mapstruct/UserInviteLogMapper.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/mapstruct/UserSignLogMapper.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/mapstruct/WxAppMapper.java
git checkout siival-system/src/main/java/com/siival/bot/modules/bsc/service/mapstruct/WxUserMapper.java
git add siival-system/

rm -rf siival-common/src/main/java/com/siival/bot/annotation/ExcelColumn.java
rm -rf siival-common/src/main/java/com/siival/bot/dto/QuestionAttributes.java
rm -rf siival-common/src/main/java/com/siival/bot/utils/ExcelUtils.java
rm -rf siival-common/src/main/java/com/siival/bot/utils/JacksonUtil.java
rm -rf siival-system/src/main/java/com/siival/bot/modules/bsc/rest/LilliaFile1Controller.java
rm -rf siival-system/src/main/java/com/siival/bot/modules/bsc/service/file/dto/Exam1.java
rm -rf siival-system/src/main/java/com/siival/bot/modules/bsc/rest/LilliaFileBatch1Controller.java
```