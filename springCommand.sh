#!/bin/bash

mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=8080 --app.generator.enabled=false --app.write.enabled=true"
