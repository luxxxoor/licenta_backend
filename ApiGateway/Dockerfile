FROM adoptopenjdk/openjdk11:latest
COPY api-gateway-app/build/libs/api-gateway-app-0.0.1-SNAPSHOT.jar /home/api-gateway-app-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","/home/api-gateway-app-0.0.1-SNAPSHOT.jar"]
EXPOSE 8604
#docker run -p 8604 --name apigateway --link registry apigateway
#docker build -t apigateway .
#docker ps -a
#docker rm nume_dreapta
