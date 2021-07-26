package bonus.doggo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor
@Slf4j
public class DoggoService {


    private ModelMapper modelMapper;

    private DoggoRepository repository;
}

    /* kutya adatainak elmentése
kutyák listázása
adott id-jű kutya adatainak visszaadása
az X fajtájú kutyák listázása (ha nincs ilyen, akkor üres lista)  */
}