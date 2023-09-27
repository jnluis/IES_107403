# Lab1_2

## Ex2

**pom.xml** - Ficheiro XML que tem toda a informação sobre o projeto e os detalhes de configuração usados pelo Maven. </br>
Contém default values para a maioria dos projetos.  Os erros de dependências estão aqui!</br> 
**Maven Archetype** - Modelo base do qual é gerado um projeto Maven com determinadas caraterísticas.</br>
**GroupID** - pt.ua.ies </br>
**artifactID**- É mais o nome da pasta, tipo Lab1_2 </br>
Mas também pode ser o que diz no guião, tipo MyWeatherRadar 

### **Alguns Comandos Maven**

**mvn package**: Obtém as dependências e gera o ficheiro JAR;

**mvn clean**: Limpa os ficheiros binários da pasta /target;

**mvn install** : Compila o projeto e instala-o no repositório local do Maven;

Para fazer o HHTP request, </br>
1. HTTP client, converter classes para JSON </br>
2. validar URL </br>
3. parsing JSON para objetos </br>

Muitos passos, podemos utilizar o RetroFit; </br>
O Retro fit é "A type-safe HTTP client for Android and Java" </br>
Temos de acrescentar essa dependência no POM.xml </br>
Mais info: https://square.github.io/retrofit/


O intellij dá para dar build no martelo verde e depois run. </br>
Mas se quisermos fazer pela CLI, podemos fazer: </br>
```java
mvn package // get dependecies, compiles the project and creates the jar </br>

mvn exec:java -Dexec.mainClass="pt.ua.ies.WeatherStarter" //adapt to match your own package structure and class name
``` 

Passar um argumento ao Maven
```java
mvn exec:java -Dexec.mainClass="pt.ua.ies.WeatherStarter" -Dexec.args="1010500"
```
Tirar o warning das Threads do Maven
```java
-Dexec.cleanupDaemonThreads=false
```
## Ex3 - Git

Ao adicionar o .gitignore, não esquecer de fazer o comando `git -rm --cached .` senão depois vão coisas que não queríamos para o remote

Ao usar o log4j2, para fazer os logs, meter sempre a sua configuração, ou seja, o seu ficheiro .xml dentro da pasta ``/src/main/resources``

É possível ter loggers diretamente numa consola, num ficheiro ou ambos. </br>
Para além disso, notar que existem ainda várias categorias de loggs:
- info;
- error;
- debug;
- framework;
- perf;
- trace.

Ter atenção à maneira como configuramos o XML. </br>

O comando ```git log --reverse --oneline``` é como o comando git log a que já estamos habituados, mas a flag `--reverse` é para aparecer primeiro no terminal o log mais antigo e a flag `--oneline` é apenas para aparecer a mensagem do commit + id. 

## Ex4 - Docker

### What is a container?
A container is a sandboxed process running on a host machine that is isolated from all other processes running on that host machine.

### What is an image?

A running container uses an isolated filesystem. This isolated filesystem is provided by an image, and the image must contain everything needed to run an application - all dependencies, configurations, scripts, binaries, etc. The image also contains other configurations for the container, such as environment variables, a default command to run, and other metadata.

**Dar build de uma imagem:**
```bash 
docker build -t getting-started .
```
A flag `-t` é para dar o nome á image e o `.` é para o Docker procurar pelo Dockerfile dentro do diretório atual.

**Correr o container:**
```bash
docker run -dp 127.0.0.1:3000:3000 getting-started
```
A flag `-d` é para o container correr no background enquanto que a flag `-p` pede os parametros `HOST:CONTAINER`, ou seja, especifica-se o endereço em que o host corre e a porta do container

**Listar todos os containers que estão a correr:**
```bash
docker ps
```

**Remover um container:** </br>
Para ver o id, fazer docker ps
```bash
docker stop <the-container-id>
docker rm <the-container-id>
```
Fazendo num único comando:
```bash
docker rm -f <the-container-id>
```

**Aceder a um container**
```bash
docker exec <container-id> cat /data.txt # neste caso o cat era para ver o resultado de uma operação realizada 
```

**Listar os ficheiros na root do container:**
```bash
docker run -it ubuntu ls / # neste caso era um container de ubuntu
```

Para guardar a informação no host e esta não ser perdida quando se desliga o container, é necessário usar Volumes.
Existem 2 tipos principais de volumes, para já vamos usar *Volume Mounts*.

**Criar um volume**
```bash
docker volume create todo-db # todo-db é o nome do volume
```
**Agora para correr o container e usar o volume:**
```bash
docker run -dp 127.0.0.1:3000:3000 --mount type=volume,src=todo-db,target=/etc/todos getting-started # getting-started é o nome da imagem
```

**Ver onde o Docker guarda os dados do volume**
```bash
docker volume inspect # Vai vir um parametro Mountpoint que é a localização verdadeira dos dados no disco. Na maioria dos casos, é necessário ter root access para chegar a este diretório pelo host
```

**Docker Compose**

Ter sempre a certeza que o Dockerfile não tem extensão associada!</br>
Temos de criar um ficheiro .yaml, para definir os serviços

**Começar a correr a app**
```bash
docker-compose up
```

**Listar imagens locais**
```bash
docker image ls
```

**Parar de correr a app**
```bash
docker-compose down ou fazer Ctrl + C
```

Podemos também adicionar uma *Bind mount* ao ficheiro .yaml e se estivermos a usaar Flask, podemos fazer ```FLASK_DEBUG: "true"``` e assim quando se fizer reload da página, podemos logo ver as alterações. </br>
O docker compose tem muitos mais comandos, por isso o melhor é fazer `--help` para ver todos.

**Parar o docker e apagar os dados do volume**
```bash
docker-compose down --volumes
```

## Ex5 

Para colocar um ficheiro java no Docker, seguir este tutorial: </br>
https://www.baeldung.com/java-dockerize-app

De notar que para o Maven dar build e package do runnable jar, é necessário fazer:
``` 
maven package
```

**Build da imagem Docker:** </br>
De notar que a flag `-t` especifica o <nome>:<tag> 
```bash
docker image build -t docker-java-jar:latest .
```

**Run da imagem:**
```bash
docker run docker-java-jar:latest
```