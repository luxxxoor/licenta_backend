FROM adoptopenjdk/openjdk11:latest
COPY build/RegistryServer-0.0.1-SNAPSHOT.jar /home/RegistryServer-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","/home/RegistryServer-0.0.1-SNAPSHOT.jar"]
EXPOSE 8600
#docker run -p 8600:8600 --name registry registryserver
#docker build -t registryserver .
#docker ps -a
#docker rm nume_dreapta