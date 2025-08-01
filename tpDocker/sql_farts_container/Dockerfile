FROM mysql:8.0

ENV MYSQL_ROOT_PASSWORD=prout
ENV MYSQL_DATABASE=fartDB


COPY init.sql /docker-entrypoint-initdb.d/

EXPOSE 3306



