<h1>API para gerenciamento de uma  Livraria</h1>

<h4>Requisitos</h4>
<p>Java 8 ou superior
Maven 3 ou superior</p>

<details>
  <summary>Documentação da API</summary>

[Clique aqui para acessar a documentação da API](http://localhost:8080/swagger-ui.html)

</details>

<h3>Livros</h3>

| Método | Endpoint               | Descrição                                     |
| ------ | ----------------------| ---------------------------------------------|
| GET    | `/books/{id}`         | Obter um Livro pelo ID                       |
| PUT    | `/books/{id}`         | Atualizar um Livro                           |
| DELETE | `/books/{id}`         | Deletar um Livro                              |
| GET    | `/books`              | Obter livros de acordo com paginação          |
| POST   | `/books`              | Inserir um novo Livro                        |


<h3>Editoras</h3> 

| Método | Endpoint                      | Descrição                                 |
| ------ | ------------------------------| -----------------------------------------|
| PUT    | `/publishers/{id}`            | Atualizar uma Editora                     |
| DELETE | `/publishers/{id}`            | Deletar uma Editora                       |
| GET    | `/publishers`                 | Obter todas as Editoras                   |
| POST   | `/publishers`                 | Inserir uma nova Editora                  |
| GET    | `/publishers/name={name}`     | Obter uma Editora pelo NOME               |
| GET    | `/publishers/id={id}`         | Obter uma Editora pelo ID                 |


<h3>Autores</h3>

| Método | Endpoint               | Descrição                             |
| ------ | ----------------------| -------------------------------------|
| PUT    | `/authors/{id}`       | Atualizar um Autor                    |
| DELETE | `/authors/{id}`       | Deletar um Autor                      |
| GET    | `/authors`            | Obter todos os Autores                |
| POST   | `/authors`            | Inserir um novo Autor                 |
| GET    | `/authors/name={name}`| Obter um Autor pelo NOME              |
| GET    | `/authors/id={id}`    | Obter um Autor por ID                 |
