#### Running a debugger inside docker:
`docker run -e "JAVA_OPTS=-agentlib:jdwp=transport=dt_socket,address=5005,server=y,suspend=n" -p 8080:8080 -p 5005:5005 -t org.wachowiak/math-service-docker`

#### Passing env variables into docker
`docker run -e "SPRING_PROFILES_ACTIVE=dev" -p 8080:8080 -t org.wachowiak/math-service-docker`
