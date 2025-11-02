package itmo.ib.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ChapterDto(

        @JsonProperty(value = "id", required = true)
        Long id,

        @JsonProperty(value = "name", required = true)
        String name
) {
}
