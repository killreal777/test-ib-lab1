package itmo.ib.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserDto(

        @JsonProperty(value = "id", required = true)
        Long id,

        @JsonProperty(value = "username", required = true)
        String username
) {
}
