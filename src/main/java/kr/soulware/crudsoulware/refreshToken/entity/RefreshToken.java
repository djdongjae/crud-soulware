package kr.soulware.crudsoulware.refreshToken.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import kr.soulware.crudsoulware.common.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "REFRESH_TOKEN")
public class RefreshToken extends BaseEntity {

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String refreshToken;

    @Builder
    public RefreshToken(String username, String refreshToken) {
        this.username = username;
        this.refreshToken = refreshToken;
    }
}
