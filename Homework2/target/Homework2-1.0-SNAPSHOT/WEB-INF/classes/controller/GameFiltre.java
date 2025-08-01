package deim.urv.cat.homework2.controller;

import jakarta.mvc.binding.MvcBinding;
import jakarta.ws.rs.FormParam;

public class GameFiltre {

    @FormParam("filtreGenere")
    @MvcBinding
    private String filtreGenere;

    @FormParam("filtreConsola")
    @MvcBinding
    private String filtreConsola;

    public String getFiltreGenere() { return filtreGenere; }

    public String getFiltreConsola() { return filtreConsola; }
}
