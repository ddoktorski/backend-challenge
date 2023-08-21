# backend-challenge

Coding challenge: Create a small web service that takes location and a search query as optional parameters and returns sorted and filtered results.

# Development

To run the app locally it is necessary to have api key credentials from Google Cloud Platfrom with "Geocoding API" enabled.

Then to run the app locally

```
API_KEY=<API_KEY> ./gradlew bootRun
```

To just build the app

```
./gradlew build
```

To run tests

```
./gradlew test
```

# Example request

http://localhost:8080/list?search=station&lat=48.5&lon=12.1
