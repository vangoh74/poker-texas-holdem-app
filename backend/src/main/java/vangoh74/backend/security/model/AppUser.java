package vangoh74.backend.security.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("appusers")
public class AppUser {

    @Id
    private String id;

    private String username;
    private String password;
}
