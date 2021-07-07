FROM adoptopenjdk:14-jre-hotspot
WORKDIR /opt/app
ADD maven/${project.artifactId}-${project.version}.jar \
  /opt/app/streams-demo.jar
CMD ["java", "-jar", "/opt/app/streams-demo.jar"]