# ビルド用：MavenとJava 25を使用
FROM maven:3.9.9-eclipse-temurin-25-alpine AS build
COPY . .
RUN mvn clean package -DskipTests

# 実行用：Java 25の実行環境
FROM eclipse-temurin:25-jre-alpine
COPY --from=build /target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]