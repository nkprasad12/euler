load("@rules_java//java:defs.bzl", "java_test", "java_library")

java_test(
    name = "all_tests",
    srcs = ["utils/datastructures/DisjointSetTest.java"],
    test_class = "test.utils.datastructures.DisjointSetTest",
    deps = [
        ":assertions",
        "//lib:junit_jars",
        "//src/utils:utils",
    ]
)

java_library(
    name = "assertions",
    srcs = ["Assertions.java"],
    deps = [
        "//lib:junit_jars",
        "//src/utils:utils"
    ]
)
