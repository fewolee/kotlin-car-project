rootProject.name = "car"

// module의 등록
include(
    ":modules:bootstrap",
    ":modules:connector",
    ":modules:application",
    ":modules:domain",
    ":modules:adapter-data-jpa",
)