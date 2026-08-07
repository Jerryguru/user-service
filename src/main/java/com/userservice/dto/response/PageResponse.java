package com.userservice.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ==========================================================
 * Class Name : PageResponse
 *
 * Description:
 * Generic DTO used to return paginated responses.
 *
 * This class can be reused for
 * UserResponse,
 * ProductResponse,
 * OrderResponse,
 * EmployeeResponse etc.
 *
 * ==========================================================
 *
 * @param <T> Response DTO Type
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageResponse<T> {

    /**
     * Current page data.
     */
    private List<T> content;

    /**
     * Current page number.
     */
    private int page;

    /**
     * Number of records per page.
     */
    private int size;

    /**
     * Total records available.
     */
    private long totalElements;

    /**
     * Total number of pages.
     */
    private int totalPages;

    /**
     * Indicates whether
     * current page is first page.
     */
    private boolean first;

    /**
     * Indicates whether
     * current page is last page.
     */
    private boolean last;

}