load("@rules_java//java:defs.bzl", "java_test")

def generate_all_tests(srcs):
  for src in srcs:
    test_class = "test." + src[:-5]
    test_name = test_class.split(".")[-1]
    native.java_test(
        name = test_name,
        test_class = test_class,    
        srcs = [src],
        deps = [
            "//test:assertions",
            "//lib:junit_jars",
            "//src/utils:utils",
        ]
    )