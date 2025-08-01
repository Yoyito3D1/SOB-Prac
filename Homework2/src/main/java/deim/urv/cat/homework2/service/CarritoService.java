package deim.urv.cat.homework2.service;

import deim.urv.cat.homework2.model.Credentials;
import deim.urv.cat.homework2.model.Rental;
import deim.urv.cat.homework2.model.Game;
import java.util.List;

public interface CarritoService {

    List<Game> getVideoGamesInCart();

    void addVideoGameToCart(Game videoGame);

    void removeVideoGameFromCart(Game videoGame);

    Rental processCheckout(List<String> games,Credentials headers);

    boolean isGameInCart(Game videoGame);
}
