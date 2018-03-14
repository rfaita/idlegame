package com.idle.game.server.interceptor;

import com.idle.game.helper.ManualTokenHelper;
import graphql.servlet.GraphQLServletListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author rafael
 */
@Component
public class TokenInterceptor implements GraphQLServletListener {

    @Autowired
    private ManualTokenHelper manualTokenHelper;

    @Override
    public RequestCallback onRequest(HttpServletRequest request, HttpServletResponse response) {
        manualTokenHelper.createAccessToken(request.getUserPrincipal());
        return null;
    }
}
