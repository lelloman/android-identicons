# android-identicons
Simple implementation of Identicons in android, 9 tiles per identicon, each tile has 32 possible shapes (8 for the central one).
The library can be added as dependency with gradle, in top level `build.gradle`:
```groovy
allprojects {
    repositories {
        jcenter()
        maven { url "https://jitpack.io" }
    }
}
```
in the module `build.gradle`
```groovy
implementation 'com.github.lelloman:android-identicons:v11'
```
then use one of the 2 View available:
```xml
<com.lelloman.identicon.view.ClassicIdenticonView
    android:layout_width="96dp"
    android:layout_height="96dp"
    app:hash="1867167182" />

<com.lelloman.identicon.view.GithubIdenticonView
    android:layout_width="96dp"
    android:layout_height="96dp"
    app:hash="654984321" />
```
![identicons](https://github.com/lelloman/android-identicons/blob/master/meta/identicons.png)

![identicons](https://github.com/lelloman/android-identicons/blob/master/meta/githubbicons.png)

they can also make good textures for hum carpets, or tiles for the bathroom

![carpet1](https://github.com/lelloman/android-identicons/blob/master/meta/carpet1.png)
![carpet2](https://github.com/lelloman/android-identicons/blob/master/meta/carpet2.png)
![carpet3](https://github.com/lelloman/android-identicons/blob/master/meta/carpet3.png)
