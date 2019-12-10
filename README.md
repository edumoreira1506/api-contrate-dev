# api-contrate-dev

This is one API of "Contrate Dev" academic project. The system uses Java, Spring Boot, H2 Database and is a test from subject 'Web Development IV' of course 'Web Systems' at ['UTFPR - Campus Guarapuava'](https://github.com/utfpr-gp) teached by [@ronifabio](https://github.com/ronifabio). (Essa é uma API do projeto acadêmico "Contrate Dev". O sistema usa Java, Spring Boot, Banco de dados H2 e é um teste da matéria de 'Desenvolvimento para WEB IV' do curso de 'Sistemas para Internet' na ['UTFPR - Campus Guarapuava'](https://github.com/utfpr-gp) ministrado por [@ronifabio](https://github.com/ronifabio).)

# Project requirements

- [x] The controllers should invoke one or more services (Os Controllers devem invocar um ou mais Services).
- [x] The services should invoke one or mone repositories (Os Services devem invocar um ou mais repositórios).
- [x] The controllers should receive and send DTOs (Os Controllers devem receber e enviar DTOs).
- [x] The controllers should be responsible for convert DTO to entities (Os Controllers devem ser responsáveis por converter DTOs em entidades e vice-versa).
- [x] Use transaction just on Service (Usar transação somente no Service).
- [x] Send answers in pattern success or error (Enviar respostas com formato (corpo) padronizado de sucesso ou erro).
- [x] Names of routes with substantives in english or portuguese (Nomes de rotas com substantivos em inglês ou português).
- [x] Send id with params in operations of edit or remove (Passar o id como parâmetro em operações de editar e remover).
- [x] Avoid data update with POST requests (Evitar atualização de dados com requisições POST).
- [ ] Avoid of one user should update or remove data from another user (Evitar que um usuário possa atualizar ou remover dados de outro usuário).
- [ ] DTOs validation with Bean Validation (Validação de DTOs com Bean Validation).
- [ ] Normal searches and with pagination (Buscas normais e com paginação).
- [ ] Unity tests in repositories (Testes unitários em repositórios).
- [ ] Domain class auditory (Auditoria de classes de domínio).
- [ ] Generic handler of exceptions (Tratamento de genérico (centralizado) de exceptions).
- [ ] Exceptions handler from origin in database (Tratamento de exceptions originados no Banco de Dados).
- [ ] Generate documentation of API with Swagger (Gerar documentação da API com Swagger).
- [ ] Use lombok in DTOs and entities (Usar lombok em DTOs e Entidades).
- [ ] Accomplish continuous integration with Travis CI (Realizar integração contínua com Travis CI).
- [ ] Autodeploy in Heroku from GitHub (Fazer o deploy automático no Heroku a partir do GitHub).
