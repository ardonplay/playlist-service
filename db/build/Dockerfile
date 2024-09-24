FROM cassandra:latest

ENV LANG C.UTF-8

RUN mkdir "docker-entrypoint-initdb.d"

COPY entrypoint.sh /entrypoint.sh

RUN ["chmod", "+x", "/entrypoint.sh"]

ENTRYPOINT ["/entrypoint.sh"]
CMD ["cassandra", "-f"]
