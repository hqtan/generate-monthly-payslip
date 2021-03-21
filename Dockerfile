FROM hseeberger/scala-sbt:11.0.10_1.4.9_2.13.5

WORKDIR /app

ADD src /app/src
ADD build.sbt /app/
ADD project/Dependencies.scala /app/project/
ADD project/build.properties /app/project/

RUN sbt package
ENTRYPOINT [ "scala", "/app/target/scala-2.13/generate-monthly-payslip_2.13-0.1.0-SNAPSHOT.jar" ]