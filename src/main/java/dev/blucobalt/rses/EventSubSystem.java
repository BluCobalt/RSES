package dev.blucobalt.rses;


import dev.blucobalt.rses.annotations.EventSubscriber;

import java.lang.annotation.Annotation;


// TODO: better javadocs
public class EventSubSystem
{
    protected String basePackage;
    protected Class<? extends Annotation> eventAnnotation = EventSubscriber.class;
    public EventSubSystem(String basePackage)
    {
        this.basePackage = basePackage;
    }
    public EventSubSystem(String basePackage, Class<? extends Annotation> annotation)
    {
        this.basePackage = basePackage;
        this.eventAnnotation = annotation;
    }
}
