FROM gradle:jdk
COPY \
 build.gradle build.properties gradle.properties ./
COPY \
 src ./src
RUN gradle classes
CMD ["gradle", "bootRun"]
