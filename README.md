# ulid-java
![CI](https://github.com/ubtr/ubt-go/actions/workflows/ci.yml/badge.svg)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.jaspeen/ulid-java)](https://central.sonatype.com/artifact/io.github.jaspeen/ulid-java)
[![javadoc](https://javadoc.io/badge2/io.github.jaspeen/ulid-java/javadoc.svg)](https://javadoc.io/doc/io.github.jaspeen/ulid-java)
![CodeRabbit Pull Request Reviews](https://img.shields.io/coderabbit/prs/github/thelittlecormorant/ulid-java?utm_source=oss&utm_medium=github&utm_campaign=thelittlecormorant%2Fulid-java&labelColor=171717&color=FF570A&link=https%3A%2F%2Fcoderabbit.ai&label=CodeRabbit+Reviews)

Generate and parse ULIDs in Crockford base32 text and binary representations.

See [ULID specification](https://github.com/ulid/spec) for more info

## Key points
* Java 11+
* API similar to java.util.UUID
* Optional monotonic generator
* Optional hibernate type and ID generator (requires hibernate 6.x)
## Install
### Maven
```xml
<dependency>
    <groupId>io.github.jaspeen</groupId>
    <artifactId>ulid-java</artifactId>
    <version>0.2.0</version>
</dependency>
```
### Gradle
```groovy
dependencies {
    implementation 'io.github.jaspeen:ulid-java:0.2.0'
}
```

## Usage

### ULID generation
```java
ULID ulid = ULID.random();
String crockfordBase32 = ulid.toString();
byte[] binary = ulid.toBytes();
```

### Parsing
```java
ULID parsedFromString = ULID.fromString("3ZFXZQYZVZFXZQYZVZFXZQYZVZ");
ULID parsedFromBytes = ULID.fromBytes(
        new byte[] {127, 127, 127, 127, 127, 127, 127, 127, 127, 127, 127, 127, 127, 127, 127, 127});
assertEquals(parsedFromString, parsedFromBytes);
```

### UUID compatibility
```java
ULID.random().toUUID();
ULID.fromUUID(UUID.randomUUID());
```

### Monotonic ULID generation
```java
MonotonicULID.random();
```

### Hibernate ID generator
Hibernate is not added as transitive dependency, it should be specified additionally
```java
@Entity
class ULIDEntity {
    @Id
    @GeneratedValue(generator = "ulid")
    @GenericGenerator(name = "ulid", strategy = "io.github.jaspeen.ulid.hibernate.ULIDIdGenerator")
    private ULID id;
}
 
 // This will generate UUID using ULID algorithm providing ordered keys 
 // while keeping other stuff same
@Entity 
class UUIDEntity {
    @Id
    @GeneratedValue(generator = "ulid")
    @GenericGenerator(name = "ulid", strategy = "io.github.jaspeen.ulid.hibernate.ULIDIdGenerator")
    private UUID id;
}
```
Generator can be defined in package-info.java for all entities instead of field annotation in every entity
```java
@GenericGenerator(name = "ulid", strategy = "io.github.jaspeen.ulid.hibernate.ULIDIdGenerator")
package my.service.model;

import org.hibernate.annotations.GenericGenerator;
```

### Notes
_For java 8 and hibernate 5 use verions 0.1.x_
