package com.raza.ecommerce.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LoginResponseDto {
    private Long userId;
    private String token;
}
