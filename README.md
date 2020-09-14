# Kotlin demo

## General description

The project is split into two gradle modules: **core** and **app**.
**core** is a kotlin module without any platform dependencies.
It contains an implementation of the main datasource - **CharactersRepository**,
and the abstractions of a platform-dependant services.
**app** module in its turn is supposed to contain the implementations of the services and UI and to bind everything together.

## UI pattern

**MVVM** was chosen as the UI logic pattern. **app** uses **androidx.lifecycle.ViewModel**.
**databinding** is used to bind actual data to a UI elements.
VMs use reactive streams approach to request data from the **CharactersRepository**.

## CharactersRepository

That is the main datasource of the application. It uses Room as a persistent storage service and Retrofit as a network API access service.
Every data layer (memory cache, persistent storage and network) uses its own data type.

## Used libraries

- AndroidX
- [Glide](https://github.com/bumptech/glide)
- [Koin](https://insert-koin.io/)
- [Reactive Streams](http://www.reactive-streams.org/)
- [Retrofit](https://github.com/square/retrofit)