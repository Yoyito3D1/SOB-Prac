package deim.urv.cat.homework2.service;

import deim.urv.cat.homework2.model.Credentials;
import jakarta.ws.rs.core.Response;

public interface LoginService {
    Response authenticateUser(Credentials credentialsDTO);
}
