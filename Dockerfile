FROM openjdk:11
EXPOSE 8080
ADD target/an-api-for-tappter.jar an-api-for-tappter.jar
ENTRYPOINT ["java","-jar","/an-api-for-tappter.jar"]