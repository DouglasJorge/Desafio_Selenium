# Upgrade Plan: Desafio_Selenium (20260602233546)

- **Generated**: 2026-06-02 23:35:00
- **HEAD Branch**: main
- **HEAD Commit ID**: N/A

## Available Tools

**JDKs**
- JDK 25.0.3: C:\Program Files\Eclipse Adoptium\jdk-25.0.3.9-hotspot\bin (required by step 1 and step 5)
- JDK 26.0.1: C:\Program Files\Java\jdk-26.0.1\bin (current environment default)

**Build Tools**
- Maven 3.9.15: C:\Users\djorger\Desktop\Automacao\apache-maven-3.9.15\bin

## Guidelines

> Note: You can add any specific guidelines or constraints for the upgrade process here if needed, bullet points are preferred.

## Options

- Working branch: appmod/java-upgrade-20260602233546
- Run tests before and after the upgrade: true

## Upgrade Goals

- Upgrade project runtime to Java 25 (latest LTS)

## Technology Stack

| Technology/Dependency | Current | Min Compatible | Why Incompatible |
| --------------------- | ------- | -------------- | --------------------------------------------- |
| Java | unspecified / Maven default | 25 | User requested latest LTS runtime |
| Maven | 3.9.15 | 3.9.0 | Compatible with Java 25 and current environment |
| maven-compiler-plugin | none configured | 3.11.0 | Required for proper Java 25 compilation support |
| maven-surefire-plugin | none configured | 3.1.2 | Recommended for JDK 17+ and higher test JVM compatibility |
| junit | 4.13.1 | 4.13.1 | Compatible but test plugin should be up to date |
| selenium-java | 4.43.0 | 4.43.0 | Compatible with latest Java runtime |

## Derived Upgrades

- Java 25 requires explicit `maven.compiler.source` and `maven.compiler.target` settings in `pom.xml`.
- Java 25 is best supported by `maven-compiler-plugin` 3.11.0.
- JDK 25 testing support is best enabled by `maven-surefire-plugin` 3.1.2.

## Impact Analysis

### Dependency Changes

| File | Dependency | Current | Action | Target | Reason |
|------|------------|---------|--------|--------|--------|
| pom.xml | maven.compiler.source property | not present | add | 25 | Set project source level to Java 25 |
| pom.xml | maven.compiler.target property | not present | add | 25 | Set project target level to Java 25 |
| pom.xml | maven-compiler-plugin | not present | add | 3.11.0 | Ensure build plugin supports Java 25 |
| pom.xml | maven-surefire-plugin | not present | add | 3.1.2 | Ensure test execution supports modern JDKs |

### Source Code Changes

No source code changes are required for the runtime upgrade itself.

### Configuration Changes

No application configuration file changes are needed.

### CI/CD Changes

No CI/CD files were detected in the repository root; none are required for this upgrade.

### Risks & Warnings

- **Java source/target compatibility**: The project does not currently declare a Java source level. A missing compiler configuration can lead Maven to use an older default. **Mitigation**: add explicit Java 25 settings and validate with `mvn clean test`.
- **JUnit 4 test execution on Java 25**: Explicit `maven-surefire-plugin` configuration will reduce risk of test JVM incompatibility.

## Upgrade Steps

- Step 1: Setup Environment
  - **Rationale**: Confirm required Java 25 and Maven 3.9.15 tools are available before changing project files.
  - **Changes to Make**: No project file changes; verify JDK and Maven availability.
  - **Verification**: `java -version && mvn -v` with JDK 25 available and Maven 3.9.15.

- Step 2: Setup Baseline
  - **Rationale**: Capture current build/test status before upgrading the runtime.
  - **Changes to Make**: No code changes; run current build and test suite on the current environment.
  - **Verification**: `mvn clean compile test-compile -q && mvn clean test -q`

- Step 3: Apply Java 25 Runtime Upgrade
  - **Rationale**: Update `pom.xml` to target Java 25 and ensure compiler/test plugins are compatible.
  - **Changes to Make**: Add Java 25 compiler properties and add `maven-compiler-plugin` 3.11.0 plus `maven-surefire-plugin` 3.1.2.
  - **Verification**: `mvn clean test-compile -q` using JDK 25.

- Step 4: CVE Validation & Fix
  - **Rationale**: Confirm direct dependencies are current and apply patch-level updates if a CVE scan reports issues.
  - **Changes to Make**: Run CVE scan on direct dependencies and update versions only if needed.
  - **Verification**: `mvn clean test-compile -q` after any fixes.

- Step 5: Final Validation
  - **Rationale**: Verify the completed upgrade compiles and all tests pass on Java 25.
  - **Changes to Make**: Resolve any compilation or test failures discovered after the upgrade.
  - **Verification**: `mvn clean test -q` using JDK 25.
