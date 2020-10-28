FROM adoptopenjdk:8-jre-openj9
ARG JAR_FILE

USER root

ENV SPRING_BOOT_HOME /usr/spring-boot-app
ENV SPRING_BOOT_LOG /var/log/spring-boot-app
ENV	SPRING_BOOT_USER sbapp
ENV	SPRING_BOOT_GROUP sbapp
ENV	SPRING_BOOT_ARTIFACT_NAME spring-boot-application.jar
ENV	JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -XX:MaxRAMFraction=1

ENV TZ America/Mexico_City
ENV LANG es_MX.UTF-8
ENV LANGUAGE es_MX.UTF-8
ENV LC_ALL es_MX.UTF-8

COPY ${JAR_FILE} ${SPRING_BOOT_HOME}/${SPRING_BOOT_ARTIFACT_NAME}

RUN apt-get update \
	&& apt-get install -y --no-install-recommends tzdata \
	&& locale-gen es_MX.UTF-8 && update-locale \
	&& addgroup --system ${SPRING_BOOT_GROUP} && adduser --system ${SPRING_BOOT_USER} --ingroup ${SPRING_BOOT_GROUP} \ 
	&& chown -R ${SPRING_BOOT_USER} ${SPRING_BOOT_HOME} \
	&& chgrp -R ${SPRING_BOOT_GROUP} ${SPRING_BOOT_HOME} \
	&& mkdir -p ${SPRING_BOOT_LOG} \
	&& chown -R ${SPRING_BOOT_USER} ${SPRING_BOOT_LOG} \
	&& chgrp -R ${SPRING_BOOT_GROUP} ${SPRING_BOOT_LOG}

WORKDIR $SPRING_BOOT_HOME
USER $SPRING_BOOT_USER

ENTRYPOINT java $JAVA_OPTS ${JAVA_OPTS_MEM} -jar ${SPRING_BOOT_HOME}/${SPRING_BOOT_ARTIFACT_NAME} ${SPRING_OPTS}