FROM openjdk:11
COPY /build/libs/oasis-jiwa-ktor-0.0.1-all.jar /app/oasis-jiwa-ktor.jar
CMD ["java", "-jar", "/app/oasis-jiwa-ktor.jar"]