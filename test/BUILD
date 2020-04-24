load("@rules_java//java:defs.bzl", "java_test", "java_library")
load("//test:generate_all_tests.bzl", "generate_all_tests")

generate_all_tests(
    srcs = glob(["**/*Test.java"])
)

java_test(
    name = "DisjointSetTestOG",
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
