def generate_targets_for_tests_in_subdirectories():
    """Generates test targets for all tests in subdirectories."""
    srcs = native.glob(["**/*Test.java"])
    for src in srcs:
        # Changes foo/bar/BazTest.java to test.foo.bar.BazTest
        test_class = "test." + src[:-5].replace("/", ".")

        # Gets BazTest from test.foo.bar.BazTest
        test_name = test_class.split(".")[-1]
        native.java_test(
            name = test_name,
            test_class = test_class,
            srcs = [src],
            deps = [
                "//test:assertions",
                "//lib:junit_jars",
                "//src/utils:utils",
            ],
        )
