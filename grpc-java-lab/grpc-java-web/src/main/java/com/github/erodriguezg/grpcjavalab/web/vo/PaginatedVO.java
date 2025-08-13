package com.github.erodriguezg.grpcjavalab.web.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginatedVO {
    private int pageNumber;
    private int pageSize;
    private int totalPages;
}
