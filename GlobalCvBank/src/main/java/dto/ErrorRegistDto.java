package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder

public class ErrorRegistDto {
   String error;
   Auth message;
   String status;

  }
