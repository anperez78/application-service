# Application service

Basic REST service to create and retrieve applications from a relational DB (PostgreSQL).

### Prerequisities

Don't forget to check you have **Java 1.8** and **Gradle 2.14** installed and a great IDE 
(such as [IntelliJ IDEA](https://www.jetbrains.com/idea/) or [Atom](https://atom.io/)) 
to start working on it.

### Installing

```
$ git clone https://github.com/anperez78/application-service.git
$ cd application-service
$ ./generate.sh
$ ./run.sh
```

And there you have your service up and running.

## Running the tests

```
$ gradle test
```

## Built With

* Dropwizard - v1.0.0-rc4
* Postgresql driver - v9.1-901-1.jdbc4
* Gradle - v2.14

## Versioning

We use [SemVer](http://semver.org/) for versioning. 

## License

MIT
