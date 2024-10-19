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

### Uso

Para executar a aplicação, os passos necessários são:

1. Realizar o _clone_ do projeto:
```bash
git clone https://github.com/SadiJr/caixa.git
```

2. Acessar o diretório do projeto:
```bash
cd caixa
```

3. Subir o banco de dados:
```bash
docker-compose up
```

4. Realizar o _build_ do projeto:
```bash
mvn clean install
```

Isso irá criar o diretório `target`, onde ficará o `jar` da aplicação.

5. Executar a aplicação:
```bash
cd target
java -jar caixa-0.0.1-SNAPSHOT.jar
```

Isso irá subir a aplicação _back-end_ na porta 8080.

6. Criar usuário para acesso às APIs:    
Tal aplicação utiliza JWT para realizar a autenticação do usuário. Não existe um usuário padrão por _default_, sendo necessário criar um:
```bash
curl --location 'localhost:8080/user/register' \
--header 'Content-Type: application/json' \
--data '{
    "username": "<username>",
    "password": "<password>"
}'
```

7. Gerar o _token_ de acesso do usuário criado:
```bash
curl --location 'localhost:8080/user/login' \
--header 'Content-Type: application/json' \
--data '{
    "username": "<username>",
    "password": "<password>"
}'
```

O _output_ desse comando conterá o _token_ de autenticação do usuário. Tal _token_ tem duração fixa de 10 horas:
```bash
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6IlNhZGkiLCJleHAiOjE3MjkzNzc2MzZ9._iihLwuu7Y_JLO8ib40c7IvYHrSIOvQJjHdt963iSsM
```

8. Utilizar tal _token_ para acessar as demais APIs:
```bash
curl --location 'localhost:8080/api/cash/findAll' \
--header 'Authorization: Bearer <token>'
```

A UI usou o React, sendo seu passo para uso:

1. Instalar as dependências:
```bash
cd ui
npm install
```

2. Rodar a aplicação:
```bash
npm run dev
```

É importante frisar que a UI está totalmente incompleta, e requer diversas melhorias.
