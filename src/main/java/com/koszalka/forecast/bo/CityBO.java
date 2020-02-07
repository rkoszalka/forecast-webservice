package com.koszalka.forecast.bo;

import com.koszalka.forecast.persistence.dto.CityDTO;
import com.koszalka.forecast.persistence.entities.CityEntity;
import com.koszalka.forecast.persistence.repositories.CityRepository;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityBO {


    private final CityRepository cityRepository;

    @Autowired
    public CityBO(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public void saveOne(CityDTO cityDTO)  {
        CityEntity entity = new CityEntity();
        entity.setCity(cityDTO.getCity());
        entity.setLat(cityDTO.getLat());
        entity.setLng(cityDTO.getLng());
        cityRepository.save(entity);
    }

    public CityEntity getCityByName(String city) {
        return cityRepository.getCityByName(city);
    }

    public List<CityEntity> getCities() {
        return cityRepository.findAll();
    }

//    private Long verifyIfHashAlreadyExist(String hash, Long expirationDate) {
//        return cityRepository.verifyIfHashAlreadyExist(hash, expirationDate);
//    }

    public ResponseEntity<CityDTO> send301Redirect(HttpServletResponse response, String newUrl) {
        response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        response.setHeader("Location", newUrl);
        response.setHeader("Connection", "close");
        return new ResponseEntity<CityDTO>(HttpStatus.MOVED_PERMANENTLY);
    }
}