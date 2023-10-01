# Spring Security Demo

```bash
curl -i http://localhost:8080/greetings/hi

export PASSWORD=596ae797-54af-4839-9a25-e746ce9164c1
curl \
  -u user:${PASSWORD?} \
  -i http://localhost:8080/greetings/hi
```
