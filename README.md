# Spring Security Demo

```bash
curl -i http://localhost:8080/greetings/hi

export PASSWORD=8c609a91-f9ee-4458-86c1-7df549935b2a
curl \
  -u user:${PASSWORD?} \
  -i http://localhost:8080/greetings/hi
```
