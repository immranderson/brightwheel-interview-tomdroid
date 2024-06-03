# brightwheel-interview-tomdroid

Hello code reviewer! Welcome to my coding submission.

### Cool things to know:
The app utilizes Stateflow and Jetpack Compose. Kotlinx Serialization is used for deserializing the github API responses.

The app is multimodular to enforce separations of concerns. The logic lives in the :commons-data, :commons-ui, and :app modules.

Rather than going with the pagination approach, I simply checked out the github api and appended the query params that would get me the info I'm looking for in the appropriate order.

The newest version of Android studio now setup build.gradle.kts with a .toml library. If you're finding that you haven't encountered this yet, you can check it out [here!](/gradle/libs.versions.toml)

### Explanation of logic:

The ViewModel receives the results from the github api, transforms the data and displays it in the composable component. Tapping on the composable will expand it and execute an api call at the time of click to fetch the top rated contributor!
I considered the tradeoff of making all ~101 api calls in parallel at app launch to eagerly load the data for a smoother UI, but figured I'd be hiting the rate limiting that way. The response is cached in the VM, so subsequent taps of the same project won't trigger reloads.

### If I had more time:
- Better Error Handling! Github of course could throw out some rate limits or other errors, so I should refactor things to better handle that.
- UI/Integration testing.
- More Unit testing -- I got one in there though, just for good vibes :-)

