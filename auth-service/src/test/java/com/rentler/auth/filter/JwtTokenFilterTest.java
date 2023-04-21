package com.rentler.auth.filter;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class JwtTokenFilterTest {

    @Test
    public void doFilter_invalidToken_badRequest() {

    }


    @Test
    public void doFilter_invalidSignature_IllegalStateException() {

    }

    @Test
    public void doFilter_validToken_setAuthentication() {

    }

}
