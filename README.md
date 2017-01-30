
[Cake Manager Micro Service (fictitious)](https://ecararus.github.io/cake-manager/) [![Build Status](https://travis-ci.org/ecararus/cake-manager.svg?branch=master)}
=======================================

A summer intern started on this project but never managed to get it finished.


Requirements:

* By accessing the root of the server (/) it should be possible to list the cakes currently in the system.  This
 must be presented in an acceptable format for a human to read.

* It must be possible for a human to add a new cake to the server.

* By accessing an alternative endpoint (/cakes) with an appropriate client it must be possible to download a list of
 the cakes currently in the system as JSON data.

* Accessing the /cakes endpoint with a web browser must show the human presentation of the list of cakes.

* The /cakes endpoint must also allow new cakes to be created.


The developer assured us that some of the above is complete, but at the moment accessing the /cakes endpoint
 returns a 404, so getting this working should be the first priority.

There may be other bugs and mistakes, feel free to fix anything you find. Likewise, feel free to re-organise,
 refactor or re-write the project anyway you see fit.


Project Info
============

The project uses Maven and Servlet 3.0.

To run a server locally execute the following command:

`mvn jetty:run`

and access the following URL:

`http://localhost:8282/`


You can use any IDE you like, so long as the project can build and run with Maven.

The project loads some pre-defined data in to an in-memory database, which is acceptable for this exercise.  There is
 no need to create persistent storage.


Submission
==========


Please provide your version of this project as a zip or gzip.   Use Google Drive or some other file sharing service to
share it with us.

Alternatively, you can submit the location of a git repository (e.g. Github, BitBucket, etc).

Please also keep a log of the changes you make as a text file and provide this to us with your submission.

Good luck!
-------------------------------------------------------


## About:
Implementation of Waracle requirements described above.

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
