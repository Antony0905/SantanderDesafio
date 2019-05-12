________________________________________________ Desafio Santander ________________________________________________

Para executarmos o projeto devemos seguir os passos abaixo.

1. Baixando projeto para repositorio local
	1.1. git init
	1.2. git pull https://github.com/Antony0905/SantanderDesafio.git

2. Startando a aplicacao.
	2.1. Com o docker iniciado, é necessário entrar na pasta onde está o arquivo docker-compose.yml e executar o comando abaixo:
	2.2. docker-compose up
	2.3. Apos todos os containers subirem com sucesso, a aplicacao ja esta pronta para ser utilizada
	
________________________________________________ Informacoes Uteis ________________________________________________

No Docker foi adicionado a instalacao do PgAdmin 4.

PgAdmin 4: http://localhost:16543/browser/
	- Crie um novo Server
	- na aba Connection adicione o seguinte endereco IP: 172.72.0.2
	
obs: O IP mencionado, eh o IP estatico do Postgres definido no docker_compose.yml

____________________________________________________________________________________________________________________

Santander Web: localhost:8802/
	- Pagina Web da aplicacao.
	- Por padrao, ja foi criado um usuario no Postgres.
		- USER: matheus@matheus.com
		- SENHA: matheus
____________________________________________________________________________________________________________________

Santander Query:
	- API utilizada somente para Querys, consumida pela aplicacao Santander Web.

____________________________________________________________________________________________________________________
	
Santander Command: localhost:8800/
	
	- END POINTS
	
		- localhost:8800/login
			- Antes de chamar qualquer outro endpoint, eh necessario fazer login na aplicacao
			- Por padrao ja foi criado um usuario no Postgres.
			- Body Request: 
				{
					"email": "santander@santander.com",
					"senha": "santander"
				}
			- No headers do response receberemos um TOKEN: Authorization → Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzYW50YW5kZXJ...
		
				
		- localhost:8800/postGastoCartao
			- Somente sistemas logados podem utilizar os endpoints
			- Para utilizar qualquer endpoint devemos adicionar o TOKEN no Headers da Request.
				- EX: Authorization  |  Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzYW50YW5kZXJAc2Fu...
			- Body Request:
				{
				  "codigoUsuario": 1,
				  "data": "2019-05-10T13:51:49.861Z",
				  "descricao": "Body Tech",
				  "valor": 50.00
				}
 
		- localhost:8800/credenciarSistema
			- Podemos criar novos usuarios para utilizar os endpoints
			- Body Request 
				{
				  "email": "everis@everis.com",
				  "nome": "everis",
				  "senha": "everis"
				}
		
		- localhost:8800/inserirCliente
			- Podemos criar novos clientes para controlar os gastos
			- Body Request 
				{
				  "codigoUsuario": 2,
				  "email": "sarah@sarah.com",
				  "nome": "sarah",
				  "senha": "sarah"
				}	
____________________________________________________________________________________________________________________