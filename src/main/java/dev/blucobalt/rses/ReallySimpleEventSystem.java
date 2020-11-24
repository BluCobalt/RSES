package dev.blucobalt.rses;

import org.reflections.Reflections;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// TODO: better javadocs
public class ReallySimpleEventSystem
{
    /**
     *
     * @param rootPackage the base package
     */
    public ReallySimpleEventSystem(String rootPackage)
    {
        r = new Reflections(rootPackage);
    }

    /**
     * runs all the given interface's methods in all classes annotated with the given annotation
     * @param annotation the annotation that the classes that handle the event are annotated with.
     * @param iface the interface that supplies the event
     */
    public void runAnnotated(Class<? extends Annotation> annotation, Class<?> iface)
    {
        for (Class<?> child: r.getTypesAnnotatedWith(annotation))
        {
            Method[] parentMethods = iface.getMethods();
            for (Method parentMethod: parentMethods)
            {
                try
                {
                     Method childMethod = child.getMethod(parentMethod.getName());
                     childMethod.invoke(child);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e)
                {
                    e.printStackTrace();
                    // TODO: better error handling
                }

            }
        }
    }
    private final Reflections r;
}
