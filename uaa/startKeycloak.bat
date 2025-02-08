set /p KEYCLOAK_ADMIN=<files\user.txt
set /p KEYCLOAK_ADMIN_PASSWORD=<files\password.txt
docker run --name example-uaa -p 8180:8080 -e KEYCLOAK_ADMIN=%KEYCLOAK_ADMIN% -e KEYCLOAK_ADMIN_PASSWORD=%KEYCLOAK_ADMIN_PASSWORD% -v %cd%\files:/opt/keycloak/data/import -d quay.io/keycloak/keycloak:latest start-dev --import-realm --verbose