package com.github.erodriguezg.grpcjavalab.web.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaginatedVO {
    private int pageNumber;
    private int pageSize;
    private int totalPages;
}
