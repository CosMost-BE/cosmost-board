package com.cosmost.project.board.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseView<T> {

    private final T data;
    private final Meta meta;

    public ApiResponseView(T data) {
        this.data = data;
        this.meta = null;
    }

    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Meta {
        private final Sort sort;
        private final Pagination pagination;

        public static Meta DEFAULT_META = new Meta(Sort.CREATED_AT, Pagination.DEFAULT_PAGING);

        private Meta(Sort sort, Pagination pagination) {
            this.sort = sort;
            this.pagination = pagination;
        }

        public enum Sort {
            CREATED_AT,
            NONE
        }

        @Getter
        private static class Pagination {
            private final int offset;
            private final int limit;
            private final long totalCount;

            public static Pagination DEFAULT_PAGING = new Pagination(0, 0, 0);

            private Pagination(int offset, int limit, long totalCount) {
                this.offset = offset;
                this.limit = limit;
                this.totalCount = totalCount;
            }
        }
    }
}
