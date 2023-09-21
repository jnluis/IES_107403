# Lab1_2

## Ex2

GroupID - pt.ua.ies </br>
artifactID- É mais o nome da pasta, tipo Lab1_2 </br>
Mas também pode ser o que diz no guião, tipo MyWeatherRadar 

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

## Ex3

Ao adicionar o .gitignore, não esquecer de fazer o comando `git -rm --cached .` senão depois vão coisas que não queríamos para o remote