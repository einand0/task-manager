#📝 Task Manager API

##Este é um projeto de API RESTful desenvolvida com Spring Boot para gerenciamento de tarefas. A aplicação permite que usuários criem, leiam, atualizem e excluam tarefas de forma simples e segura.

⚙️ Funcionalidades principais
🔐 Autenticação e autorização com JWT
Login seguro com geração de token
Rotas protegidas para operações de tarefa
✅ CRUD completo de tarefas
Criar, buscar, atualizar e deletar tarefas
Listagem de tarefas por usuário autenticado
📁 Documentação interativa com Swagger/OpenAPI
🧪 Testes unitários com JUnit e Mockito
📦 Integração com banco de dados PostgreSQL

  🛠️ Tecnologias utilizadas
Java 17
Spring Boot
Spring Security
JWT (JSON Web Token)
PostgreSQL
JUnit 5 / Mockito
Swagger (OpenAPI)

🚀 Como executar o projeto localmente
Pré-requisitos
Java 17
Maven
PostgreSQL (ou utilizar o container já configurado)

🚀 Como executar o projeto
1. Clone o repositório
git clone https://github.com/seu-usuario/taskmanager.git

2. Configure o application.properties com os dados do banco (ou use o Docker para isso)

3. Execute o projeto:
./mvnw spring-boot:run

4. Acesse o Swagger em:
http://localhost:8080/swagger-ui.html

