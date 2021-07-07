package org.training360.musicstore;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class MusicStoreService {


    private AtomicLong id = new AtomicLong();

    private ModelMapper modelMapper;


    private List<Instrument> instruments = Collections.synchronizedList(new ArrayList<>());

    public MusicStoreService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<InstrumentDTO> getInstruments(Optional<String> brand, Optional<Integer> price) {
        Type targetListType = new TypeToken<List<InstrumentDTO>>(){}.getType();

        List<Instrument> filtered =instruments.stream()
                .filter(i -> brand.isEmpty() || i.getBrand().equalsIgnoreCase(brand.get()))
                .filter(i -> price.isEmpty() || i.getPrice()== price.get())
                .collect(Collectors.toList());
        return modelMapper.map(filtered, targetListType);
    } // .map( modelMapper.map(filtered, targetListType));
    // targetListType nem kell.

    private Instrument findById(long id) {
        return instruments.stream()
                .filter(m -> m.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Cannot find:" + id));
    }


    public InstrumentDTO getInstrumentById(long id) {
        return modelMapper.map(findById(id),InstrumentDTO.class);
    }

    public InstrumentDTO createInstrument(CreateInstrumentCommand command) {
        Instrument instrument =
                new Instrument(id.incrementAndGet(), command.getBrand(),
                command.getType(), command.getPrice(), LocalDate.now());
        instruments.add(instrument);

        return modelMapper.map(instrument, InstrumentDTO.class);
    }

    public void deleteInstrumentById(long id) {

        Instrument instrument = findById(id);
        instruments.remove(instrument);

    }

    public void deleteAll() {
        instruments.clear();

        id = new AtomicLong();
    }

    public InstrumentDTO updateInstrumentPrice(long id, UpdatePriceCommand command) {

        Instrument result = findById(id);
        if(result.getPrice() != command.getPrice()) {
            result.setPrice(command.getPrice());
            result.setPostDate(LocalDate.now());
        }

        return modelMapper.map(result,InstrumentDTO.class);


    }
}
