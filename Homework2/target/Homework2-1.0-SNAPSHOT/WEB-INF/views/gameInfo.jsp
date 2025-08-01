<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Detalles del Juego</title>

    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            font-family: "metropolis", Arial, Helvetica, sans-serif;
        }

        #details-container {
            display: grid;
            grid-template-columns: 1fr 1fr; 
            gap: 20px;
            width: 80%;
            max-width: 1200px; 
            margin: 0 auto; 
        }

        #left-container {
            display: flex;
            align-items: center;
            justify-content: center;
            flex-direction: column;
        }

        #right-container {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            width: 100%; 
        }

        #right-container-child {
            width: 80%; 
            max-width: 600px; 
            padding: 20px; 
            display: flex;
            flex-direction: column;
            align-items: center;
            text-align: center;
        }

        #right-container-child h2 {
            font-size: 2.5em; 
            margin-bottom: 15px; 
        }

        #right-container-child p {
            font-size: 1.2em;
            margin: 10px 0; 
        }

        img {
            width: 100%; 
            max-width: 500px; 
            height: auto;
            margin-bottom: 20px; 
        }

        button {
            margin-top: 20px; 
            padding: 15px 30px; 
            font-size: 1.5em; 
            background-color: #4285f4; 
            color: #ffffff; 
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease-in-out;
        }

        button:hover {
            background-color: #3c76e4; 
        }
        
        .success-message {
            color: #008000; 
        }

        .error-message {
            color: #ff0000; 
        }
    </style>
</head>
<body>   
    <c:if test="${not empty message}">
        <div>${message.text}</div>
    </c:if>
    <div id="details-container">
        <div id="left-container"> 
            <h1>Detalles del Juego</h1>
            <img src="<c:url value="${gameInfo.imageURL}" />" alt="" />
            <p>Description: ${gameInfo.descripcio} </p>
        </div>
        <div id="right-container">
            <div id="right-container-child">
                <h2>${gameInfo.nom} - ${gameInfo.consola}</h2>
                <p>Disponibilitat: ${gameInfo.disponibilitat}</p>
                <p>Preu: ${gameInfo.preuLloguerSetmanal}€ (IVA included)</p>
                <p>Genere: ${gameInfo.genere}</p>
                <p>Consola: ${gameInfo.consola}</p>



                <button onclick="addToCart()">Add to Cart</button>
            </div>
        </div>
    </div>
    <script>
        function addToCart() {
            var form = document.createElement("form");
            form.method = "POST";
            form.action = "${mvc.uri('carrito/add')}";

            var input = document.createElement("input");
            input.type = "hidden";
            input.name = "videoGameName";
            input.value = "${gameInfo.nom}";
            form.appendChild(input);

            document.body.appendChild(form);
            form.submit();
        }
    </script>
</body>
</html>
