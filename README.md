# Spring Security Demo

## Frontend for CORS test

[auth0-react-samples](https://github.com/auth0-samples/auth0-react-samples)

## Configure Environment Variables

```bash
export AUTH0_DOMAIN="https://dev-xv6hd61k.us.auth0.com"
export AUTH0_ISSUER_URI="${AUTH0_DOMAIN?}/"
export AUTH0_SYSTEM_API="${AUTH0_DOMAIN?}/api/v2/"
export AUTH0_TOKEN_API="${AUTH0_DOMAIN?}/oauth/token"
export AUTH0_CLIENT_ID="CLIENT_ID_HERE"
export AUTH0_CLIENT_SECRET="CLIENT_SECRET_HERE"
```

## Get a JWT Token

```bash
cat <<EOF > /tmp/payload.json
{
    "client_id": "${AUTH0_CLIENT_ID?}",
    "client_secret": "${AUTH0_CLIENT_SECRET?}",
    "audience": "${AUTH0_SYSTEM_API?}",
    "grant_type": "client_credentials"
}
EOF

jq . /tmp/payload.json

curl \
  --silent \
  --request POST \
  --url ${AUTH0_TOKEN_API?} \
  --header "Content-type: application/json" \
  --data @/tmp/payload.json \
  --output /tmp/access-token.json

jq . /tmp/access-token.json

export TOKEN="Bearer $(jq -r .access_token /tmp/access-token.json)"
```

## Acessing a Public Endpoint

```bash
curl \
  --request POST http://localhost:8080/api/save \
  --header "Content-type: application/json" \
  --data '{
    "name": "John",
    "age": 43
  }'

```

## Acessing a Private Endpoint

```bash
curl \
  --include \
  http://localhost:8080/api/private

curl \
  --include \
  --header "Authorization: ${TOKEN?}" \
  http://localhost:8080/api/private
```
