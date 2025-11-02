package itmo.ib.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateChapterRequest(

        @JsonProperty(value = "name", required = true)
        String name
) {
}
