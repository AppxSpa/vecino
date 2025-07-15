package com.vecino.vecino.services.interfaces;


import com.vecino.vecino.dto.UpdateVecino;
import com.vecino.vecino.dto.VecinoDto;
import com.vecino.vecino.dto.VecinoResponse;
import com.vecino.vecino.entities.Vecino;

public interface VecinoService {

    Vecino createVecino(VecinoDto vecinoDto);

    VecinoResponse getVecinoByRut(Integer rut);

     Vecino updateInfo(Integer rut, UpdateVecino updateVecino);

}
