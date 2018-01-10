FROM gradle:jdk-alpine

# ENV GRADLE_USER_HOME /home/gradle/application/
# WORKDIR $GRADLE_USER_HOME
COPY \
 build.gradle build.properties gradle.properties src ./
RUN gradle classes 

CMD ["gradle", "bootRun"]
