FROM adoptopenjdk/openjdk11:latest
COPY build/libs/AuthenticationService-0.0.2-SNAPSHOT.jar /home/AuthenticationService-0.0.2-SNAPSHOT.jar
CMD ["java","-jar","/home/AuthenticationService-0.0.2-SNAPSHOT.jar"]
EXPOSE 8605
#docker run -p 8605 --name usermng userservice
#docker build -t authservice .
#docker ps -a
#docker rm nume_dreapta
