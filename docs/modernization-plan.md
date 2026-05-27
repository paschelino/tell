# Tell - Modernization Plan

## Objective

Bring the outdated Kotlin project up to the latest stable versions and migrate tests to a modern, canonical Kotlin testing framework.

---

## Phase 1: Upgrade Toolchain & Build System

### 1.1 Current State vs. Target State

| Component          | Current                              | Target                                |
| ------------------ | ------------------------------------ | ------------------------------------- |
| **Kotlin**         | `1.2.10` (2017)                      | `2.3.21` (Latest Stable, April 2026)  |
| **Gradle**         | `4.0` (2017)                         | `9.5.1` (Required for Kotlin 2.3.20+) |
| **JVM Target**     | `1.8`                                | `21` (Latest LTS)                     |
| **Build Script**   | Groovy `build.gradle`                | Kotlin DSL `build.gradle.kts`         |
| **Test Framework** | Spek 1.1.5 (unmaintained)            | **Kotest 6.x** (canonical choice)     |
| **CI Image**       | `circleci/openjdk:8-jdk`             | `cimg/openjdk:21.0`                   |
| **Repositories**   | `jcenter()` + `bintray` (deprecated) | `mavenCentral()` only                 |

### 1.2 Execution Steps

#### Step 1: Upgrade Gradle Wrapper

- Update `gradle/wrapper/gradle-wrapper.properties` to use **Gradle 9.5.1**.
- _Rationale:_ Kotlin 2.3.20+ requires Gradle 9.5.0+, and Java 25 (the local environment) requires Gradle 9.1.0+.

#### Step 2: Migrate Build Script to Kotlin DSL

- Rename `build.gradle` → `build.gradle.kts`.
- Convert all Groovy syntax to Kotlin DSL.
- Replace deprecated configurations (`compile`, `testCompile`) with modern ones (`implementation`, `testImplementation`).
- Clean up repositories: **Remove `jcenter()` and Bintray** (both are defunct), keep only `mavenCentral()`.

#### Step 3: Update Kotlin & JVM Configuration

- Apply `kotlin("jvm")` plugin version `2.3.21`.
- Configure **Java Toolchain** for JVM 21:
  ```kotlin
  java {
      toolchain {
          languageVersion = JavaLanguageVersion.of(21)
      }
  }
  ```
- Set Kotlin compiler target:
  ```kotlin
  kotlin {
      compilerOptions {
          jvmTarget = JvmTarget.JVM_21
      }
  }
  ```
- Update dependencies: Remove deprecated `kotlin-stdlib-jdk8`; `kotlin-stdlib` now includes all JDK 8+ artifacts by default.

#### Step 4: Remove Legacy JUnit Platform Plugin

- The `org.junit.platform.gradle.plugin` is obsolete and incompatible with modern Gradle.
- Delete the entire `junitPlatform { ... }` block.
- Delete the custom `junitPlatformTest` and `junitPlatformJacocoReport` task overrides.
- Configure the standard Gradle `test` task to use JUnit Platform (required for Kotest):
  ```kotlin
  tasks.test {
      useJUnitPlatform()
  }
  ```

#### Step 5: Modernize JaCoCo

- The built-in `jacoco` plugin now integrates automatically with the standard `test` task.
- Remove manual JaCoCo task wiring. The standard `jacocoTestReport` task will suffice.

#### Step 6: Add Detekt (Static Analysis)

- Apply `io.gitlab.arturbosch.detekt` plugin.
- Connect it to the existing `detekt-config.yml` in the project root.
- This is a standard practice in modern Kotlin projects and ensures code quality as the language evolves.

#### Step 7: Update CI/CD (`.circleci/config.yml`)

- Switch Docker image to `cimg/openjdk:21.0`.
- Replace all `./gradlew junitPlatformTest` calls with `./gradlew test`.
- Replace `./gradlew junitPlatformJacocoReport` with `./gradlew jacocoTestReport`.
- Update cache keys and artifact paths if needed.

---

## Phase 2: Migrate Tests (Spek → Kotest)

### 2.1 Why Kotest?

