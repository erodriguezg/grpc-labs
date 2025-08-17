package com.github.erodriguezg.grpcjavalab.web.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginatedForm {
    private int pageNumber;
    private int pageSize;
    private int totalPages;
}
