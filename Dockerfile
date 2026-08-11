FROM ubuntu/jre:25-26.04_stable

LABEL maintainer="bogdan"

WORKDIR /app

COPY target/*.jar app.jar

ENTRYPOINT ["java", "-jar"]
CMD ["app.jar"]