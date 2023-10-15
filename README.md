# Backend

## Run application

```bash
git clone https://github.com/StaZisS/VTB-Tech.git
cd VTB-Tech
docker-compose up
```


## Tech-Stack
- SpringBoot for Java
- Docker 
- Microservice Architecture
- PostgreSQL
- Lombok
- Spring Security

##Service

### [ChatBot](ChatBot/src/main/java/com/example/chatbot/ChatBotApplication.java)

#### port: 8086

### [InfoService](InfoService/src/main/java/com/example/infoservice/InfoServiceApplication.java)

#### port: 8080

### [RecomendationService](RecomendationService/src/main/java/com/example/recomendationservice/RecomendationServiceApplication.java)

#### port: 8085

### [StatisticsService](StatisticsService/src/main/java/com/example/statisticsservice/StatisticsServiceApplication.java)

#### port: 8081

### [LoadGenerator](LoadGenerator/src/main/java/com/example/loadgenerator/LoadGeneratorApplication.java)

#### port: 8082

## Swagger (развернут локально, сначала поднимите все контейнеры) 

### [ChatBot](http://localhost:8086/swagger-ui.html)

### [InfoService](http://localhost:8080/swagger-ui.html)

### [RecomendationService](http://localhost:8085/swagger-ui.html)

### [StatisticsService](http://localhost:8081/swagger-ui.html)
