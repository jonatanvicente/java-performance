


## Java Performance Optimization

Repository with resources and examples for optimizing Java applications for performance.

### Versions

* **Java 17**: The examples and benchmarks are primarily designed for Java 17, but many concepts apply to earlier versions as well.
* **Gradle 8.11**.
* **JMH 1.37**: The benchmarks use the Java Microbenchmark Harness (JMH) for accurate performance measurements.

### Folder Structure

* **jmh.java.performance**: Contains examples of Java Microbenchmark Harness (JMH) benchmarks and others.
* **star-wars**: A simple Spring Boot application that serves as a demo for performance optimization techniques.
* **gc-tuning**: Contains examples and explanations for tuning the Java Garbage Collector (GC) to improve performance. See [README](./gc-tuning/README.md) for details.
### Useful commands

```
./gradlew :jmh-samples:jmh
./gradlew :jmh-benchmarking:jmh
```

### Resources

* [Java Performance](https://www.oreilly.com/library/view/java-performance-2nd/9781492056102/) by Scott Oaks
* [Effective Java](https://www.oreilly.com/library/view/effective-java-3rd/9780134686097/) by Joshua Bloch
* [Shipilev's Blog](https://shipilev.net/)
* [JMH Documentation](https://openjdk.org/projects/code-tools/jmh/)
* [Java GC Tuning Guide](https://docs.oracle.com/en/java/javase/17/gctuning/index.html)
* [VisualVM Guide](https://visualvm.github.io/)



[![Contributor Covenant](https://img.shields.io/badge/Contributor%20Covenant-v2.0%20adopted-ff69b4.svg)](code_of_conduct_EN.md)
[![Contributor Covenant](https://img.shields.io/badge/Contributor%20Covenant-v2.0%20adopted-ff69b4.svg)](code_of_conduct_ES.md)
[![Contributor Covenant](https://img.shields.io/badge/Contributor%20Covenant-v2.0%20adopted-ff69b4.svg)](code_of_conduct_CA.md) 