load("@rules_java//java:defs.bzl", "java_library")

java_library(
    name = "problem_solutions",
    srcs = glob(["**/*.java"]),
    visibility = [
        "//test:__subpackages__",
    ],
    deps = ["//src/utils"],
)
