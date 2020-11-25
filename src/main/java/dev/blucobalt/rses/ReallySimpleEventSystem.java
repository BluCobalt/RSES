package dev.blucobalt.rses;

import org.reflections.Reflections;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// TODO: better javadocs
public class ReallySimpleEventSystem
{
    /**
     * @param rootPackage the base package
     */
    public ReallySimpleEventSystem(String rootPackage)
    {
        r = new Reflections(rootPackage);
    }
    /**
     * runs all the given interface's methods in all classes annotated with the given annotation
     *
     * @param annotation the annotation that the classes that handle the event are annotated with.
     * @param iface      the interface that supplies the event
     */
    public void runAnnotated(Class<? extends Annotation> annotation, Class<?> iface)
    {
        if (!iface.isInterface()) throw new IllegalArgumentException("An interface was expected, but not supplied.");
        Class<?>[] annotatedClasses = r.getTypesAnnotatedWith(annotation).toArray(new Class[0]);
        Method[] interfaceMethods = iface.getMethods();
        for (Method interfaceMethod : interfaceMethods)
        {
            for (Class<?> annotatedClass : annotatedClasses)
            {
                try
                {
                    Method method = annotatedClass.getMethod(interfaceMethod.getName());
                    method.invoke(annotatedClass.newInstance());
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
    private final Reflections r;
}
