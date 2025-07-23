# Etapa 1: Construção
FROM maven:3.9.6-amazoncorretto-21 AS build
WORKDIR /app

# Copiar todos os arquivos pom.xml
COPY pom.xml .
COPY rms-clean-core/pom.xml ./rms-clean-core/
COPY rms-clean-infra/pom.xml ./rms-clean-infra/

# Copiar o restante do código-fonte necessário para resolver as dependências
RUN mvn dependency:go-offline

# Copiar o restante do código-fonte
COPY rms-clean-core/src ./rms-clean-core/src
COPY rms-clean-infra/src ./rms-clean-infra/src

# Construir o aplicativo
RUN mvn clean package -DskipTests

# Etapa 2: Execução
FROM amazoncorretto:21
WORKDIR /app

# Copiar o JAR construído da etapa anterior
COPY --from=build /app/rms-clean-infra/target/*.jar /app/rms-clean-infra.jar
ENTRYPOINT ["java", "-jar", "rms-clean-infra.jar"]