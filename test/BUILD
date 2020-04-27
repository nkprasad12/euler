load("@rules_java//java:defs.bzl", "java_library", "java_test")
load("//test:generate_tests.bzl", "generate_targets_for_tests_in_subdirectories")

generate_targets_for_tests_in_subdirectories()

java_library(
    name = "assertions",
    srcs = ["assertions/Assertions.java"],
    deps = [
        "//lib:junit_jars",
        "//src/utils",
    ],
)
