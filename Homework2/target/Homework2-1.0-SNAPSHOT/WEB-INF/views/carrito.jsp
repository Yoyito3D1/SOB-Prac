<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Carrito de Videojuegos</title>

    <style>
        body {
            position: relative;
            display: grid;
            grid-template-rows: auto 1fr; 
            align-items: flex-start;
            min-height: 100vh; 
        }

        .button-bar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px;
        }

        .name-welcome {
            font-weight: bold;
            margin-right: auto; 
            color: #fff; 
        }

        .button-container {
            display: flex;
            align-items: center;
            gap: 10px;
        }

        .button-container-center {
            margin-left: auto;
            margin-right: auto;
        }

        .button-container-right {
            margin-left: auto;
        }

        .button {
            padding: 8px 16px;
            text-decoration: none;
            border-radius: 4px;
            font-weight: bold;
            color: #fff; 
        }

        .button-primary {
            background-color: #007bff;
        }

        .button-danger {
            background-color: #dc3545;
        }

        #cartContainer {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(250px, 1fr)); 
            gap: 20px;
            padding: 20px;
        }

        .cart-item {
            border: 1px solid #ccc;
            padding: 10px;
            display: flex;
            flex-direction: column;
            align-items: center;
            text-align: center;
        }

        .cart-item img {
            width: 100%; 
            max-height: 300px; 
            margin-bottom: 10px;
        }

        .remove-button {
            padding: 8px 16px;
            text-decoration: none;
            color: #fff;
            background-color: #dc3545; 
            border-radius: 4px;
            font-weight: bold; 
            cursor: pointer;
        }

        #cartContainer form {
            margin-top: auto;
            margin-bottom: 10px;
            display: flex;
            justify-content: center;
        }


        body {
            position: relative;
            display: grid;
            grid-template-rows: auto 1fr;
            align-items: flex-start;
            min-height: 100vh; 
        }

        .button-bar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px;
        }

        .name-welcome {
            font-weight: bold;
            margin-right: auto; 
            color: #000; 
        }

        .button-container {
            display: flex;
            align-items: center;
            gap: 10px;
        }

        .button-container-center {
            margin-left: auto;
            margin-right: auto;
        }

        .button-container-right {
            margin-left: auto;
        }

        .button {
            padding: 8px 16px;
            text-decoration: none;
            border-radius: 4px;
            font-weight: bold;
            color: #fff; 
        }

        .button-primary {
            background-color: #007bff;
        }

        .button-danger {
            background-color: #dc3545;
        }

        #cartContainer {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(250px, 1fr)); 
            gap: 20px;
            padding: 20px;
        }

        .cart-item {
            border: 1px solid #ccc;
            padding: 10px;
            display: flex;
            flex-direction: column;
            align-items: center;
            text-align: center;
        }

        .cart-item img {
            width: 100%; 
            max-height: 300px; 
            margin-bottom: 10px;
        }

        .remove-button {
            padding: 8px 16px;
            text-decoration: none;
            color: #fff;
            background-color: #dc3545; 
            border-radius: 4px;
            font-weight: bold; 
            cursor: pointer;
        }

        #cartContainer form {
            margin-top: auto;
            margin-bottom: 10px;
            display: flex;
            justify-content: center;
        }

        #detallsAlquiler {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            background-color: beige;
            text-align: center;
            padding: 20px;
            margin: auto;
            margin-top: 20px;
            max-width: 600px;
        }

    </style>
</head>
<body>
    
    <div class="button-bar">
        <div class="button-container">
            <h1 class="name-welcome">Benvingut, ${sessionScope.usernameCredentials}!</h1>
        </div>
        <div class="button-container button-container-center">
            <a href="${mvc.uri('mainpage')}" class="button button-primary">Tornar a l'inici</a>
        </div>
        <div class="button-container button-container-right">
            <a href="${mvc.uri('log-me-out')}" class="button button-danger">Logout</a>
        </div>
    </div>

    
    <div id="cartContainer" style="display: flex; flex-wrap: wrap;">
        <c:if test="${not empty sessionScope.cartItems}">
            <c:forEach var="cartItem" items="${sessionScope.cartItems}">
                <div class="cart-item">
                    <img src="<c:url value="${cartItem.imageURL}" />" alt="" width="200" height="300">
                    <p class="name-console">${cartItem.nom} - ${cartItem.consola}</p>
                    <p class="price">${cartItem.preuLloguerSetmanal}€ / Setmana</p>
                    <form method="post" action="<c:url value='/Web/Carrito/remove' />" id="removeForm">
                        <input type="hidden" name="videoGameName" value="${cartItem.nom}" />
                        <button type="submit" class="remove-button">Treure</button>
                    </form>
                </div>
            </c:forEach>     
        </c:if>
    </div>
    <form method="post" action="<c:url value='/Web/Carrito/carrito/checkout' />">
        <button type="submit" class="button button-primary">Pagament</button>
    </form>

    <p id="precioTotal">Preu total: 0.00€</p>

    <c:if test="${not empty rental}">
        <div id="detallsAlquiler">
            <h2>Detalls de l'alquiler:</h2>
            <p>ID de l'alquiler: ${rental.id}</p>
            <p>Data de l'alquiler: ${rental.dataAlquiler}</p>
            <p>Data de retorn: ${rental.dataTornada}</p>
            <p>Preu total: ${String.format('%.2f', rental.preuTotal)} €</p>
        </div>
    </c:if>


    <script>
        document.addEventListener('DOMContentLoaded', function () {
            function calcularYMostrarPrecioTotal() {
                var elementosCarrito = document.querySelectorAll('.cart-item');
                var precioTotal = 0;

                elementosCarrito.forEach(function (elemento) {
                    var precioSemanal = parseFloat(elemento.querySelector('.price').textContent.replace('€ / Week', ''));
                    precioTotal += precioSemanal;
                });
                document.getElementById('precioTotal').textContent = 'Precio total: ' + precioTotal.toFixed(2) + '€';
            }
            calcularYMostrarPrecioTotal();
        });
    </script>
</body>
</html>
