package kiec.ireneusz.germanelearningplatformserver.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.Min;


@Getter
@AllArgsConstructor
public class PageableRequest {

    @Min(1)
    protected int page;

    protected int size;

    public PageableRequest() {
        this.page = 1;
        this.size = 10;
    }

    @JsonIgnore
    public Pageable getPageable() {
        return PageRequest.of(this.page - 1, this.size, Sort.by(Sort.Direction.ASC, "id"));
    }

    @JsonIgnore
    public Pageable getPageable(Sort sort) {
        return PageRequest.of(this.page - 1, this.size, sort);
    }

}

