[![CI Actions Status](https://github.com/nicolaspucci1989/disney-api/workflows/ci/badge.svg)](https://github.com/nicolaspucci1989/disney-api/actions)

# disney-api
Challenge preaceleración alkemy.  
* API rest con operaciones crud.
* Spring boot, spring data, spring security.
* Base mysql.
* Busquedas con especificaciones y metamodelo.

### Levantar container sql
`docker run --name mysql -e MYSQL_DATABASE=disney -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 -d mysql:latest`