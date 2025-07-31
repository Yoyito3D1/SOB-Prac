package service;

import jakarta.ws.rs.core.Application;

@jakarta.ws.rs.ApplicationPath("webresources")
public class RESTapp extends Application {
    
    /*
    
    GAME @POST POSTMAN
    
    {
        "nom": "Carlos Duty",
        "consola": "PS3",
        "genere": "piu piu",
        "descripcio": "Joc de fotre tiros",
        "disponibilitat": "true",
        "preuLloguerSetmanal" "1.99"
    }
    
    */
    
    /*
    
    CUSTOMER @POST POSTMAN
    
    {
        "usuari": "PeterLanguila",
        "email": "donpiti@gmail.com",
        "nom": "Peter",
        "cognom": "Languilation",
        "contrassenya" "noemhackejispls"
    }
    
    */
    
    /*
    
    CUSTOMER @POST POSTMAN
    
    {
        "customer": {
            "id": 50 // ID del client existent al qual es fa el lloguer
        },
        "videojocs": [
            {
                "id": 1 // ID del primer videojoc que es lloga
            },
            {
                "id": 2 // ID del segon videojoc que es lloga
            }
            // ... pots afegir més videojocs segons sigui necessari
        ],
        "dataAlquiler": "2023-12-01T00:00:00Z",
        "dataTornada": "2023-12-08T00:00:00Z"
    }
    
    */
}
