# apiguard-ldap
Open APIs Guard LDAP Plugin

### API Doc
```markdown

/* Create API */
curl -X POST \
  http://localhost:8080/apiguard/apis \
  -H 'content-type: application/json' \
  -d '{
  "id": "f8157910-d857-11e6-8bc0-2d9f23b1a052",
  "creationDate": 1484178301729,
  "request_uri": "/google/(.*)/abc",
  "name": "google",
  "downstream_uri": "http://www.google.com"
  }'
  
/* Update API (downstream_uri) */
curl -X PATCH \
  http://localhost:8080/apiguard/apis \
  -H 'content-type: application/json' \
  -d '{
  "id": "f8157910-d857-11e6-8bc0-2d9f23b1a052",
  "creationDate": 1484178301729,
  "request_uri": "/google/(.*)/abc",
  "name": "google",
  "downstream_uri": "http://www.msn.com"
}'

/* Create client */
curl -X POST \
  http://localhost:8080/apiguard/clients \
  -H 'content-type: application/json' \
  -d '{"id":"Jason"}'

/* Add ldap auth */
curl -v -X POST http://localhost:8080/apiguard/clients/tesla/ldap-auth -H 'content-type: application/json' -d '{"request_uri":"/google/(.*)/[0-9]+", "ldap_url":"ldap://ldap.forumsys.com:389","admin_dn":"cn=read-only-admin,dc=example,dc=com","admin_Password":"password","user_base":"dc=example,dc=com","user_attribute":"uid","cache_expire_seconds":60}'

/* Invoke with ldap auth - [Authorization: ldap base64(username:password)] */
curl -v -X GET \
  http://localhost:8080/apiguard/apis/google/ho1/1 \
  -H 'Authorization: ldap dGVzbGE6cGFzc3dvcmQ='

```

