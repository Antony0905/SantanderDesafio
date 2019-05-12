<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<title>Santander Web</title>
	<link rel="icon" href="global/imagens/santander-icon.png">
	
	<link href="global/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="global/datatables/css/dataTables.bootstrap.min.css" rel="stylesheet">
	<link href="global/css/index.css" rel="stylesheet">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="global/bootstrap/js/bootstrap.min.js"></script>
	<script src="global/datatables/js/jquery.dataTables.min.js"></script>
	<script src="global/datatables/js/dataTables.bootstrap.min.js"></script>
	
	<%  %>
	
</head>

<body>
	<nav id="nav" class="navbar navbar-fixed-top navbar-inverse navbar-transparente">
		<div class="container">

			<div class="navbar-header col-md-8">
				<a href="/" class="navbar-brand, a-img"> <span class="img-logo"></span></a>
			</div>

			<c:if test="${not empty emailCliente}">
				<div id="user-login" class="col-md-3">
					<p id="p-user-login" class="text-right">
						<strong id="strong-user-login">USER</strong> ${emailCliente}
					</p>
				</div>
			</c:if>

			<div class="col-md-1 text-right">
				<a href="logout" id="a-logout" class="btn btn-primary">LOGOUT</a>
			</div>
		</div>
	</nav>
	<div id="container-historico-gastos" class="container">
		<div class="row form-row">
			<div id="div-h1-historico-gastos" class="col-md-6">
				<h1 id="h1-historico-gastos">HISTORICO DE GASTOS</h1>
			</div>
			<form id="form-filtro-date" class="col-md-6" action="filtrardata">
				
				<span id="span-filtro-date">Filtrar Por Data:</span>
				<label>
					<input type="date" name="date" class="form-control input-sm" />
				</label>
				<label>
					<button id="btn-filtrar" class="btn btn-primary btn-sm">FILTRAR</button>
				</label>
			</form>
			<div class="col-md-12">

				<table id="mydata" class="table table-bordered table-striped">
					<thead id="mydata-thead">
						<tr>
							<th scope="col">Descrição</th>
							<th scope="col">Data</th>
							<th scope="col">Categoria</th>
							<th scope="col">Valor</th>
							<th scope="col">Atualizar</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${historicoGastos}" var="historicoGasto">
							<tr>
								<td class="mydata-td">${historicoGasto.descricao}</td>
								<td class="mydata-td">${historicoGasto.data}</td>
								<td class="mydata-td">${historicoGasto.categoria}</td>
								<td class="mydata-td">${historicoGasto.valor}</td>
								<td>
									<a href="#"
										onclick="setarModal('${historicoGasto.id}','${historicoGasto.data}','${historicoGasto.categoria}','${historicoGasto.valor}','${historicoGasto.descricao}','${historicoGasto.codigoUsuario}')"
										class="btn btn-primarybtn-sm" data-toggle="modal"
										data-target="#modalatualizar" style="cursor: pointer;">
										<img src="global/imagens/refresh.png" title="Update" />
									</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="pagination-container">
					<nav>
						<ul class="pagination"></ul>
					</nav>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal -->
	<div class="modal fade" id="modalatualizar" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div id="div-modal" class="modal-header">
					<h4 class="modal-title" id="exampleModalLabel">Deseja atualizar o Histórico de Gastos?</h4>
					<button id="bt-fechar-modal" type="button" class="close" data-dismiss="modal" aria-label="Fechar">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div id="div-form-modal" class="modal-body">
					<form id="atualizacaoForm" action="atualizarGastoCartao" method="POST">
						<div class="form-group row">
							<label for="id" class="col-sm-2 col-form-label">ID</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" name="id" id="id" readonly="readonly">
							</div>
						</div>
						<div class="form-group row">
							<label for="codigoUsuarioModal" class="col-sm-2 col-form-label">Codigo
								Usuario</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="codigoUsuarioModal" name="codigoUsuarioModal" readonly="readonly">
							</div>
						</div>
						<div class="form-group row">
							<label for="descricaoModal" class="col-sm-2 col-form-label">Descrição</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="descricaoModal" name="descricaoModal">
							</div>
						</div>
						<div class="form-group row">
							<label for="dataModal" class="col-sm-2 col-form-label">Data</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="dataModal" name="dataModal" readonly="readonly">
							</div>
						</div>
						<div class="form-group row">
							<label for="categoriaModal" class="col-sm-2 col-form-label">Categoria</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="categoriaModal" name="categoriaModal">
							</div>
						</div>
						<div class="form-group row">
							<label for="valorModal" class="col-sm-2 col-form-label">Valor</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="valorModal" name="valorModal" readonly="readonly">
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
					<button onclick="submitForm();" type="button" class="btn btn-primary" type="submit">Atualizar</button>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		function submitForm() {
			$('#atualizacaoForm').submit();
		}
	</script>


	<script type="text/javascript">
		function setarModal(id, data, categoria, valor, descricao,
				codigoUsuario) {
			document.getElementById('id').value = id;
			document.getElementById('dataModal').value = data;
			document.getElementById('categoriaModal').value = categoria;
			document.getElementById('valorModal').value = valor;
			document.getElementById('descricaoModal').value = descricao;
			document.getElementById('codigoUsuarioModal').value = codigoUsuario;
		}
	</script>
	<script>
		$('#mydata').dataTable({
			"ordering": false
		});

		document.getElementById("mydata_filter").style.color = "white";
		document.getElementById("mydata_length").style.color = "white";
		document.getElementById("mydata_info").style.color = "white";
	</script>		
</body>
</html>