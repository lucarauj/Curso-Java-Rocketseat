<h2 align="center">
  <img width="550px" src="https://github.com/lucarauj/Curso-Java-Rocketseat/blob/main/Curso%20Java%20-%20Rocketseat.jpg">
</h2>

<br>

## PROJETO TO-DO LIST

- AULA 1: Construção back-end de uma aplicação de To-Do List

- AULA 2: Integração com Banco de Dados

- AULA 3: Implementando Autenticação

- AULA 4: Atualizando tarefas e validação de rotas

- AULA 5: Deploy do Back-End

<br>

## DEPENDÊNCIAS

- Spring Web
- Lombok
- Spring Data JPA
- H2 Database
- Bcrypt

<br>

## ANOTAÇÕES

- @Autowired
- @Column
- @Component
- @ControllerAdvice
- @Data
- @Entity
- @ExceptionHandler
- @GeneratedValue
- @GetMapping
- @Id
- @PathVariable
- @PostMapping
- @RestController
- @RequestMapping
- @RequestBody

<br>

## DEPLOY NO [RENDER](https://render.com/)

- Criação do file Dockerfile:

<br>

>*FROM ubuntu:latest AS build*

>*RUN apt-get update*

>*RUN apt-get install openjdk-17-jdk -y*

>*COPY . .*

>*RUN apt-get install maven -y*

>*RUN mvn clean install*

>*FROM openjdk:17-jdk-slim*

>*EXPOSE 8080*

>*COPY --from=build /target/todolist-0.0.1-SNAPSHOT.jar app.jar*

>*ENTRYPOINT [ "java", "-jar", "app.jar" ]*

<br>

- mvn clean install

<br>

>*Para que o Deploy seja concluído com êxito, o diretório /target, o Dockerfile e as demais pastas da aplicação devem estar na pasta principal do repositório.*

<br>

## DEPLOY

<img width="1200px" src="https://github.com/lucarauj/Curso-Java-Rocketseat/blob/main/images/deploy.png">

<br>

## CONSUMINDO A URL APÓS DEPLOY

<img width="900px" src="https://github.com/lucarauj/Curso-Java-Rocketseat/blob/main/images/postman-deploy.png">

<br>

# 👨🏼‍🎓 ALUNO

Lucas Araujo

<a href="https://www.linkedin.com/in/lucarauj"><img alt="lucarauj | LinkdeIN" width="40px" src="https://user-images.githubusercontent.com/43545812/144035037-0f415fc7-9f96-4517-a370-ccc6e78a714b.png" /></a>
