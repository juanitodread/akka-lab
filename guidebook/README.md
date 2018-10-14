# guidebook

### How to run in different JVM's

You need to run Guidebook and Tourist actor systems in different terminals

**Run the Guidebook actor system on port 8081**
```
> sbt "-Dakka.remote.netty.tcp.port=8081" "runMain org.juanitodread.guidebook.GuidebookMain"
```

**Run the Tourist actor system on random port**
```
>  sbt "runMain org.juanitodread.guidebook.TouristMain"
```

If everything runs well both actor systems will start to communicate between them.