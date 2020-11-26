package dev.blucobalt.rses;


import java.lang.annotation.Annotation;


// TODO: better javadocs
public class EventSystem
{
    protected String eventPackage;
    protected Class<? extends Annotation> eventAnnotation;
    public EventSystem(String eventPackage)
    {
        this.eventPackage = eventPackage;
    }
    public EventSystem(String eventPackage, Class<? extends Annotation> annotation)
    {

    }
}
