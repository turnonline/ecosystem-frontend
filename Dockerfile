FROM oracle/graalvm-ce:21.1.0-java11 as graalvm
RUN gu install native-image

COPY . /home/app/frontend
WORKDIR /home/app/frontend

RUN native-image --no-server --no-fallback --verbose -cp target/turnonline-ecosystem-frontend.jar

FROM frolvlad/alpine-glibc
RUN apk update && apk add libstdc++
EXPOSE 8080
COPY --from=graalvm /home/app/frontend/turnonline-ecosystem-frontend /app/turnonline-ecosystem-frontend
ENTRYPOINT ["/app/turnonline-ecosystem-frontend"]
