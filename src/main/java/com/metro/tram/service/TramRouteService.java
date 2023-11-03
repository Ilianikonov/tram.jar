package com.metro.tram.service;

import com.metro.tram.dao.TramRouteRepository;
import com.metro.tram.dto.TramRouteDto;
import com.metro.tram.entity.TramRoute;
import com.metro.tram.exception.CodeIsOccupiedException;
import com.metro.tram.exception.NotFoundTramRouteException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TramRouteService {
    private final TramRouteRepository tramRouteRepository;

    public List<TramRouteDto> getTrams(){
        List<TramRouteDto> tramRoutesDto = new ArrayList<>();
        List<TramRoute> tramRoutes = tramRouteRepository.findAll();
                tramRouteRepository.findAll();
        if (tramRoutes.isEmpty()){
            throw new NotFoundTramRouteException("трамвайных маршрутов нет");
        }
        for (TramRoute i: tramRoutes) {
            tramRoutesDto.add(convertToTramRouteDto(i));
        }
            return tramRoutesDto;
    }

    public TramRouteDto getTramsById(long id){
        TramRoute tramRoute = tramRouteRepository.findById(id).orElse(null);
        if (tramRoute == null){
            throw new NotFoundTramRouteException("трамвайный маршрут не найден");
        }
        return convertToTramRouteDto(tramRoute);
    }

    public long createTram(TramRouteDto tramRouteDto){
        long tramRouteId;
        try {
            tramRouteId = tramRouteRepository.save(convertToTramRoute(tramRouteDto)).getId();
        }catch (DataAccessException dataAccessException) {
            log.error("при создании трейдера произошла ошибка ", dataAccessException);
            throw new CodeIsOccupiedException("трейдер с внутренним кодм маршрута "+ tramRouteDto.getCode() +" уже существует");
        }
        return tramRouteId;
    }

    public void deleteTramById(long id){
        tramRouteRepository.deleteById(id);
    }
    public TramRouteDto updateTramRoute(TramRouteDto tramRouteDto){
        TramRoute tramRoute = convertToTramRoute(getTramsById(tramRouteDto.getId()));
        if(tramRouteDto.getName() != null){
            tramRoute.setName(tramRouteDto.getName());
        } else if (tramRouteDto.getCode() != null){
            tramRoute.setCode(tramRouteDto.getCode());
        }
        return convertToTramRouteDto(tramRouteRepository.save(tramRoute));
    }

    private TramRoute convertToTramRoute(TramRouteDto tramRouteDto){
        TramRoute tramRoute = new TramRoute();
        tramRoute.setId(tramRouteDto.getId());
        tramRoute.setName(tramRouteDto.getName());
        tramRoute.setCode(tramRouteDto.getCode());
        return tramRoute;
    }

    private TramRouteDto convertToTramRouteDto(TramRoute tramRoute){
        TramRouteDto tramRouteDto = new TramRouteDto();
        tramRouteDto.setId(tramRoute.getId());
        tramRouteDto.setName(tramRoute.getName());
        tramRouteDto.setCode(tramRoute.getCode());
        return tramRouteDto;
    }
}
