package fhw;

import jakarta.enterprise.inject.Produces;


public class InfoProducer
{
    @Produces
    //@RequestScoped
    Info makeSomeInfo()
    {
        return(new Info("Hey", "timestamp")); 
    }
}
