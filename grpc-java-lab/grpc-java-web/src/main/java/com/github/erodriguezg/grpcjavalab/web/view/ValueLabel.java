package com.github.erodriguezg.grpcjavalab.web.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValueLabel<T> {

    private T value;

    private String label;

}
