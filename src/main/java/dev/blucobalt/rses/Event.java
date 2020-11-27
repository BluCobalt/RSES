package dev.blucobalt.rses;

import dev.blucobalt.rses.annotations.EventSubscriber;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

// todo: better javadocs and inline documentation
public class Event
{
    private final Class<?> eventInterface;
    private final Reflections reflections;
    private final Class<? extends Annotation> eventAnnotation;
    public void pushEvent()
    {
        this.run();
    }
    public void pushEvent(Object obj)
    {
        this.run(obj);
    }
    private void run()
    {
        for (Class<?> annotatedClass : this.getAnnotatedClasses())
        {
            if (eventInterface.isAssignableFrom(annotatedClass))
            {
                for (Method method : this.getIFaceMethods())
                {
                    try
                    {
                        Method methodToCall = annotatedClass.getMethod(method.getName());
                        try
                        {
                            methodToCall.invoke(annotatedClass.newInstance());
                        } catch (IllegalAccessException | InvocationTargetException | InstantiationException e)
                        {
                            e.printStackTrace();
                        }
                    } catch (NoSuchMethodException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    private void run(Object obj)
    {
        for (Class<?> annotatedClass : this.getAnnotatedClasses())
        {
            if (eventInterface.isAssignableFrom(annotatedClass))
            {
                for (Method method : this.getIFaceMethods())
                {
                    try
                    {
                        Method methodToCall = annotatedClass.getMethod(method.getName(), method.getParameterTypes());
                        try
                        {
                            methodToCall.invoke(annotatedClass.newInstance(), obj);
                        } catch (IllegalAccessException | InvocationTargetException | InstantiationException e)
                        {
                            e.printStackTrace();
                        }
                    } catch (NoSuchMethodException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    private Method[] getIFaceMethods()
    {
        return this.eventInterface.getMethods();
    }
    private Set<Class<?>> getAnnotatedClasses()
    {
        return reflections.getTypesAnnotatedWith(this.eventAnnotation);
    }
    public Event(Class<?> iface, EventSubSystem ess)
    {
        this.eventInterface = iface;
        this.eventAnnotation = ess.eventAnnotation;
        this.reflections = new Reflections(ess.basePackage);
    }
    public Event(Class<?> iface, Class<? extends Annotation> annotation, String basePackage)
    {
        this.eventInterface = iface;
        this.eventAnnotation = annotation;
        this.reflections = new Reflections(basePackage);
    }
    public Event(Class<?> iface, String basePackage)
    {
        this.eventInterface = iface;
        this.eventAnnotation = EventSubscriber.class;
        this.reflections = new Reflections(basePackage);
    }
}
