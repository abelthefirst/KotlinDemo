# Kotlin demo
![Master branch](https://github.com/abelthefirst/KotlinDemo/workflows/Build%20debug%20APK/badge.svg)

## General description

The project is split into two gradle modules: **core** and **app**.
**core** is a kotlin module without any platform dependencies.
It contains an implementation of the main datasource - **CharactersRepository**,
and the abstractions of a platform-dependant services.
**app** module in its turn is supposed to contain the implementations of the services and UI and to bind everything together.

## UI pattern

**MVVM** was chosen as the UI logic pattern. **app** uses **androidx.lifecycle.ViewModel**.
**databinding** is used to bind actual data to a UI elements.

## CharactersRepository

That is the main datasource of the application.
It uses Room as a persistent storage service and Retrofit as a network API access service.
Every data layer (memory cache, persistent storage and network) uses its own data type.
Besides that, every layer is abstracted as a data access strategy ranked by an "access cost".
Those strategies are organized as the chain where the root of the chain is the fastest source.

## Used libraries

- AndroidX (UI, Room, ViewModels)
- [Glide](https://github.com/bumptech/glide)
- [Koin](https://insert-koin.io/)
- [Retrofit](https://github.com/square/retrofit)

## Branches
- master branch contains an implementation based on Kotlin coroutines
- pagination branch contains a set of refactorings, which enable pagination on the network level
- rxjava in turn uses [Reactive Streams](http://www.reactive-streams.org/)
