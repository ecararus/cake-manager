
[Cake Manager Micro Service (fictitious)](https://ecararus.github.io/cake-manager/) [![Build Status](https://travis-ci.org/ecararus/cake-manager.svg?branch=master)](https://travis-ci.org/ecararus/cake-manager)
=======================================

## About:
Implementation for Smal microservice see [requirements](https://github.com/ecararus/cake-manager/wiki/Requirements).

All requirements has been implemented the api is documented and exposed under port 8282.
To see documentation and try it please visit: http://127.0.0.1:8282/swagger-ui.html

To start application:
    i) mvn clean package spring-boot:run
    i) or run as jar

##Service Discovery:

    Consul (Optional): https://www.consul.io
    I have used consul as SD/DNS because i found it fast/stable/easy to use.
    How to install: https://www.consul.io/intro/getting-started/install.html
    How to use https://www.consul.io/intro/getting-started/agent.html
    Finally open http://localhost:8500 you should see subscribed cack manager application.


## Circuit breaker:

    In case if other service providers (e.g. hart88) can't provide reliable service or will be removed,
    than cake-manager will not propagate failure to service users.
    For more information please have a look at: https://martinfowler.com/bliki/CircuitBreaker.html
    As implementation Netflix Hystrix has been chosen, more information here|https://github.com/Netflix/Hystrix/wiki/How-it-Works.


## Monitoring:

    Hystrix dashboard (Oprional) https://github.com/ecararus/hystrix-dashboard
    To monitor the app status Hystrix dashboard has been selected.
    To configure dashboard:
        * Run commands from https://github.com/ecararus/hystrix-dashboard
        * Open : http://localhost:7979
        * Monitor: http://127.0.0.1:8282/manage/hystrix.stream

    Application management api is available: http://127.0.0.1:8282/manage or use swagger_ui http://127.0.0.1:8282/swagger-ui.html

## System requirements:
 - Java 1.8
 - Maven 3.3.3
 - Free ports: 8500(consul), 7979(Hystrix), 8282(Jetty)

## DISCLAIMER:
Purpose of project is only educational.
This project should not be used for any commercial purpose.
This work is licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License.
http://creativecommons.org/licenses/by-nc-sa/4.0/.

## Author:
Eugeniu Cararus
cararuseugeniu@gmail.com
