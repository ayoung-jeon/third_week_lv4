package com.sparta.sparta.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.sparta.dto.LoginRequestDto;
import com.sparta.sparta.dto.LoginResponseDto;
import com.sparta.sparta.jwt.JwtUtil;
import com.sparta.sparta.security.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Slf4j(topic = "로그인 및 JWT 생성")
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final JwtUtil jwtUtil;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
        setFilterProcessesUrl("/api/user/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            LoginRequestDto requestDto = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDto.class);
            // AuthenticationManager 가 인증처리
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDto.getEmail(),
                            requestDto.getPassword(),
                            null
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult)throws IOException {

        // 로그인 성공 메세지 대신 회원 가입한 회원 정보 반환 하기 위해 수정
//        String email = ((UserDetailsImpl) authResult.getPrincipal()).getUsername();
//        UserRoleEnum role = ((UserDetailsImpl) authResult.getPrincipal()).getUser().getRole();
//
//        String accessToken = jwtUtil.createAccessToken(email,role);

        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
        String accessToken = jwtUtil.createAccessToken(userDetails.getUsername(), userDetails.getUser().getRole());

        jwtUtil.addJwtToHeader(JwtUtil.ACCESSTOKEN_HEADER,accessToken,response);

        LoginResponseDto loginResponseDto = new LoginResponseDto(userDetails.getUser(), accessToken);

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(loginResponseDto));
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed)
            throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse("로그인 실패")));
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
    @Getter
    @Setter
    private static class SimpleResponse {
        private String message;

        private SimpleResponse(String message) {
            this.message = message;
        }
    }
}
