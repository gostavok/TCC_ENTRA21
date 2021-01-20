# Repositório do projeto do TCC do Entra21
--------------------------------------------

# Nome: TextilSoft

A TextilSoft pretende atualizar os modelos de software utilizados na gestão de Confecções Têxteis da cidade de Blumenau.
Rodando em um ambiente WEB os usuários são livres para acessar a aplicação de qualquer lugar do mundo. Reduzindo custos fixos e criando oportunidades de escalabilidade e melhorias o TextilSoft é o Software ideal para Negócios Locais.
A aplicação em seu estado de protótipo conta com módulos contemplando Cadastro, Estoque, Vendas, Financeiro e a geração de relatórios.
Um software de gestão empresarial(ERP), com soluções focadas no setor Têxtil.



Tecnologias: 
  - Java EE
  - Jersey
  - Maven
  - Tomcat
  - MYSQL
  - AngularJS
  - JavaScript
  - HTML
  - Bootstrap
  - Rest
  - Docker
 
 Ferramentas:
  - Git
  - MySQL Workbench
  - Eclipse
  - Sublime
  - VS Code
  - Trello
  - Lucidchat
  - Pencil

Para abrir e iniciar o projeto utilize o Eclipse, seguindo os passos:
- file -> import... -> Existing Maven Projects
- requirimento: tenha um servidor de aplicação configurado no seu Eclipse
- na classe main (/Textilsoft/src/main/java/br/com/textilsoft/MyApplication.java) 
  - Run as -> Run on Server

Comando para subir o banco já com script de população:
- iniciar banco com logs: `docker-compose up`
- iniciar banco sem logs: `docker-compose up -d`
