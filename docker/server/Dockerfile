FROM openjdk:8.0.72

MAINTAINER "qianguodong" <qian.guodong5@zte.com.cn>

ENV APP_RUN=/var/run/app

ENV HTTP_PORT=8080

ENV DEBUG_PORT=8777

RUN mkdir -p ${APP_RUN}

COPY watchdog-server.war ${APP_RUN}

COPY run.sh ${APP_RUN}

RUN chmod 755 ${APP_RUN}/*.sh

EXPOSE ${HTTP_PORT} ${DEBUG_PORT}

WORKDIR $APP_RUN

CMD  ["./run.sh"]