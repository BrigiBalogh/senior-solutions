package locations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateLocationCommand {


    @NotBlank(message = "Name can not be blank")
    private String name;
    @Min(value = -90,message = "It must not be less than -90")
    @Max(value = 90, message = "It can't be bigger than 90")
    private double lat;
    @Min(value = -180, message = "It must not be less than -90")
    @Max(value = 180,  message = "It can't be bigger than 90")
    private double lon;

}