[Kotest](https://kotest.io/) is the **canonical**, modern, and actively maintained testing framework for Kotlin. It provides:

- A rich, Kotlin-idiomatic DSL (no Java-style `assertThat(...)` required).
- Multiple spec styles (DescribeSpec, FunSpec, BehaviorSpec, etc.).
- Excellent assertion library (`kotest-assertions-core`) with elegant infix notation (e.g., `x shouldBe y`).
- First-class support for property-based testing, data-driven tests, and coroutines.
- Native JUnit 5 platform integration, so it works out of the box with Gradle's `test` task.

### 2.2 Execution Steps

#### Step 1: Swap Test Dependencies

**Remove:**

- `org.jetbrains.spek:spek-api:1.1.5`
- `org.jetbrains.spek:spek-junit-platform-engine:1.1.5`
- `org.hamcrest:hamcrest-junit:2.0.0.0`

**Add:**

- `io.kotest:kotest-runner-junit5:6.1.11` (or latest stable 6.x)
- `io.kotest:kotest-assertions-core:6.1.11`

#### Step 2: Refactor Test Files

There are **5 test files** to convert:

1. `src/test/kotlin/de/paschelino/tell/UriSpec.kt`
2. `src/test/kotlin/de/paschelino/tell/parts/SchemaSpec.kt`
3. `src/test/kotlin/de/paschelino/tell/parts/SegmentSpec.kt`
4. `src/test/kotlin/de/paschelino/tell/parts/CharacterClassesSpec.kt`
5. `src/test/kotlin/de/paschelino/tell/parts/PathSpec.kt`

**Conversion Strategy:**

| Spek Construct                        | Kotest Equivalent           | Notes                                                                   |
| ------------------------------------- | --------------------------- | ----------------------------------------------------------------------- |
| `object X : Spek({`                   | `class X : DescribeSpec({`  | `DescribeSpec` is the closest semantic match to Spek's `describe`/`it`. |
| `describe("...") {`                   | `describe("...") {`         | Syntax is nearly identical.                                             |
| `it("...") {`                         | `it("...") {`               | Syntax is identical.                                                    |
| `assertThat(x, \`is\`(y))`            | `x shouldBe y`              | Clean, idiomatic Kotlin.                                                |
| `assertThat(x, instanceOf(Y::class))` | `x shouldBeInstanceOf<Y>()` | Type-safe.                                                              |
| `try { ... } catch(e: E) { ... }`     | `shouldThrow<E> { ... }`    | More expressive.                                                        |

**Special Attention:**

- **`SchemaSpec.kt`** contains **dynamic tests** inside a `for` loop (iterating over special characters). Kotest handles this naturally inside a `describe` block.
- **`CharacterClassesSpec.kt`** also uses a `for` loop inside `describe`. This will work identically in Kotest.

#### Step 3: Example Conversion Preview

A snippet from `SchemaSpec.kt`:

**Before (Spek + Hamcrest):**

```kotlin
it("accepts lower case alphanumeric characters") {
    assertThat(Schema("a").toString(), `is`("a"))
}
it("may not start with the $s") {
    var thrown : MalformedException? = null
    try { Schema(s) } catch (e: MalformedException) { thrown = e }
    assertThat(thrown, `is`(instanceOf(MalformedException::class.java)))
    assertThat(thrown?.message, `is`("Schema may not start with '$s'!"))
}
```

**After (Kotest):**

```kotlin
it("accepts lower case alphanumeric characters") {
    Schema("a").toString() shouldBe "a"
}
it("may not start with the $s") {
    val thrown = shouldThrow<MalformedException> { Schema(s) }
    thrown.message shouldBe "Schema may not start with '$s'!"
}
```

#### Step 4: Verification

- Run `./gradlew test` to ensure all tests pass.
- Confirm `./gradlew jacocoTestReport` generates the coverage report.
- Confirm `./gradlew detekt` passes (or report findings).

---

## Post-Migration State

After execution, the project will be on a **state-of-the-art Kotlin stack**:

- **Kotlin 2.3.21** with the K2 compiler.
- **Gradle 9.5.1** with Kotlin DSL.
- **JVM 21** target.
- **Kotest 6.x** for idiomatic, modern testing.
- **Detekt** for static analysis.
- A clean CI pipeline running on a current OpenJDK image.

---

## Execution Order

1. Complete Phase 1 in full (build script, CI, etc.).
2. Verify the main source still compiles (`./gradlew build` passes, ignoring test failures rooted in Spek).
3. Complete Phase 2 (test migration).
4. Final verification: All tests pass, CI is green.
