package kr.soulware.crudsoulware.user.service;

import kr.soulware.crudsoulware.exception.ErrorCode;
import kr.soulware.crudsoulware.exception.model.BadRequestException;
import kr.soulware.crudsoulware.security.UserDetailsImpl;
import kr.soulware.crudsoulware.security.jwt.JwtProvider;
import kr.soulware.crudsoulware.user.dto.request.SignInRequestDto;
import kr.soulware.crudsoulware.user.dto.request.SignUpRequestDto;
import kr.soulware.crudsoulware.user.entity.User;
import kr.soulware.crudsoulware.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @Transactional
    public Long save(SignUpRequestDto requestDto) {
        checkDuplicateEmail(requestDto.getEmail());
        User user = generateUser(requestDto);
        return userRepository.save(user).getId();
    }

    public String signIn(SignInRequestDto requestDto) {
        UserDetailsImpl userDetails = getUserDetails(
                requestDto.getUsername(), requestDto.getPassword()
        );
        return jwtProvider.generateToken(userDetails.getUsername());
    }

    private UserDetailsImpl getUserDetails(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        return (UserDetailsImpl) authentication.getPrincipal();
    }

    private User generateUser(SignUpRequestDto requestDto) {
        return User.builder()
                .name(requestDto.getName())
                .email(requestDto.getEmail())
                .username(requestDto.getEmail())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .build();
    }

    private void checkDuplicateEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new BadRequestException(
                    ErrorCode.DUPLICATE_EMAIL_EXCEPTION,
                    ErrorCode.DUPLICATE_EMAIL_EXCEPTION.getMessage()
            );
        }
    }
}
