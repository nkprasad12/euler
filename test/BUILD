load("@rules_java//java:defs.bzl", "java_test", "java_library", "java_import")

java_test(
    name = "all_tests",
    srcs = ["utils/datastructures/DisjointSetTest.java"],
    test_class = "test.utils.datastructures.DisjointSetTest",
    deps = [
        "//src/utils:utils",
        ":assertions",
        ":junit_jars"
    ]
)

java_library(
    name = "assertions",
    srcs = ["Assertions.java"],
    deps = [":junit_jars"]
)

java_import(
    name = "junit_jars",
    jars = [
      "lib/hamcrest-core-1.3.jar",
      "lib/junit-4.13.jar"
    ]
)