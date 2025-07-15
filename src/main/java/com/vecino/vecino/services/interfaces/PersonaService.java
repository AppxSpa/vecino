package com.vecino.vecino.services.interfaces;

import com.vecino.vecino.dto.PersonaDto;
import com.vecino.vecino.dto.PersonaRequest;
import com.vecino.vecino.entities.Persona;

public interface PersonaService {

    Boolean validateRut(String rut, String vrut) ;

     Persona createPersona(PersonaRequest personaRequest);

     PersonaDto getPersona(Integer rut);

     Persona findByRut(Integer rut);

     Persona save(Persona persona);

}
