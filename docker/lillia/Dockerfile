FROM openjdk:8-jre
RUN fc-cache
RUN fc-list
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' > /etc/timezone
COPY application.yml application.yml
COPY lillia.jar lillia.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","lillia.jar"]