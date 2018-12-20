<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hora da Pipoca</title>
</head>
<body>
	<form action="manterfilmes.do" method="get">
		<h3>Titulo:</h3>
		<input type="text" name="titulo" value="${filme.titulo }"/>
		<h3>Descricao:</h3>
		<textarea type="text" name="descricao" value="${filme.descricao }" rows="4" cols="50"></textarea>
		<h3>Diretor:</h3>
		<input type="text" name="diretor" value="${filme.diretor }"/>
		<h3>PosterPath:</h3>
		<input type="text" name="posterpath" value="${filme.posterPath }"/>
		<h3>Popularidade:</h3>
		<input type="text" name="popularidade" value="${filme.popularidade }"/>
		<h3>Data de Lançamento:</h3>
		<input type="text"	name="data_lancamento" value="${filme.dataLancamento }"/>
		<h3>Genero:</h3>
		<select type="text" >
			<c:forEach var="genero" items="${lista}">
				<option name="genero" value="${genero.id }">${genero.nome }</option>
			</c:forEach>
		</select>
		<input type="submit" name="acao" value="alterarFilme"/>
	</form>
	
</body>
</html>