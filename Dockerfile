FROM openjdk:11

ENV APPNAME spring-camel-file-mover

COPY ./target/${APPNAME}.jar ${APPNAME}.jar
COPY ./config/ /config

VOLUME /logs
VOLUME /from
VOLUME /to

ENTRYPOINT java -jar ${APPNAME}.jar