FROM adoptopenjdk/openjdk11:latest
COPY user-managerment-app/build/libs/user-managerment-app-0.0.3-SNAPSHOT.jar /home/user-managerment-app-0.0.3-SNAPSHOT.jar
CMD ["java","-jar","/home/user-managerment-app-0.0.3-SNAPSHOT.jar"]
EXPOSE 8601
#docker run -p 8601 --name usermng userservice
#docker build -t userservice .
#docker ps -a
#docker rm nume_dreapta
