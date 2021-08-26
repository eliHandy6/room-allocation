package com.apopo.tola.chuka_room_allocation.apiResponse;

import com.apopo.tola.chuka_room_allocation.errors.ErrorDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {

    private Boolean success;

    private String message;

    @JsonFormat(pattern = "YYYY-MM-DD ")
    private Date timestamp;

    private HttpStatus status;

    private Object data;

    private ArrayList<ErrorDto> errors;

    private List<Object> data_list;
}
