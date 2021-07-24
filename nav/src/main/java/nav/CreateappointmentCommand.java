package nav;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateappointmentCommand {

     @IsValidTaxNumber
    private String taxNumber;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @IsValidType
    private String typeCode;
}
