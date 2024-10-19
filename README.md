# Caixa


### Premissas

1. Foi utilizada a versão 17 do Java:  
```bash
-> % java -version
openjdk version "17.0.12" 2024-07-16
OpenJDK Runtime Environment (build 17.0.12+7)
OpenJDK 64-Bit Server VM (build 17.0.12+7, mixed mode, sharing)
```

2. Foi utilizado o `maven` para o gerenciamento de dependências:
```bash
-> % mvn -version
Apache Maven 3.9.9 (8e8579a9e76f7d015ee5ec7bfcdc97d260186937)
```

3. Foi utilizado o Docker e o `docker-compose` para criar um container que servirá de banco de dados. Os dados de acesso a tal banco se encontram no arquivo `docker-compose-yml`
```bash
-> % docker --version
Docker version 27.2.1, build 9e34c9bb39
-> % docker-compose --version
Docker Compose version 2.29.5
```

4. Foi utilizado o MariaDB como banco de dados, na versão 15.1:
```bash
root@b894a205f474:/# mysql --version
mysql  Ver 15.1 Distrib 10.11.8-MariaDB, for debian-linux-gnu (x86_64) using  EditLine wrapper
```
