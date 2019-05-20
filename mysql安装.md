# Mysql安装
```bash
# pull image
docker pull mysql:latest
# run
docker run --name mysql-mstc -v F:/codeHub/docker/seumstc/data:/data -e MYSQL_ROOT_PASSWORD=123456 -d -i -p 3310:3306 --restart=always  mysql:latest
```
### 解决mysql Client does not support authentication protocol requested by server; consider upgrading MySQL错误
```sql
ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY '123456';
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY '123456';
SELECT plugin FROM mysql.user WHERE User = 'root';
```