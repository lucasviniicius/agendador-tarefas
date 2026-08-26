# Agendador de Tarefas 🗓️

Aplicação Java com Spring Boot voltada para a gestão e agendamento de tarefas, contando com rotas RESTful para gerenciamento completo do ciclo de vida das tarefas, validações de segurança e integração contínua (CI/CD) automatizada via GitHub Actions.

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java (JDK 17+)
* **Framework:** Spring Boot
* **Gerenciador de Build:** Gradle
* **Integração Contínua (CI):** GitHub Actions (`gradle.yml`)

---

## 📋 Endpoints da API

### Gestão de Tarefas

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `POST` | `/tarefa` | Cadastra uma nova tarefa |
| `GET` | `/tarefa` | Busca tarefas associadas a um e-mail |
| `GET` | `/tarefa/eventos` | Consulta tarefas em um intervalo de datas/período |
| `PUT` | `/tarefa/{id}` | Atualiza completamente uma tarefa existente |
| `PATCH` | `/tarefas{id}/status` | Atualiza o status de uma tarefa |
| `DELETE` | `/tarefas{id}` | Remove uma tarefa pelo ID |

---

## 🔄 Pipeline de CI/CD (GitHub Actions)

O projeto conta com um workflow configurado em `.github/workflows/gradle.yml` que valida automaticamente:
- O acionamento em cada **Pull Request** direcionado às branches principais (`develop` / `master`).
- Compilação do código Java e verificação dos builds via Gradle.
- Execução automatizada da suíte de testes unitários e de integração antes de permitir o merge.

---

## Autor

Lucas Vinícius
