# Lab2_3

## Ex1

### Leitura Sugerida 

Spring Boot vs. Spring MVC vs. Spring

Spring Boot não compete com Spring ou Spring MVC. Torna mais fácil usá-las. </br>

A feature mais importante do Spring FrameWork é a sua Injenção de Dependências, também chamada de Inversão de Controlo (*IOC- Inversion of Control*). </br>
Quando o IOC é usado devidamente, podemos então desenvolver aplicações com pouco *coupling*, o que torna a aplicação mais fácil de ser testada. </br>

O Spring MVC oferece assim uma forma *decoupled* de desenvolver app's web, usando conceito como *Dispatcher Servlet, ModelAndView, and View Resolver*.

Assim, o Spring Boot vem resolver o problema das muitas configurações que são necessárias para o Spring e o Spring MVC. </br>
O Spring Boot tem vários Starter Projects, que trazem já vários componentes a ele associados, dependendo da opção escolhida. </br>

Spring Initializr é a forma mais rápida de criar um projeto Spring Boot

### C) Respostas 

- No construtor da classe UserController, usamos a anotação *@Autowired*, que permite ativar a injeção da dependência do objeto implicitamente. Internamente, é usado injeção através dos *setters* ou construtores.

- Os métodos são o *save()*, *findAll()*, *findById()* e o *delete()*, que são definidos pela classe *CrudRepository*, que por sua vez vai ser extendida pela classe *UserRepository*.

- Os dados estão a ser guardados na *H2database*, que é, por *default* uma base de dados "in-memory".

- A regra que define que o endereço de email não pode ser vazio, está definido na classe *User*, onde colocámos a anotação *@NotBlank* na declaração do atributo Email espeficicando que se trata de um atributo obrigatório

## Ex2

Vamos usar um Application Server em vez de um WebServer. </br>
Usamos o TomCat e em vez de fazermos download disto, metemos numa imagem através do  docker-compose. </br>
Para lançar as imagens, fazemos ```docker-compose up```

![Screenshot](Diffs.webp)

## Ex3 

