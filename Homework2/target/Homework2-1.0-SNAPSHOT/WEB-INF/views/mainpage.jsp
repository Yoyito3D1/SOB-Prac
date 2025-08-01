<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Llista de Videojocs</title>

    <style>
        
        #loginButtonContainer {
            position: fixed;
            top: 10px;
            right: 10px;
        }
        
        .game-item {
            border: 1px solid #ccc;
            padding: 10px;
            margin: 10px;
            width: 30%;
            display: flex;
            flex-direction: column;
            align-items: center;
            text-align: center;
            transition: transform 0.3s ease-in-out;
        }
        
        body {
            margin: 0;
            padding: 0;
            position: relative;
        }

        .game-item {
            border: 1px solid #ccc;
            padding: 10px;
            margin: 10px;
            width: calc(30% - 20px);
            display: flex;
            flex-direction: column;
            align-items: center;
            text-align: center;
            transition: none;
            margin-bottom: 5px; 
        }


        .game-item img {
            width: 100%;
            max-height: 200px; 
            margin-bottom: 5px; 
        }


        .game-info {
            text-transform: uppercase;
            font-size: 1.5em;
            margin-top: 10px;
            font-weight: bold;
        }

        .disponibilitat {
            font-weight: bold;
            margin: 10px 0;
            font-size: 1em; 
        }
 
        .nom-console {
            text-transform: uppercase;
            font-weight: bold;
            font-family: 'Metropolis', Arial, Helvetica, sans-serif;
        }
        
        #headerContainer {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px;
            background-color: #ffffff;
        }

        #loginLogoutContainer {
            display: flex;
            gap: 10px;
        }

        .button {
            padding: 8px 16px;
            text-decoration: none;
            color: #fff;
            background-color: #007bff; 
            border-radius: 4px;
            font-weight: bold;
        }

        #welcomeMessage {
            font-size: 1.5em;
            font-weight: bold;
        }
        
        #gamesContainer {
            display: flex;
            justify-content: center;
        }
        
        #filterForm {
            margin-left: 75px; 
        }

    </style>

    <script>
        function submitForm() {
            document.getElementById('filterForm').submit();
        }

        function redirectToGameDetails(gameNom) {
            var form = document.createElement("form");
            form.method = "GET";
            form.action = "${mvc.uri('gameInfo')}";

            var input = document.createElement("input");
            input.type = "hidden";
            input.name = "nom";
            input.value = gameNom;

            form.appendChild(input);

            document.body.appendChild(form);

            form.submit();
        }
        document.addEventListener("DOMContentLoaded", function() {
            var filtreGenere = "${filtered.filtreGenere}";
            var filtreConsola = "${filtered.filtreConsola}";

            document.getElementById("filtreGenere").value = filtreGenere;
            document.getElementById("filtreConsola").value = filtreConsola;
        });

        document.addEventListener("DOMContentLoaded", function() {
            var disponibilitatElements = document.querySelectorAll('.disponibilitat');

            disponibilitatElements.forEach(function(element) {
                var disponibilitat = element.dataset.disponibilitat;

                if (disponibilitat === 'yes') {
                    element.style.color = 'green';
                    element.innerHTML = '<input type="checkbox" disabled="true" class="checkbox-yes"/> En stock';
                } else {
                    element.style.color = 'red';
                    element.innerHTML = '<input type="checkbox" disabled="true" class="checkbox-no"/> No está en stock';
                }
            });
        });
        
        document.addEventListener("DOMContentLoaded", function() {
            var disponibilitatElements = document.querySelectorAll('.disponibilitat');
            
            disponibilitatElements.forEach(function(element) {
                var disponibilitat = element.dataset.disponibilitat;
                
                if (disponibilitat === 'yes') {
                    element.style.color = 'green';
                    element.innerHTML = 'Disponible'; 
                } else {
                    element.style.color = 'red';
                    element.innerHTML = 'No disponible'; 
                }
            });
        });
    
        function submitForm() { document.getElementById('filterForm').submit(); }
        
    </script>
      
</head>
<body>
    <div class="filtros">
        <div id="headerContainer">
            <h1 id="welcomeMessage">
                <c:if test="${not empty sessionScope.usernameCredentials}">
                    Benvingut a la pàgina, ${sessionScope.usernameCredentials}!
                </c:if>
            </h1>

            <div id="loginLogoutContainer">
                <c:choose>
                    <c:when test="${empty sessionScope.usernameCredentials}">
                        <a class="button" href="${mvc.uri('log-in-get')}">Login</a>
                    </c:when>
                    <c:otherwise>
                        <a class="button" href="${mvc.uri('carrito-details')}">Carrito</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

        <form id="filterForm" action="${mvc.uri('mainpage')}" method="post">
            <label for="filtreGenere">Filtrar per genere:</label>
            <select id="filtreGenere" name="filtreGenere" onchange="submitForm()">
                <option value="">Tots</option>
                <option value="Plataforma">Plataforma</option>
                <option value="Accio">Accio</option>
                <option value="Aventura">Aventura</option>
                <option value="Estrategia">Estrategia</option>
                <option value="Carreres">Carreras</option>
                <option value="Puzzle">Puzzle</option>
                <option value="MMORPG">MMORPG</option>
                <option value="Esports">Esports</option>
                <option value="Ball">Ball</option>
                <option value="Musica">Musica</option>
                <option value="Festa">Festa</option>
                <option value=">Educatiu">Educatiu</option>
            </select>

            <label for="filtreConsola">Filtrar per Consola:</label>
            <select id="filtreConsola" name="filtreConsola" onchange="submitForm()">
                <option value="">Totes</option>
                <option value="Nintendo">Nintendo</option>
                <option value="PC">PC</option>
                <option value="Xbox">Xbox</option>
                <option value="PS4">PS4</option>
                <option value="PS5">PS5</option>
            </select>
        </form>
   </div>

    <div id="gamesContainer" style="display: flex; flex-wrap: wrap;">
        <c:forEach var="videoGame" items="${videoGames}">
            <div class="game-item">
                <img class="game-image" src="<c:url value="${videoGame.imageURL}" />" alt="" width="200" height="300" data-nom="${videoGame.nom}" onclick="redirectToGameDetails('${videoGame.nom}')">
                <p class="nom-consola">${videoGame.nom} - ${videoGame.consola}</p>
                <p class="disponibilitat" data-disponibilitat="${videoGame.disponibilitat}"></p>
                <p>${videoGame.preuLloguerSetmanal}€ / Week</p>
            </div>
        </c:forEach>
    </div>
            
</body>
</html>