# android-identicons

An Android library for generating [Identicons](https://en.wikipedia.org/wiki/Identicon) — unique, deterministic visual identifiers derived from hash values. Supports both traditional XML Views and Jetpack Compose.

Two styles are available:
- **Classic** — a 3x3 grid with 37 possible geometric shapes per tile (9 for the central one), with per-tile rotation and color derived from the hash.
- **GitHub-style** — a 5x5 grid with binary on/off tiles and mirror symmetry, similar to GitHub's default avatars.

![identicons](https://github.com/lelloman/android-identicons/blob/master/meta/identicons.png)

![identicons](https://github.com/lelloman/android-identicons/blob/master/meta/githubbicons.png)

## Setup

The library is distributed via [JitPack](https://jitpack.io/#lelloman/android-identicons). Min SDK is 21.

In your `settings.gradle.kts`, add the JitPack repository:
```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}
```

In your module's `build.gradle.kts`:
```kotlin
implementation("com.github.lelloman:android-identicons:v1.6")
```

## Usage

### Jetpack Compose

```kotlin
import com.lelloman.identicon.compose.ClassicIdenticon
import com.lelloman.identicon.compose.GithubIdenticon

// hash is a ByteArray (e.g. from MD5, SHA, or any byte source)
ClassicIdenticon(
    hash = myHash,
    modifier = Modifier.size(96.dp)
)

GithubIdenticon(
    hash = myHash,
    modifier = Modifier.size(96.dp)
)
```

### XML Views

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

The `hash` XML attribute accepts an integer, which is internally converted to a `ByteArray` via MD5. You can also set the hash programmatically:

```kotlin
identiconView.hash = myByteArrayHash
```

### Drawables

For custom rendering you can use the drawable classes directly:

```kotlin
val drawable = ClassicIdenticonDrawable(width, height, hash)
drawable.draw(canvas)
```

## How it works

The input `ByteArray` is interpreted bit by bit to determine tile shapes, rotations, inversion flags, and the foreground color. When an `Int` is used (e.g. from the XML attribute), it is first hashed with MD5 to produce a 16-byte array, expanding the visual space.

---

They can also make good textures for hum carpets, or tiles for the bathroom

![carpet1](https://github.com/lelloman/android-identicons/blob/master/meta/carpet1.png)
![carpet2](https://github.com/lelloman/android-identicons/blob/master/meta/carpet2.png)
![carpet3](https://github.com/lelloman/android-identicons/blob/master/meta/carpet3.png)
