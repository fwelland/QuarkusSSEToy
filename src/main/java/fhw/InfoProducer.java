package fhw;

import jakarta.enterprise.inject.Produces;
import java.time.LocalDateTime;


public class InfoProducer
{
    @Produces
    Info makeSomeInfo()
    {
        return(new Info("Hey", LocalDateTime.now().toString())); 
    }
}
