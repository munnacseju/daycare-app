# Server Setup

1. At first taking a VM in Microsoft Azure 
2. sudo apt update
3. sudo apt upgrade
4. install docker and docker-compose (https://docs.docker.com/engine/install/)
5. mkdir backend-app
6. cd backend-app
7. nano Dockerfile
8. Write their:

```
FROM openjdk:12-jdk-alpine
ARG JAR_FILE=*.war
COPY ${JAR_FILE} app.war 
ENTRYPOINT ["java","-jar","/app.war"]
```

9. Create a docker network: ```sudo docker network create -d bridge daycare-app-network```
10. Create a docker mysql container: ```sudo docker run --name=mysql --network=daycare-app-network --network-alias=backend-database-network -e MYSQL_ROOT_PASSWORD=PASSWORD -d mysql```



11. Check properties files for server
12. Packaging the app: ```./mvnw package -DskipTests=true```
13. Sending app to server: ```scp -i /path/to/.pem target/backend-0.0.1-SNAPSHOT.war USER@HOST:/home/USER/backend-app```

14. To know about docker running: ```sudo docker ps -a```
15. To enter into a docker container: ```sudo docker exec -it CONTAINER_ID bash```
16. Enter into the mysql docker
17. mysql -u root -p
18. create database daycare_app_db;
19. Build backend-app docker image: ```sudo docker build -t backend-app . ```
20. Run the docker image: ```sudo docker run --name=backend-app -p 80:8080 --network=daycare-app-network --network-alias=backend-app-network -d backend-app```













At first taking a VM in Microsoft Azure sudo apt update sudo apt upgrade

install docker and docker-compose https://docs.docker.com/engine/install/debian/ https://docs.docker.com/compose/install/#install-compose-on-linux-systems mkdir backend-app cd backend-app nano Dockerfile

FROM openjdk:12-jdk-alpine ARG JAR_FILE=*.war COPY ${JAR_FILE} app.war ENTRYPOINT ["java","-jar","/app.war"]

Creating a docker network: sudo docker network create -d bridge team-tracker-network

Creating a docker mysql container: sudo docker run --name=mysql --network=team-tracker-network --network-alias=backend-database-network -e MYSQL_ROOT_PASSWORD=hello -d mysql

Packaging the app: ./mvnw package -Dmaven.test.skip=true

Sending app to server: scp -i ../../team-tracker-vm_key.pem target/backend-0.0.1-SNAPSHOT.war sajjad@team-tracker.servehttp.com:/home/sajjad/backend-app

To enter into a docker container: sudo docker exec -it 6d18b2df5890 bash

Enter into mysql: mysql -u root -p

Create Database: create database team_tracker_db;

Building backend-app docker image: sudo docker build -t backend-app . sudo docker run --name=backend-app -p 80:8080 --network=team-tracker-network --network-alias=backend-app-network -d backend-app

To see logs of a docker container: sudo docker logs e3f3eaaf2edf

To kill a docker container: sudo docker kill e3f3eaaf2edf

To remove a docker container: sudo docker rm e3f3eaaf2edf

To check the running (with killed) docker containers: sudo docker ps -a

To enter into a docker container: sudo docker exec -it 6d18b2df5890 bash