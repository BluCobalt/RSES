# RSES
[![Build Status](https://ci.blucobalt.dev/job/rses/badge/icon)](https://ci.blucobalt.dev/job/rses/)
<br>
really simple event system
<br>
*please note that this project is a work in progress and any and all aspects could be changed, altered, or removed at anytime without notice*

## a quick, boiled down example:
### Main.java
```java 
import dev.blucobalt.rses.*;

public class Main
{
    public static void main(String[] args)
    {
        ReallySimpleEventSystem rses = new ReallySimpleEventSystem("com.example");
        rses.runAnnotated(EventSubscriber.class, SampleEvent.class);
    }
}
```
### EventSubscriber.java
```java
public @interface EventSubscriber
{}
```
### SampleEvent.java
```java
public interface SampleEvent
{
    void sampleEvent();
}
```
### EventHandler.java
```java
@EventSubscriber
public class EventHandler
    implements SampleEvent
{
    @Override
    public void sampleEvent()
    {
        System.out.println("event handled!");
    }
}
```
