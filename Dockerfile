# ステージ1: ビルド環境
# Spring Boot 4系を動かすために必要なMaven 3.9以上とJava 21が含まれるイメージを使用
FROM maven:3.9.6-eclipse-temurin-21 AS build

# 作業ディレクトリの設定
WORKDIR /app

# ソースコードをコピー
COPY . .

# Mavenでビルドを実行（テストをスキップして高速化）
RUN mvn clean package -DskipTests

# ステージ2: 実行環境
# 実行には軽量なJRE（Java実行環境）を使用
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# ビルドステージから作成されたJARファイルをコピー
# Spring Bootのビルド成果物は通常 target/ フォルダの中に作成されます
COPY --from=build /app/target/*.jar app.jar

# ポート8080を開放
EXPOSE 8080

# アプリケーションの実行
ENTRYPOINT ["java", "-jar", "app.jar"]