package kr.co.test.fejoo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["kr.co.test.fejoo"])
// @EntityScan(basePackages = ["kr.co.hmcnetworks.backoffice.commons.data", "kr.co.hmcnetworks.backoffice.domains.company", "kr.co.hmcnetworks.backoffice.domains.caremate"])
class BackofficeBusinessManagementApp

fun main(args: Array<String>) {
    runApplication<BackofficeBusinessManagementApp>(*args)
}
