package dev.blucobalt.rses;


import dev.blucobalt.rses.annotations.EventSubscriber;

import java.lang.annotation.Annotation;
import java.util.Set;


/**
 * the main event subsystem, used for setting the global package prefix and annotation
 */
public class EventSubSystem
{
    protected String packagePrefix;
    protected Class<? extends Annotation> eventAnnotation = EventSubscriber.class;

    /**
     * sets a global package prefix, using the default annotation for event-handling classes.
     *
     * @param packagePrefix the package prefix of event-handling classes
     * @see EventSubscriber
     */
    public EventSubSystem(String packagePrefix)
    {
        this.packagePrefix = packagePrefix;
    }

    /**
     * sets a global package prefix and annotation
     *
     * @param packagePrefix the package prefix of event-handling classes
     * @param annotation    the annotation that event-handling classes should be annotated with
     * @see EventSubscriber
     */
    public EventSubSystem(String packagePrefix, Class<? extends Annotation> annotation)
    {
        this.packagePrefix = packagePrefix;
        this.eventAnnotation = annotation;
    }
}
