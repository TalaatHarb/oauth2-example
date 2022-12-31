FROM jboss/keycloak

USER root
RUN mkdir -p /files
RUN chmod 777 /files
USER jboss
COPY files/* /files/

ENV KEYCLOAK_USER_FILE /files/user.txt
ENV KEYCLOAK_PASSWORD_FILE /files/password.txt
ENV KEYCLOAK_IMPORT /files/uaa.json
ENV PORT 8180

CMD ["-Djboss.http.port=${PORT} --server-config=standalone.xml"] 