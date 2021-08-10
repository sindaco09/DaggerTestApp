# DaggerTestApp
## About
Provide a guide and document my research into testing with Android for both unit and instrumentation tests. 

At the time of writing this, I’m using the following github as a reference to what i’ve tested
https://github.com/sindaco09/DaggerTestApp/tree/hilt
The main branch uses Dagger . The primary branch is hilt which uses Hilt 

## Unit Tests

I setup my unit testing environment with the following libraries:
* Google Truth - nice assertion class, easy to read
* Mockk - mocking library
  * Great for mocking classes when unit testing classes with dependency injection like a ViewModel or a Repository. You don’t have to mock everything, just the variables in the constructor

### JUnit Testing Frameworks
* JUnit 4 is primarily supported testing framework
  * pros
    * It’s supported directly by android and works well with all 3rd party libraries
    * Works out of the box, no extra steps needed 
  * cons
    * It’s outDated. JUnit 5 has been around for 4 years now
* JUnit 5 released in 2017
  * pros
    * Functions are easier to read, 
      * fun `test name can look like this`()
      * fun insteadOfThis()
      * fun or_instead_of_this()
    * Can have conditional tests
    * Can disable tests
    * Nested tests
    * Useful tutorials
      * https://medium.com/@boonkeat/android-unit-testing-with-junit5-d1b8f9c620b6
  * cons
    * Requires some boilerplate 
    * Need to either build or use a 3rd party library testRunner
      * https://github.com/mannodermaus/android-junit5
      * This is the only one i’ve found that seems to have a lot of support and is used/suggested by the community

## Instrumentation Tests

To set-up instrumentation tests, I found the best combination of libraries to be the following
* Mockk - mocking library for kotlin
  * https://mockk.io/
  * Works better than Mockito for kotlin classes
  * Heavily supported and maintained
* JUnit 4
  * Would love to use JUnit 5 but not fully or easily supported, especially when using Dependency Injection in activities
  * It’s just easier to use JUnit 4 and not have to worry about workarounds for everything
* Espresso - used to write the UI tests
  * For asynchronous operations on different threads, it’s helpful to use a CountingIdlingResource
    * This is a user defined counter placed in the main code. When the counter == 0, the system is idle. In the main code it doesn’t do anything. In the testing environment, it lets espresso know when the system is done working
    * In the main directory, I use TestCountingIdlingResource.kt
      * When new threads are started, this resource is incremented, when threads close, it’s decremented
  * Testing takes a bit longer
    * More realistic to what it looks like if a user was actually testing. Entering text is char by char, almost like watching the screen of a QA tester
  * Only operates when the system is in an Idle State	

## Challenges

With the migration to Kotlin, testing for kotlin activities/fragments was heavily impacted because kotlin classes, by default, are private. Although immutable (java final) variables are not a new concept, they’re more prevalent (for good reason) when writing kotlin classes but make mocking tricky. One of the biggest challenges was getting around not being able to mock already existing val or final variables not defined in a constructor
* If an Activity injects a viewmodel, you cannot reassign the viewModel if the viewModel is a val
  * To get around the viewModel problem, I aim to mock a data layer. I find it best to go to the highest layer available and mock that (An API or CACHE/DAO class).


### Hilt Challenges
* Testing a Hilt Fragment requires a Hilt Activity parent to work.
  * https://developer.android.com/training/dependency-injection/hilt-testing
  * Android provided a handy inline function to host fragments called launchFragmentInContainer<>() and launchFragment<>()
  * The current workaround for this is to create a blank Hilt activity called HiltTestActivity and host it there. Until this is fixed, this is the way it has to be done. An example from the link above can be found at this github repo
    * https://github.com/android/architecture-samples
    * I had to create the HiltTestActivity in a debug directory with a separate AndroidManifest that didn’t export the activity to get around this. It couldn’t be created in AndroidTest.
      * I believe it could be made in the main directory but i wanted to avoid any floating activities that weren’t being used by the main code
* Mocking Hilt injected objects can be done, but they require a module if they can be mocked for testing. This is why I opted to test at the highest level module to avoid making a bunch of modules. 
  * I mocked CacheModule which could be synonymous to UserCache, BartCache, AccountCache, etc.. 
  * It’s best to make one module per object you are providing vs the old dagger way where there was one module NetworkModule for 10+ provided objects.
  * In a UI test, you can Uninstall a module like CacheModule and replace it with a mock defined in the test
    * This is why it’s handy to have 1 provided object per module so if you replace 1 object, like CacheModule, you don’t have to replace them all
  * You can also make a class to uninstall CacheModule everywhere and replace it with something else as well
    * androidTest/java/.../data/network/TestOkHttpModule
    * I add a mock interceptor to intercept outgoing network calls and returning json responses

### MockK Challenges
* With the transition from Rules to Scenarios, certain APIs weren’t working correctly due to the order of which things were initialized
* In order for a test to run as expected
  1. Make sure mocks are set up to return whatever mock responses you want FIRST
  2. Inject HiltAndroidRule
  3. Launch activity last.
* An inline function lazyActivityScenarioRule<>() was created to delay when an activity was started but initialize what activity was being created


