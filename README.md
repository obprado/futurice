# README #

This application calculates expressions

This README will walk you through the basic set up of the project.

### How do I build the project? ###

```
cd futurice
mvn clean install
```

### Run it!

The first parameter is the port. Of example, if you fancy starting the server locally on port 80:

```
cd target
java -jar futurice-1.0-SNAPSHOT-jar-with-dependencies.jar 80
```

### How do I deploy to production?

Just commit to master, circleCI and heroku will take care of it. ;)

Look at `.circleci/config.yml` and `Procfile` if you want to se the `CI/CD pipeline as code`