FROM openjdk:11

ENV APPNAME spring-camel-file-mover

COPY ./target/${APPNAME}.jar ${APPNAME}.jar
COPY ./config/ /config

ENTRYPOINT java -jar ${APPNAME}.jar