package com.skku.skkuduler.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//TODO 좋아요 수
public class CalendarEventDetailDto {
    private Boolean isDepartmentEvent;
    private Boolean isMyEvent;
    private Boolean isAddedToMyCalendar;
    private Long ownerUserId;
    private String ownerName;
    private Long departmentId;
    private String departmentName;
    private EventInfo eventInfo;
    private List<ImageInfo> images;

}
