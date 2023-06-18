FROM postgres:9.6-alpine

RUN apk add --update htop

COPY setup-primary.sh /docker-entrypoint-initdb.d/setup-primary.sh

RUN chmod 0666 /docker-entrypoint-initdb.d/setup-primary.sh
