FROM openjdk:17-alpine
COPY target/ExamQuestionnaire-1.0-SNAPSHOT.jar application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]