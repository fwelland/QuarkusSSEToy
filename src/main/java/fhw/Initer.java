package fhw;

import io.quarkiverse.qute.web.DataInitializer;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class Initer
    implements DataInitializer     
{
    @Inject 
    Info info; 
    
    @Override
    public void initialize(InitContext ctx)
    {
        Log.infof("what is my path %s", ctx.path());
//        switch(ctx.path())
//        {
//            case String s when s.endsWith("index") -> index(); 
//            default  -> nothing();        
//        }
        if (ctx.path().endsWith("index"))
        {
            index();
            ctx.templateInstance().data("info", info);
        }
    }
    
    private void nothing()
    {
    
    }
    private void index()
    {
        Log.infof("in indx() method");
    }
}

