<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Filme Selecionado</title>
</head>
<body>
	<h3>${filme}</h3>
	<c:if test="${filme.posterPath != null}">
		<h3>Data de Lançamento: <fmt:formatDate value="${filme.dataLancamento}" pattern="dd/MM/yyyy"/></h3>
		<img alt="poster" src="${filme.posterPath}" height="480" width="320">
		<form action="manterfilmes.do" method="get">
			<input type="hidden" name="idFilme" value="${filme.id}"/>
			<input type="submit" name="acao" value="excluir"/>
			<input type="submit" name="acao" value="alterar"/>
		</form>
	</c:if>
</body>
</html>