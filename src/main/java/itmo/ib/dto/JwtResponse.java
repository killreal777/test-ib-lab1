package itmo.ib.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record JwtResponse(

        @JsonProperty(value = "username", required = true)
        String username,

        @JsonProperty(value = "accessToken", required = true)
        String accessToken
) {
}

