package com.idle.game.helper.interceptor;

import com.idle.game.helper.ManualTokenHelper;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 *
 * @author rafael
 */
@Component
public class UserFeignClientInterceptor implements RequestInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_TOKEN_TYPE = "Bearer";

    @Autowired
    private ManualTokenHelper manualTokenHelper;

    @Override
    public void apply(RequestTemplate template) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();

        if (authentication != null) {
            manualTokenHelper.createAccessToken(authentication);
            template.header(AUTHORIZATION_HEADER,
                    String.format("%s %s", BEARER_TOKEN_TYPE, manualTokenHelper.getToken()));
        }
    }
}
