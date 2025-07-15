package com.vecino.vecino.services.interfaces;

import com.vecino.vecino.dto.CreateDireccionResponse;
import com.vecino.vecino.dto.DireccionRequest;

public interface ApiDireccionService {



    CreateDireccionResponse createDireccion(DireccionRequest request);

    

}
