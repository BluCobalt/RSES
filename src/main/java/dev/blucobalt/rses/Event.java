package dev.blucobalt.rses;

import dev.blucobalt.rses.annotations.EventSubscriber;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * defines the event.
 */
public class Event
{
    private final Class<?> eventInterface;
    private final Reflections reflections;
    private final Class<? extends Annotation> eventAnnotation;

    /**
     * pushes the event to subscribers.
     */
    public void pushEvent()
    {
        this.run();
    }

    /**
     * pushes the event to subscribers along with an object.
     *
     * @param obj object to push to subscribers
     */
    public void pushEvent(Object obj)
    {
        this.run(obj);
    }

    /**
     * runs the event
     */
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

    /**
     * runs the event with object
     *
     * @param obj object
     */
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

    /**
     * @return the methods from the interface
     */
    private Method[] getIFaceMethods()
    {
        return this.eventInterface.getMethods();
    }

    /**
     * @return all annotated classes
     */
    private Set<Class<?>> getAnnotatedClasses()
    {
        return reflections.getTypesAnnotatedWith(this.eventAnnotation);
    }

    /**
     * creates an event, using the global EventSubSystem.
     *
     * @param iface the event's interface
     * @param ess   the global EventSubSystem
     * @see EventSubSystem
     * @see EventSubscriber
     */
    public Event(Class<?> iface, EventSubSystem ess)
    {
        this.eventInterface = iface;
        this.eventAnnotation = ess.eventAnnotation;
        this.reflections = new Reflections(ess.packagePrefix);
    }

    /**
     * creates an event, completely independent of any and all other events, not requiring an instance of EventSubSystem.
     *
     * @param iface         the event's interface
     * @param packagePrefix the package prefix of event-handling classes
     * @see EventSubscriber
     */
    public Event(Class<?> iface, String packagePrefix)
    {
        this.eventInterface = iface;
        this.eventAnnotation = EventSubscriber.class;
        this.reflections = new Reflections(packagePrefix);
    }

    /**
     * creates an event, completely independent of any and all other events, using it's own custom annotation,
     * not requiring an instance of EventSubSystem.
     *
     * @param iface         the event's interface
     * @param annotation    custom event subscriber annotation,
     * @param packagePrefix the package prefix of event-handling classes
     * @see EventSubscriber
     */
    public Event(Class<?> iface, Class<? extends Annotation> annotation, String packagePrefix)
    {
        this.eventInterface = iface;
        this.eventAnnotation = annotation;
        this.reflections = new Reflections(packagePrefix);
    }
}
