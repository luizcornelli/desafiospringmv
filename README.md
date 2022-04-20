# Desenvolvedor JAVA/SPRING Sênior

## Informações sobre o projeto

Um microsserviço que disponibiliza operações a partir de um Cliente, Cidade e Estado

Cadastro de Estado: 

* Nome

Cadastro de Cidade: 

* Nome
* Id Estado

Cadastro de Cliente: 

* Nome completo
* Sexo
* Data de nascimento
* Idade 
* Id Cidade onde mora

Opção de busca de Cidade por parâmetros: 

* nomeCidade = Busca pelo nome da Cidade. 

* nomeEstado = Busca pelo nome do Estado da Cidade.  

Opção de busca de Cliente por parâmetros: 

* id = Busca pelo id do Cliente. 

* nome = Busca pelo nome do Cliente.

### Tecnologias utilizadas

- Java 11
- Grandle
- Junit
- SpringBoot

### Ferramentas

- Postman (Teste de endpoints)
- Swagger (Geração de documentação)
- Git (Controle de versão)
- GitHub (Versionamento Remoto)

### Os testes unitarios estão disponiveis em:

src/test/java/com.desafiospringmv > DesafiomvspringApplicationTests

### API REST deve ser executada

src/main/java/com.product > DesafiomvspringApplication > Run as SpringBoot

### Como executar

O projeto conta com uma classe DesafiomvspringApplication dentro do módulo da API para startar a aplicação através do SpringInitializer

### Portas e endpoints

Endpoints Swagger-UI: http://localhost:8080/swagger-ui/index.html#/

Endpoint raiz Postman: http://localhost:8080/cidades/[operações]
	http://localhost:8080/estados/[operações]
	http://localhost:8080/clientes/[operações]

### Exemplo de arquivo application.properties externo 

	spring.datasource.url=jdbc:postgresql://localhost:5432/desafiospringmv
	spring.datasource.username=postgres
	spring.datasource.password=12345
	spring.database.driverClassName=org.postgresql.Driver

	server.port: 8080

