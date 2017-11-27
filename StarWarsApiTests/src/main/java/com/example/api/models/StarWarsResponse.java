package com.example.api.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class StarWarsResponse<T> extends SimplifiedHttpResponse
{
        private SimplifiedHttpResponse simplifiedHttpResponse;
        private T response;

        public StarWarsResponse(SimplifiedHttpResponse httpResponse) {
                this.simplifiedHttpResponse = httpResponse;
        }
}
