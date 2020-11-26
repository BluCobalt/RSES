package dev.blucobalt.rses;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Event
{
    private String rootPackage;
    private Method[] eventMethods;

    public void pushEvent()
    {

    }
    protected void run(Class<?> aClass)
    {
        for (Method method: eventMethods)
        {
            try
            {
                method.invoke(aClass.newInstance());
            } catch (IllegalAccessException | InvocationTargetException | InstantiationException e)
            {
                e.printStackTrace();
            }
        }
    }
    public Event(Class<?> iface, EventSystem eventSystem) {}
    public Event(Class<?> iface, String basePackage) {}
    public Event(Class<?> iface, Class<? extends Annotation> annotation) {}
}
