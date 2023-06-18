FROM postgres:9.6-alpine

RUN apk add --update iputils
RUN apk add --update htop

COPY setup-secundary.sh /docker-entrypoint.sh

RUN chmod +x /docker-entrypoint.sh

USER postgres

ENTRYPOINT ["/docker-entrypoint.sh"]

CMD ["postgres"]
