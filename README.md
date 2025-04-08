#  Task Manager API

### 📝 Descrição

Este é um projeto de API RESTful desenvolvida com Spring Boot para gerenciamento de tarefas. A aplicação permite que usuários criem, leiam, atualizem e excluam tarefas de forma simples e segura.

## ⚙️ Funcionalidades principais
🔐 Autenticação e autorização com JWT:

Login seguro com geração de token

Rotas protegidas para operações de tarefa

✅ CRUD completo de tarefas:

Criar, buscar, atualizar e deletar tarefas

Listagem de tarefas por usuário autenticado

📁 Documentação interativa com Swagger/OpenAPI

🧪 Testes unitários com JUnit e Mockito

📦 Integração com banco de dados PostgreSQL

## 🛠️ Tecnologias utilizadas
Java 17

Spring Boot

Spring Security

JWT (JSON Web Token)

PostgreSQL

JUnit 5 / Mockito

Swagger (OpenAPI)


## 🚀 Como executar o projeto localmente

### Pré-requisitos
- Java 17
- Maven
- PostgreSQL

1. Clone o repositório
``` bash
git clone https://github.com/seu-usuario/taskmanager.git
```

2. Configure o application.properties com os dados do banco (ou use o Docker para isso)

3. Execute o projeto:
 ``` bash
./mvnw spring-boot:run
```

5. Acesse o Swagger em:
``` bash
http://localhost:8080/swagger-ui.html
```

## 👨‍💻 Autor

Desenvolvido por [Fernando](https://www.linkedin.com/in/fernandolopess/)  
Estudante de Sistemas de Informação | Foco em desenvolvimento Java e Spring Boot
