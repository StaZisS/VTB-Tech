FROM gradle:7.4-jdk17-alpine AS build
RUN mkdir -p /home/gradle/project
WORKDIR /home/gradle/project
COPY --chown=gradle:gradle . /home/gradle/project
RUN gradle build --no-daemon
CMD ["gradle", "run"]
EXPOSE 8086