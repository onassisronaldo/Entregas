<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Alterar Filme</title>
	<link rel="stylesheet" type="text/css" href="slick/slick.css">
 	<link rel="stylesheet" type="text/css" href="slick/slick-theme.css">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    
	<style type="text/css">
   	  	html, body {
      		margin: 0;
      		padding: 0;
    	}

        * {
      		box-sizing: border-box;
    	}

    	.slider {
        	width: 50%;
        	margin: 100px auto;
   	    }

    	.slick-slide {
      		margin: 0px 20px;
    	}

    	.slick-slide img {
      		width: 100%;
    	}

    	.slick-prev:before,
    	.slick-next:before {
      		color: black;
    	}


    	.slick-slide {
      		transition: all ease-in-out .3s;
      		opacity: .2;
    	}
    
    	.slick-active {
      		opacity: .5;
    	}

    	.slick-current {
      		opacity: 1;
    	}
	</style>
</head>

<body>
    <!-- Barra superior com os menus de navegação -->
	<c:import url="Menu.jsp"/>
    <!-- Container Principal -->
    <br><br>

    <section class="regular slider">
		<c:forEach var="filme" items="${lista}">
    		<div class="thumbnail">
      			<img alt="?" src="${filme.posterPath}" class="img-responsive" >
      			<div class="caption">
          			<p><strong>Gênero:&nbsp;</strong>${filme.genero.nome}</p>
          			<p><strong>Direção:&nbsp;</strong>${filme.diretor}</p>
          			<p><strong>Descrição:</strong>${filme.descricao}</p>
          			<p><strong>Lançamento:&nbsp;</strong>
						<fmt:formatDate value="${filme.dataLancamento}" pattern="dd/MM/yyyy"/>
					</p>
		  			<p><strong>Popularidade:&nbsp;</strong>${filme.popularidade}</p>
		  
        		</div>
    		</div>
    	</c:forEach>
  	</section>  	


    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery-2.2.0.min.js" type="text/javascript"></script>
  	<script src="./slick/slick.js" type="text/javascript" charset="utf-8"></script>
  	<script type="text/javascript">
    	$(document).on('ready', function() {
      		$(".vertical-center-4").slick({
        		dots: true,
        		vertical: true,
        		centerMode: true,
        		slidesToShow: 4,
        		slidesToScroll: 2
      		});
	      	$(".vertical-center-3").slick({
	        	dots: true,
	        	vertical: true,
	        	centerMode: true,
	        	slidesToShow: 3,
	        	slidesToScroll: 3
	      	});
	      	$(".vertical-center-2").slick({
	        	dots: true,
	        	vertical: true,
	        	centerMode: true,
	        	slidesToShow: 2,
	        	slidesToScroll: 2
	      	});
	      	$(".vertical-center").slick({
	        	dots: true,
	        	vertical: true,
	        	centerMode: true,
	      	});
	      	$(".vertical").slick({
	        	dots: true,
	        	vertical: true,
	        	slidesToShow: 3,
	        	slidesToScroll: 3
	      	});
	      	$(".regular").slick({
	        	dots: true,
	        	infinite: true,
	        	slidesToShow: 3,
	        	slidesToScroll: 3
	      	});
	      	$(".center").slick({
	        	dots: true,
	        	infinite: true,
	        	centerMode: true,
	        	slidesToShow: 5,
	        	slidesToScroll: 3
	      	});
	      	$(".variable").slick({
	        	dots: true,
	        	infinite: true,
	        	variableWidth: true
	      	});
      		$(".lazy").slick({
        		lazyLoad: 'ondemand', // ondemand progressive anticipated
        		infinite: true
      		});
    	});
	</script>
    
</body>

</html>