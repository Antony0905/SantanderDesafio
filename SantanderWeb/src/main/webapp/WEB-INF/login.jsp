<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Login Page</title>
<link rel="icon" href="global/imagens/santander-icon.png">

<!-- Bootstrap -->
<link href="global/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="global/css/login.css" rel="stylesheet">

<style type="text/css">
.aleta {
	width: 100%;
	height: 50px;
	margin: auto;
	bottom: 0;
	position: fixed;
}
</style>

</head>
<body>
	<div class="container">
		<div class="login">
			<h1>Authentication Area</h1>

			<form method="post" action="login" modelAttribute="cliente">
				<input type="email" name="email" placeholder="Email"
					required="required" /> <input type="password" name="senha"
					placeholder="Password" required="required" />
				<button type="submit" class="btn btn-primary btn-block btn-large">LOGIN</button>
			</form>
		</div>
	</div>

	<div class="container">

	<c:if test="${not empty error}">
		<div class="alert alert-danger alerta" role="alert" style="bottom: 0;position: absolute;width: 84%;">
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Fechar">
				<span aria-hidden="true">&times;</span>
			</button>
			<strong>Error: ${error}</strong>
		</div>
	</c:if>

	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="global/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>