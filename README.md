
this project use gradle wrapper.

tasks:
------
|task|description|
| --- | --- |
| compile          | compile sources |
| build            | create a tar containing the helloword application |
| dockerBuildImage | create a docker image of the project |
| composeUp        | start the environment (based on docker-compose.yml) |
| composeDown       | stop the environmentall |

To run integration test use :
```java
test.doFirst {
    // exposes "${serviceName}_HOST" and "${serviceName}_TCP_${exposedPort}" environment variables
    // for example exposes "WEB_HOST" and "WEB_TCP_80" environment variables for service named `web` with exposed port `80`
    dockerCompose.exposeAsEnvironment(test)
    // exposes "${serviceName}.host" and "${serviceName}.tcp.${exposedPort}" system properties
    // for example exposes "web.host" and "web.tcp.80" system properties for service named `web` with exposed port `80`
    dockerCompose.exposeAsSystemProperties(test)
    // get information about container of service `web` (declared in docker-compose.yml)
    def webInfo = dockerCompose.servicesInfos.web
    // pass host and exposed TCP port 80 as custom-named Java System properties
    systemProperty 'myweb.host', webInfo.host
    systemProperty 'myweb.port', webInfo.ports[80]
}
```

Note that the port 27017 of the mongodb machine is exposed only for debugging purpose.